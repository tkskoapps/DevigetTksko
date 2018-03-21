package com.test.dvtest.ui.fragments.presenter;

import android.support.v4.app.Fragment;

import com.test.dvtest.ui.fragments.view.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<View extends BaseView> implements Presenter {

    protected View view;

    protected Fragment fragment;

    private CompositeSubscription subscriptions;

    public BasePresenter() {

        this.subscriptions = new CompositeSubscription();
    }

    public void setView(View view, Fragment fragment) {

        this.view = view;
        this.fragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

        cancelAllSubscriptions();

        this.view = null;

        this.fragment = null;
    }

    protected void addSubscription(Subscription subscription) {

        subscriptions.add(subscription);
    }

    private void cancelAllSubscriptions() {

        subscriptions.unsubscribe();
    }

}
