package com.example.work_liuchangxu.work_0430

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.work_liuchangxu.R

class GameItemProvider : BaseItemProvider<ItemData>() {
    override val itemViewType: Int
        get() = ItemData.TYPE_GAME
    override val layoutId: Int
        get() = R.layout.item_game_layout_0428_2

    override fun convert(helper: BaseViewHolder, item: ItemData) {
        if (item.`object` is GameData) {
            val gameInfo = item.`object` as GameData
            helper.setText(R.id.textview_game_name, gameInfo.gameName)
            helper.setText(R.id.textview_game_brief, " | " + gameInfo.brief)
            helper.setText(R.id.textview_game_tag, " " + gameInfo.tags + " ")
            helper.setText(R.id.textview_game_score, "⭐" + gameInfo.score)
            if(item.isStared){
                helper.setGone(R.id.textview_game_collected, false)
                helper.setText(R.id.textview_game_collected, "已收藏")
            }else{
                helper.setGone(R.id.textview_game_collected, true)
                helper.setText(R.id.textview_game_collected, "未收藏")
            }
            // 使用Glide加载网络图片并添加圆角
            val radius = 50 // 圆角半径
            val roundedCorners = RoundedCorners(radius)
            Glide.with(helper.itemView.context)
                .load(gameInfo.icon)
                .transform(roundedCorners)
                .into(helper.getView(R.id.item_game_image) as ImageView)
        } else {
            Log.e("GameItemProvider", "convert: item.`object` is not GameData")
            Log.e("GameItemProvider", "convert: item.`object` is ${item.`object`}")
        }
    }
}
