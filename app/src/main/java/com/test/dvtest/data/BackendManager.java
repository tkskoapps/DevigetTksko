package com.test.dvtest.data;

import com.test.dvtest.data.resolver.GetPostsBEResolver;
import com.test.dvtest.data.response.PostListResponse;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

public class BackendManager {

    private static BackendManager instance;

    public static BackendManager getInstance() {

        if (instance == null)
            instance = new BackendManager();

        return instance;

    }

    public BackendManager() {
    }

    public Observable<PostListResponse> getPosts(int pageNumber, String lastItemId) {

        GetPostsBEResolver backendResolver = new GetPostsBEResolver();

        Map<String, Object> params = new HashMap<>();
        params.put("pageNumber", pageNumber);
        params.put("after", lastItemId);

        return backendResolver.makeCall(params);

    }

}
