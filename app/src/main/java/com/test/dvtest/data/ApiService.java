package com.test.dvtest.data;

import com.test.dvtest.data.response.PostListResponse;
import com.test.dvtest.ui.config.AppConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(AppConstants.EndPoint.GET_TOP_POSTS)
    Call<PostListResponse> getPosts(
            @Query("after") String after,
            @Query("t") String type,
            @Query("count") String count,
            @Query("limit") String limit
    );

}
