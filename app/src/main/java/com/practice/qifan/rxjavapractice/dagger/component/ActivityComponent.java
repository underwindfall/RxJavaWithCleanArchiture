package com.practice.qifan.rxjavapractice.dagger.component;

import android.app.Activity;

import com.practice.qifan.rxjavapractice.dagger.module.ActivityModule;
import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;

import dagger.Component;

/**
 * Created by qifan on 2018/2/25.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
