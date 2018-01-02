package com.ht.uilib.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ht.uilib.R;
import com.ht.uilib.adapter.GuideFragmentAdapter;
import com.ht.uilib.fragment.GuideFragment;
import com.ht.uilib.widget.indicator.IconPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangtao on 16/7/1.
 */
public abstract class BaseGuideActivity extends BaseActivity {
    public static final String IS_FIRST_OPEN = "is_first_open_v1";

    private ViewPager mViewPager;
    private IconPageIndicator mIndicator;

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected boolean isFulleScreen() {
        return true;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_base_guide;
    }

    @Override
    protected void initChildView() {
        mViewPager = (ViewPager)findViewById(R.id.vp_activity_base_guide);
        mIndicator = (IconPageIndicator) findViewById(R.id.vpi_activity_base_guide);
    }

    @Override
    protected void initChildData() {
        final List<GuideFragment> fragmentList = new ArrayList<>();
        List<Integer> resList = new ArrayList<>();
        addResIds(resList);
        for (int i = 0; i < resList.size(); i++) {
            Integer resId = resList.get(i);
            Bundle bundle = new Bundle();
            GuideFragment guideFragment = new GuideFragment();
            bundle.putInt(GuideFragment.RES_ID, resId);

            bundle.putBoolean(GuideFragment.IS_LAST_FRAGMENT, i == resList.size() - 1);
            guideFragment.setArguments(bundle);
            fragmentList.add(guideFragment);
        }

        mViewPager.setAdapter(new GuideFragmentAdapter(getSupportFragmentManager(), fragmentList, getIconResId()));
        mIndicator.setViewPager(mViewPager);


        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == fragmentList.size() -1){
                    mIndicator.setVisibility(View.GONE);
                } else {
                    mIndicator.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void setChildViewListener() {

    }

    protected abstract void addResIds(List<Integer> resList);

    protected abstract int getIconResId();

    public abstract void onClick();
}