package com.practice.qifan.rxjavapractice;

/**
 * Created by qifan on 2018/2/26.
 */

public interface BaseContract {
    interface View {

    }

    interface Presenter {
        void subscribe(View view);

        void unsubscribe();
    }
}
