package com.practice.qifan.data.network.dagger;

import com.practice.qifan.data.network.repository.GankImageDataRepository;
import com.practice.qifan.data.network.repository.ZhuangbiImageDataRepository;
import com.practice.qifan.domain.repository.GankImageRepository;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qifan on 2018/2/25.
 * RepositoryModule
 */
@Module
public class RepositoryModule {

    public RepositoryModule() {
    }

    @Provides
    ZhuangbiImageRepository provideZhaungbiImageDataRepository(ZhuangbiImageDataRepository zhuangbiImageDataRepository) {
        return zhuangbiImageDataRepository;
    }

    @Provides
    GankImageRepository provideGankImageDataRepository(GankImageDataRepository gankImageDataRepository) {
        return gankImageDataRepository;
    }
}
