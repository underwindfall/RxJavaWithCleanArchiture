package com.practice.qifan.rxjavapractice.ui.fragment.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.practice.qifan.rxjavapractice.BaseFragment;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.adapter.ItemListAdapter;
import com.practice.qifan.rxjavapractice.mapper.GankResultModel;
import com.practice.qifan.rxjavapractice.presenter.MapPresenter;
import com.practice.qifan.rxjavapractice.ui.activity.MainActivity;
import com.practice.qifan.rxjavapractice.view.MapContract;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qifan on 2018/2/26.
 */

public class MapFragment extends BaseFragment implements MapContract.View {
    @BindView(R.id.pageTv)
    TextView pageTv;
    @BindView(R.id.previousPageBt)
    Button previousPageBt;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;
    ItemListAdapter adapter = new ItemListAdapter();
    @Inject
    MapPresenter mMapPresenter;
    private int page = 0;

    @OnClick(R.id.previousPageBt)
    void previousPage() {
        loadPage(--page);
        if (page == 1) {
            previousPageBt.setEnabled(false);
        }
    }


    @OnClick(R.id.nextPageBt)
    void nextPage() {
        loadPage(++page);
        if (page == 2) {
            previousPageBt.setEnabled(true);
        }
    }

    private void loadPage(int page) {
        swipeRefreshLayout.setRefreshing(true);
        mMapPresenter.setKeyWord(10, page);
        mMapPresenter.showImageList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);
        gridRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ImageComponent.Initializer.init(getApplicationComponent(), getActivityModule()).inject(this);
        ((MainActivity) getActivity()).getImageComponent().inject(this);

        mMapPresenter.subscribe(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapPresenter.unsubscribe();
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_map;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_map;
    }

    @Override
    public void renderImageList(Collection<GankResultModel> gankResultModelCollection) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setItems((List<GankResultModel>) gankResultModelCollection);
    }
}
