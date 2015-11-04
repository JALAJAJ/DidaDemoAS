package com.dida.first.activity;

import com.dida.first.R;
import com.dida.first.utils.SharedPreferencesUtils;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author KingJA
 * @data 2015-9-21 上午10:05:58
 * @use
 * 
 */
public class Personal_EditGenderk_Activity extends BackTitleActivity {

	private RelativeLayout rl_personal_gender_man;
	private RelativeLayout rl_personal_gender_woman;
	private ImageView iv_personal_gender_man;
	private ImageView iv_personal_gender_woman;
	private Button btn_personal_editgender_confirm;
	private String gender="男";

	@Override
	public View setView() {
		view=View.inflate(this, R.layout.activity_mine_personal_gender, null);
		return view;
	}

	@Override
	public void initView() {
		rl_personal_gender_man = (RelativeLayout) view.findViewById(R.id.rl_personal_gender_man);
		rl_personal_gender_woman = (RelativeLayout) view.findViewById(R.id.rl_personal_gender_woman);
		iv_personal_gender_man = (ImageView) view.findViewById(R.id.iv_personal_gender_man);
		iv_personal_gender_woman = (ImageView) view.findViewById(R.id.iv_personal_gender_woman);
		btn_personal_editgender_confirm = (Button) view.findViewById(R.id.btn_personal_editgender_confirm);
	}

	@Override
	public void initDoNet() {
		rl_personal_gender_man.setOnClickListener(this);
		rl_personal_gender_woman.setOnClickListener(this);
		btn_personal_editgender_confirm.setOnClickListener(this);
	}

	@Override
	public void initEvent() {
		
	}

	@Override
	public void initData() {
		setBackTitle("修改性别");
		initGender();
	}

	private void initGender() {
		String gender = SharedPreferencesUtils.getStringData("gender", "");
		if (gender=="男") {
			setGender(0);
		}
		if(gender=="女"){
			setGender(1);
		}
	}

	@Override
	public void onChildClick(View v) {
		reSet();
		switch (v.getId()) {
		case R.id.rl_personal_gender_man:
			setGender(0);
			break;
		case R.id.rl_personal_gender_woman:
			setGender(1);
			break;
		case R.id.btn_personal_editgender_confirm:
			upLoadGender(gender);
			resultGender();
			break;

		default:
			break;
		}
		
	}

	private void setGender(int position) {
		switch (position) {
		case 0:
			iv_personal_gender_man.setVisibility(View.VISIBLE);
			gender="男";
			break;
		case 1:
			iv_personal_gender_woman.setVisibility(View.VISIBLE);
			gender="女";
			break;

		default:
			break;
		}
		SharedPreferencesUtils.saveStringData("gender", gender);
	}

	/**
	 * 返回性别
	 */
	private void resultGender() {
		
		Intent intent=new Intent();
		intent.putExtra("gender", gender);
		setResult(20, intent);
		finish();
	}

	/**
	 * @param 上传性别
	 */
	private void upLoadGender(String gender) {
		// TODO Auto-generated method stub
		
	}

	private void reSet() {
		iv_personal_gender_man.setVisibility(View.GONE);
		iv_personal_gender_woman.setVisibility(View.GONE);
		
	}

	@Override
	public void setBackClick() {
		finish();
	}}
