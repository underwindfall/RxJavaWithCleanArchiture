package com.practice.qifan.domain.usecase.Zhuangbi;

import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.domain.executor.PostExecutionThread;
import com.practice.qifan.domain.executor.ThreadExecutor;
import com.practice.qifan.domain.modelMapper.Mapper;
import com.practice.qifan.domain.repository.ZhuangbiImageRepository;
import com.practice.qifan.domain.usecase.UseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by qifan on 2018/2/20.
 */

public class GetZhuangbiListUseCase extends UseCase<List<ZhuangbiImageBean>> {
    private ZhuangbiImageRepository mZhuangbiImageRepository;
    private String mKeyword;

    GetZhuangbiListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ZhuangbiImageRepository zhuangbiImageRepository) {
        super(threadExecutor, postExecutionThread);
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
