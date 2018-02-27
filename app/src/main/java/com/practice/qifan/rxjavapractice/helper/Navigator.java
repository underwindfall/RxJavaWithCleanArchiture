package com.practice.qifan.rxjavapractice.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.practice.qifan.rxjavapractice.ui.activity.GalleryActivity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by qifan on 2018/2/26.
 */
@Singleton
public class Navigator {
    @Inject

    public Navigator() {
    }

    public void callIntActivity(@NonNull Context context, List<String> urls) {
        Intent intentToLaunch = GalleryActivity.getCallingIntent(context, urls);
        context.startActivity(intentToLaunch);
    }
}
