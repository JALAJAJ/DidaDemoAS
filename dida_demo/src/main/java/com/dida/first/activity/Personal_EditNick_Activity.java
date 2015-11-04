/**
 * 
 */
package com.dida.first.activity;

import com.dida.first.R;
import com.dida.first.utils.StringUtil;
import com.dida.first.utils.ToastUtil;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * @author KingJA
 * @data 2015-9-21 上午10:05:58
 * @use
 * 
 */
public class Personal_EditNick_Activity extends BackTitleActivity {

	private EditText et_personal_editnick_nick;
	private ImageView iv_personal_editnick_delete;

	@Override
	public View setView() {
		view = View.inflate(this, R.layout.activity_mine_personal_nick, null);
		return view;
	}

	@Override
	public void initView() {
		et_personal_editnick_nick = (EditText) view
				.findViewById(R.id.et_personal_editnick_nick);
		iv_personal_editnick_delete = (ImageView) view
				.findViewById(R.id.iv_personal_editnick_delete);
		btn_personal_editnick_confirm = (Button) view
				.findViewById(R.id.btn_personal_editnick_confirm);
	}

	@Override
	public void initDoNet() {

	}

	@Override
	public void initEvent() {
		et_personal_editnick_nick.addTextChangedListener(nickWatcher);
		iv_personal_editnick_delete.setOnClickListener(this);
		btn_personal_editnick_confirm.setOnClickListener(this);

	}

	@Override
	public void initData() {
		setBackTitle("修改昵称");

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.iv_personal_editnick_delete:
			et_personal_editnick_nick.setText("");
			iv_personal_editnick_delete.setVisibility(View.GONE);
			break;
		case R.id.btn_personal_editnick_confirm:
			
			upLoadNick();
			
			break;

		default:
			break;
		}

	}

	/**
	 * 上传昵称
	 */
	private void upLoadNick() {
	if (checkNick(nick)) {
		
		resultNick();
	}
		
	}
	/**
	 * @param nick2
	 * @return
	 */
	private boolean checkNick(String nick) {
		if (TextUtils.isEmpty(nick)) {
			ToastUtil.showMyToast("昵称不能为空");
			return false;
		}
		return true;
	}

	/**
	 * 返回昵称
	 */
	private void resultNick() {
		Intent intent = new Intent();
		intent.putExtra("nick", nick);
		setResult(10, intent);
		finish();
	}

	@Override
	public void setBackClick() {
		finish();

	}

	private TextWatcher nickWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			nick = et_personal_editnick_nick.getText().toString().trim();
			if (nick.length() > 0) {
				iv_personal_editnick_delete.setVisibility(View.VISIBLE);
			} else {
				iv_personal_editnick_delete.setVisibility(View.GONE);
			}

		}
	};
	private String nick;
	private Button btn_personal_editnick_confirm;

}
