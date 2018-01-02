package com.ht.uilib.base;

import android.view.View;

import com.ht.baselib.utils.UIUtils;

/**
 * Created by huangtao on 16/9/18.
 */
public abstract class BaseViewHolder<T> {

    protected View mRootView;

    protected T t;

    public BaseViewHolder(View view) {
        this.mRootView = view;
        mRootView.setTag(this);
        initView();
    }

    public BaseViewHolder(int resId) {
        this.mRootView = UIUtils.inflate(resId);
        mRootView.setTag(this);
        initView();
    }

    public void setData(T t){
        this.t = t;
        refreshView(t);
    }

    protected abstract void initView();

    protected abstract void refreshView(T t);

    public View getRootView(){
        return mRootView;
    }

    public <T extends View> T findViewById(int viewId){
        return (T) this.mRootView.findViewById(viewId);
    }
}
