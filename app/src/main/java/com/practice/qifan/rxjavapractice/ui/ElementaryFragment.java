package com.practice.qifan.rxjavapractice.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.practice.qifan.rxjavapractice.BaseFragment;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.adapter.ZhuangbiListAdapter;
import com.practice.qifan.rxjavapractice.dagger.component.ImageComponent;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;
import com.practice.qifan.rxjavapractice.presenter.ElementaryPresenter;
import com.practice.qifan.rxjavapractice.view.ElementaryContract;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.reactivex.annotations.NonNull;

/**
 * Created by qifan on 2018/1/30.
 */

public class ElementaryFragment extends BaseFragment implements ElementaryContract.View {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;
    @Inject
    ElementaryPresenter mElementaryPresenter;

    ZhuangbiListAdapter adapter = new ZhuangbiListAdapter();

    @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
    void onTagChecked(RadioButton searchRb, boolean checked) {
        if (checked) {
            adapter.setImages(null);
            swipeRefreshLayout.setRefreshing(true);
            mElementaryPresenter.setKeyWord(searchRb.getText().toString());
            mElementaryPresenter.showImageList();
        }
    }


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ((MainActivity)getActivity()).getImageComponent().inject(this);
        ImageComponent.Initializer.init(getApplicationComponent(), getActivityModule()).inject(this);
        mElementaryPresenter.subscribe(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mElementaryPresenter.unsubscribe();
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
    public void renderImageList(Collection<ZhuangbiModel> zhuangbiModelCollection) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setImages((List<ZhuangbiModel>) zhuangbiModelCollection);
        Log.d("Fragment", zhuangbiModelCollection.size() + "");
    }
}
