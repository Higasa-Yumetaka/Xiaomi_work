package com.example.work.work_0422;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.work.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main0422Activity extends AppCompatActivity implements ButtomFragment_1.IButtomFragmentAction_1, ButtomFragment_2.IButtomFragmentAction_2, ButtomFragment_3.IButtomFragmentAction_3{



    private ViewPager viewPager;
    private ButtomFragment_1 btn_frame_1;
    private ButtomFragment_2 btn_frame_2;
    private ButtomFragment_3 btn_frame_3;

    private boolean init = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_0422);

        viewPager = findViewById(R.id.viewPager);
        //bottomNavigationView = findViewById(R.id.bottomNavigationView);

         btn_frame_1 = new ButtomFragment_1();
         btn_frame_2 = new ButtomFragment_2();
         btn_frame_3 = new ButtomFragment_3();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.buttom_frame_1, btn_frame_1)
                .add(R.id.buttom_frame_2, btn_frame_2)
                .add(R.id.buttom_frame_3, btn_frame_3)
                .commit();



        // 创建 ViewPager 适配器
        FragmentStatePagerAdapter fragmentPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        if (!init){
                            btn_frame_1.TextSelected(true);
                            init = true;
                        }
                        return new PagerFragment_1();
                    case 1:
                        return new PagerFragment_2();
                    case 2:
                        return new PagerFragment_3();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Page " + (position + 1);
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);

        // 监听ViewPager页面切换
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btn_frame_1.TextSelected(true);
                        btn_frame_2.TextSelected(false);
                        btn_frame_3.TextSelected(false);
                        break;
                    case 1:
                        btn_frame_1.TextSelected(false);
                        btn_frame_2.TextSelected(true);
                        btn_frame_3.TextSelected(false);
                        break;
                    case 2:
                        btn_frame_1.TextSelected(false);
                        btn_frame_2.TextSelected(false);
                        btn_frame_3.TextSelected(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void changeViewPagerFragment(int page) {
        if (viewPager != null) {
            viewPager.setCurrentItem(page);
        }
    }

}