package com.test.dvtest.data.resolver;

import com.test.dvtest.data.response.PostListResponse;

import java.util.Map;

import static com.test.dvtest.ui.config.AppConstants.POST_PAGE_SIZE;

public class GetPostsBEResolver extends BaseBEResolver<PostListResponse> {

    @Override
    protected void getDataFromBackend(Map<String, Object> params) {

        int pageNumber = (int) params.get("pageNumber");

        String after = (String) params.get("after");
        if (after != null && after.length() == 0)
            after = null;

        client.getPosts(
                after,
                "day",
                String.valueOf(pageNumber * POST_PAGE_SIZE),
                String.valueOf(POST_PAGE_SIZE)
        ).enqueue(this);

    }

}
