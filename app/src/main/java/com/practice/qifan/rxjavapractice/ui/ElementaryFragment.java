package com.practice.qifan.rxjavapractice.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.practice.qifan.data.network.Network;
import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.rxjavapractice.BaseFragment;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.adapter.ZhuangbiListAdapter;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;
import com.practice.qifan.rxjavapractice.presenter.ElementaryPresenter;
import com.practice.qifan.rxjavapractice.view.ElementaryView;

import java.util.Collection;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by qifan on 2018/1/30.
 */

public class ElementaryFragment extends BaseFragment implements ElementaryView{

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;
    ElementaryPresenter elementaryPresenter = new ElementaryPresenter(this);
    ZhuangbiListAdapter adapter = new ZhuangbiListAdapter();

    @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
    void onTagChecked(RadioButton searchRb, boolean checked) {
        if (checked) {
//            unSubscribe();
            adapter.setImages(null);
            swipeRefreshLayout.setRefreshing(true);
            this.elementaryPresenter.initialize();
        }
    }

    @Override
    public void onViewCreated(@android.support.annotation.NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.elementaryPresenter.setView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.elementaryPresenter.destory();
    }
//    private void search(String key) {
//        disposable = Network.getZhuangbiApi()
//                .search(key)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        new Consumer<List<ZhuangbiImageBean>>() {
//                            @Override
//                            public void accept(@NonNull List<ZhuangbiImageBean> images) throws Exception {
//
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(@NonNull Throwable throwable) throws Exception {
//                                swipeRefreshLayout.setRefreshing(false);
//                                Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                );
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elementary, container, false);
        ButterKnife.bind(this, view);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_elementary;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_elementary;
    }

    @Override
    public void renderUserList(Collection<ZhuangbiModel> zhuangbiModelCollection) {
        if (zhuangbiModelCollection!=null){
            swipeRefreshLayout.setRefreshing(false);
            adapter.setImages((List<ZhuangbiModel>) zhuangbiModelCollection);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void viewUZhuangbi(ZhuangbiModel userModel) {

    }
}
