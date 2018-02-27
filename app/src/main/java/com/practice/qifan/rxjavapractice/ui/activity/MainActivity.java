package com.practice.qifan.rxjavapractice.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.practice.qifan.rxjavapractice.BaseActivity;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.dagger.component.ImageComponent;
import com.practice.qifan.rxjavapractice.ui.fragment.Elementary.ElementaryFragment;
import com.practice.qifan.rxjavapractice.ui.fragment.Map.MapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private static final String KEY_CURRENT_POSITION = "com.practice.qifan.rxjavapractice.ui.key.currentPosition";
    public static int currentPosition;
    @BindView(android.R.id.tabs)
    TabLayout tabLayout;
    //    @BindView(R.id.viewPager)
//    ViewPager viewPager;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    ImageComponent mImageComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0);
            // Return here to prevent adding additional GridFragments when changing orientation.
            return;
        }
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        mImageComponent = ImageComponent.Initializer.init(getApplicationComponent(), getActivityModule());

//        viewPager.setAdapter((new FragmentStatePagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                switch (position) {
//                    case 0:
//                        return new ElementaryFragment();
//                    case 1:
//                        return new MapFragment();
////                    case 2:
////                        return new ZipFragment();
////                    case 3:
////                        return new TokenFragment();
////                    case 4:
////                        return new TokenAdvancedFragment();
////                    case 5:
////                        return new CacheFragment();
//                    default:
//                        return new ElementaryFragment();
//                }
//            }
//
//            @Override
//            public int getCount() {
//                return 2;
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                switch (position) {
//                    case 0:
//                        return getString(R.string.title_elementary);
//                    case 1:
//                        return getString(R.string.title_map);
////                    case 2:
////                        return getString(R.string.title_zip);
////                    case 3:
////                        return getString(R.string.title_token);
////                    case 4:
////                        return getString(R.string.title_token_advanced);
////                    case 5:
////                        return getString(R.string.title_cache);
//                    default:
//                        return getString(R.string.title_elementary);
//                }
//            }
//        }));


        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.title_elementary)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.title_map)));
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_container, new ElementaryFragment())
//                .addToBackStack(null)
//                .commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 2:
                        replaceFragmentToStack(new ElementaryFragment());
                    case 1:
                        replaceFragmentToStack(new MapFragment());
                    default:
                        replaceFragmentToStack(new ElementaryFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void replaceFragmentToStack(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
//        if (fragmentManager.getFragments()!=null){
//            fragmentManager.popBackStack();
//        }
        fragmentManager.getFragments().clear();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_POSITION, currentPosition);
    }

    public ImageComponent getImageComponent() {
        return mImageComponent;
    }
}
