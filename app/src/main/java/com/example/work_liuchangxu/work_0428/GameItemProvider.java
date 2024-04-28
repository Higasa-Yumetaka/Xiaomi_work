package com.example.work_liuchangxu.work_0428;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.work_liuchangxu.R;

import java.util.Objects;

public class GameItemProvider extends BaseItemProvider<MyStruct> {

    @Override
    public int getItemViewType() {
        return MyStruct.TYPE_GAME;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_game_layout_0428_2;
    }

    @Override
    public void convert(@NonNull BaseViewHolder baseViewHolder, MyStruct myStruct) {
        baseViewHolder.setText(R.id.textview_game_name, myStruct.getGameName());
        baseViewHolder.setText(R.id.textview_game_brief, myStruct.getBrief());
        baseViewHolder.setText(R.id.textview_game_tag, myStruct.getTags());
        baseViewHolder.setText(R.id.textview_game_score, myStruct.getScore());

        // 使用Glide加载网络图片并添加圆角
        int radius = 50; // 圆角半径
        RoundedCorners roundedCorners = new RoundedCorners(radius);
        Glide.with(baseViewHolder.itemView.getContext())
                .load(myStruct.getIcon())
                .transform(roundedCorners)
                .into((ImageView) Objects.requireNonNull(baseViewHolder.findView(R.id.item_game_image)));
    }
}
