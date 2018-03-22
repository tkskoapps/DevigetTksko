package com.test.dvtest.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.test.dvtest.R;
import com.test.dvtest.ui.fragments.PostDetailFragment;
import com.test.dvtest.ui.fragments.PostListFragment;
import com.test.dvtest.ui.model.PostUIModel;
import com.test.dvtest.util.BaseUtils;

public class MainActivity extends AppCompatActivity {

    private boolean landMode = false;

    PostListFragment listFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FrameLayout detailContainer = findViewById(R.id.activity_main_frame_layout_detail);
        landMode = detailContainer != null;

        if (savedInstanceState == null) {

            listFragment = PostListFragment.newInstance();

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.activity_main_frame_layout_list, listFragment).
                    commit();

        } else
            listFragment = (PostListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "savedListFragment");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "savedListFragment", listFragment);

    }

    public void openPostDetail(PostUIModel post) {

        String postString = BaseUtils.getObjectAsJson(post);

        if (landMode)
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.activity_main_frame_layout_detail, PostDetailFragment.newInstance(postString)).
                    commit();
        else
            startActivity(PostDetailActivity.getCallingIntent(this, postString));

    }

}
