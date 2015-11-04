package com.dida.first.fragment;

import com.dida.first.R;
import com.dida.first.activity.LoginActivity;
import com.dida.first.activity.MainActivity;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.listener.IfLoginClickListener;
import com.dida.first.utils.CustomConstants;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

public class RightMenuFragment extends Base4Fragment {

	private RelativeLayout rl_right_login_register;

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.rl_right_login_register:
			/**
			 * 调转到注册页面
			 */
			ToastUtil.singleToast(context, "登录注册");
			Intent intent =new Intent(ActivityFactory.mainActivity,LoginActivity.class);
			intent.putExtra(CustomConstants.TITLE, "登录");
			startActivity(intent);
			ActivityFactory.mainActivity.overridePendingTransition(R.anim.next_in, R.anim.next_out);
			
			break;

		default:
			break;
		}

	}

	@Override
	public View initFragmentView() {
		view=View.inflate(context, R.layout.fragment_right_slidingmenu, null);
		return view;
	}

	@Override
	public void initFragmentData() {
		initView();
		if (UIUtils.getHasLogin()) {
			//已经登录
			rl_right_login_register.setVisibility(View.GONE);
		}
		initListener();
		

	}

	private void initListener() {
		
	
		rl_right_login_register.setOnClickListener(new IfLoginClickListener() {
			
			@Override
			public Context setContext() {
				return ActivityFactory.mainActivity;
			}
			
			@Override
			public void onLoginedClick(View v) {
				
				
			}
		});
	}

	private void initView() {
		
		rl_right_login_register = (RelativeLayout) view.findViewById(R.id.rl_right_login_register);
		
	}

	@Override
	public void doNet() {

	}

}
