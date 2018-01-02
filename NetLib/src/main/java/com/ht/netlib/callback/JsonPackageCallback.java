package com.ht.netlib.callback;

import com.alibaba.fastjson.JSON;

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
public abstract class JsonPackageCallback<T> extends BaseGetJsonCallback<T> {

    @Override
    protected T parseRealResponseData(String realData) {
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return JSON.parseObject(realData, type);
    }

}