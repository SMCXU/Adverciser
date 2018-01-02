package com.ht.uilib.base;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.ht.baselib.utils.ActivityUtils;
import com.ht.jsbridgelib.BridgeHandler;
import com.ht.jsbridgelib.BridgeWebView;
import com.ht.jsbridgelib.CallBackFunction;
import com.ht.jsbridgelib.CustomerWebViewClient;
import com.ht.uilib.R;

/**
 * Created by huangtao on 16/10/19.
 */

public abstract class BaseWebViewActivity extends BaseActivity {

    public static final String URL = "url";
    public static final String TITLE = "title";

    protected BridgeWebView mWebView;
    protected ProgressBar mProgressBar;

    protected String mUrl;
    protected String mCenterTitle;

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected void initIntentData(Intent callIntent) {
        mUrl = callIntent.getStringExtra(URL);
        mCenterTitle = callIntent.getStringExtra(TITLE);
    }

    @Override
    protected void initTitleBar() {
        mTitleBarView
                .setTitleText(mCenterTitle)
                .setFinishBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void back(View v) {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        } else {
            ActivityUtils.finishActivity(this);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
            if (mWebView.canGoBack()){
                mWebView.goBack();
            } else {
                ActivityUtils.finishActivity(this);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_base_webview;
    }

    @Override
    protected void initChildData() {
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected void initChildView() {
        mWebView = (BridgeWebView)findViewById(R.id.activity_base_webview_webview);
        mProgressBar = (ProgressBar)findViewById(R.id.activity_base_webview_progress);

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计

        mWebView.setCustomerWebViewClient(getWebViewClient());

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (View.GONE == mProgressBar.getVisibility()) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
    }

    protected CustomerWebViewClient getWebViewClient() {
        return null;
    }

    @Override
    protected void setChildViewListener() {
        registerJsFun();
    }

    protected abstract void registerJsFun();

    protected void callJs(String jsFunName, String data, CallBackFunction callBack){
        mWebView.callHandler(jsFunName, data, callBack);
    }

    protected void registerHandler(String name, BridgeHandler func){
        mWebView.registerHandler(name, func);
    }
}