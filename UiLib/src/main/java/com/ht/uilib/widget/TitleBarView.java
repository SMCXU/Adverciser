package com.ht.uilib.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ht.baselib.utils.DeviceUtils;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;

/**
 * Created by huangtao on 16/8/26.
 */
public class TitleBarView extends LinearLayout implements View.OnClickListener {
    private static final int DEFAULT_ACTION_PADDING = 5;
    private static final float DEFAULT_ACTION_TEXTSIZE = 16;

    private View mStaturbarView;
    private View mTitleBarContent;

    private TextView mLeftBtnView;

    private FrameLayout mCenterLayout;
    private TextView mTitleView;

    private LinearLayout mRightLayout;
    private int defaultActionPadding;
    private TextView mFinishView;

    public TitleBarView(Context context) {
        super(context);
        init();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);

        defaultActionPadding = UIUtils.dip2px(DEFAULT_ACTION_PADDING);

        mStaturbarView = new View(getContext());
        mStaturbarView.setBackgroundColor(getResources().getColor(R.color.app_base_color));
        mTitleBarContent = UIUtils.inflate(R.layout.view_title_bar);

        addView(mStaturbarView, new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, 0));
        addView(mTitleBarContent);

        mLeftBtnView = (TextView) findViewById(R.id.tv_view_title_bar_back);
        mFinishView = (TextView) findViewById(R.id.tv_view_title_bar_finish);

        mCenterLayout = (FrameLayout) findViewById(R.id.fl_view_title_bar_center_layout);
        mTitleView = (TextView) findViewById(R.id.tv_view_title_bar_title);

        mRightLayout = (LinearLayout) findViewById(R.id.fl_view_title_bar_right_layout);
    }

    public TitleBarView showStatusBar(){
        mStaturbarView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, DeviceUtils.getStatusBarHeight()));
        return this;
    }


    public TitleBarView setLeftBtnText(String text){
        mLeftBtnView.setText(text);
        mLeftBtnView.setVisibility(VISIBLE);
        return this;
    }

    public TitleBarView setLeftBtnText(@StringRes int resId){
        setLeftBtnText(getResources().getString(resId));
        return this;
    }

    public TitleBarView addCenterView(View view){
        if (view != null){
            mCenterLayout.addView(view);
        }
        return this;
    }

    public TitleBarView setTitleText(String text){
        mTitleView.setText(text);
        return this;
    }

    public TitleBarView setLeftBtnIcon(int resId){
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mLeftBtnView.setCompoundDrawables(drawable, null, null, null);
        return this;
    }

    public TitleBarView setTitleText(@StringRes int resId){
        setTitleText(getResources().getString(resId));
        return this;
    }

    public TitleBarView setTitleTextColor(int resId){
        mTitleView.setTextColor(UIUtils.getColor(resId));
        return this;
    }

    public TitleBarView setLeftBtnVisibility(int visible){
        mLeftBtnView.setVisibility(visible);
        return this;
    }

    public TitleBarView setFinishBtnVisibility(int visible){
        mFinishView.setVisibility(visible);
        return this;
    }

    public TitleBarView setTitleViewVisibility(int visible){
        mTitleView.setVisibility(visible);
        return this;
    }

    public TitleBarView setOnLeftBtnClickListener(OnClickListener onClickListener) {
        mLeftBtnView.setOnClickListener(onClickListener);
        return this;
    }

    public TitleBarView setOnFinishClickListener(OnClickListener onClickListener) {
        mFinishView.setOnClickListener(onClickListener);
        return this;
    }

    public TitleBarView addActions(Action ... actions){
        if (actions != null){
            int length = actions.length;
            for (int i = 0; i < length; i++) {
                Action action = actions[i];
                View view = inflateAction(action);
                mRightLayout.addView(view, new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT));
            }
        }
        return this;
    }


    public TitleBarView removeAction(int index){
        if (index <= mRightLayout.getChildCount()-1){
            mRightLayout.removeViewAt(index);
        }
        return this;
    }


    public TitleBarView hidenAction(int index){
        int childCount = mRightLayout.getChildCount();
        if (index <= childCount -1){
            for (int i = 0; i < childCount; i++) {
                if (i == index){
                    View view = mRightLayout.getChildAt(i);
                    view.setVisibility(GONE);
                }
            }
        }
        return this;
    }

    public TitleBarView updateAction(int index, String actionTitle){
        return updateAction(index, actionTitle, 0);
    }

    public TitleBarView updateAction(int index, int actionDrawble){
        return updateAction(index, null, actionDrawble);
    }

    public TitleBarView updateAction(int index, String actionTitle, int actionDrawble){
        if (index <= mRightLayout.getChildCount()-1) {
            TextView actionView = (TextView) mRightLayout.getChildAt(index);

            if (!TextUtils.isEmpty(actionTitle)) {
                actionView.setText(actionTitle);
                actionView.setTextSize(DEFAULT_ACTION_TEXTSIZE);
                actionView.setTextColor(getResources().getColorStateList(R.color.title_bar_action_text_color));
            }

            if (actionDrawble != 0) {
                Drawable drawable = getResources().getDrawable(actionDrawble);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                actionView.setCompoundDrawables(null, null, drawable, null);
            }
        }
        return this;
    }

    public TitleBarView showAction(int index){
        int childCount = mRightLayout.getChildCount();
        if (index <= childCount -1){
            for (int i = 0; i < childCount; i++) {
                if (i == index) {
                    View view = mRightLayout.getChildAt(i);
                    view.setVisibility(VISIBLE);
                }
            }
        }
        return this;
    }

    public TitleBarView setActionVisibility(int index, int visibility){
        int childCount = mRightLayout.getChildCount();
        if (index <= childCount -1){
            for (int i = 0; i < childCount; i++) {
                if (i == index) {
                    View view = mRightLayout.getChildAt(i);
                    view.setVisibility(visibility);
                }
            }
        }
        return this;
    }

    private View inflateAction(Action action) {
        TextView actionView = new TextView(getContext());

        if (!TextUtils.isEmpty(action.title)){
            actionView.setText(action.title);
            actionView.setTextSize(DEFAULT_ACTION_TEXTSIZE);
            actionView.setTextColor(getResources().getColorStateList(R.color.title_bar_action_text_color));
        }

        if (action.drable != 0){
            Drawable drawable = getResources().getDrawable(action.drable);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            actionView.setCompoundDrawables(null, null, drawable, null);
        }

        actionView.setTag(action);
        actionView.setOnClickListener(this);

        actionView.setGravity(Gravity.CENTER_VERTICAL);
        actionView.setPadding(defaultActionPadding, 0, defaultActionPadding, 0);
        return actionView;
    }

    public void setBackgroundColor(int color) {
        mTitleBarContent.setBackgroundColor(color);
        mStaturbarView.setBackgroundColor(color);
    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof Action) {
            final Action action = (Action) tag;
            action.performAction(v);
        }
    }

    public static abstract class Action{
        public String title;
        public int drable;
        public Action(String title){
            this.title = title;
        }

        public Action(int drable) {
            this.drable = drable;
        }

        public Action(String title, int drable) {
            this.title = title;
            this.drable = drable;
        }

        public abstract void performAction(View view);
    }

}