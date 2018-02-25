package com.practice.qifan.rxjavapractice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.practice.qifan.domain.bean.ZhuangbiImageBean;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.mapper.ZhuangbiModel;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2018/1/30.
 */

public class ZhuangbiListAdapter extends RecyclerView.Adapter {
    private List<ZhuangbiModel> models;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        ZhuangbiModel model = models.get(position);
        Glide.with(holder.itemView.getContext()).load(model.getImageUrl()).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    public void setImages(List<ZhuangbiModel> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageIv)
        ImageView imageIv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;

        private DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
