package com.test.dvtest.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.dvtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_item_post_main_layout)
    public LinearLayout mainLayout;

    @BindView(R.id.list_item_post_thumbnail)
    public ImageView thumbnailView;

    @BindView(R.id.list_item_post_read_status)
    public ImageView statusView;

    @BindView(R.id.list_item_post_title)
    public TextView titleView;

    @BindView(R.id.list_item_post_author)
    public TextView authorView;

    @BindView(R.id.list_item_post_time_ago)
    public TextView timeAgoView;

    @BindView(R.id.list_item_post_comments_count)
    public TextView commentsView;

    public PostViewHolder(View itemView) {

        super(itemView);

        ButterKnife.bind(this, itemView);

    }

}
