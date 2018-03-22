package com.test.dvtest.util;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class InfiniteOnScrollListener extends RecyclerView.OnScrollListener {

    // true if we are still waiting for the last set of data to load.
    private boolean loading = false;

    // the current page being displayed/loaded
    private int current_page = 0;

    private LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View mProgress;

    // if there is no more data to load, we stop fetching from the server
    private boolean isLimitReached = false;

    public InfiniteOnScrollListener(LinearLayoutManager linearLayoutManager,
                                    SwipeRefreshLayout swipeRefreshLayout,
                                    View progress) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.mProgress = progress;

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (mLinearLayoutManager == null)
            return;

        // first and last visible items of the list
        int firstVisibleItem = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
        int lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();

        if (swipeRefreshLayout != null) {

            swipeRefreshLayout.setEnabled(
                    firstVisibleItem == 0 &&
                            (mProgress == null || mProgress.getVisibility() == View.GONE));
        }

        if (isLimitReached) return;

        // when the user reaches the bottom of the list, we fetch more data
        if (!loading && lastVisibleItem == recyclerView.getAdapter().getItemCount() - 1) {

            // load next page
            current_page++;

            onLoadMore(current_page);
        }

    }

    /**
     * Start loading from the first page again.
     */
    public void reset() {
        this.loading = true;
        this.current_page = 0;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public boolean isLimitReached() {
        return isLimitReached;
    }

    public void setIsLimitReached(boolean isLimitReached) {
        this.isLimitReached = isLimitReached;
    }

    public abstract void onLoadMore(int current_page);

    public int getCurrent_page() {
        return current_page;
    }
}