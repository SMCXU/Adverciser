package com.ht.baselib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.ht.baselib.application.HTApplication;


public class NetUtils {
    /**
     * 
     * @describe:是否有活动的网络连接
     * 
     */
    public static final boolean isNetWorkConnection() {
        // 获取连接活动管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) HTApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取链接网络信息
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isAvailable());
    }

    public static final void openNetworkSettingView() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        HTApplication.getContext().startActivity(intent);
    }


    /**
     * @describe:是否为wifi网络
     */
    public static final boolean isWifiConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        // 是否有网络并且已经连接
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());

    }

    /**
     * @describe:是否为移动网络
     */
    public static final boolean isGPRSConnection(Context context) {
        // 获取活动连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (networkInfo != null && networkInfo.isAvailable());

    }

    /**
     * @describe:返回网络类型
     */
    public static final int getNetWorkConnectionType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiNetworkInfo != null && wifiNetworkInfo.isAvailable()) {
            return ConnectivityManager.TYPE_WIFI;
        } else if (mobileNetworkInfo != null && mobileNetworkInfo.isAvailable()) {
            return ConnectivityManager.TYPE_MOBILE;
        } else {
            return -1;
        }
    }
}
