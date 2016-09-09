package com.xuejinwei.centercycletablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager          viewPager;
    @Bind(R.id.tabLayout) CenteringTabLayout mTabLayout;

    String[] mStrTitle = {"户型D3", "户型D4", "通行", "户型D1", "户型D2"};
    List<String> mListTitle = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Collections.addAll(mListTitle, mStrTitle);

        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        mTabLayout.setupWithViewPager(viewPager);
        final RelativeLayout tabView = (RelativeLayout) LayoutInflater
                .from(MainActivity.this)
                .inflate(R.layout.tabview, mTabLayout, false);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setCustomView(tabView);
            }

            @Override
            public void onTabUnselected(CenteringTabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(CenteringTabLayout.Tab tab) {

            }
        });

        mTabLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(2);//选中5个中间的第3个
            }
        }, 10);

    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());

        for (int i = 0; i < mListTitle.size(); i++) {
            adapter.addFragment(MyFragment.newInstance(mListTitle.get(i)), mListTitle.get(i));
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
    }

    static class FragmentAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments      = new ArrayList<>();
        private final List<String>   mFragmentTitles = new ArrayList<>();

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);

        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

    }
}
