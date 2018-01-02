package com.ht.baselib.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.ht.baselib.application.HTApplication;

import java.util.List;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/27 16:53
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class AppUtils {

    public static boolean isInMainAppProcess(){
        ActivityManager am = ((ActivityManager) HTApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = HTApplication.getContext().getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

}
