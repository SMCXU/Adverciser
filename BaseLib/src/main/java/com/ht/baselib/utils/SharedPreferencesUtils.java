package com.ht.baselib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ht.baselib.application.HTApplication;


public class SharedPreferencesUtils {
	private static String CONFIG = "config";
	private static SharedPreferences sharedPreferences;

	private static void putString(String key, String value){
        if(sharedPreferences == null){
            sharedPreferences = HTApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
		sharedPreferences.edit().putString(key, value).commit();
	}

    public static String getString(String key, String defValue){
        if(sharedPreferences == null){
            sharedPreferences = HTApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
		return sharedPreferences.getString(key, defValue);
	}

    public static int getInt(String key, int defValue) {
        if(sharedPreferences == null){
            sharedPreferences = HTApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, defValue);
    }

    private static void putInt(String key, int defValue) {
        if(sharedPreferences == null){
            sharedPreferences = HTApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putInt(key, defValue).commit();
    }

    public static long getLong(String key, long defValue) {
        if(sharedPreferences == null){
            sharedPreferences = HTApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getLong(key, defValue);
    }

    private static void putLong(String key, long defValue) {
        if(sharedPreferences == null){
            sharedPreferences = HTApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putLong(key, defValue).commit();
    }

    public static boolean getBool(String key, boolean defValue) {
        if(sharedPreferences == null){
            sharedPreferences = HTApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    private static void putBool(String key, boolean value) {
        if(sharedPreferences == null){
            sharedPreferences = HTApplication.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key, value).commit();
    }


    public static void put(String key, Object value){
        if (value instanceof Boolean){
            putBool(key, (Boolean) value);
        } else if (value instanceof Integer){
            putInt(key, (Integer) value);
        } else if (value instanceof Long){
            putLong(key, (Long) value);
        } else if (value instanceof String){
            putString(key, (String) value);
        }
    }
}
