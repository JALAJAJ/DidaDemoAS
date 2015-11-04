package com.dida.first.utils;


import com.dida.first.application.App;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	private static SharedPreferences sharedPreferences=App.getSP();
	//存储
	public static void saveStringData(String key,String value){
		sharedPreferences.edit().putString(key, value).commit();
	}
	public static void saveBooleanData(String key,boolean value){
		sharedPreferences.edit().putBoolean(key, value).commit();
	}
	
	//读取
	public static String getStringData(String key,String defValue){
		return sharedPreferences.getString(key, defValue);
	}
	public static boolean getBooleanData(String key,boolean defValue){
		return sharedPreferences.getBoolean(key, defValue);
	}
}
