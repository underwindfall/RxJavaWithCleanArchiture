package com.practice.qifan.rxjavapractice.presenter;

import android.util.Log;

import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.domain.usecase.Zhuangbi.GetZhuangbiListUseCase;
import com.practice.qifan.rxjavapractice.BaseContract;
import com.practice.qifan.rxjavapractice.BasePresenter;
import com.practice.qifan.rxjavapractice.dagger.scope.PerActivity;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModelMapper;
import com.practice.qifan.rxjavapractice.view.ElementaryContract;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by qifan on 2018/2/20.
 */
@PerActivity
public class ElementaryPresenter extends BasePresenter implements ElementaryContract.Presenter {

    private final GetZhuangbiListUseCase mGetZhuangbiListUseCase;
    private final ZhuangbiModelMapper mZhuangbiModelMapper;
    private ElementaryContract.View mView;

    @Inject
    public ElementaryPresenter(GetZhuangbiListUseCase getZhuangbiListUseCase, ZhuangbiModelMapper zhuangbiModelMapper) {
        mGetZhuangbiListUseCase = getZhuangbiListUseCase;
        mZhuangbiModelMapper = zhuangbiModelMapper;
    }


    @Override
    public void subscribe(BaseContract.View view) {
        mView = (ElementaryContract.View) view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
        mGetZhuangbiListUseCase.dispose();
    }


    @Override
    public void showImageList() {
        mGetZhuangbiListUseCase.execute(new DisposableObserver<List<ZhuangbiImageBean>>() {
            @Override
            public void onNext(List<ZhuangbiImageBean> zhuangbiImageBeans) {
                Log.d("Presenter", "OnNext");
                showImageCollectionInView(zhuangbiImageBeans);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Presenter", "onError");
            }

            @Override
            public void onComplete() {
                Log.d("Presenter", "onComplete");
            }
        });
    }

    @Override
    public void setKeyWord(String name) {
        mGetZhuangbiListUseCase.setKeyword(name);
    }

    private void showImageCollectionInView(List<ZhuangbiImageBean> zhuangbiImageBeans) {
        final Collection<ZhuangbiModel> zhuangbiModelCollection = mZhuangbiModelMapper.transform(zhuangbiImageBeans);
        Log.d("%s", zhuangbiModelCollection.size() + "");
        mView.renderImageList(zhuangbiModelCollection);
    }
}
