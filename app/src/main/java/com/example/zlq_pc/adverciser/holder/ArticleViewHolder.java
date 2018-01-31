package com.example.zlq_pc.adverciser.holder;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zlq_pc.adverciser.R;
import com.example.zlq_pc.adverciser.entity.ArticleBean;
import com.ht.imglib.ImgManager;
import com.ht.uilib.base.BaseViewHolder;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/16 14:55
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class ArticleViewHolder extends BaseViewHolder<ArticleBean> {
    private Context mContext;
    private ImageView mLogoView;
    private TextView mTitleView;

    public ArticleViewHolder(Context context) {
        super(R.layout.list_article_item);
        this.mContext = context;
    }

    @Override
    protected void initView() {
        mLogoView = findViewById(R.id.iv_list_artcle_item_logo);
        mTitleView = findViewById(R.id.iv_list_artcle_item_title);
    }

    @Override
    protected void refreshView(ArticleBean articleBean) {
        ImgManager.display(mContext, articleBean.imgurl, mLogoView);
        mTitleView.setText(articleBean.title);
    }
}
