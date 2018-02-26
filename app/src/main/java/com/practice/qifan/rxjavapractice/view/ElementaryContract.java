package com.practice.qifan.rxjavapractice.view;

import com.practice.qifan.rxjavapractice.BaseContract;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;

import java.util.Collection;

/**
 * Created by qifan on 2018/2/20.
 */

public interface ElementaryContract {
    interface View extends BaseContract.View{
        void renderImageList(Collection<ZhuangbiModel> zhuangbiModelCollection);
    }
    interface Presenter extends BaseContract.Presenter{
        void showImageList();
        void setKeyWord(String name);
    }

}
