package com.test.dvtest.ui.application;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static Context applicationContext;

    @Override
    public void onCreate() {

        super.onCreate();

        applicationContext = this;

    }

}
