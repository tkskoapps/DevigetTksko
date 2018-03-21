package com.test.dvtest.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.dvtest.R;
import com.test.dvtest.ui.fragments.presenter.PostListPresenter;
import com.test.dvtest.ui.fragments.view.BaseView;
import com.test.dvtest.ui.model.PostUIModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostDetailFragment extends BaseFragment<PostListPresenter> implements BaseView {

    @BindView(R.id.fragment_post_detail_thumbnail)
    ImageView thumbnailView;

    @BindView(R.id.fragment_post_detail_title)
    TextView titleView;

    private PostUIModel postData;

    public static PostDetailFragment newInstance(String postJson) {

        PostDetailFragment fragment = new PostDetailFragment();

        Bundle args = new Bundle();
        args.putString("POST", postJson);
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        postData = PostUIModel.fromString(getArguments().getString("POST"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);

        bkUnbinder = ButterKnife.bind(this, view);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        loadPostData();

    }

    private void loadPostData() {

        if (postData != null) {

            titleView.setText(postData.getTitle());

            Picasso.get()
                    .load(postData.getThumbnail())
                    .placeholder(R.drawable.empty_placeholder)
                    .into(thumbnailView);

        }

    }

    @Override
    public void onError(String message) {

    }

    @OnClick(R.id.fragment_post_detail_thumbnail)
    public void onThumbnailClick() {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(postData.getUrl())));

    }

}
