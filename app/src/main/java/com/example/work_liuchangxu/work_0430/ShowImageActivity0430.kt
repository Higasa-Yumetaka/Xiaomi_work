package com.example.work_liuchangxu.work_0430

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.work_liuchangxu.R
import org.greenrobot.eventbus.EventBus

class ShowImageActivity0430 : AppCompatActivity() {
    private var image = 0
    var stared = false
    var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image0425) // 设置布局文件

        // 获取extra
        image = intent.getIntExtra("image", R.drawable.great_wall)
        stared = intent.getBooleanExtra("stared", false)
        position = intent.getIntExtra("position", 0)
        // 设置image
        val imageView = findViewById<ImageView>(R.id.ActivityImageView0425)
        Glide.with(this)
            .load(image)
            .into(imageView)
        val imageButton = findViewById<ImageButton>(R.id.star_button_activity_image0425)
        if (stared) {
            imageButton.setAlpha(1.0f)
            imageButton.setColorFilter(getColor(R.color.red))
        } else {
            imageButton.setAlpha(0.3f)
            imageButton.setColorFilter(getColor(R.color.grey))
        }
        imageButton.setOnClickListener { v: View? ->
            if (stared) {
                Toast.makeText(this, "取消点赞", Toast.LENGTH_SHORT).show()
                imageButton.setAlpha(0.3f)
                imageButton.setColorFilter(getColor(R.color.grey))
            } else {
                Toast.makeText(this, "点赞成功", Toast.LENGTH_SHORT).show()
                imageButton.setAlpha(1.0f)
                imageButton.setColorFilter(getColor(R.color.red))
            }
            stared = !stared
            EventBus.getDefault().post(MyEvent(position, stared))
        }
    }
}
