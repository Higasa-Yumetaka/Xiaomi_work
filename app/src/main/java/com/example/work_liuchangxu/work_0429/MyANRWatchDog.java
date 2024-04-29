package com.example.work_liuchangxu.work_0429;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
检测方法：
采用检测主线程一段时间内连续阻塞的方式来检测ANR，本质上是检测主线程是否存在持续一段时间的阻塞
可以在初始化时设置ANR的超时时间和检测的精度(ms)
通过轮询的方式检测主线程是否阻塞，向主线程发送消息_ticker，若主线程在检测精度的时间间隔内未能将_ticker清零，则认为主线程在该时间段内阻塞
在一次轮询中，检测主线程在检测精度的时间间隔内是否阻塞
若主线程在该检测精度的时间间隔内阻塞，则计数器加1，否则计数器清零，进行下一次轮询
若计数器达到触发ANR的次数，则触发ANR，若未达到触发ANR的次数，则计数器清零，进行下一次轮询
 */

/*
实现逻辑：
初始化时设置ANR超时时间和检测精度(ms)
创建一个计数器 = 0，用于记录主线程在检测精度的时间间隔内阻塞的次数
创建一个_ticker，用于向主线程发送消息
触发ANR阈值 = ANR超时时常/检测精度
while(线程未被中断){
    向主线程发送_ticker
    sleep(检测精度)
    if (_tick != 0){  //主线程在检测精度的时间间隔内未能将_ticker清零，即主线程阻塞
        计数器++
    } else {  //主线程在检测精度的时间间隔内将_ticker清零，即主线程未阻塞
        计数器 = 0
        break
    }
    if (计数器 == 触发ANR阈值){  //主线程在一次轮询中达到触发ANR的次数
        触发ANR
    }
}
 */

/**
 * A watchdog timer thread that detects when the UI thread has frozen.
 */
@SuppressWarnings("UnusedReturnValue")
public class MyANRWatchDog extends Thread {

    private static final int DEFAULT_ANR_TIMEOUT = 5000;
    private static final int DEFAULT_DETECTION_ACCURACY = 1000;
    private static final ANRListener DEFAULT_ANR_LISTENER = new ANRListener() {
        @Override
        public void onAppNotResponding(@NonNull MyANRError error) {
            throw error;
        }
    };
    private static final ANRInterceptor DEFAULT_ANR_INTERCEPTOR = new ANRInterceptor() {
        @Override
        public long intercept(long duration) {
            return 0;
        }
    };
    private static final InterruptionListener DEFAULT_INTERRUPTION_LISTENER = new InterruptionListener() {
        @Override
        public void onInterrupted(@NonNull InterruptedException exception) {
            Log.w("MyANRWatchDog", "Interrupted: " + exception.getMessage());
        }
    };
    private final Handler _uiHandler = new Handler(Looper.getMainLooper());
    private final int _timeoutInterval;
    private final int _detectionAccuracy;
    private ANRListener _anrListener = DEFAULT_ANR_LISTENER;
    private ANRInterceptor _anrInterceptor = DEFAULT_ANR_INTERCEPTOR;
    private InterruptionListener _interruptionListener = DEFAULT_INTERRUPTION_LISTENER;
    private String _namePrefix = "";
    private boolean _logThreadsWithoutStackTrace = false;
    private boolean _ignoreDebugger = false;
    private volatile long _tick = 0;
    private volatile boolean _reported = false;
    private final Runnable _ticker = () -> {
        _tick = 0;
        _reported = false;
    };

    /**
     * Constructs a watchdog that checks the ui thread every {@value #DEFAULT_ANR_TIMEOUT} milliseconds
     */
    public MyANRWatchDog() {
        this(DEFAULT_ANR_TIMEOUT, DEFAULT_DETECTION_ACCURACY);
    }
    /**
     * Constructs a watchdog that checks the ui thread every given interval
     *
     * @param timeoutInterval The interval, in milliseconds, between to checks of the UI thread.
     *                        It is therefore the maximum time the UI may freeze before being reported as ANR.
     */
    public MyANRWatchDog(int timeoutInterval, int detectionAccuracy) {
        super();
        _timeoutInterval = timeoutInterval;
        _detectionAccuracy = detectionAccuracy;
    }

    /**
     * @return The interval the WatchDog
     */
    public int getTimeoutInterval() {
        return _timeoutInterval;
    }

    /**
     * Sets an interface for when an ANR is detected.
     * If not set, the default behavior is to throw an error and crash the application.
     *
     * @param listener The new listener or null
     * @return itself for chaining.
     */
    @NonNull
    public MyANRWatchDog setANRListener(@Nullable ANRListener listener) {
        if (listener == null) {
            _anrListener = DEFAULT_ANR_LISTENER;
        } else {
            _anrListener = listener;
        }
        return this;
    }

    /**
     * Sets an interface to intercept ANRs before they are reported.
     * If set, you can define if, given the current duration of the detected ANR and external context, it is necessary to report the ANR.
     *
     * @param interceptor The new interceptor or null
     * @return itself for chaining.
     */
    @NonNull
    public MyANRWatchDog setANRInterceptor(@Nullable ANRInterceptor interceptor) {
        if (interceptor == null) {
            _anrInterceptor = DEFAULT_ANR_INTERCEPTOR;
        } else {
            _anrInterceptor = interceptor;
        }
        return this;
    }

