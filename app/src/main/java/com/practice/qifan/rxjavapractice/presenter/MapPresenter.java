package com.practice.qifan.rxjavapractice.presenter;

import com.practice.qifan.domain.bean.GankImageBean;
import com.practice.qifan.domain.bean.GankImageResultBean;
import com.practice.qifan.domain.usecase.gank.GetGankListUseCase;
import com.practice.qifan.rxjavapractice.BaseContract;
import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;
import com.practice.qifan.rxjavapractice.mapper.GankResultModel;
import com.practice.qifan.rxjavapractice.mapper.GankResultModelMapper;
import com.practice.qifan.rxjavapractice.view.MapContract;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by qifan on 2018/2/26.
 */
@PerActivity
public class MapPresenter implements MapContract.Presenter {

    private GetGankListUseCase mGetGankListUseCase;

    private GankResultModelMapper mGankResultModelMapper;
    private MapContract.View mView;

    @Inject
    MapPresenter(GetGankListUseCase mGetGankListUseCase, GankResultModelMapper mGankResultModelMapper) {
        this.mGetGankListUseCase = mGetGankListUseCase;
        this.mGankResultModelMapper = mGankResultModelMapper;
    }

    @Override
    public void subscribe(BaseContract.View view) {
        mView = (MapContract.View) view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
        mGetGankListUseCase.dispose();
    }

    @Override
    public void showImageList() {
        mGetGankListUseCase.execute(new DisposableObserver<GankImageBean>() {
            @Override
            public void onNext(GankImageBean gankImageBean) {
                showImageCollectionInView(gankImageBean.getGankImageResultBean());
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    public void setKeyWord(int number, int page) {
        mGetGankListUseCase.setKeyWord(number, page);
    }

    private void showImageCollectionInView(List<GankImageResultBean> gankImageResultBean) {
        final Collection<GankResultModel> gankResultModelCollection = mGankResultModelMapper.transform(gankImageResultBean);
        mView.renderImageList(gankResultModelCollection);
    }
}
