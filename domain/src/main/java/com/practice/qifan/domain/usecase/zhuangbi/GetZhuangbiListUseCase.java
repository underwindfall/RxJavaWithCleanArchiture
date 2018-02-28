package com.practice.qifan.domain.usecase.zhuangbi;

import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.domain.executor.PostExecutionThread;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;
import com.practice.qifan.domain.usecase.UseCase;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by qifan on 2018/2/20.
 * GetZhuangbiListUseCase
 */

public class GetZhuangbiListUseCase extends UseCase<List<ZhuangbiImageBean>> {
    private ZhuangbiImageRepository mZhuangbiImageRepository;
    private String mKeyword;


    public GetZhuangbiListUseCase(PostExecutionThread postExecutionThread, ZhuangbiImageRepository zhuangbiImageRepository) {
        super(postExecutionThread);
        mZhuangbiImageRepository = zhuangbiImageRepository;
    }

    public void setKeyword(String keyword) {
        mKeyword = keyword;
    }

    @Override
    protected Observable<List<ZhuangbiImageBean>> buildUseCaseObservable() {
        return mZhuangbiImageRepository.search(mKeyword);
    }
}
