package com.ht.netlib.callback;


import com.ht.netlib.callback.abs.StringResultCallback;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 18:08
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class StringCallback extends StringResultCallback<String> {

    @Override
    protected String parseResponseData(String responseData) {
        return responseData;
    }
}
