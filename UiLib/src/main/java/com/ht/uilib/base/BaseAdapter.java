package com.ht.uilib.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> mData;
	
	protected Context mContext;
	
	public BaseAdapter(Context context, List<T> data) {
		mContext = context;
		mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder<T> viewHolder;
        if (convertView == null){
            viewHolder = getViewHolder(position, mData);
        } else {
            viewHolder = (BaseViewHolder<T>) convertView.getTag();
        }

        viewHolder.setData(mData.get(position));

        return viewHolder.getRootView();
    }

    protected abstract BaseViewHolder<T> getViewHolder(int position, List<T> mData);
}
