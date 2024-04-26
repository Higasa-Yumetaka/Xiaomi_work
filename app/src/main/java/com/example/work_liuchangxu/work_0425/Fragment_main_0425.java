package com.example.work_liuchangxu.work_0425;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        View rootView = inflater.inflate(R.layout.fragment_main_layout_0425, container, false);
        RecyclerView recyclerView;
        if (rootView != null) {
            Log.w("Fragment_main_0425", "onCreateView: rootView is not null");
            recyclerView = rootView.findViewById(R.id.recycleView_0425);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            Log.w("Fragment_main_0425", "onCreateView: recyclerView is not null");
            List<MyStruct> dataList = new ArrayList<>();
            for(int i = 0; i < 100; i++) {
                dataList.add(new MyStruct(MyStruct.TYPE_TEXT, "这是第" + i + "个文本"));
                dataList.add(new MyStruct(MyStruct.TYPE_IMAGE, R.drawable.great_wall));
            }
            MyBaseProviderMultiAdapter adapter = new MyBaseProviderMultiAdapter(dataList);
            recyclerView.setAdapter(adapter);
        }else {
            Log.e("Fragment_main_0425", "onCreateView: rootView is null");
        }
        return rootView;
    }
}