    /**
     * Sets an interface for when the watchdog thread is interrupted.
     * If not set, the default behavior is to just log the interruption message.
     *
     * @param listener The new listener or null.
     * @return itself for chaining.
     */
    @NonNull
    public MyANRWatchDog setInterruptionListener(@Nullable InterruptionListener listener) {
        if (listener == null) {
            _interruptionListener = DEFAULT_INTERRUPTION_LISTENER;
        } else {
            _interruptionListener = listener;
        }
        return this;
    }

    /**
     * Set the prefix that a thread's name must have for the thread to be reported.
     * Note that the main thread is always reported.
     * Default "".
     *
     * @param prefix The thread name's prefix for a thread to be reported.
     * @return itself for chaining.
     */
    @NonNull
    public MyANRWatchDog setReportThreadNamePrefix(@Nullable String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        _namePrefix = prefix;
        return this;
    }

    /**
     * Set that only the main thread will be reported.
     *
     * @return itself for chaining.
     */
    @NonNull
    public MyANRWatchDog setReportMainThreadOnly() {
        _namePrefix = null;
        return this;
    }

    /**
     * Set that all threads will be reported (default behaviour).
     *
     * @return itself for chaining.
     */
    @NonNull
    public MyANRWatchDog setReportAllThreads() {
        _namePrefix = "";
        return this;
    }

    /**
     * Set that all running threads will be reported,
     * even those from which no stack trace could be extracted.
     * Default false.
     *
     * @param logThreadsWithoutStackTrace Whether or not all running threads should be reported
     * @return itself for chaining.
     */
    @NonNull
    public MyANRWatchDog setLogThreadsWithoutStackTrace(boolean logThreadsWithoutStackTrace) {
        _logThreadsWithoutStackTrace = logThreadsWithoutStackTrace;
        return this;
    }

    /**
     * Set whether to ignore the debugger when detecting ANRs.
     * When ignoring the debugger, MyANRWatchDog will detect ANRs even if the debugger is connected.
     * By default, it does not, to avoid interpreting debugging pauses as ANRs.
     * Default false.
     *
     * @param ignoreDebugger Whether to ignore the debugger.
     * @return itself for chaining.
     */
    @NonNull
    public MyANRWatchDog setIgnoreDebugger(boolean ignoreDebugger) {
        _ignoreDebugger = ignoreDebugger;
        return this;
    }

    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    @Override
    public void run() {
        setName("|My-ANR-WatchDog|");
        long interval = _timeoutInterval;
        int intervalCount = 0;
        int intervalLimit = (int) (interval / (long) _detectionAccuracy);
        while (!isInterrupted()) {
            boolean needPost = _tick == 0;
            _tick += 1000;
            if (needPost) {
                _uiHandler.post(_ticker);
            }
            try {
                Thread.sleep(_detectionAccuracy);
            } catch (InterruptedException e) {
                _interruptionListener.onInterrupted(e);
                return;
            }
            if (_tick != 0 && !_reported) {
                if (!_ignoreDebugger && (Debug.isDebuggerConnected() || Debug.waitingForDebugger())) {
                    Log.w("MyANRWatchDog", "An ANR was detected but ignored because the debugger is connected (you can prevent this with setIgnoreDebugger(true))");
                    _reported = true;
                    continue;
                }
                interval = _anrInterceptor.intercept(_tick);
                if (interval > 0) {
                    continue;
                }
                Log.w("MyANRWatchDog", "Main thread blocking detected, count: " + (intervalCount + 1) + ", duration: " + ((intervalCount + 1) * _detectionAccuracy) + " ms" + "threshold: " + intervalLimit * _detectionAccuracy + " ms");
                intervalCount++;
            } else {
                if (intervalCount > 0) {
                    Log.w("MyANRWatchDog", "An ANR may have occurred, but the threshold was not reached, ANR duration is " + (intervalCount * _detectionAccuracy) + " ms");
                }
                intervalCount = 0;
            }
            if (intervalCount == intervalLimit) {
                Log.e("MyANRWatchDog", "An ANR was detected, duration: " + intervalLimit * _detectionAccuracy + " ms, " + "threshold: " + intervalLimit * _detectionAccuracy + " ms");
                final MyANRError error;
                if (_namePrefix != null) {
                    error = MyANRError.New(_tick, _namePrefix, _logThreadsWithoutStackTrace);
                } else {
                    error = MyANRError.NewMainOnly(_tick);
                }
                _anrListener.onAppNotResponding(error);
                _reported = true;
            }
        }
    }

    public interface ANRListener {
        /**
         * Called when an ANR is detected.
         *
         * @param error The error describing the ANR.
         */
        void onAppNotResponding(@NonNull MyANRError error);
    }

    public interface ANRInterceptor {
        /**
         * Called when main thread has froze more time than defined by the timeout.
         *
         * @param duration The minimum time (in ms) the main thread has been frozen (may be more).
         * @return 0 or negative if the ANR should be reported immediately. A positive number of ms to postpone the reporting.
         */
        long intercept(long duration);
    }

    public interface InterruptionListener {
        void onInterrupted(@NonNull InterruptedException exception);
    }
}

