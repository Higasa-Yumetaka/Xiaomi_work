package com.example.work_liuchangxu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.work_liuchangxu.work_0421.Work0421MainActivity
import com.example.work_liuchangxu.work_0422.Main0422Activity
import com.example.work_liuchangxu.work_0423.RecycleActivity
import com.example.work_liuchangxu.work_0424.Main0424Activity
import com.example.work_liuchangxu.work_0425.Main0425Activity
import com.example.work_liuchangxu.work_0426.Main0426Activity
import com.example.work_liuchangxu.work_0427.Main0427Activity
import com.example.work_liuchangxu.work_0428.Main0428Activity_1
import com.example.work_liuchangxu.work_0428.Main0428Activity_2
import com.example.work_liuchangxu.work_0429.Main0428Activity_optimized
import com.example.work_liuchangxu.work_0429.Main0429Activity
import com.example.work_liuchangxu.work_0430.Main0430Activity

// 作业项目统一入口
class TrueMainActivity : AppCompatActivity() {
    private val NameList: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.true_activity_main)

        // 新建作业添加到NameList
        NameList.add("work_0430|Kotlin")
        NameList.add("work_0429_2|ANR")
        NameList.add("work_0429_1|完善work_0428_2的内存泄漏问题")
        NameList.add("work_0428_2|Network")
        NameList.add("work_0428_1|Handler")
        NameList.add("work_0427|View")
        NameList.add("work_0426|Animation")
        NameList.add("work_0425|Components")
        NameList.add("work_0424|UI")
        NameList.add("work_0423|RecycleView")
        NameList.add("work_0422|Fragment")
        NameList.add("work_0421|Activity")
        val adapter = MyListAdapter(this@TrueMainActivity, R.layout.list_item_layout, NameList)
        adapter.setOnItemClickListener(onButtonClickListener)
        val listView = findViewById<ListView>(R.id.main_listView)
        listView.setAdapter(adapter)
    }

    private val onButtonClickListener: MyListAdapter.OnItemClickListener =
        MyListAdapter.OnItemClickListener { view: View?, position: Int ->
            // Toast.makeText(TrueMainActivity.this, "You clicked " + StringList.get(position), Toast.LENGTH_SHORT).show();
            var intent: Intent? = null
            val totalNum = NameList.size

            // 新建作业跳转
            if (position == totalNum - 1) {
                intent = Intent(this, Work0421MainActivity::class.java)
            } else if (position == totalNum - 2) {
                intent = Intent(this, Main0422Activity::class.java)
            } else if (position == totalNum - 3) {
                intent = Intent(this, RecycleActivity::class.java)
            } else if (position == totalNum - 4) {
                intent = Intent(this, Main0424Activity::class.java)
            } else if (position == totalNum - 5) {
                intent = Intent(this, Main0425Activity::class.java)
            } else if (position == totalNum - 6) {
                intent = Intent(this, Main0426Activity::class.java)
            } else if (position == totalNum - 7) {
                intent = Intent(this, Main0427Activity::class.java)
            } else if (position == totalNum - 8) {
                intent = Intent(this, Main0428Activity_1::class.java)
            } else if (position == totalNum - 9) {
                intent = Intent(this, Main0428Activity_2::class.java)
            } else if (position == totalNum - 10) {
                intent = Intent(this, Main0428Activity_optimized::class.java)
            } else if (position == totalNum - 11) {
                intent = Intent(this, Main0429Activity::class.java)
            } else if (position == totalNum - 12) {
                intent = Intent(this, Main0430Activity::class.java)
            }
            startActivity(intent)
        }

    // 尝试使用ListView
    class MyListAdapter(context: Context?, private val resourceId: Int, objects: List<String>?) :
        ArrayAdapter<String?>(
            context!!, resourceId, objects!!
        ) {
        var mOnItemClickListener: OnItemClickListener? = null

        fun interface OnItemClickListener {
            fun onItemClick(view: View?, position: Int)
        }

        fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
            mOnItemClickListener = onItemClickListener
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val work = getItem(position)
            val view: View
            val holder: ListItemViewHolder
            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(resourceId, parent, false)
                holder = ListItemViewHolder()
                holder.workName = view.findViewById(R.id.list_item_work_name_textview)
                holder.workDetail = view.findViewById(R.id.list_item_work_detail_textview)
                holder.startButton = view.findViewById(R.id.list_item_start_button)
                view.tag = holder
            } else {
                view = convertView
                holder = view.tag as ListItemViewHolder
            }
            var work_info = arrayOfNulls<String>(0)
            if (work != null) {
                work_info =
                    work.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            }
            holder.workName!!.text = work_info[0]
            holder.workDetail!!.text = work_info[1]
            val localButton = holder.startButton
            localButton?.setOnClickListener { v: View? ->
                if (mOnItemClickListener != null) {
                    mOnItemClickListener!!.onItemClick(v, position)
                }
            }
            return view
        }

        class ListItemViewHolder {
            var workName: TextView? = null
            var startButton: Button? = null
            var workDetail: TextView? = null
        }
    }
}