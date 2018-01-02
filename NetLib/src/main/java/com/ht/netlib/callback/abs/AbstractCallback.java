package com.ht.netlib.callback.abs;


import com.ht.baselib.application.HTApplication;
import com.ht.baselib.utils.UIUtils;
import com.ht.netlib.utils.NetLogUtils;

import okhttp3.Response;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 18:04
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class AbstractCallback<T> implements ICallback<T> {

    @Override
    public boolean onPreExecute() {
        return false;
    }

    public abstract T handleResponse(Response response) throws Exception;

    @Override
    public void onError(Exception t) {
        String msg = "服务器忙, 请稍后重试";
        if (HTApplication.IS_DEBUG_MODE){
            msg+= " ("+t.getMessage()+' '+t.getClass().getSimpleName()+")";
        }
        NetLogUtils.error(msg);
        UIUtils.showToast(msg);
    }

    @Override
    public void onFinishMainThread(boolean isSuccess) {

    }

}
