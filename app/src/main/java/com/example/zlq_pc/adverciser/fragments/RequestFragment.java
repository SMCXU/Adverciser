package com.example.zlq_pc.adverciser.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zlq_pc.adverciser.R;
import com.example.zlq_pc.adverciser.activity.LoginActivity;
import com.example.zlq_pc.adverciser.activity.ReleaseActivity;
import com.example.zlq_pc.adverciser.utils.KEYS;
import com.ht.baselib.utils.ActivityUtils;
import com.ht.baselib.utils.AppUtils;
import com.ht.baselib.utils.DeviceUtils;
import com.ht.baselib.utils.SharedPreferencesUtils;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.base.BaseFragment;


public class RequestFragment extends BaseFragment {

    private TextView btRequest;

    @Override
    protected View initContentView() {
        View view = UIUtils.inflate(R.layout.fragment_request);
        btRequest = (TextView)view.findViewById(R.id.bt_Request);
        btRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SharedPreferencesUtils.getBool(KEYS.KEY_ISLOGIN,false)){
                    UIUtils.showToast("请先登录");
                    ActivityUtils.startActivityFromBottom(getActivity(),new Intent(getContext(), LoginActivity.class));
                    return;
                }
                ActivityUtils.startActivityAndFinishByAlpha(getActivity(),new Intent(getContext(), ReleaseActivity.class));
            }
        });
        return view;
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
