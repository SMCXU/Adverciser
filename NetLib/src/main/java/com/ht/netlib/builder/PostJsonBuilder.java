package com.ht.netlib.builder;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.ht.netlib.constant.MediaType;
import com.ht.netlib.request.RequestCall;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 18:24
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class PostJsonBuilder extends HttpBuilder<PostJsonBuilder>{



    private String mContent;



    public PostJsonBuilder content(String json){
        this.mContent = json;
        return this;
    }

    public PostJsonBuilder content(Object obj){
        this.mContent = JSON.toJSONString(obj);
        return this;
    }

    @Override
    public PostJsonBuilder params(@NonNull Map<String, Serializable> params) {
        this.mParams = params;
        return this;
    }

    @Override
    public PostJsonBuilder addParams(String key, Serializable val) {
        if (this.mParams == null){
            this.mParams = new LinkedHashMap<>();
        }
        this.mParams.put(key, val);
        return this;
    }

    @Override
    public RequestCall build() {
        if (mUrl == null){
            throw new IllegalArgumentException("the request url is not null");
        }

        RequestBody requestBody = RequestBody.create(MediaType.MEDIA_TYPE_JSON, this.mContent);

        Headers.Builder headerBuilder = new Headers.Builder();
        if (mHeaders != null && !mHeaders.isEmpty()){
            for (String key : mHeaders.keySet()) {
                headerBuilder.add(key, mHeaders.get(key));
            }
        }

        Request request = new Request
                .Builder()//
                .url(mUrl)//
                .post(requestBody)
                .headers(headerBuilder.build())//
                .tag(mTag)//
                .build();

        return new RequestCall(request);
    }


}
