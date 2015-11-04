/**
 * 
 */
package com.dida.first.holder;

import android.view.View;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.bean.BeanAddress;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-9-21 下午3:14:58 
 * @use			
 *
 */
public class HolderAddress extends BaseHolder<BeanAddress> {

	private TextView tv_mine_personal_item_name;
	private TextView tv_mine_personal_item_phone;
	private TextView tv_mine_personal_item_address;
	private TextView tv_mine_personal_item_default;

	@Override
	public View initView() {
		view=UIUtils.inflate(R.layout.item_mine_personal_address);
		tv_mine_personal_item_name = (TextView) view.findViewById(R.id.tv_mine_personal_item_name);
		tv_mine_personal_item_phone = (TextView) view.findViewById(R.id.tv_mine_personal_item_phone);
		tv_mine_personal_item_address = (TextView) view.findViewById(R.id.tv_mine_personal_item_address);
		tv_mine_personal_item_default = (TextView) view.findViewById(R.id.tv_mine_personal_item_default);
		return view;
	}

	@Override
	public void refreshView() {
		BeanAddress addressBean = getData();
		if (addressBean.isDefault()) {
			tv_mine_personal_item_default.setVisibility(View.VISIBLE);
		}else {
			tv_mine_personal_item_default.setVisibility(View.GONE);
		}
		tv_mine_personal_item_name.setText(addressBean.getName());
		tv_mine_personal_item_phone.setText(addressBean.getPhone());
		tv_mine_personal_item_address.setText(addressBean.getProvince()+addressBean.getCity()+addressBean.getArea()+"  "+addressBean.getAddress());
	}

}
