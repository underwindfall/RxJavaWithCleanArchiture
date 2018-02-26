package com.practice.qifan.rxjavapractice.dagger.component;

import android.content.Context;

import com.google.gson.Gson;
import com.practice.qifan.data.network.dagger.NetworkModule;
import com.practice.qifan.data.network.dagger.RepositoryModule;
import com.practice.qifan.domain.executor.PostExecutionThread;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;
import com.practice.qifan.rxjavapractice.BaseActivity;
import com.practice.qifan.rxjavapractice.RxJavaSampleApp;
import com.practice.qifan.rxjavapractice.dagger.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

/**
 * Created by qifan on 2018/2/25.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    void inject(RxJavaSampleApp rxJavaSampleApp);

    void inject(BaseActivity baseActivity);

    Context applicationContext();

    Gson gson();

    PostExecutionThread postExecutionThread();

    ZhuangbiImageRepository zhuangbiImageRepository();


    final class Initializer {
        private Initializer() {
            throw new UnsupportedOperationException();
        }

        public static ApplicationComponent init(Context context) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(context))
                    .networkModule(new NetworkModule())
                    .repositoryModule(new RepositoryModule())
                    .build();
        }
    }
}
