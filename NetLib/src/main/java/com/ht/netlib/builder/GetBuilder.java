package com.ht.netlib.builder;


import com.ht.netlib.progress.ProgressResponseBody;
import com.ht.netlib.request.RequestCall;
import com.ht.netlib.utils.ParameterUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;


/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 18:24
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class GetBuilder extends HttpBuilder<GetBuilder>{
    private ProgressResponseBody.OnProgressListener mOnProgressListener;

    public GetBuilder(){

    }

    public GetBuilder(ProgressResponseBody.OnProgressListener onProgressListener){
        this.mOnProgressListener = onProgressListener;
    }

    @Override
    public GetBuilder params(Map<String, Serializable> params) {
        this.mParams = params;
        return this;
    }

    @Override
    public GetBuilder addParams(String key, Serializable val) {
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

        //url
        if (mParams!=null && !mParams.isEmpty()){
            Map<String, String> params = new HashMap<>();
            for (Map.Entry<String, Serializable> entry : this.mParams.entrySet()){
                params.put(entry.getKey(), entry.getValue().toString());
            }
            mUrl = ParameterUtils.buildGetParameter(mUrl, params);
        }

        Headers.Builder headerBuilder = new Headers.Builder();
        if (mHeaders != null && !mHeaders.isEmpty()){
            for (String key : mHeaders.keySet()) {
                headerBuilder.add(key, mHeaders.get(key));
            }
        }

        Request request = new Request
                .Builder()//
                .url(mUrl)//
                .headers(headerBuilder.build())//
                .tag(mTag)//
                .build();

        return new RequestCall(request, mOnProgressListener);
    }


}
