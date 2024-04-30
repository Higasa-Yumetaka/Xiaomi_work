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
import java.lang.ref.WeakReference

// 首页Fragment
class FragmentMainKotlin : Fragment() {
    private var dataList: MutableList<ItemData?>? = null
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
            Log.w("Fragment_main_043", "onCreateView: recyclerView is not null")
            dataList = ArrayList()
            val localDataList = dataList
            for (i in 0..20) {
                localDataList?.add(ItemData(ItemData.TYPE_TEXT, "这是第" + (i + 1) + "个文本"))
                localDataList?.add(ItemData(ItemData.TYPE_IMAGE, R.drawable.great_wall))
            }
            adapter = MyBaseProviderMultiAdapter(localDataList)
            localRecyclerView?.setAdapter(adapter)
            localRecyclerView?.setItemAnimator(DefaultItemAnimator())
        } else {
            Log.e("Fragment_main_0430", "onCreateView: rootView is null")
        }
        if (rootView != null) {
            val swipeRefreshLayout =
                rootView!!.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout_0425)
            swipeRefreshLayout.setOnRefreshListener {
                // 使用弱引用持有 Fragment 实例
                val weakFragment = WeakReference(this)
                Handler().postDelayed(
                    {
                        // 清空并重新加载数据
                        dataList!!.clear()
                        for (i in 0..20) {
                            dataList!!.add(ItemData(ItemData.TYPE_TEXT, "这是第" + (i + 1) + "个文本"))
                            dataList!!.add(ItemData(ItemData.TYPE_IMAGE, R.drawable.great_wall))
                        }
                        adapter!!.notifyDataSetChanged()
                        swipeRefreshLayout.isRefreshing = false
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
                    val count = (Math.random() * 10).toInt() + 10
                    Thread.sleep(sleepTime.toLong() * count)
                    // 在主线程中更新 UI
                    weakFragment.get()?.activity?.runOnUiThread {
                        val newDataList: MutableList<ItemData> = ArrayList()
                        for (i in 0 until count) {
                            newDataList.add(
                                ItemData(
                                    ItemData.TYPE_TEXT,
                                    "这是第" + (dataList!!.size / 2 + 1 + i) + "个文本"
                                )
                            )
                            newDataList.add(ItemData(ItemData.TYPE_IMAGE, R.drawable.great_wall))
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