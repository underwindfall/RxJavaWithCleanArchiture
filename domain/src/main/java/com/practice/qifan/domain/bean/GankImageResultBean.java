package com.practice.qifan.domain.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qifan on 2018/2/26.
 * GankImageResultBean
 */

public class GankImageResultBean {

    @SerializedName("_id")
    private String mId;

    @SerializedName("createdAt")
    private String mCreatedTime;

    @SerializedName("desc")
    private String mDate;

    @SerializedName("publishedAt")
    private String mPublishedTime;

    @SerializedName("source")
    private String mSource;

    @SerializedName("type")
    private String mType;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("used")
    private boolean mUsed;

    @SerializedName("who")
    private String mAuthor;

    public String getId() {
        return mId;
    }

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public String getDate() {
        return mDate;
    }

    public String getPublishedTime() {
        return mPublishedTime;
    }

    public String getSource() {
        return mSource;
    }

    public String getType() {
        return mType;
    }

    public String getUrl() {
        return mUrl;
    }

    public boolean getUsed() {
        return mUsed;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
