package com.example.work_liuchangxu.work_0430

import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.work_liuchangxu.R

class MyTextItemProvider : BaseItemProvider<ItemData>() {
    override val itemViewType: Int
        get() = ItemData.TYPE_TEXT
    override val layoutId: Int
        get() = R.layout.item_text_recycle_0425

    override fun convert(helper: BaseViewHolder, item: ItemData) {
        helper.setText(R.id.item_textView0425, item.`object` as String)
        val imageButton = helper.getView<ImageButton>(R.id.star_button_text0425)
        if (item.isStared) {
            imageButton.setAlpha(1.0f)
            imageButton.setColorFilter(context.getColor(R.color.red))
        } else {
            imageButton.setAlpha(0.3f)
            imageButton.setColorFilter(context.getColor(R.color.grey))
        }
        imageButton.setOnClickListener {
            if (item.isStared) Toast.makeText(context, "取消点赞", Toast.LENGTH_SHORT)
                .show() else Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show()
            item.isStared = !item.isStared
            imageButton.setAlpha(if (item.isStared) 1.0f else 0.3f)
            imageButton.setColorFilter(context.getColor(if (item.isStared) R.color.red else R.color.grey))
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: ItemData, position: Int) {
        val intent = Intent(context, ShowTextActivity0430::class.java)
        intent.putExtra("text", data.`object` as String)
        intent.putExtra("stared", data.isStared)
        intent.putExtra("position", position)
        ContextCompat.startActivity(context, intent, null)
    }
}
