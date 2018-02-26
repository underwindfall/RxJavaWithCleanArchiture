package com.practice.qifan.data.network.dagger;

import com.practice.qifan.data.network.repository.ZhuangbiImageDataRepository;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qifan on 2018/2/25.
 */
@Module
public class RepositoryModule {
    @Provides
    public ZhuangbiImageRepository provideZhaungbiImageDataRepository(ZhuangbiImageDataRepository zhuangbiImageDataRepository) {
        return zhuangbiImageDataRepository;
    }
}
