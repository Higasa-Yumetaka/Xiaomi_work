package com.example.work_liuchangxu.work_0430

import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.work_liuchangxu.R

class MyImageProvider : BaseItemProvider<MyStruct>() {
    override val itemViewType: Int
        get() = MyStruct.TYPE_IMAGE
    override val layoutId: Int
        get() = R.layout.item_image_recycle_0425

    override fun convert(helper: BaseViewHolder, item: MyStruct) {
        Glide.with(context)
            .load(item.`object` as Int)
            .apply(RequestOptions().transforms(RoundedCorners(25)))
            .into((helper.getView<View>(R.id.item_imageView0425) as ImageView))
        val imageButton = helper.getView<ImageButton>(R.id.star_button_image0425)
        if (item.isStared) {
            imageButton.setAlpha(1.0f)
            imageButton.setColorFilter(context.getColor(R.color.red))
        } else {
            imageButton.setAlpha(0.3f)
            imageButton.setColorFilter(context.getColor(R.color.grey))
        }
        imageButton.setOnClickListener { v: View? ->
            if (item.isStared) Toast.makeText(context, "取消点赞", Toast.LENGTH_SHORT)
                .show() else Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show()
            item.isStared = !item.isStared
            imageButton.setAlpha(if (item.isStared) 1.0f else 0.3f)
            imageButton.setColorFilter(context.getColor(if (item.isStared) R.color.red else R.color.grey))
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: MyStruct, position: Int) {
        val intent = Intent(context, ShowImageActivity0430::class.java)
        intent.putExtra("image", data.`object` as Int)
        intent.putExtra("stared", data.isStared)
        intent.putExtra("position", position)
        ContextCompat.startActivity(context, intent, null)
    }
}
