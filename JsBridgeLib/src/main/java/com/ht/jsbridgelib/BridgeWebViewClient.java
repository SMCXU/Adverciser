package com.ht.jsbridgelib;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public abstract class BridgeWebViewClient extends WebViewClient {
    private BridgeWebView mWebView;

    public BridgeWebViewClient(BridgeWebView webView) {
        this.mWebView = webView;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (url.startsWith(BridgeUtils.YY_RETURN_DATA)) { // 如果是返回数据
            mWebView.handlerReturnData(url);
            return true;
        } else if (url.startsWith(BridgeUtils.YY_OVERRIDE_SCHEMA)) { //
            mWebView.flushMessageQueue();
            return true;
        } else {
            return shouldOverrideUrlLoadingInner(view, url);
        }
    }

    protected abstract boolean shouldOverrideUrlLoadingInner(WebView view, String url);

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        BridgeUtils.webViewLoadLocalJs(view, BridgeWebView.toLoadJs);

        if (mWebView.getStartupMessage() != null) {
            for (Message m : mWebView.getStartupMessage()) {
                mWebView.dispatchMessage(m);
            }
            mWebView.setStartupMessage(null);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }
}