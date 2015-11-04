/**
 * 
 */
package com.dida.first.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dida.first.activity.NameActivity;
import com.dida.first.activity.RegisterActivity;

/**
 * @author		KingJA 
 * @data		2015-10-21 上午11:12:56 
 * @use			
 *
 */
public class ActivityUtil {
	public static void goActivity(Activity activity,Class clazz){
		Intent intent = new Intent(activity,
				clazz);
		activity.startActivity(intent);
	}
	public static void goActivityAndFinish(Activity activity,Class clazz){
		Intent intent = new Intent(activity,
				clazz);
		activity.startActivity(intent);
		activity.finish();
	}
	public static void goActivityWithBundle(Activity activity,Class clazz,Bundle bundle){
		Intent intent = new Intent(activity,
				clazz);
		intent.putExtras(bundle);
		activity.startActivity(intent);
	}

}
