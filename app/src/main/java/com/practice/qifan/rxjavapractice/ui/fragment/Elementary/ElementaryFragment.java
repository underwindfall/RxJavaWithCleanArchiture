package com.practice.qifan.rxjavapractice.ui.fragment.Elementary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.practice.qifan.rxjavapractice.BaseFragment;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.adapter.ZhuangbiListAdapter;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;
import com.practice.qifan.rxjavapractice.presenter.ElementaryPresenter;
import com.practice.qifan.rxjavapractice.ui.activity.MainActivity;
import com.practice.qifan.rxjavapractice.view.ElementaryContract;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    ZhuangbiListAdapter adapter;

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
        adapter = new ZhuangbiListAdapter(this);
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        prepareTransitions();
        postponeEnterTransition();
        return view;
    }

    /**
     * Prepares the shared element transition to the pager fragment, as well as the other transitions
     * that affect the flow.
     */
    private void prepareTransitions() {
        setExitTransition(TransitionInflater.from(getActivity())
                .inflateTransition(R.transition.grid_exit_transition));

        //A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
        setExitSharedElementCallback(
                new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        super.onMapSharedElements(names, sharedElements);
                        //Locate the viewHolder for the clicked position
                        RecyclerView.ViewHolder selectedViewHolder = gridRv.findViewHolderForAdapterPosition(MainActivity.currentPosition);
                        if (selectedViewHolder == null || selectedViewHolder.itemView == null) {
                            return;
                        }
                        // Map the first shared element name to the child ImageView.
                        sharedElements
                                .put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.imageIv));

                    }
                });
    }

    @Override
    public void onViewCreated(@android.support.annotation.NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollToPosition();
    }

    /**
     * Scrolls the recycler view to show the last viewed item in the grid. This is important when
     * navigating back from the grid.
     */
    private void scrollToPosition() {
        gridRv.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left,
                                       int top,
                                       int right,
                                       int bottom,
                                       int oldLeft,
                                       int oldTop,
                                       int oldRight,
                                       int oldBottom) {
                gridRv.removeOnLayoutChangeListener(this);
                final RecyclerView.LayoutManager layoutManager = gridRv.getLayoutManager();
                View viewAtPosition = layoutManager.findViewByPosition(MainActivity.currentPosition);
                // Scroll to position if the view for the current position is null (not currently part of
                // layout manager children), or it's not completely visible.
                if (viewAtPosition == null || layoutManager.isViewPartiallyVisible(viewAtPosition, false, true)) {
                    gridRv.post(() -> layoutManager.scrollToPosition(MainActivity.currentPosition));
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getImageComponent().inject(this);
//        ImageComponent.Initializer.init(getApplicationComponent(), getActivityModule()).inject(this);
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
    }

    public ZhuangbiListAdapter getAdapter() {
        return adapter;
    }
}
