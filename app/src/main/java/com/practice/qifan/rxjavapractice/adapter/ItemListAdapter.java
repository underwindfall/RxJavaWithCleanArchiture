package com.practice.qifan.rxjavapractice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.practice.qifan.rxjavapractice.R;
import com.practice.qifan.rxjavapractice.mapper.GankResultModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2018/2/26.
 */

public class ItemListAdapter extends RecyclerView.Adapter {
    List<GankResultModel> gankResultModels;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        GankResultModel model = gankResultModels.get(position);
        Glide.with(holder.itemView.getContext()).load(model.getUrl()).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(model.getCreatedTime());
    }

    @Override
    public int getItemCount() {
        return gankResultModels == null ? 0 : gankResultModels.size();
    }

    public void setItems(List<GankResultModel> gankResultModels) {
        this.gankResultModels = gankResultModels;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageIv)
        ImageView imageIv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;

        DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}