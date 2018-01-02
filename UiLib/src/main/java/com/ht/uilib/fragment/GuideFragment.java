package com.ht.uilib.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ht.baselib.utils.SharedPreferencesUtils;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;
import com.ht.uilib.base.BaseFragment;
import com.ht.uilib.base.BaseGuideActivity;

/**
 * Created by huangtao on 16/7/1.
 */
public class GuideFragment extends BaseFragment {
    public static final String RES_ID = "res_id";
    public static final String IS_LAST_FRAGMENT = "is_last_fragment";
    private int mResId;
    private boolean isLastFragment;

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected void initArgumentsData(Bundle args) {
        mResId = args.getInt(RES_ID, 0);
        isLastFragment = args.getBoolean(IS_LAST_FRAGMENT, false);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected View initContentView() {
        return UIUtils.inflate(R.layout.fragment_guide);
    }

    @Override
    protected void initChildView() {
        ImageView imageView = findViewById(R.id.iv_fragment_guide);
        imageView.setImageResource(mResId);

        if (isLastFragment){
            View btnView = findViewById(R.id.btn_fragment_guide);
            btnView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void setChildViewListener() {
        TextView btnView = findViewById(R.id.btn_fragment_guide);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_fragment_guide){
            SharedPreferencesUtils.put(BaseGuideActivity.IS_FIRST_OPEN, false);
            ((BaseGuideActivity) mContext).onClick();
        }
    }
}
