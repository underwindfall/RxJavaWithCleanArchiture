package com.practice.qifan.rxjavapractice.dagger.module;

import com.practice.qifan.domain.executor.PostExecutionThread;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;
import com.practice.qifan.domain.usecase.Zhuangbi.GetZhuangbiListUseCase;
import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qifan on 2018/2/26.
 */

@Module
public class ImageModule {

    @Provides
    @PerActivity
    GetZhuangbiListUseCase provideZhuangbiListUseCase(PostExecutionThread postExecutionThread, ZhuangbiImageRepository zhuangbiImageRepository) {
        return new GetZhuangbiListUseCase(postExecutionThread, zhuangbiImageRepository);
    }
}
