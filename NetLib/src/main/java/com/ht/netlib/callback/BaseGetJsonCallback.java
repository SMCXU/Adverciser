package com.ht.netlib.callback;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.ht.baselib.utils.UIUtils;
import com.ht.netlib.bean.BaseGetBean;
import com.ht.netlib.callback.abs.StringResultCallback;
import com.ht.netlib.utils.NetLogUtils;


/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/3 18:08
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class BaseGetJsonCallback<T> extends StringResultCallback<T> {

    @Override
    protected T parseResponseData(String responseData) {
        NetLogUtils.error(responseData);

        if (TextUtils.isEmpty(responseData)){
            throw new IllegalArgumentException("the server don't have reponse body");
        }

        BaseGetBean baseGetBean = JSON.parseObject(responseData, BaseGetBean.class);

        if (baseGetBean.code != 200){
            UIUtils.showToast(baseGetBean.msg);
            return null;
        }

        if (TextUtils.isEmpty(baseGetBean.data)){
            baseGetBean.data = "{}";
        }

        return parseRealResponseData(baseGetBean.data);
    }

    protected abstract T parseRealResponseData(String realData);
}
