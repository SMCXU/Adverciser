package com.example.zlq_pc.adverciser.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zlq_pc.adverciser.R;
import com.example.zlq_pc.adverciser.utils.KEYS;
import com.example.zlq_pc.adverciser.utils.SharedPreferencesUtils;
import com.ht.baselib.utils.ActivityUtils;
import com.ht.uilib.base.BaseActivity;
import com.ht.uilib.base.BaseSplashActivity;

public class LunchActivity extends BaseSplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
    }

    @Override
    protected int getSplashResId() {
        return R.mipmap.bg_splash;
    }

    @Override
    protected Class<? extends BaseActivity> getDescClass() {
        if ("true".equals(SharedPreferencesUtils.getString(this, KEYS.KEY_ISFIRST, "true"))) {
            return GuideActivity.class;
        } else
        {
            return HomeActivity.class;
        }
    }
}
