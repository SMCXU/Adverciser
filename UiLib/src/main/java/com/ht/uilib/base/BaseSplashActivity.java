package com.ht.uilib.base;

import android.os.Message;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.ht.baselib.utils.ActivityUtils;
import com.ht.baselib.utils.ThreadUtils;
import com.ht.uilib.R;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/13 16:06
 * QQ：876046815
 * Email:876046815@qq.com
 */
public abstract class BaseSplashActivity extends BaseActivity {

    private static final long TIME_DELAY_TO_HOME = 1000;

    private ImageView mSplashBgView;

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected boolean isFulleScreen() {
        return true;
    }

    @Override
    protected boolean isIncludeStatus() {
        return true;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_base_splash;
    }

    @Override
    protected void initChildView() {
        mSplashBgView = (ImageView)findViewById(R.id.iv_activity_splash_bg);
    }

    @Override
    protected void initChildData() {
        mSplashBgView.setImageResource(getSplashResId());
    }

    protected abstract int getSplashResId();

    @Override
    protected void setChildViewListener() {
        ThreadUtils.execute(new Runnable() {
            @Override
            public void run() {
                long delayTime = getDelayTime();
                if (delayTime == 0){
                    delayTime = TIME_DELAY_TO_HOME;
                }
                SystemClock.sleep(delayTime);
                mHandler.sendEmptyMessage(200);
            }
        });
    }

    protected long getDelayTime(){
        return 0;
    }

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void handleMessage(BaseActivity context, Message msg) {
        ActivityUtils.startActivityAndFinishByAlpha(this, getDescClass());
    }

    protected abstract Class<? extends BaseActivity> getDescClass();
}