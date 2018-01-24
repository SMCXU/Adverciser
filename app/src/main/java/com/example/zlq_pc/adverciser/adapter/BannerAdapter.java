package com.example.zlq_pc.adverciser.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.zlq_pc.adverciser.R;
import com.example.zlq_pc.adverciser.constant.ConstantPool;
import com.example.zlq_pc.adverciser.entity.LunboBean;
import com.ht.baselib.utils.ActivityUtils;
import com.ht.uilib.base.DefaultPagerAdapter;

import java.util.List;

/**
 * Created by huangtao on 16/3/1.
 */
public class BannerAdapter extends DefaultPagerAdapter<LunboBean> {

    public BannerAdapter(Context context, List<LunboBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected View getView(List<LunboBean> dataList, final int position) {
        final LunboBean lunboBean = dataList.get(position);

        View view = View.inflate(mContext, R.layout.pager_home_top_pic_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tv_pager_home_top_pic_item_bg);
        TextView descView = (TextView) view.findViewById(R.id.tv_pager_home_top_pic_item_desc);
        TextView nameView = (TextView) view.findViewById(R.id.tv_pager_home_top_pic_item_name_and_job);
        // 设置图片点击监听
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lunboBean.lunbo_type == 0) {
                    Bundle bundle = new Bundle();
//                    bundle.putLong(AdvisorDetailActivity.ADVISOR_ID, lunboBean.gid);
//                    ActivityUtils.startActivity((Activity) mContext, AdvisorDetailActivity.class, bundle);
                } else {
                    Bundle bundle = new Bundle();
//                    bundle.putBoolean(WebViewActivity.IS_FROM_ACTIVITY, true);
//                    bundle.putString(WebViewActivity.URL, lunboBean.url);
//                    bundle.putString(WebViewActivity.TITLE, lunboBean.title);
//                    ActivityUtils.startActivity((Activity) mContext, WebViewActivity.class, bundle);
                }

            }
        });
        if (!TextUtils.isEmpty(lunboBean.image)){
            imageView.setImageURI(Uri.parse(lunboBean.image));
        }else{
            imageView.setImageURI(Uri.parse(ConstantPool.FRESCO_RES+R.mipmap.default_avater));
        }


        if (lunboBean.lunbo_type == 0){
            nameView.setText(lunboBean.name+" | "+lunboBean.company+" "+lunboBean.job);
            nameView.setVisibility(View.VISIBLE);
        } else {
            nameView.setVisibility(View.GONE);
        }

        descView.setText(lunboBean.title);
        return view;
    }
}
