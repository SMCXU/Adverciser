package com.ht.netlib.callback;


import com.ht.baselib.utils.FileUtils;
import com.ht.netlib.callback.abs.AbstractCallback;

import java.io.File;

import okhttp3.Response;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/6 14:45
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class FileCallback extends AbstractCallback<File> {

    private String mDescPath;

    public FileCallback(String descPath){
        this.mDescPath = descPath;
    }

    public FileCallback(String descDir, String descFileName){
        this.mDescPath = new File(descDir, descFileName).getAbsolutePath();
    }

    @Override
    public File handleResponse(Response response) throws Exception {

        File file = FileUtils.bytes2File(response.body().bytes(), mDescPath);

        if (file == null){
            throw new IllegalArgumentException("save file error");
        }
        return file;
    }
}
