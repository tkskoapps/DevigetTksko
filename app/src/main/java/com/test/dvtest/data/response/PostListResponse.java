package com.test.dvtest.data.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.dvtest.data.model.PostListEntity;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class PostListResponse extends BaseResponse {

    @JsonProperty("data")
    private PostListEntity postList;

    public PostListResponse() {

        this.postList = new PostListEntity();
    }

    public PostListEntity getPostList() {
        return postList;
    }

    public void setPostList(PostListEntity postList) {
        this.postList = postList;
    }

}
