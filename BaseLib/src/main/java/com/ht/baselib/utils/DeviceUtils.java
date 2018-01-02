package com.ht.baselib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import com.ht.baselib.application.HTApplication;

import java.io.File;

public class DeviceUtils {
	// 获取手机型号
	public static String getMobileType() {
		return Build.MODEL.replaceAll(" ", "");
	}

	public static String getSystemVersion() {
		return Build.VERSION.RELEASE;
	}

    public static String getDeviceId() {
        try {
            TelephonyManager tm = (TelephonyManager) HTApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception e){
            e.printStackTrace();
            return "000000";
        }
    }

	public static String getAppVersionName() {
		String versionName = "";
		try {
			PackageManager packageManager = HTApplication.getContext().getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(HTApplication.getContext().getPackageName(), 0);
			versionName = packageInfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	public static int getAppVersionCode() {
		int versionCode = -1;
		try {
			PackageManager packageManager = HTApplication.getContext().getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(HTApplication.getContext().getPackageName(), 0);
			versionCode = packageInfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}

    public static void installApk(Activity activity, String path) {
        File file = new File(path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = HTApplication.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = HTApplication.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) HTApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) HTApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }

    public static boolean isSDCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
