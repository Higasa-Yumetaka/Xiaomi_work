package com.example.work_liuchangxu.work_0425;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.work_liuchangxu.R;

import org.jetbrains.annotations.NotNull;

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

        ImageButton imageButton = baseViewHolder.getView(R.id.star_button_text0425);
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
        //Toast.makeText(getContext(), "Text " + position, Toast.LENGTH_SHORT).show();
        // 启动ShowImageActivity
        Intent intent = new Intent(getContext(), ShowTextActivity0425.class);
        intent.putExtra("text", (String) data.getObject());
        intent.putExtra("stared", data.isStared());
        intent.putExtra("position", position);
        startActivity(getContext(), intent, null);
    }
}
