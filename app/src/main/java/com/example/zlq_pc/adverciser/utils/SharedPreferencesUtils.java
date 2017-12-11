package com.haodai.topadvisor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.haodai.topadvisor.application.HaodaiApplication;

public class SharedPreferencesUtils {
	private static String CONFIG = "config";
	private static SharedPreferences sharedPreferences;
	public static void putString(Context context,String key,String value){
		if(sharedPreferences == null){
			sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		sharedPreferences.edit().putString(key, value).commit();
	}
	
	public static String getString(Context context,String key,String defValue){
		if(sharedPreferences == null){
			sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		return sharedPreferences.getString(key, defValue);
	}

    public static int getInt(Context context, String key, int defValue) {
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, defValue);
    }

    public static void putInt(Context context, String key, int defValue) {
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putInt(key, defValue).commit();
    }

    public static void putBool(Context context, String key, boolean value) {
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getBool(Context context, String key, boolean defValue) {
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static boolean getBool(String key, boolean defValue) {
        if(sharedPreferences == null){
            sharedPreferences = HaodaiApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }
}
