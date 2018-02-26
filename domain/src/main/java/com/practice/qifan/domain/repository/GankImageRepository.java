package com.practice.qifan.domain.repository;

import com.practice.qifan.domain.bean.GankImageBean;

import io.reactivex.Observable;

/**
 * Created by qifan on 2018/2/26.
 * GankImageRepository
 */

public interface GankImageRepository {
    Observable<GankImageBean> search(int number, int page);
}
