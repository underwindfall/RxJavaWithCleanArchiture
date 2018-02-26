package com.practice.qifan.rxjavapractice.view;

import com.practice.qifan.rxjavapractice.BaseContract;
import com.practice.qifan.rxjavapractice.mapper.GankResultModel;

import java.util.Collection;

/**
 * Created by qifan on 2018/2/26.
 * MapContract
 */

public interface MapContract {

    interface View extends BaseContract.View {
        void renderImageList(Collection<GankResultModel> gankResultModelCollection);
    }

    interface Presenter extends BaseContract.Presenter {
        void showImageList();

        void setKeyWord(int number, int page);
    }
}
