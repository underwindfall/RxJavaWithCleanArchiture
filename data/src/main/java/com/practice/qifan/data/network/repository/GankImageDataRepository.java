package com.practice.qifan.data.network.repository;

import com.practice.qifan.data.network.dagger.NetworkModule;
import com.practice.qifan.data.network.net.GankApiService;
import com.practice.qifan.domain.bean.GankImageBean;
import com.practice.qifan.domain.repository.GankImageRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by qifan on 2018/2/26.
 */
@Singleton
public class GankImageDataRepository implements GankImageRepository {

    GankApiService gankApiService;

    @Inject
    GankImageDataRepository(@Named(NetworkModule.RETROFIT_GANK) Retrofit retrofit) {
        gankApiService = retrofit.create(GankApiService.class);
    }

    @Override
    public Observable<GankImageBean> search(int number, int page) {
        return gankApiService.getBeauties(number, page);
    }
}
