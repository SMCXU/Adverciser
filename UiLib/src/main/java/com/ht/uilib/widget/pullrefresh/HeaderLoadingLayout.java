package com.ht.uilib.widget.pullrefresh;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ht.uilib.R;


public class HeaderLoadingLayout extends LoadingLayout {
    /** 旋转动画时间 */
    private static final int ROTATE_ANIM_DURATION = 150;
    /**Header的容器*/
    private RelativeLayout mHeaderContainer;
    /**箭头图片*/
    private ImageView mImageView;
    /**状态提示TextView*/
    private TextView mTextView;
    /**向上的动画*/
    private Animation mRotateUpAnim;
    /**向下的动画*/
    private Animation mRotateDownAnim;

    /**
     * 构造方法
     * 
     * @param context context
     */
    public HeaderLoadingLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     */
    public HeaderLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * 
     * @param context context
     */
    private void init(Context context) {
        mHeaderContainer = (RelativeLayout) findViewById(R.id.pull_to_refresh_header_content);
        mImageView = (ImageView) findViewById(R.id.pull_to_refresh_header_arrow);

        mTextView = (TextView) findViewById(R.id.pull_to_refresh_header_hint_textview);

        float pivotValue = 0.5f;    // SUPPRESS CHECKSTYLE
        float toDegree = -180f;     // SUPPRESS CHECKSTYLE
        // 初始化旋转动画
        mRotateUpAnim = new RotateAnimation(0.0f, toDegree, Animation.RELATIVE_TO_SELF, pivotValue,
                Animation.RELATIVE_TO_SELF, pivotValue);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(toDegree, 0.0f, Animation.RELATIVE_TO_SELF, pivotValue,
                Animation.RELATIVE_TO_SELF, pivotValue);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

//    @Override
//    public void setLastUpdatedLabel(CharSequence label) {
//        // 如果最后更新的时间的文本是空的话，隐藏前面的标题
//        mHeaderTimeViewTitle.setVisibility(TextUtils.isEmpty(label) ? View.INVISIBLE : View.VISIBLE);
//        mHeaderTimeView.setText(label);
//    }

    @Override
    public int getContentSize() {
        if (null != mHeaderContainer) {
            return mHeaderContainer.getHeight();
        }
        
        return (int) (getResources().getDisplayMetrics().density * 60);
    }
    
    @Override
    protected View createLoadingView(Context context, AttributeSet attrs) {
        View container = LayoutInflater.from(context).inflate(R.layout.view_pull_refresh_header, null);
        return container;
    }
    
    @Override
    protected void onStateChanged(State curState, State oldState) {
        mImageView.setImageResource(R.mipmap.view_pull_refresh_arrow_down);

        super.onStateChanged(curState, oldState);
    }

    @Override
    protected void onReset() {
        mImageView.clearAnimation();
        mTextView.setText(R.string.pull_to_refresh_header_hint_normal);
    }

    @Override
    protected void onPullToRefresh() {
        if (State.RELEASE_TO_REFRESH == getPreState()) {
            mImageView.clearAnimation();
            mImageView.startAnimation(mRotateDownAnim);
        }
        
        mTextView.setText(R.string.pull_to_refresh_header_hint_normal);
    }

    @Override
    protected void onReleaseToRefresh() {
        mImageView.clearAnimation();
        mImageView.startAnimation(mRotateUpAnim);
        mTextView.setText(R.string.pull_to_refresh_header_hint_ready);
    }

    @Override
    protected void onRefreshing() {
        mImageView.clearAnimation();
        mImageView.setImageResource(R.drawable.view_pull_refresh_loding);
        mTextView.setText(R.string.pull_to_refresh_header_hint_loading);
    }
}