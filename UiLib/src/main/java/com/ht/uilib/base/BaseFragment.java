package com.ht.uilib.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ht.baselib.event.EventCenter;
import com.ht.baselib.event.EventCode;
import com.ht.baselib.utils.NetUtils;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;
import com.ht.uilib.widget.LoadingView;
import com.ht.uilib.widget.TitleBarView;

import java.lang.ref.WeakReference;

import de.greenrobot.event.EventBus;

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    public static final String IS_ATTACH_FRAGMENT_LAYOUT = "is_attach_fragment_layout";
    protected BaseActivity mContext;
    protected Handler mHandler;

    private boolean isViewInit;
    private boolean isVisible;
    private boolean isLoadedData;

    private LoadingView mContentView;
    protected LinearLayout mRootView;
    protected TitleBarView mTitleBarView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (BaseActivity) getActivity();
        mHandler = new XHandler(mContext, this);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        isVisible = args.getBoolean(IS_ATTACH_FRAGMENT_LAYOUT, false);
        initArgumentsData(args);
    }

    protected void initArgumentsData(Bundle args){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = new LinearLayout(mContext);
            mRootView.setOrientation(LinearLayout.VERTICAL);
            mTitleBarView = new TitleBarView(mContext);

            initTitleBar();
            setTitleBarListener();

            mTitleBarView.setVisibility(isShowTitleBarView()?View.VISIBLE:View.GONE);

            mContentView = new LoadingView(mContext) {
                @Override
                public View createContentView() {
                    return initContentView();
                }

                @Override
                public void load() {
                    BaseFragment.this.load();
                }
            };

            if (isStaticPage()) {
                mContentView.refreshState(LoadingView.LoadResult.SUCCEED);
            }

            mRootView.addView(mTitleBarView, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mRootView.addView(mContentView, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            initChildView();
            isViewInit = true;
        } else {
            UIUtils.removeSelfFromParent(mRootView);
        }
        return mRootView;
    }

    protected boolean isShowTitleBarView(){
        return false;
    }

    private void setTitleBarListener() {
        mTitleBarView.setOnLeftBtnClickListener(this);
    }

    protected void initTitleBar(){

    }

    protected void addCenterView(View view){
        mTitleBarView.addCenterView(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preLoadData();
        setChildViewListener();
        if (hasEvent() && !EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    protected boolean hasEvent() {
        return false;
    }

    protected boolean isStaticPage() {
        return false;
    }
    protected abstract View initContentView();
    protected abstract void initChildView();
    protected abstract void setChildViewListener();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        handleVisibleChanged(!hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        handleVisibleChanged(isVisibleToUser);
    }

    private void handleVisibleChanged(boolean visible) {
        isVisible = visible;
        if (visible) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    protected <T extends View>T findViewById(int resId){
        return (T) mRootView.findViewById(resId);
    }

    /**
     * fragment可见回调
     */
    protected void onVisible() {
        preLoadData();
    }

    private void preLoadData() {
        if (isViewInit && isVisible && !isLoadedData){
            isLoadedData = true;
            load();
        }
    }

    /**
     * fragment不可见回调
     */
    protected void onInvisible() {

    }

    protected void load(){

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_view_title_bar_back){
            back(v);
        }
    }

    protected void back(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected void onLoadFinish(LoadingView.LoadResult result){
        mContentView.refreshState(result);
    }

    public void onEvent(EventCenter eventCenter) {
        switch (eventCenter.eventCode) {
            case EventCode.EVENT_CODE_NET_STATE_CHANGE:
                boolean isNetWorkConn = NetUtils.isNetWorkConnection();

                if (isNetWorkConn && whenNetConnIsReloadData()) {
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
        load();
    }

    protected boolean whenNetConnIsReloadData() {
        return false;
    }

    private static class XHandler extends Handler {
        private final WeakReference<BaseActivity> activityWeakReference;
        private final WeakReference<BaseFragment> fragmentWeakReference;
        public XHandler(BaseActivity context, BaseFragment baseFragment) {
            activityWeakReference = new WeakReference<>(context);
            fragmentWeakReference = new WeakReference<>(baseFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity context = activityWeakReference.get();
            BaseFragment baseFragment = fragmentWeakReference.get();
            if (baseFragment != null && context != null){
                baseFragment.handleMessage(context, msg);
            }
        }
    }

    protected void handleMessage(Context context, Message msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (hasEvent()){
            EventBus.getDefault().unregister(this);
        }
    }

}