package com.example.work_liuchangxu.work_0425;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.work_liuchangxu.R;

import java.util.List;

public class MyBaseMultiItemQuickAdapter extends BaseMultiItemQuickAdapter<MyStruct, BaseViewHolder> {

    public MyBaseMultiItemQuickAdapter(@Nullable List<MyStruct> data) {
        super(data);
        addItemType(QuickMultiEntity.TYPE_TEXT, R.layout.item_text_recycle_0425);
        addItemType(QuickMultiEntity.TYPE_IMAGE, R.layout.item_image_recycle_0425);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyStruct providerMultiEntity) {
        switch (helper.getItemViewType()){
            case QuickMultiEntity.TYPE_TEXT:
                helper.setText(R.id.item_textView0425, (String) providerMultiEntity.getObject());
                break;
            case QuickMultiEntity.TYPE_IMAGE:
                helper.setImageResource(R.id.item_imageView0425, (int) providerMultiEntity.getObject());
                break;
        }
    }
}
