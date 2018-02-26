package com.practice.qifan.rxjavapractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.practice.qifan.rxjavapractice.dagger.component.ApplicationComponent;
import com.practice.qifan.rxjavapractice.dagger.module.ActivityModule;
import com.practice.qifan.rxjavapractice.helper.Navigator;

import javax.inject.Inject;

/**
 * Created by qifan on 2018/2/26.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Inject
    protected Navigator mNavigator;

    /**
     * Get the Main Application component for dependency injection.
     */
    public ApplicationComponent getApplicationComponent() {
        return ((RxJavaSampleApp) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }
}
