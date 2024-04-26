package com.example.work_liuchangxu.work_0425;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.work_liuchangxu.R;

import org.jetbrains.annotations.NotNull;

public class MyImageProvider extends BaseItemProvider<MyStruct> {
    @Override
    public int getItemViewType() {
        return MyStruct.TYPE_IMAGE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_image_recycle_0425;
    }

    @Override
    public void convert(@NonNull BaseViewHolder baseViewHolder, MyStruct myStruct) {
        Glide.with(getContext())
                .load((int) myStruct.getObject())
                .apply(new RequestOptions().transforms(new RoundedCorners(25)))
                .into((ImageView) baseViewHolder.getView(R.id.item_imageView0425));

        ImageButton imageButton = baseViewHolder.getView(R.id.star_button_image0425);
        if (myStruct.isStared()) {
            imageButton.setAlpha(1.0f);
            imageButton.setColorFilter(getContext().getColor(R.color.red));
        } else {
            imageButton.setAlpha(0.3f);
            imageButton.setColorFilter(getContext().getColor(R.color.grey));
        }
        imageButton.setOnClickListener(v -> {
            if(myStruct.isStared())
                Toast.makeText(getContext(), "取消点赞", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "点赞成功", Toast.LENGTH_SHORT).show();
            myStruct.setStared(!myStruct.isStared());
            imageButton.setAlpha(myStruct.isStared() ? 1.0f : 0.3f);
            imageButton.setColorFilter(getContext().getColor(myStruct.isStared() ? R.color.red : R.color.grey));
        });
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, MyStruct data, int position) {
        Intent intent = new Intent(getContext(), ShowImageActivity0425.class);
        intent.putExtra("image", (int) data.getObject());
        intent.putExtra("stared", (boolean) data.isStared());
        intent.putExtra("position", position);
        startActivity(getContext(), intent, null);
    }

}
