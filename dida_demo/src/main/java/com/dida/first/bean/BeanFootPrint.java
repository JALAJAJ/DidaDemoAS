/**
 * 
 */
package com.dida.first.bean;

import java.util.List;

import android.R.bool;

/**
 * @author		KingJA 
 * @data		2015-9-24 下午3:35:08 
 * @use			
 *
 */
public class BeanFootPrint {
	public boolean ifShow;
	public String date;
	public List<ItemFootPrint> itemFootPrintList;
	public class ItemFootPrint{
		public boolean ifDelete;
		public boolean ifShow;
		public boolean ifCheck;
		public String imgUrl;
		public String price;
	}

}
