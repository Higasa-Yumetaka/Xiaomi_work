package com.example.work_liuchangxu.work_0430

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import org.greenrobot.eventbus.EventBus

class MyBaseProviderMultiAdapter(data: List<ItemData?>?) :
    BaseProviderMultiAdapter<ItemData>(data as MutableList<ItemData>?) {
    private var isLoading = false // 新增字段

    init {
        addItemProvider(MyImageItemProvider())
        addItemProvider(MyTextItemProvider())
        addItemProvider(MyItemLoadingProvider())
    }

    override fun getItemType(data: List<ItemData>, position: Int): Int {
        // 当滑动到最后时返回ItemData.TYPE_LOADING，否则返回data[position].itemType
        return if (position + 1 == data.size) {
            if (!isLoading) {
                isLoading = true
                EventBus.getDefault()
                    .post(LoadingEvent(LoadingEvent.STATE_LOADING))
            }
            ItemData.TYPE_LOADING
        } else {
            data[position].itemType
        }
    }

    // 新增方法，用于在加载完成后调用
    fun setLoadingComplete() {
        isLoading = false
    }
}