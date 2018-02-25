package com.practice.qifan.domain.repository;

import com.practice.qifan.domain.bean.ZhuangbiImageBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by qifan on 2018/2/1.
 */

public interface ZhuangbiImageRepository {
    Observable<List<ZhuangbiImageBean>> search(String keyword);
}
