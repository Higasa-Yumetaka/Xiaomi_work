package com.example.liuchangxu.work_0423;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liuchangxu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecycleActivity extends AppCompatActivity{

    List<MyBean> beanList = new ArrayList<>();
    MyRecycleViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        RecyclerView myRecyclerView = findViewById(R.id.recycle_view);



        for (int i = 0; i < 20; i++) {
            MyBean bean = new MyBean();
            bean.setName("Game"+(i + 1));
            bean.setIcon(getResources().getIdentifier("logo" + (i % 12 + 1), "drawable", getPackageName()));
            beanList.add(bean);
        }
        adapter = new MyRecycleViewAdapter(beanList);
        adapter.setOnItemClickListener(onButtonClickListener);
        adapter.setOnItemLongClickListener(onButtonLongClickListener);
        myRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);

        findViewById(R.id.add_button).setOnClickListener(v -> {
            Log.i("FloatingActionButton", "Game添加");
            Log.i("RecycleActivity", "Game " + (Objects.requireNonNull(myRecyclerView.getAdapter()).getItemCount() + 1) + " 号  添加");
            MyBean newBean = new MyBean();
            newBean.setName("Game" + (myRecyclerView.getAdapter().getItemCount() + 1));
            newBean.setIcon(getResources().getIdentifier("logo" + (myRecyclerView.getAdapter().getItemCount() % 12 + 1), "drawable", getPackageName()));
            beanList.add(newBean);
            adapter.notifyItemInserted(myRecyclerView.getAdapter().getItemCount() - 1);
            //adapter.notifyItemRangeChanged(0, myRecyclerView.getAdapter().getItemCount() - 1);
            Toast.makeText(RecycleActivity.this, "Game " + (myRecyclerView.getAdapter().getItemCount()) + " 号已添加", Toast.LENGTH_SHORT).show();
        });

    }
//    private final MyRecycleViewAdapter.OnItemClickListener onButtonClickListener = (view, position) -> Log.i("RecycleActivity", "第 " + (position + 1) + " 个原神，启动 ！");
    private final MyRecycleViewAdapter.OnItemClickListener onButtonClickListener = new MyRecycleViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(android.view.View view, int position) {
            if (view.getId() == R.id.item_logo) {
//                Log.i("RecycleActivity", "Game " + (position + 1) + " 号  ICON");
                Toast.makeText(RecycleActivity.this, "Game " + (position + 1) + " 号  ICON", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == R.id.item_name_textview) {
//                Log.i("RecycleActivity", "Game " + (position + 1) + " 号  Textview");
                Toast.makeText(RecycleActivity.this, "Game " + (position + 1) + " 号  Textview", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == R.id.item_button) {
//                Log.i("RecycleActivity", "Game " + (position + 1) + " 号  启动");
                Toast.makeText(RecycleActivity.this, "Game " + (position + 1) + " 号  启动", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final MyRecycleViewAdapter.OnItemLongClickListener onButtonLongClickListener = (view, position) -> {
        // 弹出对话框
        Log.i("RecycleActivity", "Game " + (position + 1) + " 号  长按");
        LayoutInflater inflater = LayoutInflater.from(RecycleActivity.this);
        android.view.View dialogView = inflater.inflate(R.layout.delete_dialog, null);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RecycleActivity.this);
        builder.setView(dialogView);
        android.app.AlertDialog dialog = builder.create();
        Button cancelButton = dialogView.findViewById(R.id.delete_cancel);
        cancelButton.setOnClickListener(v -> dialog.dismiss());
        Button confirmButton = dialogView.findViewById(R.id.delete_confirm);
        confirmButton.setOnClickListener(v -> {
            Log.i("RecycleActivity", "Game " + (position + 1) + " 号  删除");
            beanList.remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            dialog.dismiss();
            Toast.makeText(RecycleActivity.this, "Game " + (position + 1) + " 号已删除", Toast.LENGTH_SHORT).show();
        });
        dialog.show();
    };

}