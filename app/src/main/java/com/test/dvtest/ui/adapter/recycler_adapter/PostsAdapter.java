package com.test.dvtest.ui.adapter.recycler_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.squareup.picasso.Picasso;
import com.test.dvtest.R;
import com.test.dvtest.ui.adapter.holder.PostViewHolder;
import com.test.dvtest.ui.model.PostUIModel;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<PostUIModel> list;

    private String lastItemId;

    private IPostAdapterListener listener;

    public PostsAdapter(IPostAdapterListener listener) {

        this.list = new ArrayList();
        this.lastItemId = null;
        this.listener = listener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_post, parent, false);

        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {

        final PostUIModel item = list.get(position);

        if (item != null) {

            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!item.isRead()) {
                        item.setRead(true);
                        notifyItemChanged(position);
                    }

                    if (listener != null)
                        listener.onPostClick(item);

                }

            });

            Picasso.get()
                    .load(item.getThumbnail())
                    .placeholder(R.drawable.empty_placeholder)
                    .into(holder.thumbnailView);

            holder.titleView.setText(item.getTitle());

            holder.authorView.setText(item.getAuthor());

            holder.timeAgoView.setText(TimeAgo.using(item.getCreatedDate()));

            holder.commentsView.setText(String.valueOf(item.getCommentsCount()));

            holder.statusView.setVisibility(item.isRead() ? View.INVISIBLE : View.VISIBLE);

            holder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, list.size());

                }
            });

        }

    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }

    public boolean isEmpty() {

        return getItemCount() == 0;
    }

    public void updateList(List list, boolean isFirstPage) {

        if (isFirstPage) {

            int count = this.list.size();

            this.lastItemId = null;
            this.list = list;

            this.notifyItemRangeRemoved(0, count);

            this.notifyItemRangeInserted(0, this.list.size());

        } else {

            int positionStart = this.list.size();

            this.list.addAll(list);

            this.notifyItemRangeInserted(positionStart, list.size());

        }

    }

    public void clearAllPosts() {

        int count = list.size();

        list.clear();

        notifyItemRangeRemoved(0, count);

    }

    public String getLastItemId() {
        return lastItemId;
    }

    public void setLastItemId(String lastItemId) {
        this.lastItemId = lastItemId;
    }

    public interface IPostAdapterListener {

        void onPostClick(PostUIModel post);

    }

}