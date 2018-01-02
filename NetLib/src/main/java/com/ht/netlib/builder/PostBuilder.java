package com.ht.netlib.builder;

import android.support.annotation.NonNull;

import com.ht.netlib.request.RequestCall;
import com.ht.netlib.utils.NetLogUtils;
import com.ht.netlib.utils.ParameterUtils;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
public class PostBuilder extends HttpBuilder<PostBuilder>{

    private Map<String, Object> mFileMap;

    @Override
    public PostBuilder params(@NonNull Map<String, Serializable> params) {
        this.mParams = params;
        return this;
    }

    @Override
    public PostBuilder addParams(String key, Serializable val) {
        if (this.mParams == null){
            this.mParams = new LinkedHashMap<>();
        }
        this.mParams.put(key, val);
        return this;
    }

    public PostBuilder addFile(String key, File file) {
        if (this.mFileMap == null){
            this.mFileMap = new LinkedHashMap<>();
        }
        this.mFileMap.put(key, file);
        return this;
    }

    public PostBuilder addFiles(String key, File ... files) {

        if (files != null && files.length > 0){
            if (this.mFileMap == null){
                this.mFileMap = new LinkedHashMap<>();
            }
            this.mFileMap.put(key, files);
        } else {
            NetLogUtils.error("the upload file array is empty");
        }

        return this;
    }

    public PostBuilder addFiles(String key, List<File> fileList) {

        if (fileList != null && fileList.size() > 0){
            if (this.mFileMap == null){
                this.mFileMap = new LinkedHashMap<>();
            }
            this.mFileMap.put(key, fileList);
        } else {
            NetLogUtils.error("the upload file array is empty");
        }

        return this;
    }

    @Override
    public RequestCall build() {
        if (mUrl == null){
            throw new IllegalArgumentException("the request url is not null");
        }

        RequestBody requestBody;
        if (this.mFileMap == null || this.mFileMap.isEmpty()){
            requestBody = ParameterUtils.buildNoFilePostParameter(this.mParams);
        } else {
            Map<String, Object> params = new HashMap<>();
            params.putAll(this.mParams);
            params.putAll(this.mFileMap);
            requestBody = ParameterUtils.buildFilePostParameter(params);
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
                .post(requestBody)
                .headers(headerBuilder.build())//
                .tag(mTag)//
                .build();

        return new RequestCall(request);
    }


}
