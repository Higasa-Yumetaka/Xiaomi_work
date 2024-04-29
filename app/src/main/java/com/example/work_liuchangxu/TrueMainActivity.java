package com.example.work_liuchangxu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


// 作业项目统一入口
public class TrueMainActivity extends AppCompatActivity {

    private final List<String> NameList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.true_activity_main);

        // 新建作业添加到NameList
        NameList.add("work_0429_2|ANR");
        NameList.add("work_0429_1|完善work_0428_2的内存泄漏问题");
        NameList.add("work_0428_2|Network");
        NameList.add("work_0428_1|Handler");
        NameList.add("work_0427|View");
        NameList.add("work_0426|Animation");
        NameList.add("work_0425|Components");
        NameList.add("work_0424|UI");
        NameList.add("work_0423|RecycleView");
        NameList.add("work_0422|Fragment");
        NameList.add("work_0421|Activity");

        MyListAdapter adapter = new MyListAdapter(TrueMainActivity.this, R.layout.list_item_layout, NameList);
        adapter.setOnItemClickListener(onButtonClickListener);
        ListView listView = findViewById(R.id.main_listView);
        listView.setAdapter(adapter);
    }

    private final MyListAdapter.OnItemClickListener onButtonClickListener = (view, position) -> {
        // Toast.makeText(TrueMainActivity.this, "You clicked " + StringList.get(position), Toast.LENGTH_SHORT).show();
        Intent intent = null;

        final int Total_nums = NameList.size();

        // 新建作业跳转
        if (position == (Total_nums - 1)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0421.Work0421MainActivity.class);
        } else if (position == (Total_nums - 2)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0422.Main0422Activity.class);
        } else if (position == (Total_nums - 3)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0423.RecycleActivity.class);
        } else if (position == (Total_nums - 4)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0424.Main0424Activity.class);
        } else if (position == (Total_nums - 5)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0425.Main0425Activity.class);
        } else if (position == (Total_nums - 6)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0426.Main0426Activity.class);
        } else if (position == (Total_nums - 7)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0427.Main0427Activity.class);
        } else if(position == (Total_nums - 8)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0428.Main0428Activity_1.class);
        } else if(position == (Total_nums - 9)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0428.Main0428Activity_2.class);
        } else if(position == (Total_nums - 10)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0429.Main0428Activity_2.class);
        } else if(position == (Total_nums - 11)){
            intent = new Intent(this, com.example.work_liuchangxu.work_0429.Main0429Activity.class);
        }
        startActivity(intent);
    };

    // 尝试使用ListView
    public static class MyListAdapter extends ArrayAdapter<String> {
        private final int resourceId;

        public OnItemClickListener mOnItemClickListener;

        public interface OnItemClickListener {
            void onItemClick(View view, int position);
        }

        public MyListAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        protected void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @NonNull
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            String work = getItem(position);
            View view;
            ListItemViewHolder holder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                holder = new ListItemViewHolder();
                holder.WorkName = view.findViewById(R.id.list_item_work_name_textview);
                holder.WorkDetail = view.findViewById(R.id.list_item_work_detail_textview);
                holder.StartButton = view.findViewById(R.id.list_item_start_button);
                // 为button分配点击事件
                holder.StartButton.setOnClickListener(v -> {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position);
                    }
                });
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ListItemViewHolder) view.getTag();
            }
            String[] work_info = new String[0];
            if (work != null) {
                work_info = work.split("\\|");
            }

            holder.WorkName.setText(work_info[0]);
            holder.WorkDetail.setText(work_info[1]);
            return view;
        }

        public static class ListItemViewHolder {
            public TextView WorkName;

            public Button StartButton;

            public TextView WorkDetail;
        }

    }
}