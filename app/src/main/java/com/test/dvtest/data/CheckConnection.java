package com.test.dvtest.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import static com.test.dvtest.ui.config.AppConstants.APP_TAG;

public class CheckConnection {

    private static CheckConnection instance = new CheckConnection();

    static Context context;

    ConnectivityManager connectivityManager;

    boolean connected = false;

    public static CheckConnection getInstance(@NonNull Context ctx) {

        context = ctx.getApplicationContext();

        return instance;
    }

    public boolean isOnline() {

        try {

            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();

            return connected;

        } catch (Exception e) {

            if (e.getMessage() != null) {

                Log.e(APP_TAG, "CheckConnectivity Exception: " + e.getMessage());
            }

        }

        return connected;
    }

}
