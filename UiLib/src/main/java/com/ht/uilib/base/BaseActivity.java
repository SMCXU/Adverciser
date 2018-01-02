package com.ht.uilib.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.ht.baselib.event.EventCenter;
import com.ht.baselib.event.EventCode;
import com.ht.baselib.manager.AppManager;
import com.ht.baselib.utils.ActivityUtils;
import com.ht.baselib.utils.DeviceUtils;
import com.ht.baselib.utils.NetUtils;
import com.ht.baselib.utils.SharedPreferencesUtils;
import com.ht.baselib.utils.SmartBarUtils;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;
import com.ht.uilib.dialog.LoadingDialog;
import com.ht.uilib.widget.LoadingView;
import com.ht.uilib.widget.TitleBarView;

import java.lang.ref.WeakReference;

import de.greenrobot.event.EventBus;


public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    public static BaseActivity currentActivity;

    private Intent mCallIntent;

    private LoadingDialog mLoadingDialog;
    protected Handler mHandler;

    protected Fragment mNowFragment;
    protected RelativeLayout mRootView;

    protected TitleBarView mTitleBarView;

    private LoadingView mContentView;
    private View mNetSettingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = this;
        AppManager.getInstance().addActivity(this);
        if (true){
            initUpdate();
        }
        setTranslucentStatus(isIncludeStatus());
        settingSmartBar();
        settingFullScreen();

        settingHandler();
        settingCallIntent();
        initIntentData(mCallIntent);

        initLoadingDialog();
        initDao();
        setContentView(R.layout.activity_base);

        settingStatusBar();
        initTitleBar();
        setTitleBarListener();

        settingContentView();
        initChildView();
        initChildData();
        setChildViewListener();
        if (hasEvent() && !EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    protected void initUpdate() {
        
    }

    protected boolean hasEvent() {
        return false;
    }

    private void setTitleBarListener() {
        mTitleBarView.setOnLeftBtnClickListener(this);
        mTitleBarView.setOnFinishClickListener(this);
    }

    private void settingFullScreen() {
        if (isFulleScreen() && isIncludeStatus()){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void settingContentView() {
        mRootView = (RelativeLayout) findViewById(R.id.rl_activity_base_content);

        if (!isUnderTitleBar()){
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRootView.getLayoutParams();
            layoutParams.addRule(RelativeLayout.BELOW, R.id.tbv_activity_base_title_bar);
        }

        mContentView = new LoadingView(this) {
            @Override
            public View createContentView() {
                int contentResId = initContentView();
                if (contentResId != 0){
                    return UIUtils.inflate(contentResId);
                } else{
                    throw new RuntimeException("请设置"+getClass().getSimpleName()+"资源文件");
                }
            }

            @Override
            public void load() {
                BaseActivity.this.reloadData();
            }
        };
        mRootView.addView(mContentView, 0, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        if (isStaticPage()){
            mContentView.refreshState(LoadingView.LoadResult.SUCCEED);
        }

        mNetSettingView = findViewById(R.id.rl_activity_base_net_setting);
        mNetSettingView.setOnClickListener(this);

        if (isShowNetSettingView()){
            if (mTitleBarView.getVisibility() == View.GONE){
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mNetSettingView.getLayoutParams();
                layoutParams.topMargin = (int) (DeviceUtils.getStatusBarHeight() + getResources().getDimension(R.dimen.customer_action_bar_size));
            }
            mNetSettingView.setVisibility(NetUtils.isNetWorkConnection() ? View.GONE : View.VISIBLE);
        }
    }

    protected boolean isShowNetSettingView() {
        return false;
    }

    private void settingCallIntent() {
        mCallIntent = getIntent();
    }

    private void initLoadingDialog() {
        mLoadingDialog = LoadingDialog.newInstance(this, true);
    }

    private void settingSmartBar() {
        SmartBarUtils.hide(getWindow().getDecorView());
    }

    private void settingHandler() {
        mHandler = new XHandler(this);
    }

    private void settingStatusBar() {
        mTitleBarView = (TitleBarView) findViewById(R.id.tbv_activity_base_title_bar);

        if (isFulleScreen()){
            mTitleBarView.setVisibility(View.GONE);
        }else{
            if (isIncludeStatus() && isCanInCludeStatus() && !isUnderTitleBar()){
                mTitleBarView.showStatusBar();
            } else if (isUnderTitleBar()){
                mTitleBarView.setTitleViewVisibility(View.GONE)
                        .setLeftBtnIcon(getBackIcon())
                        .setBackgroundColor(UIUtils.getColor(R.color.transparent));
            }
        }
    }

    protected int getBackIcon() {
        return R.mipmap.base_color_back;
    }

    protected boolean isUnderTitleBar() {
        return false;
    }

    protected void addCenterView(View view){
        mTitleBarView.addCenterView(view);
        view.setVisibility(View.GONE);
    }

    protected boolean isFulleScreen() {
        return false;
    }

    protected boolean isIncludeStatus() {
        return false;
    }

    public boolean isFragmentHiddenTitleBar(){
        return mTitleBarView.getVisibility() == View.VISIBLE;
    }

    private void setTranslucentStatus(boolean on) {
        if (isCanInCludeStatus() && !isUnderTitleBar()) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    private boolean isCanInCludeStatus(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    protected void initDao() {

    }

    protected void initIntentData(Intent callIntent) {

    }

    protected void onLoadFinish(LoadingView.LoadResult result){
        mContentView.refreshState(result);
    }


    public void showLoadingDialog(){
        mLoadingDialog.setCancelable(true);
        if (!isFinishing()){
            mLoadingDialog.show();
        }
    }

    public void showLoadingDialogCanotCancle(){
        mLoadingDialog.setCancelable(false);
        if (!isFinishing()) {
            mLoadingDialog.show();
        }
    }

    public void hideLodingDialog(){
        mLoadingDialog.hide();
    }

    private void dismissLodingDialog(){
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
        dismissLodingDialog();
        if (hasEvent()){
            EventBus.getDefault().unregister(this);
        }
    }

    protected void switchFragment(Fragment fragment, int resId) {
        if (fragment != mNowFragment) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!fragment.isAdded()) {
                fragmentTransaction.add(resId, fragment).commit();
            } else {
                fragmentTransaction.show(fragment).commit();
            }
            //隐藏之前的fragment
            if (mNowFragment != null){
                fragmentTransaction.hide(mNowFragment);
            }
            mNowFragment = fragment;
        }
    }

    private static class XHandler extends Handler {
        private final WeakReference<BaseActivity> activityWeakReference;
        public XHandler(BaseActivity context) {
            activityWeakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity context = activityWeakReference.get();
            if (context != null){
                context.handleMessage(context, msg);
            }
        }
    }

    protected abstract void initTitleBar();

    protected boolean isStaticPage() {
        return false;
    }

    protected abstract int initContentView();

    protected abstract void initChildView();

    protected abstract void initChildData();

    protected abstract void setChildViewListener();

    protected void handleMessage(BaseActivity context, Message msg) {

    }

    protected String getFromCache(String key){
        return SharedPreferencesUtils.getString(key, null);
    }

    protected void add2Cache(String key, String value){
        SharedPreferencesUtils.put(key, value);
    }

    public void onEvent(EventCenter eventCenter) {
        switch (eventCenter.eventCode){
            case EventCode.EVENT_CODE_NET_STATE_CHANGE:
                boolean isNetWorkConn = NetUtils.isNetWorkConnection();

                if (isShowNetSettingView()){
                    mNetSettingView.setVisibility(isNetWorkConn ? View.GONE : View.VISIBLE);
                }

                if (isNetWorkConn && whenNetConnIsReloadData()){
                    reloadData();
                }
                break;
            default:
                onEvent(eventCenter.eventCode, eventCenter.data);
                break;
        }
    }

    protected void onEvent(int eventCode, Object data) {

    }

    protected void reloadData() {
        initChildData();
    }

    protected boolean whenNetConnIsReloadData() {
        return false;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_view_title_bar_back){
            back(v);
        } else if (v.getId() == R.id.tv_view_title_bar_finish){
            ActivityUtils.finishActivity(this);
        }else if (v.getId() == R.id.rl_activity_base_net_setting){
            NetUtils.openNetworkSettingView();
        }
    }

    protected void back(View v) {
        ActivityUtils.finishActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (!HTApplication.IS_DEBUG_MODE){
//            MobclickAgent.onResume(this);
//        }
    }

    @Override
    protected void onPause() {
//        if (!HTApplication.IS_DEBUG_MODE){
//            MobclickAgent.onPause(this);
//        }
        super.onPause();
    }

}