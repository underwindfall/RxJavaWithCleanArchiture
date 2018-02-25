package com.practice.qifan.data.network.repository;

import com.practice.qifan.data.network.Network;
import com.practice.qifan.data.network.api.ZhuangbiApi;
import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by qifan on 2018/2/1.
 */

public class ZhuangbiImageDataRepository implements ZhuangbiImageRepository {

    ZhuangbiApi api;

    public ZhuangbiImageDataRepository(Network network) {
        api = network.getZhuangbiApi();
    }

    @Override
    public Observable<List<ZhuangbiImageBean>> search(String keyword) {
        return api.search(keyword);
    }
}
