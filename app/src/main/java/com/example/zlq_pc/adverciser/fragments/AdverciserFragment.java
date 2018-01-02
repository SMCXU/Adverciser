package com.example.zlq_pc.adverciser.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zlq_pc.adverciser.R;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.base.BaseFragment;
import com.ht.uilib.widget.TitleBarView;


public class AdverciserFragment extends BaseFragment {


    private View mHeadView;
    private ViewPager mVPcontainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initView() {
        mHeadView = UIUtils.inflate(R.layout.head_fragment_adverciser);
        mVPcontainer = (ViewPager)mHeadView.findViewById(R.id.vp_container);

    }

    @Override
    protected View initContentView() {
        return UIUtils.inflate(R.layout.fragment_adverciser);
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
        mTitleBarView.setTitleText("银牌顾问").setLeftBtnText("北京").
                setLeftBtnIcon(R.mipmap.icon_title_bar_location).
                setLeftBtnVisibility(View.VISIBLE).addActions(new TitleBarView.Action(R.mipmap.icon_search) {
            @Override
            public void performAction(View view) {

            }
        });
    }

    @Override
    protected boolean isShowTitleBarView() {
        return true;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()==R.id.tv_view_title_bar_back){
            UIUtils.showToast("I hate myself for loving you");
        }
    }
}
