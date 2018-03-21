package com.test.dvtest.data.resolver;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.test.dvtest.data.ApiService;
import com.test.dvtest.data.CheckConnection;
import com.test.dvtest.data.RestClient;
import com.test.dvtest.data.exception.BackendException;
import com.test.dvtest.data.exception.NoConnectionException;
import com.test.dvtest.data.exception.ServerNotAvailableException;
import com.test.dvtest.data.response.BaseResponse;
import com.test.dvtest.ui.application.App;
import com.test.dvtest.util.BaseUtils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public abstract class BaseBEResolver<T> implements Callback<T> {

    protected ApiService client = null;

    protected Subscriber<? super T> subscriber;

    public BaseBEResolver() {

        this.client = RestClient.INSTANCE.getApiService();
    }

    public Observable<T> makeCall(@Nullable final Map<String, Object> params) {

        return Observable.create(new Observable.OnSubscribe<T>() {

                                     @Override
                                     public void call(final Subscriber<? super T> subscriber) {

                                         BaseBEResolver.this.subscriber = subscriber;

                                         if (isNetworkConnectivityAvailable()) {

                                             getDataFromBackend(params);
                                         } else {

                                             subscriber.onError(new NoConnectionException());
                                         }

                                     }
                                 }
        )
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected abstract void getDataFromBackend(Map<String, Object> params);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        T baseResponse;

        try {

            if (response.isSuccessful()) {

                baseResponse = response.body();

                this.onSuccessResponse(baseResponse);

                subscriber.onNext(baseResponse);

                subscriber.onCompleted();

            } else {

                String jsonResponse = response.errorBody().string();

                BaseResponse errorResponse = BaseUtils.getMapper().readValue(jsonResponse, new TypeReference<BaseResponse>() {
                });

                subscriber.onError(new BackendException(errorResponse));

            }

        } catch (Exception e) {

            int code = response.code();

            if (code >= 500) {

                e = new ServerNotAvailableException();
            }

            subscriber.onError(e);

        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        subscriber.onError(t);
    }

    protected void onSuccessResponse(T baseResponse) {
    }

    private boolean isNetworkConnectivityAvailable() {

        return CheckConnection.getInstance(App.applicationContext).isOnline();
    }

}
