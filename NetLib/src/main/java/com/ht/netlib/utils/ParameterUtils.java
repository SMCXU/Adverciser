package com.ht.netlib.utils;


import com.ht.netlib.constant.Encoding;
import com.ht.netlib.constant.MediaType;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 16/12/2 13:15
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class ParameterUtils {

    public static String buildGetParameter(String url, Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return url;
        }

        StringBuilder urlParams = new StringBuilder(url);
        if (!url.contains("?")){
            urlParams.append("?");
        } else {
            urlParams.append("&");
        }

        for (Map.Entry<String, String> entry : params.entrySet()) {
            Object value = entry.getValue();
            try {
                value = URLEncoder.encode((String) value, Encoding.ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            urlParams.append(entry.getKey()+"="+ value +"&");
        }
        return urlParams.deleteCharAt(urlParams.length()-1).toString();
    }

    public static RequestBody buildNoFilePostParameter(Map<String, Serializable> params) throws IllegalArgumentException {
        if (params == null || params.size() == 0) {
            return null;
        }

        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, Serializable> entry : params.entrySet()) {
            Serializable value = entry.getValue();
            if (value instanceof File){
                throw new IllegalArgumentException("include file type params, please use doUpload method!");
            }
            bodyBuilder.add(entry.getKey(), value.toString());
        }

        return bodyBuilder.build();
    }

    public static MultipartBody buildFilePostParameter(Map<String, Object> params) throws IllegalArgumentException {
        if (params == null || params.size() == 0) {
            return null;
        }

        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof File){
                File file = (File) value;
                bodyBuilder.addFormDataPart(entry.getKey(), file.getName(), RequestBody.create(MediaType.MEDIA_TYPE_STREAM, file));
            } else if (value instanceof List){
                List fileList = (List) value;

                for (int i = 0; i < fileList.size(); i++) {
                    Object object = fileList.get(i);
                    if (object instanceof File){
                        File file = (File) object;
                        bodyBuilder.addFormDataPart(entry.getKey(), file.getName(), RequestBody.create(MediaType.MEDIA_TYPE_STREAM, file));
                    } else {
                        throw new IllegalArgumentException("only file list parameter");
                    }
                }

            } else if (value instanceof File[]){
                File[] files = (File[]) value;

                for (int i = 0; i < files.length; i++) {
                    Object object = files[i];
                    if (object instanceof File){
                        File file = (File) object;
                        bodyBuilder.addFormDataPart(entry.getKey(), file.getName(), RequestBody.create(MediaType.MEDIA_TYPE_STREAM, file));
                    } else {
                        throw new IllegalArgumentException("only file list parameter");
                    }
                }

            }else {
                bodyBuilder.addFormDataPart(entry.getKey(), entry.getValue().toString());
            }
        }

        return bodyBuilder.build();
    }
}
