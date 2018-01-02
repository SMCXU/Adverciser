package com.ht.uilib.base;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;
import com.ht.uilib.adapter.TabFragmentAdapter;
import com.ht.uilib.widget.indicator.UnderlinePageIndicator;

import java.util.List;


public abstract class BaseTabActivity extends BaseActivity {
    protected LinearLayout mTabTitleLayout;
    private ViewPager mViewPager;
    private UnderlinePageIndicator mIndicator;

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_base_tab;
    }

    @Override
    protected void initChildView() {
        mTabTitleLayout = (LinearLayout) findViewById(R.id.ll_activity_base_tab_title);

        mIndicator = (UnderlinePageIndicator) findViewById(R.id.vpi_activity_base_tab);
        mIndicator.setFades(false);

        mViewPager = (ViewPager) findViewById(R.id.vp_activity_base_tab);
    }

    @Override
    protected void initChildData() {
        final List<String> tabTitleList = getTabTitleList();
        for (int i = 0; i < tabTitleList.size(); i++) {
            TextView tabTitleView = UIUtils.inflate(R.layout.tab_title_textview);
            tabTitleView.setTag(i);
            tabTitleView.setText(tabTitleList.get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            mTabTitleLayout.addView(tabTitleView, params);

            if (i == 0){
                tabTitleView.setSelected(true);
            }

            tabTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!v.isSelected()) {
                        for (int i = 0; i < mTabTitleLayout.getChildCount(); i++) {
                            View tabTileView = mTabTitleLayout.getChildAt(i);
                            tabTileView.setSelected(false);
                        }
                        v.setSelected(true);

                        mViewPager.setCurrentItem((int)v.getTag());
                    }
                }
            });
        }


        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), getFragmentList());
        mViewPager.setAdapter(tabFragmentAdapter);

        mIndicator.setViewPager(mViewPager);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mTabTitleLayout.getChildCount(); i++) {
                    View tabTileView = mTabTitleLayout.getChildAt(i);
                    tabTileView.setSelected(false);
                }

                View tabTitleView = mTabTitleLayout.getChildAt(position);
                if (tabTitleView != null){
                    tabTitleView.setSelected(true);
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

    protected abstract List<String> getTabTitleList();

    protected abstract List<Fragment> getFragmentList();

}