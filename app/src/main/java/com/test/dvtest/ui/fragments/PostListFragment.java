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
import com.test.dvtest.ui.model.PostUIModel;
import com.test.dvtest.util.InfiniteOnScrollListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.test.dvtest.ui.config.AppConstants.POST_PAGE_SIZE;

public class PostListFragment extends BaseFragment<PostListPresenter> implements PostListView, PostsAdapter.IPostAdapterListener {

    @BindView(R.id.view_recycle_view_list)
    RecyclerView recyclerView;

    @BindView(R.id.view_recycle_view_empty_layout)
    View viewEmpty;

    @BindView(R.id.view_recycle_view_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    PostsAdapter adapter;

    private InfiniteOnScrollListener infiniteOnScrollListener;

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

        getList(0);

    }

    private void setRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostsAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                getList(0);
            }
        });

        infiniteOnScrollListener = new InfiniteOnScrollListener(layoutManager, swipeRefreshLayout, null) {

            @Override
            public void onLoadMore(int page) {

                getList(page);

            }

        };

        recyclerView.addOnScrollListener(infiniteOnScrollListener);

    }

    private void getList(int pageNumber) {

        if (adapter == null || infiniteOnScrollListener == null)
            return;

        if (pageNumber == 0) {
            adapter.setLastItemId(null);
            infiniteOnScrollListener.reset();
        } else
            infiniteOnScrollListener.setLoading(true);

        swipeRefreshLayout.setRefreshing(true);

        presenter.getList(pageNumber, adapter.getLastItemId());

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
    public void onSuccessGetPosts(List newPosts, boolean isFirstPage, String lastItemId) {

        infiniteOnScrollListener.setIsLimitReached(newPosts == null
                || newPosts.isEmpty() || newPosts.size() < POST_PAGE_SIZE);

        swipeRefreshLayout.setRefreshing(false);
        infiniteOnScrollListener.setLoading(false);

        adapter.updateList(newPosts, isFirstPage);
        adapter.setLastItemId(lastItemId);

        setEmptyViewVisibility();

    }

    @Override
    public void onPostClick(PostUIModel post) {

    }

}
