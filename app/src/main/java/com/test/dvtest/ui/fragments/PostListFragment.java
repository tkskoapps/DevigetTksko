package com.test.dvtest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.dvtest.R;
import com.test.dvtest.ui.adapter.recycler_adapter.PostsAdapter;
import com.test.dvtest.ui.fragments.presenter.PostListPresenter;
import com.test.dvtest.ui.fragments.view.PostListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostListFragment extends BaseFragment<PostListPresenter> implements PostListView {

    @BindView(R.id.view_recycle_view_list)
    RecyclerView recyclerView;

    @BindView(R.id.view_recycle_view_empty_layout)
    View viewEmpty;

    @BindView(R.id.view_recycle_view_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    PostsAdapter adapter;

    public static PostListFragment newInstance() {

        PostListFragment fragment = new PostListFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        presenter = new PostListPresenter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post_list, container, false);

        bkUnbinder = ButterKnife.bind(this, view);

        setRecyclerView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this, this);

        getList();

    }

    private void setRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostsAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                getList();
            }
        });

    }

    private void getList() {

        if (adapter == null)
            return;

        swipeRefreshLayout.setRefreshing(true);

        presenter.getList();

    }

    private void setEmptyViewVisibility() {

        viewEmpty.setVisibility(adapter.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onError(String message) {

        swipeRefreshLayout.setRefreshing(false);

        setEmptyViewVisibility();

    }

    @Override
    public void onSuccessGetPosts(List newPosts) {

        swipeRefreshLayout.setRefreshing(false);

        adapter.updateList(newPosts);

        setEmptyViewVisibility();

    }

}
