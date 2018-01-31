package com.example.zlq_pc.adverciser.activity;

import android.os.Bundle;
import android.view.View;

import com.example.zlq_pc.adverciser.R;
import com.ht.uilib.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initTitleBar() {
        mTitleBarView.setTitleText("登陆");
    }

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initChildView() {

    }

    @Override
    protected void initChildData() {

    }

    @Override
    protected void setChildViewListener() {

    }
}
