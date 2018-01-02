package com.ht.baselib.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ht.baselib.application.HTApplication;


public class UIUtils {
	/** dip转换px */
	public static int dip2px(int dip) {
		final float scale = HTApplication.getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	/** px转换dip */
	public static int px2dip(int px) {
		final float scale = HTApplication.getContext().getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	public static void showToast(final String msg){
		HTApplication.getMainHandler().post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(HTApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public static void showToast(int resId){
		showToast(HTApplication.getContext().getResources().getString(resId));
	}

    public static <T extends View>T inflate(int resId){
        return inflate(resId, null);
    }

    public static <T extends View>T inflate(int resId, ViewGroup groupView){
        return (T) LayoutInflater.from(HTApplication.getContext()).inflate(resId, groupView, false);
    }

    /**
     * 生成一个 透明的背景图片
     *
     * @return
     */
    public static Drawable getTransactionDrawable() {
        ShapeDrawable bgdrawable = new ShapeDrawable(new OvalShape());
        bgdrawable.getPaint().setColor(HTApplication.getContext().getResources().getColor(android.R.color.transparent));
        return bgdrawable;
    }

    public static long getMainThreadId() {
        return HTApplication.getMainThreadId();
    }

    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runOnMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /** 在主线程执行runnable */
    public static boolean post(Runnable runnable) {
        return HTApplication.getMainHandler().post(runnable);
    }

    /** 把自身从父View中移除 */
    public static void removeSelfFromParent(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(view);
            }
        }
    }

    public static int getColor(int color) {
        return HTApplication.getContext().getResources().getColor(color);
    }

    public static Drawable getDrawable(int drawable) {
        return HTApplication.getContext().getResources().getDrawable(drawable);
    }

    public static float getDimension(int dimension) {
        return HTApplication.getContext().getResources().getDimension(dimension);
    }


    public static void hideKeyboard(final View view) {
        InputMethodManager imm =
                (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(final View view) {
        view.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(view, 0);
    }
}