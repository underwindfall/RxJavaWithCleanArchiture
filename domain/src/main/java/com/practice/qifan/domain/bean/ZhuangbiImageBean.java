package com.practice.qifan.domain.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qifan on 2018/2/1.
 */

public class ZhuangbiImageBean {
    @SerializedName("description")
    public String description;
    @SerializedName("image_url")
    public String image_url;


    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }
}