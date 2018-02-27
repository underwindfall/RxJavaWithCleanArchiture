package com.practice.qifan.rxjavapractice.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;
import com.practice.qifan.rxjavapractice.ui.activity.MainActivity;
import com.practice.qifan.rxjavapractice.ui.fragment.Elementary.ElementaryFragment;
import com.practice.qifan.rxjavapractice.ui.fragment.common.ImagePagerFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2018/1/30.
 * <p>
 * A fragment for displaying a grid of images.
 */

public class ZhuangbiListAdapter extends RecyclerView.Adapter {
    private final RequestManager requestManager;
    private List<ZhuangbiModel> models;
    private ViewHolderListener listener;

    public ZhuangbiListAdapter(Fragment fragment) {
        this.requestManager = Glide.with(fragment);
        this.listener = new ViewHolderListenerImpl(fragment);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view, requestManager, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        final ZhuangbiModel model = models.get(position);
        debounceViewHolder.bind(model);
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    public void setImages(List<ZhuangbiModel> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    List<String> getUrls() {
        List<String> urls = new ArrayList<>();
        for (ZhuangbiModel model : models) {
            urls.add(model.getImageUrl());
        }
        return urls;
    }

    public interface ViewHolderListener {
        void onItemClick(View view, int adapterPosition);

        void onLoadCompleted(ImageView view, int adapterPosition);
    }

    /**
     * Default {@link ViewHolderListener} implementation.
     */

    private static class ViewHolderListenerImpl implements ViewHolderListener {
        private Fragment fragment;
        private AtomicBoolean enterTransitionStarted;
        private List<String> imageUrls = new ArrayList<>();

        ViewHolderListenerImpl(Fragment fragment) {
            this.fragment = fragment;
            this.enterTransitionStarted = new AtomicBoolean();
        }

        @Override
        public void onLoadCompleted(ImageView view, int adapterPosition) {
            // Call startPostponedEnterTransition only when the 'selected' image loading is completed.
            if (MainActivity.currentPosition != adapterPosition) {
                return;
            }
            if (enterTransitionStarted.getAndSet(true)) {
                return;
            }
            fragment.startPostponedEnterTransition();
        }

        /**
         * Handles a view click by setting the current position to the given {@code position} and
         * starting a {@link  com.practice.qifan.rxjavapractice.ui.fragment.common.ImagePagerFragment} which displays the image at the position.
         *
         * @param view            the clicked {@link ImageView} (the shared element view will be re-mapped at the
         *                        GridFragment's SharedElementCallback)
         * @param adapterPosition the selected view position
         */

        @Override
        public void onItemClick(View view, int adapterPosition) {
            //update the position
            MainActivity.currentPosition = adapterPosition;
            // Exclude the clicked card from the exit transition (e.g. the card will disappear immediately
            // instead of fading out with the rest to prevent an overlapping animation of fade and move).
            ImageView transitioningView = view.findViewById(R.id.imageIv);
            imageUrls = ((ElementaryFragment) fragment).getAdapter().getUrls();
            ((TransitionSet) fragment.getExitTransition()).excludeTarget(view, true);
//            Intent intent = new Intent();
//            intent.putStringArrayListExtra(GalleryActivity.IMAGE_URLS, (ArrayList<String>) imageUrls);
//            fragment.getActivity().startActivity(intent);
            fragment.getFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)// Optimize for shared element transition  setAllowOptimization before 26.1.0
                    .addSharedElement(transitioningView, transitioningView.getTransitionName())
                    .replace(R.id.fragment_container, ImagePagerFragment.newInstance(imageUrls), ImagePagerFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        }


    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        private final RequestManager requestManager;
        private final ViewHolderListener viewHolderListener;
        @BindView(R.id.imageIv)
        ImageView imageIv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;


        private DebounceViewHolder(View itemView, RequestManager requestManager, ViewHolderListener viewHolderListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.requestManager = requestManager;
            this.viewHolderListener = viewHolderListener;
            // Let the listener start the ImagePagerFragment
            itemView.setOnClickListener(view -> viewHolderListener.onItemClick(view, getAdapterPosition()));
        }


        void bind(ZhuangbiModel model) {
            // Set the string value of the image resource as the unique transition name for the view.
            imageIv.setTransitionName(model.getImageUrl());
            descriptionTv.setText(model.getDescription());
            requestManager.load(model.getImageUrl())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            viewHolderListener.onLoadCompleted(imageIv, getAdapterPosition());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            viewHolderListener.onLoadCompleted(imageIv, getAdapterPosition());
                            return false;
                        }
                    }).into(imageIv);
        }
    }
}
