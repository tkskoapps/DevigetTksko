package com.test.dvtest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.test.dvtest.ui.fragments.presenter.BasePresenter;

import butterknife.Unbinder;

public class BaseFragment<Presenter extends BasePresenter> extends Fragment {

    protected Presenter presenter;

    protected Unbinder bkUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {

        super.onResume();

        if (presenter != null) {
            presenter.onResume();
        }

    }

    @Override
    public void onPause() {

        super.onPause();

        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        if (bkUnbinder != null) {
            bkUnbinder.unbind();
        }

        if (presenter != null) {
            presenter.onDestroy();
        }

    }

}
