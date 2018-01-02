package com.ht.uilib.base;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ht.uilib.R;
import com.ht.uilib.adapter.HomePagerAdapter;
import com.ht.uilib.utils.ViewUtils;
import com.ht.uilib.widget.XViewPager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄 on 17/3/15 14:34
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class BaseHomeActivity extends BaseActivity {

    private XViewPager mViewPager;
    private HomePagerAdapter mFragmentAdapter;
    private List<Fragment> mFragmentList;
    private RadioGroup mHomeTabGroupView;

    @Override
    protected void initUpdate() {
        super.initUpdate();
    }

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        mTitleBarView.setVisibility(View.GONE);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_base_home;
    }

    @Override
    protected void initChildView() {
        mViewPager = (XViewPager) findViewById(R.id.vp_activity_home_content);
        mViewPager.setEnableScroll(isViewPageEnableScroll());

        mFragmentList = new ArrayList<>();
        mHomeTabGroupView = (RadioGroup) findViewById(R.id.rg_home_tab);
    }

    @Override
    protected void initChildData() {

        addHomeFragment(mFragmentList);

        Map<String, Integer> homeTabInfoMap = new LinkedHashMap<>();
        addHomeTabInfo(homeTabInfoMap);

        if (mFragmentList.size() != homeTabInfoMap.size()){
            throw new RuntimeException("the fragment size is not equal home tab size");
        }

        mFragmentAdapter = new HomePagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);

        int tabIndex = 0;
        for (Map.Entry<String, Integer> entry : homeTabInfoMap.entrySet()) {
            RadioButton homeTabView = ViewUtils.createHomeTabView(entry.getKey(), entry.getValue(), homeTabInfoMap.size());
            homeTabView.setId(tabIndex);
            if (tabIndex == 0){
                homeTabView.setChecked(true);
            }
            mHomeTabGroupView.addView(homeTabView);
            tabIndex++;
        }

    }

    protected abstract void addHomeFragment(List<Fragment> fragmentList);

    protected abstract void addHomeTabInfo(Map<String, Integer> homeTabInfoMap);

    @Override
    protected void setChildViewListener() {
        mHomeTabGroupView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mViewPager.setCurrentItem(checkedId, false);
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton homeTabView = (RadioButton) mHomeTabGroupView.getChildAt(position);
                homeTabView.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected boolean isViewPageEnableScroll() {
        return false;
    }
}
