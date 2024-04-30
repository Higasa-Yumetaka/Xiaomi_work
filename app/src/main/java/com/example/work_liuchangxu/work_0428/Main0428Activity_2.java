package com.example.work_liuchangxu.work_0428;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.work_liuchangxu.R;
import com.google.android.material.tabs.TabLayout;

public class Main0428Activity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0425);
        TabLayout tabLayout = findViewById(R.id.tablayout0425);
        ViewPager viewPager = findViewById(R.id.viewPager0425);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    FragmentStatePagerAdapter fragmentPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()){

        @Override
        public int getCount() {
            return 2;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return switch (position) {
                case 0 -> new Fragment_main_0428_2();
                case 1 -> new Fragment_self_0428_2();
                default -> null;
            };
        }

        public CharSequence getPageTitle(int position){
            return switch (position){
                case 0 -> "首页";
                case 1 -> "我的";
                default -> null;
            };
        }
    };

}