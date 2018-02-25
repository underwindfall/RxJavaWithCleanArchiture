package com.practice.qifan.data.network.api;

import com.practice.qifan.domain.bean.ZhuangbiImageBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by qifan on 2018/2/1.
 */

public interface ZhuangbiApi {
    @GET("search")
    Observable<List<ZhuangbiImageBean>> search(@Query("q") String query);
}

