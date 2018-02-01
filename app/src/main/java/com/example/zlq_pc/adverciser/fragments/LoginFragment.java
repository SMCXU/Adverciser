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

public class LoginFragment extends BaseFragment {

    @Override
    protected View initContentView() {
        return UIUtils.inflate(R.layout.fragment_login);
    }

    @Override
    protected void initChildView() {

    }

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected void setChildViewListener() {

    }
}
