package com.dida.first.activity;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;
import com.dida.first.wheelview.ChangeAddressDialog;
import com.dida.first.wheelview.ChangeAddressDialog.OnAddressCListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author KingJA
 * @data 2015-9-22 下午1:51:09
 * @use
 * 
 */
public class Personal_AddAddress_Activity extends BackTitleActivity {

	private TextView tv_mine_personal_add_address_province;
	private EditText et_mine_personal_add_address_address;
	private EditText et_mine_personal_add_address_phone;
	private EditText et_mine_personal_add_address_name;

	@Override
	public View setView() {
		view = View.inflate(this, R.layout.activity_mine_personal_add_address,
				null);
		return view;
	}

	@Override
	public void initView() {
		et_mine_personal_add_address_name = (EditText) view
				.findViewById(R.id.et_mine_personal_add_address_name);
		et_mine_personal_add_address_phone = (EditText) view
				.findViewById(R.id.et_mine_personal_add_address_phone);
		et_mine_personal_add_address_address = (EditText) view
				.findViewById(R.id.et_mine_personal_add_address_address);
		tv_mine_personal_add_address_province = (TextView) view
				.findViewById(R.id.tv_mine_personal_add_address_province);

	}

	@Override
	public void initDoNet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initEvent() {
		tv_mine_personal_add_address_province.setOnClickListener(this);

	}

	@Override
	public void initData() {
		setBackTitle("新增收货地址");
		setOnTextClickListener("完成", new OnTextClickListener() {

			@Override
			public void onTextClick() {
				ToastUtil.showMyToast("完成");
				//TODO

			}
		});

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.tv_mine_personal_add_address_province:
			ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
					this);
			mChangeAddressDialog.setAddress("浙江", "温州", "鹿城区");
			mChangeAddressDialog.show();
			mChangeAddressDialog.setAddresskListener(new OnAddressCListener() {

				@Override
				public void onClick(String province, String city, String area) {
					tv_mine_personal_add_address_province.setText(province
							+ "省  " + city + "市  " + area);
				}
			});
			break;

		default:
			break;
		}
	}

	@Override
	public void setBackClick() {
		finish();

	}

}
