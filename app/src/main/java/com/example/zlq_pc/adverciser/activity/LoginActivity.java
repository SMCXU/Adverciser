package com.example.zlq_pc.adverciser.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zlq_pc.adverciser.R;
import com.example.zlq_pc.adverciser.fragments.LoginFragment;
import com.example.zlq_pc.adverciser.fragments.RegisterFragment;
import com.ht.uilib.base.BaseActivity;
import com.ht.uilib.base.BaseTabActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseTabActivity {

    @Override
    protected List<String> getTabTitleList() {
        ArrayList<String> mList = new ArrayList<>();
        mList.add("登录");
        mList.add("注册");
        return mList;
    }

    @Override
    protected List<Fragment> getFragmentList() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new LoginFragment());
        fragments.add(new RegisterFragment());
        return fragments;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarView.setTitleText("登录注册");
    }
}
