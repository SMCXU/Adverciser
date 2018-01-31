package com.example.zlq_pc.adverciser.adapter;

import android.content.Context;

import com.example.zlq_pc.adverciser.entity.ArticleBean;
import com.example.zlq_pc.adverciser.holder.ArticleViewHolder;
import com.ht.uilib.base.BaseAdapter;
import com.ht.uilib.base.BaseViewHolder;

import java.util.List;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/16 14:50
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class ArticleAdapter extends BaseAdapter<ArticleBean> {
    public ArticleAdapter(Context context, List<ArticleBean> data) {
        super(context, data);
    }

    @Override
    protected BaseViewHolder<ArticleBean> getViewHolder(int position, List<ArticleBean> mData) {
        return new ArticleViewHolder(mContext);
    }
}
