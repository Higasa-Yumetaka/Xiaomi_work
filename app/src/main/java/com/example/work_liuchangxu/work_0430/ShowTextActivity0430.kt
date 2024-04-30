package com.example.work_liuchangxu.work_0430

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.work_liuchangxu.R
import com.example.work_liuchangxu.work_0425.MyEvent
import org.greenrobot.eventbus.EventBus

class ShowTextActivity0430 : AppCompatActivity() {
    var text: String? = null
    var stared = false
    var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_text0425)
        //获取extra
        text = intent.getStringExtra("text")
        stared = intent.getBooleanExtra("stared", false)
        position = intent.getIntExtra("position", 0)
        //设置text
        val textView = findViewById<TextView>(R.id.ActivityTextView0425)
        textView.text = text
        val imageButton = findViewById<ImageButton>(R.id.star_button_activity_text0425)
        if (stared) {
            imageButton.setAlpha(1.0f)
            imageButton.setColorFilter(getColor(R.color.red))
        } else {
            imageButton.setAlpha(0.3f)
            imageButton.setColorFilter(getColor(R.color.grey))
        }
        imageButton.setOnClickListener { v: View? ->
            if (stared) {
                imageButton.setAlpha(0.3f)
                imageButton.setColorFilter(getColor(R.color.grey))
            } else {
                imageButton.setAlpha(1.0f)
                imageButton.setColorFilter(getColor(R.color.red))
            }
            stared = !stared
            EventBus.getDefault().post(StarEvent(position, stared))
        }
    }
}