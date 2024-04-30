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

class FragmentMainKotlin : Fragment() {
    private var dataList: MutableList<MyStruct?>? = null
    private var rootView: View? = null
    private var recyclerView: RecyclerView? = null
    var adapter: MyBaseProviderMultiAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.w("Fragment_main_0425", "onCreateView: ")
        rootView = inflater.inflate(R.layout.fragment_main_layout_0425, container, false)
        if (rootView != null) {
            Log.w("Fragment_main_0425", "onCreateView: rootView is not null")
            recyclerView = rootView!!.findViewById(R.id.recycleView_0425)
            val localRecyclerView = recyclerView
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                activity
            )
            localRecyclerView?.setLayoutManager(layoutManager)
            Log.w("Fragment_main_0425", "onCreateView: recyclerView is not null")
            dataList = ArrayList()
            val localDataList = dataList
            for (i in 0..20) {
                localDataList?.add(MyStruct(MyStruct.TYPE_TEXT, "这是第" + (i + 1) + "个文本"))
                localDataList?.add(MyStruct(MyStruct.TYPE_IMAGE, R.drawable.great_wall))
            }
            adapter = MyBaseProviderMultiAdapter(localDataList)
            localRecyclerView?.setAdapter(adapter)
            localRecyclerView?.setItemAnimator(DefaultItemAnimator())
        } else {
            Log.e("Fragment_main_0425", "onCreateView: rootView is null")
        }
        if (rootView != null) {
            val swipeRefreshLayout =
                rootView!!.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout_0425)
            swipeRefreshLayout.setOnRefreshListener {
                Handler().postDelayed(
                    {
                        // 在这里停止刷新
                        swipeRefreshLayout.isRefreshing = false
                        Toast.makeText(activity, "刷新完成", Toast.LENGTH_SHORT).show()
                    }, 500
                ) // 延迟2秒
            }
        }
        return rootView
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: MyEvent) {
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
    fun addItem(event: MyLoadingEvent) {
        Log.w("Fragment_main_0425", "addItem: ")
        if (event.LOADING_STATE == MyLoadingEvent.STATE_LOADING) {
            // 令起线程异步模拟向数据源中添加数据
            Thread {
                try {
                    // 随机每个加载耗时
                    val sleepTime = (Math.random() * 120).toInt()
                    // 随机加载10到20个数据
                    val count = (Math.random() * 10).toInt() + 10
                    Thread.sleep(sleepTime.toLong() * count)
                    requireActivity().runOnUiThread {
                        val newDataList: MutableList<MyStruct> = ArrayList()
                        for (i in 0 until count) {
                            newDataList.add(
                                MyStruct(
                                    MyStruct.TYPE_TEXT,
                                    "这是第" + (dataList!!.size / 2 + 1 + i) + "个文本"
                                )
                            )
                            newDataList.add(MyStruct(MyStruct.TYPE_IMAGE, R.drawable.great_wall))
                        }
                        // 将新数据设置到 Adapter 中
                        adapter!!.addData(newDataList)
                        // 通知 Adapter 刷新数据
                        adapter!!.notifyItemInserted(dataList!!.size - count * 2)
                        // 标记加载完成
                        adapter!!.setLoadingComplete()
                        Toast.makeText(activity, "加载了" + count + "条数据", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }
}