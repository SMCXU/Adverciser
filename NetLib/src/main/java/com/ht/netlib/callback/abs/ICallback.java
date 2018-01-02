package com.ht.netlib.callback.abs;

import okhttp3.Response;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 17:49
 * QQ：876046815
 * Email:876046815@qq.com
 */
public interface ICallback<T> {

    /**
     *
     * @return if true, will skip request
     */
    boolean onPreExecute();

    T handleResponse(Response response) throws Exception;

    void onSuccess(T t);

    void onError(Exception t);

    void onFinishMainThread(boolean isSuccess);
}
