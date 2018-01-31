package com.example.zlq_pc.adverciser.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.zlq_pc.adverciser.R;
import com.example.zlq_pc.adverciser.adapter.ArticleAdapter;
import com.example.zlq_pc.adverciser.adapter.BannerAdapter;
import com.example.zlq_pc.adverciser.constant.ConstantPool;
import com.example.zlq_pc.adverciser.entity.ArticleBean;
import com.example.zlq_pc.adverciser.entity.LunboBean;
import com.example.zlq_pc.adverciser.weight.AutoScrollViewPager;
import com.ht.baselib.utils.AppUtils;
import com.ht.baselib.utils.ChannelUtils;
import com.ht.baselib.utils.DeviceUtils;
import com.ht.baselib.utils.MD5Utils;
import com.ht.baselib.utils.UIUtils;
import com.ht.netlib.RequestManager;
import com.ht.netlib.builder.GetBuilder;
import com.ht.netlib.callback.JsonCallback;
import com.ht.uilib.base.BaseFragment;
import com.ht.uilib.base.BaseRefreshListViewFragment;
import com.ht.uilib.widget.TitleBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdverciserFragment extends BaseRefreshListViewFragment {


    private View mHeadView;
    private ListView mListView;

    private int mLoadTime;
    private boolean mHasMoreData = true;

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
    protected View getHeaderView() {
        mHeadView = UIUtils.inflate(R.layout.head_fragment_adverciser);
        return mHeadView;
    }

    @Override
    protected void initChildDataFromCache() {

    }

    @Override
    protected BaseAdapter getAdapter() {
        return new ArticleAdapter(getContext(), mDataList);
    }

    @Override
    protected void loadData() {
        mLoadTime = 0;
        mHasMoreData = true;
        refreshData(mLoadTime);
    }

    @Override
    protected void onLoadMore() {
        mLoadTime++;
        mHasMoreData = true;
        refreshData(mLoadTime);
    }

    private void refreshData(final int loadTime) {
        Map<String, String> map = new HashMap();
        map.put("device_id",DeviceUtils.getDeviceId());
        map.put("device_channel",ChannelUtils.getChannel());
        map.put("device_type","android");
        map.put("appVersion",DeviceUtils.getAppVersionName());
        RequestManager
                .post()
                .url("http://guwen.haodai.comTopic/home2")
                .addParams("device_id", DeviceUtils.getDeviceId())
                .addParams("device_channel", ChannelUtils.getChannel())
                .addParams("device_type", "android")
                .addParams("appVersion", DeviceUtils.getAppVersionName())
                .addParams("sign",sign(map) )
                .build()
                .execute(new JsonCallback<List<LunboBean>>() {

                    @Override
                    public void onSuccess(List<LunboBean> articleBeenList) {
                        if (loadTime == 0) {
                            if (articleBeenList.size() > 0) {
                                mDataList.clear();
                                mDataList.addAll(articleBeenList);
                                onRefreshCompleteSuccess();
                            } else {
                                onRefreshCompleteEmpty();
                            }
                        } else {
                            if (articleBeenList.size() > 0) {
                                mDataList.addAll(articleBeenList);
                            } else {
                                mHasMoreData = false;
                            }
                        }
                    }

                    @Override
                    public void onFinishMainThread(boolean isSuccess) {
                        if (loadTime == 0) {
                            if (!isSuccess) {
                                onRefreshCompleteError();
                            }
                        } else {
                            onLoadMoreComplete();
                        }
                    }
                });
    }

    public static String sign(Map<String, String> map){
        StringBuilder md5Key = new StringBuilder();
        if (map.size() != 0){
            List<String> keyList = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()){
                keyList.add(entry.getKey());
            }

            Collections.sort(keyList);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < keyList.size(); i++) {
                String key = keyList.get(i);
                sb.append(key).append("=").append(map.get(key)).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            md5Key.append(ConstantPool.SIGN_KEY).append(sb).append(ConstantPool.SIGN_KEY);
        }else {
            md5Key.append(ConstantPool.SIGN_KEY).append(ConstantPool.SIGN_KEY);
        }

        return MD5Utils.encode(md5Key.toString().toUpperCase());
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
        mLoadTime = 0;
        mHasMoreData = true;
        refreshData(mLoadTime);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_view_title_bar_back) {
            UIUtils.showToast("I hate myself for loving you");
        }
    }
}
