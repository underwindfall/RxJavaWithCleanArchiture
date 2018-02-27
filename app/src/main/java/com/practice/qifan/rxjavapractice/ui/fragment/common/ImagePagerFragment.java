package com.practice.qifan.rxjavapractice.ui.fragment.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by qifan on 2018/2/27.
 */

public class ImagePagerFragment extends Fragment {
    private static final String KEY_IMAGE_URLS = "com.practice.qifan.rxjavapractice.ui.fragment.common.urls.imageRes";
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private List<String> imageUrls = new ArrayList<>();
    private ImagePagerAdapter adapter;

    public static ImagePagerFragment newInstance(List<String> urls) {
        ImagePagerFragment fragment = new ImagePagerFragment();
        Bundle argument = new Bundle();
        argument.putStringArrayList(KEY_IMAGE_URLS, (ArrayList<String>) urls);
        fragment.setArguments(argument);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewPager = (ViewPager) inflater.inflate(R.layout.fragment_pager, container, false);
        Bundle arguments = getArguments();
        imageUrls = arguments.getStringArrayList(KEY_IMAGE_URLS);
        adapter = new ImagePagerAdapter(this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(MainActivity.currentPosition);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                MainActivity.currentPosition = position;
            }
        });


        prepareSharedElementTransition();

        // Avoid a postponeEnterTransition on orientation change, and postpone only of first creation.
        if (savedInstanceState == null) {
            postponeEnterTransition();
        }

        return mViewPager;
    }

    /**
     * Prepares the shared element transition from and back to the grid fragment.
     */

    private void prepareSharedElementTransition() {
        Transition transition =
                TransitionInflater.from(getContext())
                        .inflateTransition(R.transition.image_shared_element_transition);
        setSharedElementEnterTransition(transition);

        // A similar mapping is set at the GridFragment with a setExitSharedElementCallback.
        setEnterSharedElementCallback(
                new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        // Locate the image view at the primary fragment (the ImageFragment that is currently
                        // visible). To locate the fragment, call instantiateItem with the selection position.
                        // At this stage, the method will simply return the fragment at the position and will
                        // not create a new one.
                        Fragment currentFragment = (Fragment) mViewPager.getAdapter()
                                .instantiateItem(mViewPager, MainActivity.currentPosition);
                        View view = currentFragment.getView();
                        if (view == null) {
                            return;
                        }

                        // Map the first shared element name to the child ImageView.
                        sharedElements.put(names.get(0), view.findViewById(R.id.image));
                    }
                });
    }


    public class ImagePagerAdapter extends FragmentStatePagerAdapter {

        ImagePagerAdapter(Fragment fragment) {
            // Note: Initialize with the child fragment manager.
            super(fragment.getChildFragmentManager());
        }

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public Fragment getItem(int position) {
            return ImageFragment.newInstance(imageUrls.get(position));
        }
    }

}
