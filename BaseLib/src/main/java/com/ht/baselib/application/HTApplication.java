package com.ht.baselib.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by huangtao on 16/1/31.
 */
public abstract class HTApplication extends Application {
    public static boolean IS_DEBUG_MODE = true;

    private static int mMainThreadId = -1;

    private static Context mContext;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        IS_DEBUG_MODE = isDebugMode();
        init();
    }

    protected abstract void init();


    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getMainHandler(){
        return mHandler;
    }

    public static Context getContext(){
        return mContext;
    }

    protected abstract boolean isDebugMode();
}
