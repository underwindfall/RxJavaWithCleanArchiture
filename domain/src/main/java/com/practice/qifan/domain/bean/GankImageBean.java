package com.practice.qifan.domain.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by qifan on 2018/2/26.
 * GankImageBean
 */

public class GankImageBean {

    @SerializedName("error")
    private boolean mError;

    @SerializedName("results")
    private ArrayList<GankImageResultBean> mGankImageResultBean;

    public GankImageBean() {
    }

    public boolean getError() {
        return mError;
    }

    public ArrayList<GankImageResultBean> getGankImageResultBean() {
        return mGankImageResultBean;
    }
}
