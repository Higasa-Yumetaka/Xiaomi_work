package com.example.work_liuchangxu.work_0425;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.work_liuchangxu.R;

public class MyTextProvider extends BaseItemProvider<MyStruct> {
    @Override
    public int getItemViewType() {
        return MyStruct.TYPE_TEXT;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_text_recycle_0425;
    }

    @Override
    public void convert(@NonNull BaseViewHolder baseViewHolder, MyStruct myStruct) {
        baseViewHolder.setText(R.id.item_textView0425, (String) myStruct.getObject());
    }
}
