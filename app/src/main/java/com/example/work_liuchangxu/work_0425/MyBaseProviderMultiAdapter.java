package com.example.work_liuchangxu.work_0425;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyBaseProviderMultiAdapter extends BaseProviderMultiAdapter<MyStruct> {

    public MyBaseProviderMultiAdapter(List<MyStruct> datalist) {
        super(datalist);
        addItemProvider(new MyImageProvider());
        addItemProvider(new MyTextProvider());
        addItemProvider(new MyLoadingProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends MyStruct> data, int position) {
        // 当滑动到最后时返回MyStruct.TYPE_LOADING，否则返回data.get(position).getType()
        if (position + 1 == data.size()) {
            if(data.size() > 50) {
                EventBus.getDefault().post(new MyLoadingEvent(MyLoadingEvent.STATE_END));
            }else{
                // 异步等待2s发送EventBus.getDefault().post(new MyLoadingEvent(MyLoadingEvent.STATE_LOADING));
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        EventBus.getDefault().post(new MyLoadingEvent(MyLoadingEvent.STATE_LOADING));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            return MyStruct.TYPE_LOADING;
        } else {
            return data.get(position).getType();
        }
    }
}
