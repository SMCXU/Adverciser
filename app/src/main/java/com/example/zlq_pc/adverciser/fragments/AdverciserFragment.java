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
import android.widget.ListView;

import com.example.zlq_pc.adverciser.R;
import com.example.zlq_pc.adverciser.adapter.BannerAdapter;
import com.example.zlq_pc.adverciser.entity.LunboBean;
import com.example.zlq_pc.adverciser.weight.AutoScrollViewPager;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.base.BaseFragment;
import com.ht.uilib.widget.TitleBarView;
import com.ht.uilib.widget.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;


public class AdverciserFragment extends BaseFragment {


    private View mHeadView;
    private AutoScrollViewPager mVPcontainer;
    private ArrayList<LunboBean> mList;
    private BannerAdapter bannerAdapter;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initView() {
        mHeadView = UIUtils.inflate(R.layout.head_fragment_adverciser);
        mVPcontainer = (AutoScrollViewPager)mHeadView.findViewById(R.id.vp_container);
        mListView = (ListView) initContentView().findViewById(R.id.mPTRListView);
        mVPcontainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mListView.addHeaderView(mHeadView);
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
    protected void load() {
        super.load();
        mList = new ArrayList<>();
        bannerAdapter = new BannerAdapter(getContext(), mList);
        mVPcontainer.setAdapter(bannerAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()==R.id.tv_view_title_bar_back){
            UIUtils.showToast("I hate myself for loving you");
        }
    }
}
