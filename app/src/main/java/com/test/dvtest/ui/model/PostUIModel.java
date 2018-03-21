package com.test.dvtest.ui.model;

import com.test.dvtest.data.model.PostDataEntity;
import com.test.dvtest.util.BaseUtils;

import java.io.IOException;

public class PostUIModel {

    private String id;

    private String title;

    private String author;

    private String thumbnail;

    private String url;

    private long createdDate;

    private long commentsCount;

    private boolean read;

    public PostUIModel() {

        this.id = "";
        this.title = "";
        this.author = "";
        this.thumbnail = "";
        this.url = "";
        this.createdDate = 0;
        this.commentsCount = 0;
        this.read = false;
    }

    public PostUIModel(PostDataEntity entity) {

        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.thumbnail = entity.getThumbnail();
        this.url = entity.getUrl();
        this.createdDate = entity.getCreatedDate() * 1000;
        this.commentsCount = entity.getCommentsCount();
        this.read = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public static PostUIModel fromString(String json) {

        try {

            return BaseUtils.getMapper().readValue(json, PostUIModel.class);

        } catch (IOException e) {
            return null;
        }

    }
}
