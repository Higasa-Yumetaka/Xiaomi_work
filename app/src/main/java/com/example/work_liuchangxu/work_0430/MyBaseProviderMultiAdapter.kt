package com.example.work_liuchangxu.work_0430

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import org.greenrobot.eventbus.EventBus

class MyBaseProviderMultiAdapter(datalist: List<MyStruct?>?) :
    BaseProviderMultiAdapter<MyStruct>(datalist as MutableList<MyStruct>?) {
    private var isLoading = false // 新增字段

    init {
        addItemProvider(MyImageProvider())
        addItemProvider(MyTextProvider())
        addItemProvider(MyLoadingProvider())
    }

    override fun getItemType(data: List<MyStruct>, position: Int): Int {
        // 当滑动到最后时返回MyStruct.TYPE_LOADING，否则返回data.get(position).getType()
        return if (position + 1 == data.size) {
            if (!isLoading) {
                isLoading = true
                EventBus.getDefault()
                    .post(MyLoadingEvent(MyLoadingEvent.STATE_LOADING))
            }
            MyStruct.TYPE_LOADING
        } else {
            data[position].itemType
        }
    }

    // 新增方法，用于在加载完成后调用
    fun setLoadingComplete() {
        isLoading = false
    }
}