package com.test.dvtest.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class PostEntity {

    @JsonProperty("data")
    private PostDataEntity postData;

    public PostEntity() {

        this.postData = new PostDataEntity();
    }

    public PostDataEntity getPostData() {
        return postData;
    }

    public void setPostData(PostDataEntity postData) {
        this.postData = postData;
    }

}
