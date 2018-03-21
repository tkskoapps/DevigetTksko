package com.test.dvtest.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class PostListEntity {

    @JsonProperty("after")
    private String after;

    @JsonProperty("before")
    private String before;

    @JsonProperty("children")
    private List<PostEntity> posts;

    public PostListEntity() {

        this.posts = new ArrayList<>();
        this.after = "";
        this.before = "";
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }
}
