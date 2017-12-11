package com.haodai.topadvisor.application;

import android.util.Log;

import com.ht.baselib.application.HTApplication;
import com.ht.pushlib.PushManager;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/13 14:18
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class AppApplication extends HTApplication {

    @Override
    protected void init() {
        PushManager.init("2882303761517460782", "5821746079782");

        LoggerInterface newLogger = new LoggerInterface() {
            public static final String TAG = "MiPush";

            @Override
            public void setTag(String tag) {
                // ignore
            }
            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }
            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);
    }

    @Override
    protected boolean isDebugMode() {
        return true;
    }
}
