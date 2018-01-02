package com.ht.netlib.callback.abs;


import com.ht.netlib.utils.NetLogUtils;

import okhttp3.Response;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/4 15:27
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class StringResultCallback<T> extends AbstractCallback<T>{

    @Override
    public T handleResponse(Response response) throws Exception {
        NetLogUtils.error(response);
        String responseData = response.body().string();
        NetLogUtils.error(responseData);
        return parseResponseData(responseData);
    }

    protected abstract T parseResponseData(String responseData) ;

}
