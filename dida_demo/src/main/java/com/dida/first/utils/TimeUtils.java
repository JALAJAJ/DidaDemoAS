/**
 * 
 */
package com.dida.first.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.R.integer;

/**
 * @author KingJA
 * @data 2015-8-14 上午11:02:35
 * @use
 * 
 */
public class TimeUtils {

	private static int day;
	private static int hour;
	private static int min;
	private static int sec;

	/**
	 * 2015-07-29 11:17:00
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static int[] getDeadTime(String timeString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(timeString);
		long deadTime = date.getTime();
		long nowTime = System.currentTimeMillis();
		long distanceTime = deadTime - nowTime;
		if (distanceTime < 0) {
			int[] timeArr = { 0, 0, 0 };
			return timeArr;
		}
		day = (int) (distanceTime / (1000 * 60 * 60 * 24));
		hour = (int) (distanceTime % (1000 * 60 * 60 * 24) / (1000 * 60 * 60));
		min = (int) (distanceTime % (1000 * 60 * 60 * 24) % (1000 * 60 * 60) / (1000 * 60));
		sec = (int) (distanceTime % (1000 * 60));
		
		int[] timeArr = { day, hour, min ,sec};
		System.out.println("还剩" + day + "天" + hour + "小时" + min + "分"+ sec + "秒");
		return timeArr;
	}

}
