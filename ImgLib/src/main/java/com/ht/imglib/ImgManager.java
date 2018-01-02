package com.ht.imglib;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;


/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/16 15:30
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class ImgManager {

    public static void display(Context context, String url, ImageView imageView){
        getRequestManager(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    public static void display(Context context, int resId, ImageView imageView){
        getRequestManager(context)
                .load(resId)
                .centerCrop()
                .into(imageView);
    }

    public static void display(Context context, File imgFile, ImageView imageView){
        getRequestManager(context)
                .load(imgFile)
                .centerCrop()
                .into(imageView);
    }

    public static void display(Context context, String url, ImageView imageView, int width, int height){
        getRequestManager(context)
                .load(url)
                .centerCrop()
                .override(width, height)
                .into(imageView);
    }

    public static void display(Context context, int resId, ImageView imageView, int width, int height){
        getRequestManager(context)
                .load(resId)
                .centerCrop()
                .override(width, height)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }

    public static void display(Context context, File imgFile, ImageView imageView, int width, int height){
        getRequestManager(context)
                .load(imgFile)
                .centerCrop()
                .override(width, height)
                .into(imageView);
    }

    private static RequestManager getRequestManager(Context context) {
        if (context instanceof FragmentActivity){
            return Glide.with((FragmentActivity)context);
        }

        if (context instanceof Activity){
            return Glide.with((Activity)context);
        }

        return Glide.with(context);
    }

}
