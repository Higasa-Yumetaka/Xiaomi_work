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
        switch (myStruct.getType()) {
            case MyStruct.LOADING_COMPLETE:
                baseViewHolder.setText(R.id.tv_loading, "加载完成");
                baseViewHolder.setVisible(R.id.pb_loading, false);
                baseViewHolder.setVisible(R.id.ll_end, false);
                break;
            case MyStruct.LOADING_END:
                baseViewHolder.setText(R.id.tv_loading, "没有更多了");
                baseViewHolder.setVisible(R.id.pb_loading, false);
                baseViewHolder.setVisible(R.id.ll_end, true);
                break;
            case MyStruct.TYPE_LOADING:
                baseViewHolder.setText(R.id.tv_loading, "正在加载中...");
                baseViewHolder.setVisible(R.id.pb_loading, true);
                baseViewHolder.setVisible(R.id.ll_end, false);
                break;
        }

    }
}
