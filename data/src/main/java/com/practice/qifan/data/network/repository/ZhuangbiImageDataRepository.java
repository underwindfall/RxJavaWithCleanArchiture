package com.practice.qifan.data.network.repository;

import com.practice.qifan.data.network.dagger.NetworkModule;
import com.practice.qifan.data.network.net.ZhuangbiApiService;
import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by qifan on 2018/2/1.
 */
@Singleton
public class ZhuangbiImageDataRepository implements ZhuangbiImageRepository {

    ZhuangbiApiService apiService;

    @Inject
    public ZhuangbiImageDataRepository(@Named(NetworkModule.RETROFIT_ZHUANGBI) Retrofit retrofit) {
        apiService = retrofit.create(ZhuangbiApiService.class);
    }

    @Override
    public Observable<List<ZhuangbiImageBean>> search(String keyword) {
        return apiService.search(keyword);
    }
}
