package com.practice.qifan.rxjavapractice;

import android.app.Application;

import com.practice.qifan.rxjavapractice.dagger.component.ApplicationComponent;

/**
 * Created by qifan on 2018/1/29.
 */

public class RxJavaSampleApp extends Application {
    private static RxJavaSampleApp INSTANCE;
    ApplicationComponent mApplicationComponent;

    public static RxJavaSampleApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        mApplicationComponent = initializeApplicationComponent();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    ApplicationComponent initializeApplicationComponent() {
        return ApplicationComponent.Initializer.init(getApplicationContext());
    }
}
