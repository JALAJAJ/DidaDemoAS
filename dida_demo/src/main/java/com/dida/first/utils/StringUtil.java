package com.dida.first.utils;

import java.util.UUID;


public class StringUtil {
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	public static String getUUIDString(){
		String UUIDString = UUID.randomUUID().toString();
		String newUUIDString = UUIDString.replace("-", "");
		String substring = newUUIDString.substring(0, 10);
		return substring;
	}
}
