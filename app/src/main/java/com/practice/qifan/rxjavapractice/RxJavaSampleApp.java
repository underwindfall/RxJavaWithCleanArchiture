package com.practice.qifan.rxjavapractice;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.practice.qifan.rxjavapractice.dagger.component.ApplicationComponent;

import timber.log.Timber;

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
//        if (BuildConfig.DEBUG) {
        Logger.addLogAdapter(new AndroidLogAdapter());
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
//                    super.log(priority, tag, message, t);
                    Logger.log(priority, tag, message, t);
                }
            });
//        }
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
