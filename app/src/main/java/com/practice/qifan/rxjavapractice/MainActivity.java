package com.practice.qifan.rxjavapractice;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.practice.qifan.rxjavapractice.ui.ElementaryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(android.R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        viewPager.setAdapter((new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ElementaryFragment();
//                    case 1:
//                        return new MapFragment();
//                    case 2:
//                        return new ZipFragment();
//                    case 3:
//                        return new TokenFragment();
//                    case 4:
//                        return new TokenAdvancedFragment();
//                    case 5:
//                        return new CacheFragment();
                    default:
                        return new ElementaryFragment();
                }
            }

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.title_elementary);
//                    case 1:
//                        return getString(R.string.title_map);
//                    case 2:
//                        return getString(R.string.title_zip);
//                    case 3:
//                        return getString(R.string.title_token);
//                    case 4:
//                        return getString(R.string.title_token_advanced);
//                    case 5:
//                        return getString(R.string.title_cache);
                    default:
                        return getString(R.string.title_elementary);
                }
            }
        }));
        tabLayout.setupWithViewPager(viewPager);
    }
}