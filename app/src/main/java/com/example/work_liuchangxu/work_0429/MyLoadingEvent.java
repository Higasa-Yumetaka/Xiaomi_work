package com.example.work_liuchangxu.work_0429;

public class MyLoadingEvent {

    public static int STATE_LOADING = -1;

    public static int STATE_COMPLETE = -2;
    public static int STATE_END = -3;

    public int LOADING_STATE = 0;

    public MyLoadingEvent(int state){
        this.LOADING_STATE = state;
    }
}
