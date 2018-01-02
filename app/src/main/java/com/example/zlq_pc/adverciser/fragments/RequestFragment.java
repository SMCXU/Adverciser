package com.example.zlq_pc.adverciser.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zlq_pc.adverciser.R;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.base.BaseFragment;


public class RequestFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected View initContentView() {
        return UIUtils.inflate(R.layout.fragment_request);
    }

    @Override
    protected void initChildView() {

    }

    @Override
    protected void setChildViewListener() {

    }

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarView.setTitleText("发需求").setLeftBtnVisibility(View.GONE);
    }
    @Override
    protected boolean isShowTitleBarView() {
        return true;
    }
}
