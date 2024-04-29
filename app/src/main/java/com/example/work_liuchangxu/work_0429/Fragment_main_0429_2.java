package com.example.work_liuchangxu.work_0429;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.work_liuchangxu.R;
import com.example.work_liuchangxu.work_0428.MyBaseProviderMultiAdapter;
import com.example.work_liuchangxu.work_0428.MyData;
import com.example.work_liuchangxu.work_0428.MyLoadingEvent;
import com.example.work_liuchangxu.work_0428.MyStruct;
import com.example.work_liuchangxu.work_0428.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Fragment_main_0429_2 extends Fragment {

    List<MyStruct> dataList;
    View rootView;
    RecyclerView recyclerView;
    SearchView searchView;
    SwipeRefreshLayout swipeRefreshLayout;
    MyBaseProviderMultiAdapter adapter;

    String searchQuery = "";
    int current = 1;
    int size = 10;
    int pages = 1;

    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("https://hotfix-service-prod.g.mi.com/") // 设置网络请求的Url地址
            .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
            .build();

    public interface ApiService {
        @GET("quick-game/game/search")
        Call<Response<MyData<MyStruct>>> getData(@Query("search") String param_search, @Query("current") int param_current, @Query("size") int param_size);
    }

    //创建网络请求接口对象实例
    ApiService api = mRetrofit.create(ApiService.class);

    /*
     * 0429：改为使用弱引用获取Fragment实例
     * 防止Retrofit回调中的匿名内部类持有Fragment的强引用，避免内存泄漏。
     */
    private final WeakReference<Fragment_main_0429_2> weakReference = new WeakReference<>(this);

    private void getGameData(String search, int current, int size, boolean isRefresh) {
        Log.w("Fragment_main_0429_2", "getGameData: ");
        Call<Response<MyData<MyStruct>>> call = api.getData(search, current, size);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull retrofit2.Response<Response<MyData<MyStruct>>> response) {
                /*
                    * 0429：改为使用弱引用获取Fragment实例
                    * 防止Retrofit回调中的匿名内部类持有Fragment的强引用，避免内存泄漏。
                 */
                Fragment_main_0429_2 fragment = weakReference.get();
                if (fragment != null) { // 0429：检查 Fragment 是否为 null
                    if (response.body() != null) {
                        fragment.dataList.clear(); // 0429：使用 Fragment 实例的成员变量
                        fragment.pages = response.body().getData().getPages();
                        fragment.dataList = response.body().getData().getRecords(); // 0429：使用 Fragment 实例的成员变量
                        Log.w("Fragment_main_0429_2", "onCreateView: getGameData" + fragment.dataList.size());
                        fragment.adapter = new MyBaseProviderMultiAdapter(fragment.dataList); // 0429：使用 Fragment 实例的成员变量
                        fragment.recyclerView.setAdapter(fragment.adapter); // 0429：使用 Fragment 实例的成员变量
                        fragment.recyclerView.setItemAnimator(new DefaultItemAnimator()); // 0429：使用 Fragment 实例的成员变量
                        fragment.swipeRefreshLayout.setRefreshing(false); // 0429：使用 Fragment 实例的成员变量
                        if(isRefresh){
                            fragment.recyclerView.scrollToPosition(0); // 0429：使用 Fragment 实例的成员变量
                            Toast.makeText(fragment.getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Log.e("Fragment_main_0429_2", "getGameData:onResponse: response.body() is null");
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull Throwable t) {
                Log.e("Fragment_main_0429_2", "getGameData:onFailure" + t.getMessage());
            }
        });
    }

    private void insertGameData(String search, int current, int size) {
        Call<Response<MyData<MyStruct>>> call = api.getData(search, current, size);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull retrofit2.Response<Response<MyData<MyStruct>>> response) {
                Fragment_main_0429_2 fragment = weakReference.get();
                if (fragment != null) {
                    Log.w("Fragment_main_0429_2", "insertGameData:onResponse: ");
                    //请求成功时回调
                    if (response.body() != null) {
                        Log.w("Fragment_main_0429_2", "onCreateView: getGameData" + fragment.dataList.size());
                        fragment.dataList.addAll((List<MyStruct>) response.body().getData().getRecords());
                        fragment.adapter.notifyItemInserted(fragment.dataList.size() - 1);
                        fragment.adapter.setLoadingComplete();
                    } else {
                        Log.e("Fragment_main_0429_2", "insertGameData:onResponse: response.body() is null");
                        if (fragment.adapter != null) {
                            fragment.adapter.setLoadingComplete(); // 当response.body()为null时，也调用adapter.setLoadingComplete()
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull Throwable t) {
                Fragment_main_0429_2 fragment = weakReference.get();
                if (fragment != null) {
                    //请求失败时回调
                    Log.e("Fragment_main_0429_2", "insertGameData:onFailure" + t.getMessage());
                    if (fragment.adapter != null) {
                        fragment.adapter.setLoadingComplete(); // 当请求失败时，也调用adapter.setLoadingComplete()
                    }
                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w("Fragment_main_0429_2", "onCreateView: ");
        rootView = inflater.inflate(R.layout.fragment_main_layout_0428_2, container, false);
        if (rootView != null) {

            Log.d("Fragment_main_0429_2", "onCreateView: rootView is not null");
            recyclerView = rootView.findViewById(R.id.recycleView_0428_2);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            Log.d("Fragment_main_0429_2", "onCreateView: recyclerView is not null");
            dataList = new ArrayList<>();
            current = 1;
            getGameData(searchQuery, current, size,false);

            searchView = rootView.findViewById(R.id.search_view);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.d("Fragment_main_0429_2", "onQueryTextSubmit: " + query);
                    searchQuery = query;
                    if(query.isEmpty()) {
                        searchQuery = "";
                    }
                    current = 1;
                    getGameData(searchQuery, current, size,false);
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.d("Fragment_main_0429_2", "onQueryTextSubmit: " + newText);
                    searchQuery = newText;
                    if(newText.isEmpty()) {
                        searchQuery = "";
                    }
                    current = 1;
                    getGameData(searchQuery, current, size,false);
                    return false;
                }
            });
        } else {
            Log.e("Fragment_main_0429_2", "onCreateView: rootView is null");
        }
        if (rootView != null) {
            swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout_0428_2);
            swipeRefreshLayout.setOnRefreshListener(() -> {
                current = 1;
                getGameData(searchQuery, current, size,true);
            });
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MyLoadingEvent event) {
        if (event.LOADING_STATE == MyLoadingEvent.STATE_LOADING){
            Log.w("Fragment_main_0429_2", "addItem: ");
            if(current < pages){
                current++;
                insertGameData(searchQuery, current, size);
            }else {
                if(current == pages){
                    current++;
                    adapter.setLoadingComplete();
                    adapter.setDataComplete();
                }
                Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                adapter.setLoadingComplete();
                adapter.setDataComplete();
            }
        }
    }
}
