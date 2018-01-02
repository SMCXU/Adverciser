package com.ht.baselib.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ht.baselib.R;


public class ActivityUtils {
    /**
     * activity跳转，从右至左滑入动画效果
     * @param intent 自定义intent，可以putExtra
     */
    public static void startActivity(Activity context, Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
    }
    
    public static void startActivityAndFinish(Activity context, Class<?> to){
    	startActivity(context, to);
    	context.finish();
    }

    /**
     * activity跳转，从下至上滑入动画效果
     * @param intent 自定义intent，可以putExtra
     */
    public static void startActivityFromBottom(Activity context, Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_in_from_bottom, R.anim.activity_out_to_top);
    }

    /**
     * activity跳转，从右至左滑入动画效果
     */
    public static void startActivity(Activity context, Class<?> to){
        Intent intent = new Intent(context, to);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
    }

    public static void startActivityAndFinishByAlpha(Activity context, Class<?> to){
        Intent intent = new Intent(context, to);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_in_alpha, R.anim.activity_out_alpha);
        context.finish();
    }

    public static void startActivityAndFinishByAlpha(Activity context, Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_in_alpha, R.anim.activity_out_alpha);
        context.finish();
    }

    /**
     * activity跳转，从右至左滑入动画效果
     * @param context
     * @param to
     * @param bundle
     */
    public static void startActivity(Activity context, Class<?> to, Bundle bundle)
    {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        startActivity(context, intent);
        context.overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
    }

    /**
     * activity跳转，从右至左滑入动画效果
     * @param context
     * @param to
     * @param bundle
     */
    public static void startActivityAndFinish(Activity context, Class<?> to, Bundle bundle)
    {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        startActivity(context, intent);
        context.overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
        context.finish();
    }

    /**
     * activity跳转，从右至左滑入动画效果
     * @param context
     * @param to
     * @param bundle
     * @param requestCode
     */
    public static void startActivityForResult(Activity context, Class<?> to,Bundle bundle,int requestCode)
    {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
    }

    /**
     * activity跳转，从右至左滑入动画效果
     * @param context
     * @param requestCode
     */
    public static void startActivityForResult(Activity context, Intent intent, int requestCode)
    {
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
    }

    /**
     * activity跳转，从下至上滑入动画效果
     * @param context
     * @param requestCode
     */
    public static void startActivityForResultFromBottom(Activity context, Intent intent, int requestCode)
    {
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_bottom, R.anim.activity_out_to_top);
    }

    /**
     * activity跳转，从右至左滑入动画效果
     * @param context
     * @param to
     * @param requestCode
     */
    public static void startActivityForResult(Activity context, Class<?> to, int requestCode)
    {
        Intent intent = new Intent(context, to);
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
    }

    /**
     * activity关闭，从左至右滑出动画效果
     */
    public static void finishActivity(Activity context){
        context.finish();
        context.overridePendingTransition(R.anim.activity_in_from_left, R.anim.activity_out_to_right);
    }

    /**
     * activity关闭，从上至下滑出动画效果
     */
    public static void finishActivityTopToBottom(Activity context){
        context.finish();
        context.overridePendingTransition(R.anim.activity_in_from_top, R.anim.activity_out_to_bottom);
    }

    /**
     * activity关闭，淡入淡出
     */
    public static void finishActivityByAlpha(Activity context){
        context.finish();
        context.overridePendingTransition(R.anim.activity_in_alpha, R.anim.activity_out_alpha);
    }

}
