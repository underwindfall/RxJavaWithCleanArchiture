package com.practice.qifan.data.network.net;

import com.practice.qifan.domain.bean.GankImageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by qifan on 2018/2/26.
 * GankApiService
 */

public interface GankApiService {
    @GET("data/福利/{number}/{page}")
    Observable<GankImageBean> getBeauties(@Path("number") int number, @Path("page") int page);
}
