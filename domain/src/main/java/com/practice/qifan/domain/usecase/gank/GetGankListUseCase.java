package com.practice.qifan.domain.usecase.gank;

import com.practice.qifan.domain.bean.GankImageBean;
import com.practice.qifan.domain.executor.PostExecutionThread;
import com.practice.qifan.domain.repository.GankImageRepository;
import com.practice.qifan.domain.usecase.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by qifan on 2018/2/26.
 * GetGankListUseCase
 */

public class GetGankListUseCase extends UseCase<GankImageBean> {

    private GankImageRepository mGankImageRepository;
    private int mPage;
    private int mNumber;

    @Inject
    public GetGankListUseCase(PostExecutionThread postExecutionThread, GankImageRepository gankImageRepository) {
        super(postExecutionThread);
        mGankImageRepository = gankImageRepository;
    }

    public void setKeyWord(int number, int page) {
        mPage = page;
        mNumber = number;
    }


    @Override
    protected Observable<GankImageBean> buildUseCaseObservable() {
        return mGankImageRepository.search(mNumber, mPage);
    }
}
