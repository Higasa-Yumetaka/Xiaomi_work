package com.example.work_liuchangxu.work_0425;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_liuchangxu.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_main_0425 extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w("Fragment_main_0425", "onCreateView: ");
        // 使用 inflater.inflate 方法获取布局文件的根视图
        View rootView = inflater.inflate(R.layout.fragment_main_layout_0425, container, false);
        // 通过根视图找到 RecyclerView
        RecyclerView recyclerView;
        if (rootView != null) {
            Log.w("Fragment_main_0425", "onCreateView: rootView is not null");
            recyclerView = rootView.findViewById(R.id.recycleView_0425);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            Log.w("Fragment_main_0425", "onCreateView: recyclerView is not null");
            // 初始化 dataList
            List<MyStruct> dataList = new ArrayList<>();
            // 添加数据，此处仅为示例，你需要根据实际情况添加数据
            for(int i = 0; i < 10; i++) {
                dataList.add(new MyStruct(MyStruct.TYPE_TEXT, "这是第" + i + "个文本"));
                dataList.add(new MyStruct(MyStruct.TYPE_IMAGE, R.drawable.great_wall));
            }

            // 创建适配器
            MyBaseProviderMultiAdapter adapter = new MyBaseProviderMultiAdapter(dataList);
            // 设置点击事件监听器
            adapter.setOnItemClickListener((adapter1, view, position) -> {
                // 当点击时，弹出一个 Toast
                MyStruct entity = dataList.get(position);
                Log.d("Fragment_main_0425", "onCreateView: item clicked: " + entity);
            });
            // 将适配器设置给 RecyclerView
            recyclerView.setAdapter(adapter);
        }else {
            Log.e("Fragment_main_0425", "onCreateView: rootView is null");
        }
        return rootView;
    }
}
