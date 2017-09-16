package com.example.qiuyd.tradedemo;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by qiuyd on 2017/7/16.
 */

public class util {

    public static boolean is1080p(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if(dm.heightPixels==1080){
            return true;
        }
        return false;
    }

    public static boolean is720p(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if(dm.heightPixels==720){
            return true;
        }
        return false;
    }
}
