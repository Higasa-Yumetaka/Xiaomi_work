package com.example.work_liuchangxu.work_0430

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.work_liuchangxu.R

class FragmentSelfKotlin : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_self_layout_0425, container, false)
        val textView = view.findViewById<TextView>(R.id.textView_0425)
        textView.text = "这是我的页面"
        return view
    }
}
