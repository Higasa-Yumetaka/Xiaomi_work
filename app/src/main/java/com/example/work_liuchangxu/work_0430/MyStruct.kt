package com.example.work_liuchangxu.work_0430

import com.chad.library.adapter.base.entity.MultiItemEntity

class MyStruct(override var itemType: Int, var `object`: Any) : MultiItemEntity {
    var isStared = false

    companion object {
        @JvmField
        var TYPE_TEXT = 0
        @JvmField
        var TYPE_IMAGE = 1

        // 当前加载状态，默认为加载完成
        private const val loadState = 2

        // 正在加载
        const val TYPE_LOADING = -1

        // 加载完成
        const val LOADING_COMPLETE = 2

        // 加载到底
        const val LOADING_END = 3
    }
}
