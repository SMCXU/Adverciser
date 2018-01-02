package com.ht.uilib.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ht.uilib.widget.indicator.IconPagerAdapter;

import java.util.List;

/**
 * Created by huangtao on 16/2/18.
 */
public class GuideFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    private final List<? extends Fragment> mFragmentList;
    private final int mIconResId;

    public GuideFragmentAdapter(FragmentManager fm, List<? extends Fragment> fragmentList, int iconResId) {
        super(fm);
        this.mFragmentList = fragmentList;
        this.mIconResId = iconResId;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getIconResId(int index) {
        return mIconResId;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}