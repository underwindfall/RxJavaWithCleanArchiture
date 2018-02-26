package com.practice.qifan.rxjavapractice.mapper;

/**
 * Created by qifan on 2018/2/26.
 * GankResultModel
 */

public class GankResultModel {
    private String id;
    private String createdTime;
    private String date;
    private String publishedTime;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String author;

    GankResultModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDate() {
        return date;
    }

    void setDate(String date) {
        this.date = date;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getSource() {
        return source;
    }

    void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    void setUrl(String url) {
        this.url = url;
    }

    public boolean getUsed() {
        return used;
    }

    void setUsed(boolean used) {
        this.used = used;
    }

    public String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }
}
