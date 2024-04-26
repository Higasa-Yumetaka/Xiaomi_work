package com.example.work_liuchangxu.work_0425;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyBaseProviderMultiAdapter extends BaseProviderMultiAdapter<MyStruct> {

    public MyBaseProviderMultiAdapter(List<MyStruct> datalist) {
        super(datalist);
        addItemProvider(new MyImageProvider());
        addItemProvider(new MyTextProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends MyStruct> data, int position) {
        return data.get(position).getItemType();
    }
}
