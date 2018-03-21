package com.test.dvtest.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.dvtest.R;
import com.test.dvtest.ui.fragments.PostListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_frame_layout, PostListFragment.newInstance())
                .commit();

    }

}
