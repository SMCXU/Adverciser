package com.ht.uilib.widget.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;


public class PullToRefreshWebView extends PullToRefreshBase<WebView> {
    /**
     * 构造方法
     * 
     * @param context context
     */
    public PullToRefreshWebView(Context context) {
        this(context, null);
    }
    
    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     */
    public PullToRefreshWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     * @param defStyle defStyle
     */
    public PullToRefreshWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @see import com.ht.baselibs.ui.widget.pullrefresh.PullToRefreshBase#createRefreshableView(Context, AttributeSet)
     */
    @Override
    protected WebView createRefreshableView(Context context, AttributeSet attrs) {
        WebView webView = new WebView(context);
        webView.setOverScrollMode(OVER_SCROLL_NEVER);
        return webView;
    }

    /**
     * @see com.ht.baselibs.ui.widget.pullrefresh.PullToRefreshBase#isReadyForPullDown()
     */
    @Override
    protected boolean isReadyForPullDown() {
        return mRefreshableView.getScrollY() == 0;
    }

    /**
     * @see com.ht.baselibs.ui.widget.pullrefresh.PullToRefreshBase#isReadyForPullUp()
     */
    @Override
    protected boolean isReadyForPullUp() {
        float exactContentHeight = (float) Math.floor(mRefreshableView.getContentHeight() * mRefreshableView.getScale());
        return mRefreshableView.getScrollY() >= (exactContentHeight - mRefreshableView.getHeight());
    }
}
