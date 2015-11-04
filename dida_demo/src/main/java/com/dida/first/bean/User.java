package com.dida.first.bean;

public class User {
	private String des;
	private double latitude; // 地址纬度
	private double longitude; // 地址经度
	private int sex; // 性别 0 为女 1为男
	private String sign;
	private String thumb;
	private String userId;
	private String userName;
	@Override
	public String toString() {
		return "User [des=" + des + ", latitude=" + latitude + ", longitude="
				+ longitude + ", sex=" + sex + ", sign=" + sign + ", thumb="
				+ thumb + ", userId=" + userId + ", userName=" + userName + "]";
	}
	

}
