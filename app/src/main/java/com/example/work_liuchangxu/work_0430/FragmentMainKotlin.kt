package com.example.work_liuchangxu.work_0430

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.work_liuchangxu.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.ref.WeakReference

// 首页Fragment
class FragmentMainKotlin : Fragment() {
    private var dataList: MutableList<ItemData?>? = null
    private var gameList: MutableList<GameData>? = null
    private var rootView: View? = null
    private var recyclerView: RecyclerView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var adapter: MyBaseProviderMultiAdapter? = null
    var searchQuery = ""
    var current = 1
    var size = 10
    var pages = 1
    private var mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://hotfix-service-prod.g.mi.com/") // 设置网络请求的Url地址
        .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
        .build()

    interface ApiService {
        @GET("quick-game/game/search")
        fun getData(
            @Query("search") param_search: String?,
            @Query("current") param_current: Int,
            @Query("size") param_size: Int
        ): Call<Response<Records<GameData>>>
    }

    //创建网络请求接口对象实例
    private var api: ApiService = mRetrofit.create(
        ApiService::class.java
    )

    private fun getGameData(search: String, current: Int, size: Int, isRefresh: Boolean) {
        val call = api.getData(search, current, size)
        call.enqueue(object : Callback<Response<Records<GameData>>?> {
            override fun onResponse(
                call: Call<Response<Records<GameData>>?>,
                response: retrofit2.Response<Response<Records<GameData>>?>
            ) {
                Log.w("Fragment_main_0428_2", "getGameData: onResponse")
                //请求成功时回调
                if (response.body() != null) {
                    Log.w("Fragment_main_0428_2", "getGameData: response body is not null")
                    pages = response.body()!!.data.pages
                    gameList = response.body()!!.data.records.toMutableList()
                    Log.e("Fragment_main_0428_2", "getGameData: gameList size is " + gameList!!.size)
                    for (game in gameList!!) {
                        dataList!!.add(ItemData(ItemData.TYPE_GAME, game))
                        dataList!!.add(ItemData(ItemData.TYPE_TEXT, "这是第" + (dataList!!.size / 3 + 1) + "条消息"))
                        dataList!!.add(ItemData(ItemData.TYPE_IMAGE, R.drawable.great_wall))
                    }
                    Log.e("Fragment_main_0428_2", "getGameData: dataList size is " + dataList!!.size)
                    adapter?.notifyDataSetChanged()
                    swipeRefreshLayout!!.isRefreshing = false
                    if (isRefresh) {
                        recyclerView!!.scrollToPosition(0)
                        Toast.makeText(activity, "刷新完成", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("Fragment_main_0430", "getGameData:onResponse: response.body() is null")
                }
            }

            override fun onFailure(call: Call<Response<Records<GameData>>?>, t: Throwable) {
                //请求失败时回调
                Log.e("Fragment_main_0430", "getGameData:onFailure" + t.message)
            }
        })
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.w("Fragment_main_0430", "onCreateView: ")
        rootView = inflater.inflate(R.layout.fragment_main_layout_0425, container, false)
        if (rootView != null) {
            Log.w("Fragment_main_0430", "onCreateView: rootView is not null")
            recyclerView = rootView!!.findViewById(R.id.recycleView_0425)
            val localRecyclerView = recyclerView
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                activity
            )
            localRecyclerView?.setLayoutManager(layoutManager)
            dataList = ArrayList()
            getGameData(searchQuery, current, size, false)
            adapter = MyBaseProviderMultiAdapter(dataList)
            localRecyclerView?.setAdapter(adapter)
            localRecyclerView?.setItemAnimator(DefaultItemAnimator())
        } else {
            Log.e("Fragment_main_0430", "onCreateView: rootView is null")
        }
        if (rootView != null) {
            swipeRefreshLayout =
                rootView!!.findViewById(R.id.swipeRefreshLayout_0425)
            swipeRefreshLayout?.setOnRefreshListener {
                // 使用弱引用持有 Fragment 实例
                val weakFragment = WeakReference(this)
                Handler().postDelayed(
                    {
                        // 清空并重新加载数据
                        dataList!!.clear()
                        current = 1
                        getGameData(searchQuery, current, size, true)
                        weakFragment.get()?.let {
                            Toast.makeText(it.activity, "刷新完成", Toast.LENGTH_SHORT).show()
                        }
                    }, 500
                )
            }
        }
        return rootView
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: StarEvent) {
        // 更新数据源中的数据
        val localDataList = dataList
        localDataList!![event.position]!!.isStared = event.isStared
        // 通知适配器更新数据
        adapter!!.notifyItemChanged(event.position)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun addItem(event: LoadingEvent) {
        Log.w("Fragment_main_0430", "addItem: ")
        if (event.LOADING_STATE == LoadingEvent.STATE_LOADING) {
            // 令起线程异步模拟向数据源中添加数据
            // 使用弱引用持有 Fragment 实例
            val weakFragment = WeakReference(this)
            Thread {
                try {
                    val sleepTime = (Math.random() * 120).toInt()
                    // 随机加载10到20个数据
                    val count = (Math.random() * 5).toInt() + 5
                    Thread.sleep(sleepTime.toLong() * count)
                    // 在主线程中更新 UI
                    weakFragment.get()?.activity?.runOnUiThread {
                        current++
                        getGameData(searchQuery, current, size, false)
                        // 标记加载完成
                        adapter!!.setLoadingComplete()
                        Toast.makeText(activity, "加载完成", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }
}