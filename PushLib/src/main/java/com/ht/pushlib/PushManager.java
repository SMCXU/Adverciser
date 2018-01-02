package com.ht.pushlib;

import com.ht.baselib.application.HTApplication;
import com.ht.baselib.utils.AppUtils;
import com.ht.baselib.utils.SharedPreferencesUtils;
import com.xiaomi.mipush.sdk.MiPushClient;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/27 17:01
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class PushManager {

    public static final String REGISTER_ID = "register_id";

    public static void init(String appId, String appKey){
        if (AppUtils.isInMainAppProcess()){
            MiPushClient.registerPush(HTApplication.getContext(), appId, appKey);
        }
    }


    public static String getRegisterId(){
        return SharedPreferencesUtils.getString(REGISTER_ID, "");
    }
}