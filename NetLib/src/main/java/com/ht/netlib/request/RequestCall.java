package com.ht.netlib.request;


import com.ht.baselib.utils.ThreadUtils;
import com.ht.baselib.utils.UIUtils;
import com.ht.netlib.RequestManager;
import com.ht.netlib.callback.abs.ICallback;
import com.ht.netlib.progress.ProgressResponseBody;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 19:17
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class RequestCall {

    private Request mRequest;
    private ProgressResponseBody.OnProgressListener mOnProgressListener;

    public RequestCall(Request request) {
        this.mRequest = request;
    }

    public RequestCall(Request request, ProgressResponseBody.OnProgressListener onProgressListener) {
        this.mRequest = request;
        this.mOnProgressListener = onProgressListener;
    }

    public void execute(final ICallback callback){
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {

                if (callback != null && callback.onPreExecute()){
                    return;
                }

                try {

                    OkHttpClient okHttpClient = RequestManager//
                            .getInstance()//
                            .getOkHttpClient();

                    if (mOnProgressListener != null){
                        okHttpClient = okHttpClient
                                .newBuilder()
                                .addNetworkInterceptor(new Interceptor() {
                                    @Override public Response intercept(Chain chain) throws IOException {
                                        Response originalResponse = chain.proceed(chain.request());
                                        return originalResponse.newBuilder()
                                                .body(new ProgressResponseBody(originalResponse.body(), mOnProgressListener))
                                                .build();
                                    }
                                })
                                .build();
                    }

                    Call call = okHttpClient//
                            .newCall(mRequest);

                    Response response = call//
                            .execute();//

                    if (!response.isSuccessful()){
                        throw new IOException("Unexpected code " + response.code());
                    }

                    if (callback != null){
                        final Object t = callback.handleResponse(response);

                        UIUtils.runOnMainThread(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onSuccess(t);

                                    callback.onFinishMainThread(true);
                                }
                            });
                    }
                } catch (final Exception e) {
                    if (callback != null){
                        UIUtils.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                callback.onError(e);

                                callback.onFinishMainThread(false);
                            }
                        });
                    }
                }

            }
        });
    }


}
