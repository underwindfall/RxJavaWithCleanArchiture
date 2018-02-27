package com.practice.qifan.rxjavapractice.ui.fragment.common;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.practice.qifan.rxjavapractice.R;

import butterknife.BindView;

/**
 * Created by qifan on 2018/2/27.
 */

public class ImageFragment extends Fragment {
    private static final String KEY_IMAGE_RES = "com.practice.qifan.rxjavapractice.ui.fragment.common.key.imageRes";

    @BindView(R.id.image)
    ImageView imageView;

    public static ImageFragment newInstance(String url) {
        ImageFragment fragment = new ImageFragment();
        Bundle argument = new Bundle();
        argument.putString(KEY_IMAGE_RES, url);
        fragment.setArguments(argument);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_image, container, false);
        Bundle arguments = getArguments();
        String imageUrl = arguments.getString(KEY_IMAGE_RES);
        // Just like we do when binding views at the grid, we set the transition name to be the string
        // value of the image res.
        view.findViewById(R.id.image).setTransitionName(imageUrl);
        // Load the image with Glide to prevent OOM error when the image drawables are very large.
        Glide.with(this)
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // The postponeEnterTransition is called on the parent ImagePagerFragment, so the
                        // startPostponedEnterTransition() should also be called on it to get the transition
                        // going in case of a failure.
                        getParentFragment().startPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // The postponeEnterTransition is called on the parent ImagePagerFragment, so the
                        // startPostponedEnterTransition() should also be called on it to get the transition
                        // going when the image is ready.
                        getParentFragment().startPostponedEnterTransition();
                        return false;
                    }
                })
                .into((ImageView) view.findViewById(R.id.image));
        return view;
    }
}
