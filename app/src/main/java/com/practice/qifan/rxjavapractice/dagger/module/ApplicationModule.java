package com.practice.qifan.rxjavapractice.dagger.module;

import android.content.Context;

import com.practice.qifan.domain.executor.PostExecutionThread;
import com.practice.qifan.rxjavapractice.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by qifan on 2018/2/25.
 */
@Module
public class ApplicationModule {
    private final Context mApplicationContext;

    public ApplicationModule(Context mApplicationContext) {
        this.mApplicationContext = mApplicationContext;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplicationContext;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread){
        return uiThread;
    }

}
