package com.test.dvtest.ui.fragments.presenter;

import com.test.dvtest.ui.fragments.view.PostListView;

import java.util.ArrayList;

public class PostListPresenter extends BasePresenter<PostListView> {

    public void getList() {

        view.onSuccessGetPosts(new ArrayList());

    }

}
