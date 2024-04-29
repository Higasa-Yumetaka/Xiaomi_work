package com.example.work_liuchangxu.work_0429;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyBaseProviderMultiAdapter extends BaseProviderMultiAdapter<MyStruct> {

    private boolean isLoading = false;
    private boolean isDataComplete = false; // 新增字段

    public MyBaseProviderMultiAdapter(List<MyStruct> datalist) {
        super(datalist);
        addItemProvider(new GameItemProvider());
        addItemProvider(new MyLoadingProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends MyStruct> data, int position) {
        if (position + 1 == data.size()) {
            if(!isLoading && !isDataComplete){
                isLoading = true;
                EventBus.getDefault().post(new MyLoadingEvent(MyLoadingEvent.STATE_LOADING));
            }
            return isDataComplete ? data.get(position).getType() : MyStruct.TYPE_LOADING;
        } else {
            return data.get(position).getType();
        }
    }

    public void setLoadingComplete() {
        isLoading = false;
    }

    public void setDataComplete() {
        isDataComplete = true;
    }
}