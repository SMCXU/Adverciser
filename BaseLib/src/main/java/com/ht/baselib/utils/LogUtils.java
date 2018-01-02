package com.ht.baselib.utils;

import android.text.TextUtils;
import android.util.Log;

import com.ht.baselib.application.HTApplication;


public class LogUtils {
	private static final String TAG = "BaseLib";

	public static void info(String tag, String msg) {
		if (!HTApplication.IS_DEBUG_MODE)
			return;
		if (!TextUtils.isEmpty(tag)) {
			Log.i(tag, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
		} else {
			Log.i(TAG, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
		}
	}

    public static void info(String msg) {
        info(null, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
    }

	public static void debug(String tag, String msg) {
		if (!HTApplication.IS_DEBUG_MODE)
			return;
		if (!TextUtils.isEmpty(tag)) {
			Log.d(tag, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
		} else {
			Log.d(TAG, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
		}
	}

    public static void debug(String msg) {
        debug(null, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
    }

    public static void error(String msg) {
        if (!HTApplication.IS_DEBUG_MODE)
            return;

        Log.e(TAG, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
    }

    public static void error(Throwable e) {
        if (!HTApplication.IS_DEBUG_MODE)
            return;
        Log.e(TAG, "Address:"+getTopStackInfo()+"\n Cotent:"+e.getMessage());
    }

	public static void error(String tag, String msg) {
		if (!HTApplication.IS_DEBUG_MODE)
			return;
		if (!TextUtils.isEmpty(tag)) {
			Log.e(tag, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
		} else {
			Log.e(TAG, "Address:"+getTopStackInfo()+"\n Cotent:"+msg);
		}
	}

	public static void error(String tag, String msg, Throwable tr) {
		if (!HTApplication.IS_DEBUG_MODE)
			return;
		if (!TextUtils.isEmpty(tag)) {
			Log.e(tag, "Address:"+getTopStackInfo()+"\n Cotent:"+msg, tr);
		} else {
			Log.e(TAG, "Address:"+getTopStackInfo()+"\n Cotent:"+msg, tr);
		}
	}

    /**
     * 获取当前栈信息
     *
     * @return
     */
    protected static StackTraceElement getCurrentStackTrace() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        int stackOffset = getStackOffset(trace);
        if (stackOffset == -1){
            return null;
        }
        StackTraceElement caller = trace[stackOffset];
        return caller;
    }

    protected static int getStackOffset(StackTraceElement[] trace) {
        String preClassName = "";
        for (int i = 0; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            try {
                if (!"".equals(preClassName)){
                    Class<?> preClass = Class.forName(preClassName);
                    Class<?> nowClass = Class.forName(name);
                    if ((preClass == LogUtils.class || preClass.getSuperclass() == LogUtils.class) && (nowClass != LogUtils.class && nowClass.getSuperclass() != LogUtils.class)){
                        return i;
                    }
                }
                preClassName = name;
            } catch (ClassNotFoundException exc) {
                exc.printStackTrace();
            }
        }
        return -1;
    }

    protected static String getTopStackInfo() {
        StackTraceElement caller = getCurrentStackTrace();
        if (caller == null){
            return null;
        }
        String stackTrace = caller.toString();
        stackTrace = stackTrace.substring(stackTrace.lastIndexOf('('), stackTrace.length());
        String tag = "%s.%s%s";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), stackTrace);
        return tag;
    }
}
