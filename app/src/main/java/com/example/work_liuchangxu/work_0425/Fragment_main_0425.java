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

import java.util.ArrayList;
import java.util.List;

public class Fragment_main_0425 extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w("Fragment_main_0425", "onCreateView: ");
        View rootView = inflater.inflate(R.layout.fragment_main_layout_0425, container, false);
        RecyclerView recyclerView;
        if (rootView != null) {
            Log.w("Fragment_main_0425", "onCreateView: rootView is not null");
            recyclerView = rootView.findViewById(R.id.recycleView_0425);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            Log.w("Fragment_main_0425", "onCreateView: recyclerView is not null");
            List<MyStruct> dataList = new ArrayList<>();
            for(int i = 0; i < 20; i++) {
                dataList.add(new MyStruct(MyStruct.TYPE_TEXT, "这是第" + (i+1) + "个文本"));
                dataList.add(new MyStruct(MyStruct.TYPE_IMAGE, R.drawable.great_wall));
            }
            MyBaseProviderMultiAdapter adapter = new MyBaseProviderMultiAdapter(dataList);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }else {
            Log.e("Fragment_main_0425", "onCreateView: rootView is null");
        }

        if (rootView != null) {
            SwipeRefreshLayout swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout_0425);

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 在这里停止刷新
                            swipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000); // 延迟2秒
                }
            });
        }
        return rootView;
    }
}
