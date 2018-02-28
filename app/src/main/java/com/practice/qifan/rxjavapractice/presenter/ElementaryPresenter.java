package com.practice.qifan.rxjavapractice.presenter;

import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.domain.usecase.zhuangbi.GetZhuangbiListUseCase;
import com.practice.qifan.rxjavapractice.BaseContract;
import com.practice.qifan.rxjavapractice.BasePresenter;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModelMapper;
import com.practice.qifan.rxjavapractice.view.ElementaryContract;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by qifan on 2018/2/20.
 */

public class ElementaryPresenter extends BasePresenter implements ElementaryContract.Presenter {

    private GetZhuangbiListUseCase mGetZhuangbiListUseCase;
    private ZhuangbiModelMapper mZhuangbiModelMapper;
    private ElementaryContract.View mView;

    @Inject
    ElementaryPresenter(GetZhuangbiListUseCase getZhuangbiListUseCase, ZhuangbiModelMapper zhuangbiModelMapper) {
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
        Timber.d(mGetZhuangbiListUseCase.toString());
        mGetZhuangbiListUseCase.execute(new DisposableObserver<List<ZhuangbiImageBean>>() {
            @Override
            public void onNext(List<ZhuangbiImageBean> zhuangbiImageBeans) {
//                Timber.d("OnNext");
                showImageCollectionInView(zhuangbiImageBeans);
            }

            @Override
            public void onError(Throwable e) {
//                Timber.e(e);
            }

            @Override
            public void onComplete() {
//                Timber.d("ElementaryPresenter is Completed");
            }
        });
    }

    @Override
    public void setKeyWord(String name) {
        mGetZhuangbiListUseCase.setKeyword(name);
    }

    private void showImageCollectionInView(List<ZhuangbiImageBean> zhuangbiImageBeans) {
        final Collection<ZhuangbiModel> zhuangbiModelCollection = mZhuangbiModelMapper.transform(zhuangbiImageBeans);
//        Timber.d(zhuangbiModelCollection.size() + "");
        mView.renderImageList(zhuangbiModelCollection);
    }
}
