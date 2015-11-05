/**
 * 
 */
package com.dida.first.bean;

/**
 * @author		KingJA 
 * @data		2015-8-5 下午4:35:47 
 * @use			
 *
 */
public class ShaiDanItemBean {

	private boolean isChecked;
	private int productId;

	public boolean isChecked() {
		return isChecked;
	}

	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public ShaiDanItemBean(boolean isChecked, int productId) {
		this.isChecked = isChecked;
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ShaiDanItemBean{" +
				"isChecked=" + isChecked +
				", productId=" + productId +
				'}';
	}
}
