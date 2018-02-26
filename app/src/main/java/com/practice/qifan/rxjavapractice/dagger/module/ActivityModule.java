package com.practice.qifan.rxjavapractice.dagger.module;

import android.app.Activity;

import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qifan on 2018/2/25.
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return mActivity;
    }
}
