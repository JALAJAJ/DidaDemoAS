/**
 * 
 */
package com.dida.first.entity;

/**
 * @author		KingJA 
 * @data		2015-9-21 下午3:14:11 
 * @use			
 *
 */
public class BeanAddress {
	private boolean isDefault;
	private String name;
	private String phone;
	private String province;
	private String city;
	private String area;
	private String address;
	
	/**
	 * @param isDefault
	 * @param name
	 * @param phone
	 * @param province
	 * @param city
	 * @param area
	 * @param address
	 */
	public BeanAddress(boolean isDefault, String name, String phone,
			String province, String city, String area, String address) {
		super();
		this.isDefault = isDefault;
		this.name = name;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.area = area;
		this.address = address;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	

}
