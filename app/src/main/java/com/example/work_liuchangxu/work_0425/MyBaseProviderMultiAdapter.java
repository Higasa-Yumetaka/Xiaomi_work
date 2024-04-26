package com.example.work_liuchangxu.work_0425;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

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
            return MyStruct.TYPE_LOADING;
        } else {
            return data.get(position).getType();
        }
    }
}
