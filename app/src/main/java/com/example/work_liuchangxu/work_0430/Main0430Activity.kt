package com.example.work_liuchangxu.work_0430

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.work_liuchangxu.R
import com.google.android.material.tabs.TabLayout

class Main0430Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main0425)
        val tabLayout = findViewById<TabLayout>(R.id.tablayout0425)
        val viewPager = findViewById<ViewPager>(R.id.viewPager0425)
        viewPager.setAdapter(fragmentPagerAdapter)
        tabLayout.setupWithViewPager(viewPager)
    }

    private var fragmentPagerAdapter: FragmentStatePagerAdapter = object : FragmentStatePagerAdapter(
        supportFragmentManager
    ) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FragmentMainKotlin()
                1 -> FragmentSelfKotlin()
                else -> FragmentSelfKotlin() // kotlin不允许返回null
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "首页"
                1 -> "我的"
                else -> null
            }
        }
    }
}