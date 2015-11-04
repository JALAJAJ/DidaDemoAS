/**
 * 
 */
package com.dida.first.activity;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;
import com.dida.first.wheelview.ChangeAddressDialog;
import com.dida.first.wheelview.ChangeAddressDialog.OnAddressCListener;

/**
 * @author		KingJA 
 * @data		2015-9-21 下午3:49:44 
 * @use			
 *
 */
public class Detail_Address_Activity extends BackTitleActivity {

	private LinearLayout ll_mine_personal_address_province;
	private LinearLayout ll_mine_personal_address_edit_name;
	private LinearLayout ll_mine_personal_address_edit_name_des;
	private EditText et_mine_personal_address_edit_name;
	private TextView tv_mine_personal_address_edit_name;

	private LinearLayout ll_mine_personal_address_edit_phone;
	private LinearLayout ll_mine_personal_address_edit_phone_des;
	private EditText et_mine_personal_address_edit_phone;
	private TextView tv_mine_personal_address_edit_phone;
	@Override
	public View setView() {
		view=View.inflate(this, R.layout.activity_mine_personal_address_edit, null);
		return view;
	}

	@Override
	public void initView() {
		
		
		ll_mine_personal_address_province = (LinearLayout) view.findViewById(R.id.ll_mine_personal_address_province);
		ll_mine_personal_address_edit_name_des = (LinearLayout) view.findViewById(R.id.ll_mine_personal_address_edit_name_des);
		ll_mine_personal_address_edit_name = (LinearLayout) view.findViewById(R.id.ll_mine_personal_address_edit_name);
		et_mine_personal_address_edit_name = (EditText) view.findViewById(R.id.et_mine_personal_address_edit_name);
		tv_mine_personal_address_edit_name = (TextView) view.findViewById(R.id.tv_mine_personal_address_edit_name);

		ll_mine_personal_address_edit_name_des = (LinearLayout) view.findViewById(R.id.ll_mine_personal_address_edit_name_des);
		ll_mine_personal_address_edit_phone = (LinearLayout) view.findViewById(R.id.ll_mine_personal_address_edit_phone);
		et_mine_personal_address_edit_phone = (EditText) view.findViewById(R.id.et_mine_personal_address_edit_phone);
		tv_mine_personal_address_edit_phone = (TextView) view.findViewById(R.id.tv_mine_personal_address_edit_phone);
	}

	@Override
	public void initDoNet() {

	}

	@Override
	public void initEvent() {
		ll_mine_personal_address_province.setOnClickListener(this);
		ll_mine_personal_address_edit_name.setOnClickListener(this);
		ll_mine_personal_address_edit_phone.setOnClickListener(this);

	}

	@Override
	public void initData() {
		setBackTitle("修改地址");

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.ll_mine_personal_address_edit_name:
			ll_mine_personal_address_edit_name_des.setVisibility(View.GONE);
			edit(et_mine_personal_address_edit_name,tv_mine_personal_address_edit_name);
			break;
		case R.id.ll_mine_personal_address_edit_phone:
			ll_mine_personal_address_edit_phone_des.setVisibility(View.GONE);
			edit(et_mine_personal_address_edit_phone,tv_mine_personal_address_edit_phone);
			break;
		case R.id.ll_mine_personal_address_province:
			ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
					this);
			mChangeAddressDialog.setAddress("浙江", "温州","鹿城区");
			mChangeAddressDialog.show();
			mChangeAddressDialog
					.setAddresskListener(new OnAddressCListener() {

						@Override
						public void onClick(String province, String city, String area) {
							ToastUtil.showMyToast(province + "-" + city+ "-" + area);
						}
					});
		
			break;
		default:
			break;
		}

	}

	private void edit(EditText editText,TextView textView) {
		editText.setVisibility(View.VISIBLE);
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		editText.setText(textView.getText());
		editText.setSelection(textView.getText().length());
		//打开软键盘
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	public void setBackClick() {
	finish();

	}

}
