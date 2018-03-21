package com.test.dvtest.ui.fragments.presenter;

import com.test.dvtest.data.BackendManager;
import com.test.dvtest.data.model.PostEntity;
import com.test.dvtest.data.response.PostListResponse;
import com.test.dvtest.ui.fragments.view.PostListView;
import com.test.dvtest.ui.model.PostUIModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class PostListPresenter extends BasePresenter<PostListView> {

    public void getList(final int pageNumber, String lastItemId) {

        addSubscription(BackendManager.getInstance().getPosts(pageNumber, lastItemId).subscribe(new Subscriber<PostListResponse>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                view.onError(e.getMessage());

            }

            @Override
            public void onNext(PostListResponse postListResponse) {

                List<PostUIModel> posts = new ArrayList<>();

                for (PostEntity postEntity : postListResponse.getPostList().getPosts()) {

                    posts.add(new PostUIModel(postEntity.getPostData()));

                }

                view.onSuccessGetPosts(posts, pageNumber == 0, postListResponse.getPostList().getAfter());

            }

        }));

    }

}
