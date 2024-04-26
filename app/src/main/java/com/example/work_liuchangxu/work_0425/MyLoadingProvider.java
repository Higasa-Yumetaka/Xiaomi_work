package com.example.work_liuchangxu.work_0425;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.work_liuchangxu.R;

public class MyLoadingProvider extends BaseItemProvider<MyStruct> {
    @Override
    public int getItemViewType() {
        return MyStruct.TYPE_LOADING;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_refresh_footer;
    }

    @Override
    public void convert(@NonNull BaseViewHolder baseViewHolder, MyStruct myStruct) {
    }
}
