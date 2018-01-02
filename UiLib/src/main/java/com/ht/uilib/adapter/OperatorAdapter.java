package com.ht.uilib.adapter;


import android.content.Context;

import com.ht.uilib.base.BaseAdapter;
import com.ht.uilib.base.BaseViewHolder;
import com.ht.uilib.bean.OperatorBean;
import com.ht.uilib.holder.OperatorViewHolder;

import java.util.List;


/**
 * Created by huangtao on 16/3/29.
 */
public class OperatorAdapter extends BaseAdapter<OperatorBean> {

    public OperatorAdapter(Context context, List<OperatorBean> data) {
        super(context, data);
    }

    @Override
    protected BaseViewHolder<OperatorBean> getViewHolder(int position, List<OperatorBean> mData) {
        return new OperatorViewHolder(position, mData);
    }

}