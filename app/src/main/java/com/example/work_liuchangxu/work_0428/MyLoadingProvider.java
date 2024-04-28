package com.example.work_liuchangxu.work_0428;

import android.util.Log;

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
        Log.w("MyLoadingProvider", "convert: " + myStruct.getType());
        switch (myStruct.getType()) {
            case MyStruct.LOADING_COMPLETE:
                baseViewHolder.setText(R.id.tv_loading, "加载完成");
                baseViewHolder.setGone(R.id.pb_loading, true);
                break;
            case MyStruct.LOADING_END:
                baseViewHolder.setText(R.id.tv_loading, "没有更多了");
                baseViewHolder.setGone(R.id.pb_loading, true);
                break;
            case MyStruct.TYPE_LOADING:
                baseViewHolder.setText(R.id.tv_loading, "正在加载中...");
                baseViewHolder.setGone(R.id.pb_loading, false);
                break;
        }
    }
}
