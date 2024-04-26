package com.example.work_liuchangxu.work_0425;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_liuchangxu.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class Fragment_main_0425 extends Fragment {


    List<MyStruct> dataList;
    View rootView;
    RecyclerView recyclerView;
    MyBaseProviderMultiAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w("Fragment_main_0425", "onCreateView: ");
        rootView = inflater.inflate(R.layout.fragment_main_layout_0425, container, false);
        if (rootView != null) {
            Log.w("Fragment_main_0425", "onCreateView: rootView is not null");
            recyclerView = rootView.findViewById(R.id.recycleView_0425);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            Log.w("Fragment_main_0425", "onCreateView: recyclerView is not null");
            dataList = new ArrayList<>();
            for(int i = 0; i < 20; i++) {
                dataList.add(new MyStruct(MyStruct.TYPE_TEXT, "这是第" + (i+1) + "个文本"));
                dataList.add(new MyStruct(MyStruct.TYPE_IMAGE, R.drawable.great_wall));
            }
            adapter = new MyBaseProviderMultiAdapter(dataList);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }else {
            Log.e("Fragment_main_0425", "onCreateView: rootView is null");
        }
        if (rootView != null) {
            SwipeRefreshLayout swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout_0425);
            swipeRefreshLayout.setOnRefreshListener(() -> {
                new Handler().postDelayed(() -> {
                    // 在这里停止刷新
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                }, 500); // 延迟2秒
            });
        }
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MyEvent event) {
        // 更新数据源中的数据
        dataList.get(event.getPosition()).setStared(event.isStared());
        // 通知适配器更新数据
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}