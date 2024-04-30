package com.example.work_liuchangxu.work_0430

import android.util.Log
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.work_liuchangxu.R

class MyLoadingProvider : BaseItemProvider<MyStruct>() {
    override val itemViewType: Int
        get() = MyStruct.TYPE_LOADING
    override val layoutId: Int
        get() = R.layout.item_refresh_footer

    override fun convert(helper: BaseViewHolder, item: MyStruct) {
        Log.w("MyLoadingProvider", "convert: " + item.itemType)
        when (item.itemType) {
            MyStruct.LOADING_COMPLETE -> {
                helper.setText(R.id.tv_loading, "加载完成")
                helper.setGone(R.id.pb_loading, true)
            }

            MyStruct.LOADING_END -> {
                helper.setText(R.id.tv_loading, "没有更多了")
                helper.setGone(R.id.pb_loading, true)
            }

            MyStruct.TYPE_LOADING -> {
                helper.setText(R.id.tv_loading, "正在加载中...")
                helper.setGone(R.id.pb_loading, false)
            }
        }
    }
}
