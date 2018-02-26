package com.practice.qifan.rxjavapractice.dagger.component;

import android.content.Context;

import com.google.gson.Gson;
import com.practice.qifan.data.network.dagger.NetworkModule;
import com.practice.qifan.data.network.dagger.RepositoryModule;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;
import com.practice.qifan.rxjavapractice.RxJavaSampleApp;
import com.practice.qifan.rxjavapractice.dagger.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import io.reactivex.Scheduler;

/**
 * Created by qifan on 2018/2/25.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {
    void inject(RxJavaSampleApp rxJavaSampleApp);

    Context applicationContext();

    Gson gson();

    Scheduler scheduler();

    ZhuangbiImageRepository zhuangbiImageRepository();

    final class Initializer {
        private Initializer() {
            throw new UnsupportedOperationException();
        }

        public static ApplicationComponent init(Context context) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(context))
                    .netModule(new NetworkModule())
                    .repositoryModule(new RepositoryModule())
                    .build();
        }
    }
}
