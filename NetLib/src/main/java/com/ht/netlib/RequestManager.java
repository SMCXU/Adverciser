package com.ht.netlib;


import com.ht.netlib.builder.GetBuilder;
import com.ht.netlib.builder.PostBuilder;
import com.ht.netlib.builder.PostJsonBuilder;
import com.ht.netlib.progress.ProgressResponseBody;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 17:42
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class RequestManager {

    public static final long DEFAULT_TIMEOUT_MILLISECONDS = 10_000L;

    private static OkHttpClient mOkHttpClient;
    private static RequestManager mInstance;

    static {
//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(DEFAULT_TIMEOUT_MILLISECONDS, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(DEFAULT_TIMEOUT_MILLISECONDS, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(DEFAULT_TIMEOUT_MILLISECONDS, TimeUnit.SECONDS)//设置写入超时时间
//                .sslSocketFactory(sslParams.sSLSocketFactory)
                .build();
    }

    private RequestManager(OkHttpClient okHttpClient){
        if (okHttpClient != null){
            mOkHttpClient = okHttpClient;
        }
    }

    public static RequestManager initOkHttpClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (RequestManager.class) {
                mInstance = new RequestManager(okHttpClient);
            }
        }
        return mInstance;
    }

    public static RequestManager getInstance() {
        return initOkHttpClient(null);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }


    public static GetBuilder get(){
        return new GetBuilder();
    }

    public static GetBuilder download(ProgressResponseBody.OnProgressListener onProgressListener){
        return new GetBuilder(onProgressListener);
    }

    public static PostJsonBuilder postJson(){
        return new PostJsonBuilder();
    }

    public static PostBuilder post(){
        return new PostBuilder();
    }

}
