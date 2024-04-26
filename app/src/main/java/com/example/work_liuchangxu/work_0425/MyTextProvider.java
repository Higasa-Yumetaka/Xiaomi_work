package com.example.work_liuchangxu.work_0425;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
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
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, MyStruct data, int position) {
        //Toast.makeText(getContext(), "Text " + position, Toast.LENGTH_SHORT).show();
        // 启动ShowImageActivity
        Intent intent = new Intent(getContext(), ShowTextActivity0425.class);
        intent.putExtra("text", (String) data.getObject());
        startActivity(getContext(), intent, null);
    }
}
