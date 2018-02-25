package com.practice.qifan.rxjavapractice.presenter;

import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.domain.usecase.Zhuangbi.GetZhuangbiListUseCase;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModelMapper;
import com.practice.qifan.rxjavapractice.ui.ElementaryFragment;
import com.practice.qifan.rxjavapractice.view.ElementaryView;

import java.util.Collection;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by qifan on 2018/2/20.
 */

public class ElementaryPresenter {
    private ElementaryView view;
    private final GetZhuangbiListUseCase getZhuangbiListUseCase;
    private final ZhuangbiModelMapper zhuangbiModelMapper;

    public ElementaryPresenter(GetZhuangbiListUseCase getZhuangbiListUseCase, ZhuangbiModelMapper zhuangbiModelMapper) {
        this.getZhuangbiListUseCase = getZhuangbiListUseCase;
        this.zhuangbiModelMapper = zhuangbiModelMapper;
    }


    public void setView(ElementaryView view) {
        this.view = view;
    }

    public void destory() {
        this.getZhuangbiListUseCase.dispose();
        this.view = null;
    }

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadUserList();
    }

    private void showZhuangbiCollectionInView(Collection<ZhuangbiImageBean> zhuangbiCollection) {
        final Collection<ZhuangbiModel> zhuangbiModelCollection =
                this.zhuangbiModelMapper.transform(zhuangbiCollection);
        this.view.renderUserList(zhuangbiModelCollection);
    }


    private void loadUserList() {
        this.getZhuangbiListUseCase.execute(new ZhuangbiListObserver());
    }

    private class ZhuangbiListObserver extends DisposableObserver<List<ZhuangbiImageBean>> {

        @Override
        public void onNext(List<ZhuangbiImageBean> zhuangbiModels) {
            ElementaryPresenter.this.showZhuangbiCollectionInView(zhuangbiModels);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
