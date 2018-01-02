package com.ht.uilib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.ht.uilib.R;


public abstract class LoadingView extends FrameLayout implements View.OnClickListener{
	private static final int STATE_UNLOADED = 0;//未知状态
	private static final int STATE_LOADING = 1;//加载状态
	private static final int STATE_ERROR = 3;//加载完毕，但是出错状态
	private static final int STATE_EMPTY = 4;//加载完毕，但是没有数据状态
	private static final int STATE_SUCCEED = 5;//加载成功

	private View mLoadingView;//加载时显示的View
	private View mErrorView;//加载出错显示的View
	private View mEmptyView;//加载没有数据显示的View
	private View mSucceedView;//加载成功显示的View

	private int mState;//当前的状态，显示需要根据该状态判断

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
		setBackgroundColor(getContext().getResources().getColor(R.color.transparent));//设置背景
		mState = STATE_UNLOADED;//初始化状态

		//创建对应的View，并添加到布局中
		mLoadingView = createLoadingView();
		if (null != mLoadingView) {
			addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}

		mErrorView = createErrorView();
		if (null != mErrorView) {
			addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}

		mEmptyView = createEmptyView();
		if (null != mEmptyView) {
			addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}

        mSucceedView = createContentView();
        if (mSucceedView != null) {
            addView(mSucceedView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            mSucceedView.setBackgroundColor(getContext().getResources().getColor(R.color.defualt_bg_color));
        }
		//显示对应的View
        showPage();
	}

	/** 显示对应的View */
	private void showPage() {
		if (null != mLoadingView) {
			mLoadingView.setVisibility(mState == STATE_UNLOADED || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
		}
		if (null != mErrorView) {
			mErrorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
		}
		if (null != mEmptyView) {
			mEmptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
		}

		if (null != mSucceedView) {
			mSucceedView.setVisibility(mState == STATE_SUCCEED ? View.VISIBLE : View.INVISIBLE);
		}
	}

	/** 是否需要恢复状态 */
	protected boolean needReset() {
		return mState == STATE_ERROR || mState == STATE_EMPTY;
	}

	/** 有外部调用，会根据状态判断是否引发load */
	public synchronized void show() {
		if (needReset()) {
			mState = STATE_UNLOADED;
		}
		if (mState == STATE_UNLOADED) {
			mState = STATE_LOADING;
            load();
        }
        showPage();
	}

    public synchronized void refreshState(LoadResult result) {
        switch (result){
            case EMPTY:
                mState = STATE_EMPTY;
                break;
            case ERROR:
                mState = STATE_ERROR;
                break;
            case SUCCEED:
                mState = STATE_SUCCEED;
                break;
        }
        showPage();
    }

	protected View createLoadingView() {
		return inflate(getContext(), R.layout.view_loading_view_loading, null);
	}

	protected View createEmptyView() {
		return inflate(getContext(), R.layout.view_loading_view_empty, null);
	}

	protected View createErrorView() {
		View view = inflate(getContext(), R.layout.view_loading_view_error, null);
        view.findViewById(R.id.rl_view_loading_view_error).setOnClickListener(this);
		return view;
	}

	public abstract View createContentView();

	public abstract void load();

	public enum LoadResult {
		ERROR(3), EMPTY(4), SUCCEED(5);
		int value;

		LoadResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_view_loading_view_error){
            show();
        }
    }
}
