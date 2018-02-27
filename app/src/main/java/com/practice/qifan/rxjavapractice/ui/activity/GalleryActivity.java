package com.practice.qifan.rxjavapractice.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.practice.qifan.rxjavapractice.BaseActivity;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.ui.fragment.common.ImagePagerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2018/2/27.
 */

public class GalleryActivity extends BaseActivity {
    public static final String IMAGE_URLS = "com.practice.qifan.rxjavapractice.ui.activity.urls";
    private static final String KEY_CURRENT_POSITION = "com.practice.qifan.rxjavapractice.ui.key.currentPosition";
    public static int currentPosition;
    @BindView(R.id.fragment_container)
    FrameLayout frameLayout;

    public static Intent getCallingIntent(Context context, List<String> urls) {
        Intent callingIntent = new Intent(context, GalleryActivity.class);
        callingIntent.putStringArrayListExtra(IMAGE_URLS, (ArrayList<String>) urls);
        return callingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0);
            // Return here to prevent adding additional GridFragments when changing orientation.
            return;
        }
        List<String> urls = getIntent().getStringArrayListExtra(IMAGE_URLS);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, ImagePagerFragment.newInstance(urls), ImagePagerFragment.class.getSimpleName())
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_POSITION, currentPosition);
    }

}
