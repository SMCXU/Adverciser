package com.ht.uilib.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.view.Gravity;
import android.widget.RadioButton;

import com.ht.baselib.utils.DeviceUtils;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/15 14:42
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class ViewUtils {

    public static RadioButton createHomeTabView(String title, @DrawableRes int topResId, int tabSize){
        RadioButton homeTabView = UIUtils.inflate(R.layout.view_home_tab);

        Drawable drawable = UIUtils.getDrawable(topResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        homeTabView.setCompoundDrawables(null, drawable, null, null);

        homeTabView.setText(title);

        int screenWidth = DeviceUtils.getScreenWidth();
        homeTabView.setWidth(screenWidth/tabSize);

        homeTabView.setGravity(Gravity.CENTER);
        return homeTabView;
    }

}
