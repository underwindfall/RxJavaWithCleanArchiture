package com.practice.qifan.rxjavapractice;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;

import com.practice.qifan.rxjavapractice.dagger.component.ApplicationComponent;
import com.practice.qifan.rxjavapractice.dagger.module.ActivityModule;

import butterknife.OnClick;

/**
 * Created by qifan on 2018/1/29.
 */

public abstract class BaseFragment extends Fragment {

    @OnClick(R.id.tipBt)
    void tip() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
                .show();
    }

    protected abstract int getDialogRes();

    protected abstract int getTitleRes();

    protected ApplicationComponent getApplicationComponent() {
        return ((RxJavaSampleApp) getActivity().getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(getActivity());
    }
}
