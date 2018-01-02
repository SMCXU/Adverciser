package com.ht.uilib.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by huangtao on 16/2/25.
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {
    private final List<T> mDataList;
    public BasePagerAdapter(List<T> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(mDataList, position);
        container.addView(view);
        return view;
    }

    protected abstract View getView(List<T> dataList, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
