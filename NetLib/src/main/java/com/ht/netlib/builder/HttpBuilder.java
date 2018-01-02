package com.ht.netlib.builder;

import android.support.annotation.NonNull;

import com.ht.netlib.request.RequestCall;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 18:27
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class HttpBuilder <T extends HttpBuilder>{

    protected String mUrl;
    protected Object mTag;
    protected Map<String, String> mHeaders;
    protected Map<String, Serializable> mParams;


    public T headers(@NonNull Map<String, String> headers){
        this.mHeaders = headers;
        return (T) this;
    }

    public T addHeader(String key, String val){
        if (this.mHeaders == null){
            this.mHeaders = new LinkedHashMap<>();
        }
        this.mHeaders.put(key, val);
        return (T) this;
    }

    public abstract T params(@NonNull Map<String, Serializable> params);
    public abstract T addParams(String key, Serializable val);

    public abstract RequestCall build();

    public T url(String url){
        this.mUrl = url;
        return (T) this;
    }

}
