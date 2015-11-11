package com.dida.first.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.application.App;
import com.dida.first.bean.BeanSms;
import com.dida.first.textwatcher.MyTextWatcher;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.HttpUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.VolleyStringRequest;
import com.dida.first.view.DialogDouble;
import com.dida.first.view.DialogDouble.OnDoubleClickListener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author KingJA
 * @data 2015-6-24 下午3:01:51
 * @use
 * 
 */
public class RegisterActivity extends BackTitleActivity {

	private EditText et_register_phone;
	private EditText et_register_code;
	private EditText et_register_password;
	private String phone;
	private String password;
	private ImageView iv_register_regex_phone;
	private ImageView iv_register_regex_password;
	private Button btn_register_get_code;
	private LinearLayout ll_register_focus;
	private Button btn_register;
	private String code;
	private String receivedCode;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			BeanSms beanSms=(BeanSms) msg.obj;
			if (beanSms.getCode()==1) {
				receivedCode=beanSms.getRes();
				
			}else {
				 showDoubleDialog();
			}
		};
	};

	/**
	 * 验证所有：手机格式，验证码，密码
	 */
	private boolean checkAll() {
		if (checkPhone() && checkCode() && checkPassword()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 验证手机号码
	 */
	private boolean checkPhone() {
		// 判断非空
		if (TextUtils.isEmpty(phone)) {
			ToastUtil.showMyToast("手机号码不能为空");
			return false;
		}

		// 判断手机号格式
		if (!Pattern.matches(
				"^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$", phone)) {
			ToastUtil.showMyToast("手机号码格式不对");
			return false;
		}
		// 判断手机号是否注册过
		if ("18888888888".equals(phone)) {
			 showDoubleDialog();
			return false;
		}
		return true;

	}

	/**
	 * 
	 */
	private void showDoubleDialog() {
		DialogDouble doubleDialog = new DialogDouble(
				RegisterActivity.this,
				"这个号码已经被注册过了，请换一个手机号码注册或者直接登录", "去登录", "换个手机");
		doubleDialog.setOnDoubleClickListener(new OnDoubleClickListener() {

			@Override
			public void onLeft() {
				finish();

			}

			@Override
			public void onRight() {
				et_register_phone.setText("");

			}
		});
	}

	/**
	 * 验证验证码
	 */
	private boolean checkCode() {
		code = et_register_code.getText().toString().trim();
		// 判断非空
		if (TextUtils.isEmpty(code)) {
			ToastUtil.showMyToast("请输入验证码");
			return false;
		}
		if (!receivedCode.equals(code)) {
			ToastUtil.showMyToast("验证码错误");
			return false;
		}
		/**
		 * TODO 判断验证码正确性
		 */
		return true;

	}

	/**
	 * 验证密码
	 */
	private boolean checkPassword() {
		// 判断非空
		if (TextUtils.isEmpty(password)) {
			ToastUtil.showMyToast("密码不能为空");
			return false;
		}
		if (!Pattern.matches("[a-zA-Z0-9]{6,12}", password)) {
			ToastUtil.showMyToast("密码须为6-12位字母或数字组合");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public View setView() {
		view = View.inflate(RegisterActivity.this, R.layout.activity_register,
				null);
		return view;
	}

	@Override
	public void initView() {
		btn_register = (Button) view.findViewById(R.id.btn_register);
		ll_register_focus = (LinearLayout) view
				.findViewById(R.id.ll_register_focus);
		btn_register_get_code = (Button) view
				.findViewById(R.id.btn_register_get_code);
		iv_register_regex_phone = (ImageView) view
				.findViewById(R.id.iv_register_regex_phone);
		iv_register_regex_password = (ImageView) view
				.findViewById(R.id.iv_register_regex_password);
		et_register_phone = (EditText) view
				.findViewById(R.id.et_register_phone);
		et_register_code = (EditText) view.findViewById(R.id.et_register_code);
		et_register_password = (EditText) view
				.findViewById(R.id.et_register_password);
		
	}

	@Override
	public void initDoNet() {

	}

	@Override
	public void initEvent() {
		btn_register.setClickable(false);
		btn_register_get_code.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		et_register_phone.addTextChangedListener(phoneTextWatcher);
		et_register_password.addTextChangedListener(passwordTextWatcher);
	}

	@Override
	public void initData() {
		setBackTitle("1/2  注册");
	}

	private TextWatcher phoneTextWatcher = new MyTextWatcher() {

		@Override
		public void onAfterTextChanged() {
			phone = et_register_phone.getText().toString().trim();
			if (phone.length() > 0) {
				btn_register.setClickable(true);
				btn_register.setBackgroundResource(R.drawable.bg_btn_red4);
			} else {
				btn_register.setClickable(false);
				btn_register.setBackgroundResource(R.drawable.bg_btn_gray);
			}
			if (Pattern.matches("^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\\d{8}$",
					phone)) {
				iv_register_regex_phone.setVisibility(View.VISIBLE);
				btn_register_get_code
						.setBackgroundResource(R.drawable.bg_btn_red4);
			} else {
				btn_register_get_code
						.setBackgroundResource(R.drawable.bg_btn_gray);
				iv_register_regex_phone.setVisibility(View.GONE);
			}
		}

	};
	private TextWatcher passwordTextWatcher = new MyTextWatcher() {

		@Override
		public void onAfterTextChanged() {
			password = et_register_password.getText().toString().trim();
			if (Pattern.matches("[a-zA-Z0-9]{6,12}", password)) {
				iv_register_regex_password.setVisibility(View.VISIBLE);
			} else {
				iv_register_regex_password.setVisibility(View.GONE);
			}

		}
	};
	private TimeCount timeCount;

	class TimeCount extends CountDownTimer {

		/**
		 * @param millisInFuture
		 * @param countDownInterval
		 */
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			btn_register_get_code.setClickable(false);
			btn_register_get_code.setText(millisUntilFinished / 1000 + "秒后重发");
		}

		@Override
		public void onFinish() {
			onCodeComplete();
		}


	}
	private void onCodeComplete() {
		timeCount.cancel();
		btn_register_get_code.setText("获取验证码");
		btn_register_get_code.setClickable(true);
		btn_register_get_code.setBackgroundResource(R.drawable.bg_btn_red4);
	}
	@Override
	public void setBackClick() {
		if (timeCount != null) {
			timeCount.cancel();
		}
		finish();
	}

	@Override
	public void onChildClick(View v) {

		switch (v.getId()) {
		case R.id.btn_register_get_code:
			if (checkPhone()) {
				postByVolley();
				 btn_register_get_code.setBackgroundResource(R.drawable.bg_btn_gray);
				 timeCount = new TimeCount(60 * 1000, 1000);
				 timeCount.start();
				 // 截取输入框焦点
				 ll_register_focus.setFocusable(true);
				 ll_register_focus.setFocusableInTouchMode(true);
				 ll_register_focus.requestFocus();
				 // 关闭键盘
				 closeKeyBoard();
			}

			break;

		case R.id.btn_register:
			if (checkAll()) {
				Bundle bundle = new Bundle();
				bundle.putString("phone", phone);
				bundle.putString("password",password);
				ToastUtil.showMyToast("开始取名字");
				ActivityUtil.goActivityWithBundle(RegisterActivity.this, NameActivity.class, bundle);
				finish();
			}

			break;
		}

	}

	private void postByVolley(){
        VolleyStringRequest<BeanSms> userRequest = new VolleyStringRequest<BeanSms>(HttpUtil.REGISTER_SMS, BeanSms.class,null, new Listener<BeanSms>()  
        {  
            @Override  
            public void onResponse(BeanSms beanSms)  
            {  
            	Message msg = handler.obtainMessage();
            	msg.obj=beanSms;
            	handler.sendMessage(msg);
                Log.e("onResponse", beanSms.toString());  
            }  
        },  new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				 Log.e("onErrorResponse", error.toString());  
				
			}
		}){@Override
		protected Map<String, String> getParams() throws AuthFailureError {
			Map<String, String> map = new HashMap<String, String>();  
			map.put("mobile", "18868269007");  
			return map;
		}};  
  
        App.getQueue().add(userRequest); 
	}

	

	/**
	 * 关闭键盘
	 */
	private void closeKeyBoard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	protected void onDestroy() {
		if (timeCount != null) {
			timeCount.cancel();
		}
		super.onDestroy();
	}
}
