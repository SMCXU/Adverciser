package com.ht.netlib.callback;


import com.alibaba.fastjson.JSON;
import com.ht.netlib.callback.abs.StringResultCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 18:08
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class JsonCallback<T> extends StringResultCallback<T> {

    @Override
    protected T parseResponseData(String responseData) {
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return JSON.parseObject(responseData, type);
    }
}
