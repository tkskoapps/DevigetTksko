package com.test.dvtest.data;

import com.test.dvtest.BuildConfig;
import com.test.dvtest.util.BaseUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public enum RestClient {

    INSTANCE;

    private ApiService apiService;

    private OkHttpClient okHttpClient = new OkHttpClient();

    public ApiService getApiService() {

        if (apiService == null) {

            synchronized (INSTANCE) {
                if (apiService == null)
                    init();
            }
        }

        return apiService;

    }

    private void init() {

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(configureHttpClient())
                .addConverterFactory(JacksonConverterFactory.create(BaseUtils.getMapper()))
                .build();

        apiService = restAdapter.create(ApiService.class);

    }

    private OkHttpClient configureHttpClient() {

        okHttpClient = okHttpClient.newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        return okHttpClient;

    }

}
