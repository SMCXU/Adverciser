package com.example.zlq_pc.adverciser.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zlq_pc.adverciser.R;
import com.ht.uilib.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private TextView tvLineLogin,tvLineRegister,tvRegister,tvLogin;
    private LinearLayout llLogin,llRegisterrr;

    @Override
    protected void initTitleBar() {
        mTitleBarView.setTitleText("登录").setTitleViewVisibility(View.VISIBLE);
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
        tvLineLogin =(TextView) findViewById(R.id.tv_line_login);
        tvLineRegister =(TextView) findViewById(R.id.tv_line_registerr);
        tvRegister =(TextView) findViewById(R.id.tv_register);
        tvLogin =(TextView) findViewById(R.id.tv_login);
        llLogin = (LinearLayout) findViewById(R.id.ll_login);
        llRegisterrr = (LinearLayout) findViewById(R.id.ll_register);

    }

    @Override
    protected void initChildData() {

    }

    @Override
    protected void setChildViewListener() {
        llRegisterrr.setOnClickListener(this);
        llLogin.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ll_login:
                tvLineRegister.setBackground(getResources().getDrawable(R.color.white));
                tvLineLogin.setBackground(getResources().getDrawable(R.color.blue_btn_press));
                break;
            case R.id.ll_register:
                tvLineRegister.setBackground(getResources().getDrawable(R.color.blue_btn_press));
                tvLineLogin.setBackground(getResources().getDrawable(R.color.white));
                break;
        }
    }
}
