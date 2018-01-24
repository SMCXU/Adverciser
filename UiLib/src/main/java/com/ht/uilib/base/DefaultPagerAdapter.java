package com.ht.uilib.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by huangtao on 16/2/25.
 */
public abstract class DefaultPagerAdapter<T> extends PagerAdapter {
    private final List<T> mDataList;
    protected final Context mContext;

    public DefaultPagerAdapter(Context context, List<T> dataList) {
        this.mDataList = dataList;
        this.mContext = context;
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
