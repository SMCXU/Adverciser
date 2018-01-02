package com.ht.jsbridgelib;

import android.webkit.WebView;

/**
 * Created by huangtao on 16/10/19.
 */

public abstract class CustomerWebViewClient {
    public abstract void shouldOverrideUrlLoadingInner(WebView view, String url);

}
