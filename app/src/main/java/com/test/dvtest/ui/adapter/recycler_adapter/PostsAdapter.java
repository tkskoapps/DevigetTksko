package com.test.dvtest.ui.adapter.recycler_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.dvtest.R;
import com.test.dvtest.ui.adapter.holder.PostViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Object> list;

    public PostsAdapter() {

        this.list = new ArrayList();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_post, parent, false);

        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {

        Object item = list.get(position);

        if (item != null) {

        }

    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }

    public boolean isEmpty() {

        return getItemCount() == 0;
    }

    public void updateList(List list) {

        int count = this.list.size();

        this.list = list;

        this.notifyItemRangeRemoved(0, count);

        this.notifyItemRangeInserted(0, this.list.size());

    }

}