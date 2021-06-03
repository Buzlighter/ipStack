package com.example.ipstack.data.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class CheckNetworkConnection {
    private Context context;

    public CheckNetworkConnection(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void registerNetworkCallback() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    ConnectionState.isNetworkConnected = true;
                }
                @Override
                public void onLost(Network network) {
                    ConnectionState.isNetworkConnected = false;
                }
            });
        }
        catch (Exception e) {
            ConnectionState.isNetworkConnected = false;
        }
    }

}