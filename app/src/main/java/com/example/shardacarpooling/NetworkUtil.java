package com.example.shardacarpooling;
import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class NetworkUtil {
    public static String getConnectivityStatusString(Context context) {
        String status = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
            }
        } else {
            status = "No internet is available";
        }
        return status;
    }
}