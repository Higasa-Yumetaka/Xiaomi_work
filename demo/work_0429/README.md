## 弱引用：
```
使用WeakReference引用Fragment_main_0428_2实例，使得在回调中持有对fragment的引用变为弱引用。
防止回调持有fragment的强引用，避免了内存泄漏问题，因为即使fragment被销毁，回调中的弱引用也不会阻止其被垃圾回收。
```

#### 修改前的代码：
```java
private void getGameData(String search, int current, int size, boolean isRefresh) {
        Log.w("Fragment_main_0428_2", "getGameData: ");
        Call<Response<MyData<MyStruct>>> call = api.getData(search, current, size);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull retrofit2.Response<Response<MyData<MyStruct>>> response) {
                //请求成功时回调
                if (response.body() != null) {
                    dataList.clear();
                    pages = response.body().getData().getPages();
                    dataList = (List<MyStruct>) response.body().getData().getRecords();
                    Log.w("Fragment_main_0428_2", "onCreateView: getGameData" + dataList.size());
                    adapter = new MyBaseProviderMultiAdapter(dataList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    swipeRefreshLayout.setRefreshing(false);
                    if(isRefresh){
                        recyclerView.scrollToPosition(0);
                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.e("Fragment_main_0428_2", "getGameData:onResponse: response.body() is null");
                }
            }
            @Override
            public void onFailure(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull Throwable t) {
                //请求失败时回调
                Log.e("Fragment_main_0428_2", "getGameData:onFailure" + t.getMessage());
            }
        });
    }

    private void insertGameData(String search, int current, int size) {
        Call<Response<MyData<MyStruct>>> call = api.getData(search, current, size);

        call.enqueue(new Callback<>() {

            @Override
            public void onResponse(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull retrofit2.Response<Response<MyData<MyStruct>>> response) {
                Log.w("Fragment_main_0428_2", "insertGameData:onResponse: ");
                //请求成功时回调
                if (response.body() != null) {
                    Log.w("Fragment_main_0428_2", "onCreateView: getGameData" + dataList.size());
                    dataList.addAll((List<MyStruct>) response.body().getData().getRecords());
                    adapter.notifyItemInserted(dataList.size() - 1);
                    adapter.setLoadingComplete();
                }else {
                    Log.e("Fragment_main_0428_2", "insertGameData:onResponse: response.body() is null");
                    adapter.setLoadingComplete(); // 当response.body()为null时，也调用adapter.setLoadingComplete()
                }
            }
            @Override
            public void onFailure(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull Throwable t) {
                //请求失败时回调
                Log.e("Fragment_main_0428_2", "insertGameData:onFailure" + t.getMessage());
                adapter.setLoadingComplete(); // 当请求失败时，也调用adapter.setLoadingComplete()
            }
        });
    }
```

#### 修改后的代码：
```java
    /*
     * 0429：改为使用弱引用获取Fragment实例
     * 防止Retrofit回调中的匿名内部类持有Fragment的强引用，避免内存泄漏。
     */
    private final WeakReference<Fragment_main_0428_2> weakReference = new WeakReference<>(this);

    private void getGameData(String search, int current, int size, boolean isRefresh) {
        Log.w("Fragment_main_0428_2", "getGameData: ");
        Call<Response<MyData<MyStruct>>> call = api.getData(search, current, size);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull retrofit2.Response<Response<MyData<MyStruct>>> response) {
                /*
                    * 0429：改为使用弱引用获取Fragment实例
                    * 防止Retrofit回调中的匿名内部类持有Fragment的强引用，避免内存泄漏。
                 */
                Fragment_main_0428_2 fragment = weakReference.get();
                if (fragment != null) { // 0429：检查 Fragment 是否为 null
                    if (response.body() != null) {
                        fragment.dataList.clear(); // 0429：使用 Fragment 实例的成员变量
                        fragment.pages = response.body().getData().getPages();
                        fragment.dataList = response.body().getData().getRecords(); // 0429：使用 Fragment 实例的成员变量
                        Log.w("Fragment_main_0428_2", "onCreateView: getGameData" + fragment.dataList.size());
                        fragment.adapter = new MyBaseProviderMultiAdapter(fragment.dataList); // 0429：使用 Fragment 实例的成员变量
                        fragment.recyclerView.setAdapter(fragment.adapter); // 0429：使用 Fragment 实例的成员变量
                        fragment.recyclerView.setItemAnimator(new DefaultItemAnimator()); // 0429：使用 Fragment 实例的成员变量
                        fragment.swipeRefreshLayout.setRefreshing(false); // 0429：使用 Fragment 实例的成员变量
                        if(isRefresh){
                            fragment.recyclerView.scrollToPosition(0); // 0429：使用 Fragment 实例的成员变量
                            Toast.makeText(fragment.getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Log.e("Fragment_main_0428_2", "getGameData:onResponse: response.body() is null");
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull Throwable t) {
                Log.e("Fragment_main_0428_2", "getGameData:onFailure" + t.getMessage());
            }
        });
    }

    private void insertGameData(String search, int current, int size) {
        Call<Response<MyData<MyStruct>>> call = api.getData(search, current, size);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull retrofit2.Response<Response<MyData<MyStruct>>> response) {
                Fragment_main_0428_2 fragment = weakReference.get();
                if (fragment != null) {
                    Log.w("Fragment_main_0428_2", "insertGameData:onResponse: ");
                    //请求成功时回调
                    if (response.body() != null) {
                        Log.w("Fragment_main_0428_2", "onCreateView: getGameData" + fragment.dataList.size());
                        fragment.dataList.addAll((List<MyStruct>) response.body().getData().getRecords());
                        fragment.adapter.notifyItemInserted(fragment.dataList.size() - 1);
                        fragment.adapter.setLoadingComplete();
                    } else {
                        Log.e("Fragment_main_0428_2", "insertGameData:onResponse: response.body() is null");
                        if (fragment.adapter != null) {
                            fragment.adapter.setLoadingComplete(); // 当response.body()为null时，也调用adapter.setLoadingComplete()
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response<MyData<MyStruct>>> call, @NonNull Throwable t) {
                Fragment_main_0428_2 fragment = weakReference.get();
                if (fragment != null) {
                    //请求失败时回调
                    Log.e("Fragment_main_0428_2", "insertGameData:onFailure" + t.getMessage());
                    if (fragment.adapter != null) {
                        fragment.adapter.setLoadingComplete(); // 当请求失败时，也调用adapter.setLoadingComplete()
                    }
                }
            }
        });
    }
```