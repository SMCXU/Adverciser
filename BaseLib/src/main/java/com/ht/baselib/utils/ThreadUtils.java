package com.ht.baselib.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 16/12/2 13:01
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class ThreadUtils {
    private static final  ExecutorService mExecutorService = Executors.newCachedThreadPool();

    public static void execute(Runnable command){
        mExecutorService.execute(command);
    }

}
