package com.ht.uilib.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by huangtao on 16/2/18.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {
    private final List<? extends Fragment> mFragmentList;

    public TabFragmentAdapter(FragmentManager fm, List<? extends Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}