package com.practice.qifan.rxjavapractice;

import android.app.Application;

/**
 * Created by qifan on 2018/1/29.
 */

public class App extends Application {
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
