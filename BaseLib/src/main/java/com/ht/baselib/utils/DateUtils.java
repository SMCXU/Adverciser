package com.ht.baselib.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huangtao on 16/2/29.
 */
public class DateUtils {

    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(new Date());
    }


    public static long getCurrentTimeLong(){
        return new Date().getTime();
    }
}