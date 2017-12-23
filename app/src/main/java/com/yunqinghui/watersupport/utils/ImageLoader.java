package com.yunqinghui.watersupport.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * Created by HJH
 * Data 2017/11/17.
 */

public class ImageLoader {

    public static void load(String path, ImageView imageView, Activity activity){
        GlideApp.with(activity)
                .load(path)
                .centerCrop()
                .into(imageView);
    }
    public static void load(String path, ImageView imageView, Fragment fragment){
        GlideApp.with(fragment)
                .load(path)
                .centerCrop()
                .into(imageView);
    }
    public static void load(String path, ImageView imageView, Context context){
        GlideApp.with(context)
                .load(path)
                .centerCrop()
                .into(imageView);
    }

    public static void loadCenterInside(String path, ImageView imageView, Activity activity){
        GlideApp.with(activity)
                .load(path)
                .centerInside()
                .into(imageView);
    }
}
