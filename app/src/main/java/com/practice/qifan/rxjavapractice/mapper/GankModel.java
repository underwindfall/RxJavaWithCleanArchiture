package com.practice.qifan.rxjavapractice.mapper;

import java.util.ArrayList;

/**
 * Created by qifan on 2018/2/26.
 * GankModel
 */

public class GankModel {

    private boolean mError;
    private ArrayList<GankResultModel> mGankResultModels;

    GankModel() {
    }

    public boolean getError() {
        return mError;
    }

    void setError(boolean mError) {
        this.mError = mError;
    }

    public ArrayList<GankResultModel> getGankResultModels() {
        return mGankResultModels;
    }

    void setGankResultModels(ArrayList<GankResultModel> mGankResultModels) {
        this.mGankResultModels = mGankResultModels;
    }
}
