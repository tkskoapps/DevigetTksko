package com.test.dvtest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.dvtest.R;
import com.test.dvtest.ui.fragments.PostDetailFragment;

public class PostDetailActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context, String postJson) {

        Intent intent = new Intent(context, PostDetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("POST", postJson);
        intent.putExtras(bundle);

        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_detail);

        String postJson = "";

        if (getIntent().hasExtra("POST")) {
            postJson = getIntent().getStringExtra("POST");
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_post_detail_frame_layout, PostDetailFragment.newInstance(postJson))
                .commit();

    }

}
