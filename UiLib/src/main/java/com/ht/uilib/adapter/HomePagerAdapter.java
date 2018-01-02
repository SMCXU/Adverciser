package com.ht.uilib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/14 14:44
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class HomePagerAdapter extends FragmentPagerAdapter{

    private List<? extends Fragment> mDataList;

    public HomePagerAdapter(FragmentManager fm, List<? extends Fragment> dataList) {
        super(fm);
        this.mDataList = dataList;
    }

    @Override
    public Fragment getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public int getCount() {
        return mDataList == null?0:mDataList.size();
    }
}
