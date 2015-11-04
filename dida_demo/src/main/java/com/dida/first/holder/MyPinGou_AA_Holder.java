/**
 * 
 */
package com.dida.first.holder;

import android.view.View;

import com.dida.first.R;
import com.dida.first.bean.BeanPinGouGroup;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-10-14 下午2:37:01 
 * @use			
 *
 */
public class MyPinGou_AA_Holder extends BaseHolder<BeanPinGouGroup> {

	@Override
	public View initView() {
		view = UIUtils.inflate(R.layout.item_mine_pingou_group);
		return view;
	}

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub

	}

}
