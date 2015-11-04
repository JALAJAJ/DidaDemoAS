package com.dida.first.fragment;

import com.dida.first.R;
import com.dida.first.activity.LoginActivity;
import com.dida.first.activity.Mine_FootPrint_Activity;
import com.dida.first.activity.Mine_Personal_Activity;
import com.dida.first.activity.Mine_MyOrder_Activity;
import com.dida.first.activity.Mine_PinGou_Activity;
import com.dida.first.activity.Mine_Product_Activity;
import com.dida.first.activity.Mine_Refund_Activity;
import com.dida.first.activity.Mine_Setting_Activity;
import com.dida.first.activity.Mine_ShopCar_Activity;
import com.dida.first.activity.Mine_Stone_Activity;
import com.dida.first.utils.CustomConstants;
import com.dida.first.utils.SharedPreferencesUtils;
import com.meg7.widget.CircleImageView;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WodeFragment extends Fragment implements OnClickListener {
	private View view;
	private ImageView iv_mine_chat;
	private CircleImageView iv_mine_icon;
	private TextView tv_mine_login;
	private LinearLayout ll_mine_dianpu;
	private LinearLayout ll_mine_product;
	private LinearLayout ll_mine_zuji;
	private LinearLayout ll_mine_tuikuan;
	private RelativeLayout rl_mine_myorder;
	private RelativeLayout rl_mine_shoporder;
//	private RelativeLayout rl_mine_myfriends;
	private RelativeLayout rl_mine_myyaoyue;
	private RelativeLayout rl_mine_personial;
	private RelativeLayout rl_mine_setting;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.fragment_wode, null);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initListener();
		initData();
	}

	private void initView() {
		iv_mine_chat = (ImageView) view.findViewById(R.id.iv_mine_chat);
		iv_mine_icon = (CircleImageView) view.findViewById(R.id.iv_mine_icon);
		tv_mine_login = (TextView) view.findViewById(R.id.tv_mine_login);
		ll_mine_dianpu = (LinearLayout) view.findViewById(R.id.ll_mine_dianpu);
		ll_mine_product = (LinearLayout) view.findViewById(R.id.ll_mine_product);
		ll_mine_zuji = (LinearLayout) view.findViewById(R.id.ll_mine_zuji);
		ll_mine_tuikuan = (LinearLayout) view
				.findViewById(R.id.ll_mine_tuikuan);
		rl_mine_myorder = (RelativeLayout) view
				.findViewById(R.id.rl_mine_myorder);
		rl_mine_shoporder = (RelativeLayout) view
				.findViewById(R.id.rl_mine_shoporder);
//		rl_mine_myfriends = (RelativeLayout) view
//				.findViewById(R.id.rl_mine_myfriends);
		rl_mine_myyaoyue = (RelativeLayout) view
				.findViewById(R.id.rl_mine_myyaoyue);
		rl_mine_personial = (RelativeLayout) view
				.findViewById(R.id.rl_mine_personial);
		rl_mine_setting = (RelativeLayout) view
				.findViewById(R.id.rl_mine_setting);

	}

	private void initListener() {
		iv_mine_chat.setOnClickListener(this);
		iv_mine_icon.setOnClickListener(this);
		tv_mine_login.setOnClickListener(this);

		ll_mine_dianpu.setOnClickListener(this);
		ll_mine_product.setOnClickListener(this);
		ll_mine_zuji.setOnClickListener(this);
		ll_mine_tuikuan.setOnClickListener(this);

		rl_mine_myorder.setOnClickListener(this);
		rl_mine_shoporder.setOnClickListener(this);
//		rl_mine_myfriends.setOnClickListener(this);
		rl_mine_myyaoyue.setOnClickListener(this);
		rl_mine_personial.setOnClickListener(this);
		rl_mine_setting.setOnClickListener(this);

	}

	private void initData() {

	}

	@Override
	public void onStart() {
		Log.i("WodeFragment", "onStart 开始刷新");
		super.onStart();
		if (SharedPreferencesUtils.getBooleanData(CustomConstants.HASLOGIN,
				false)) {
			tv_mine_login.setText(SharedPreferencesUtils.getStringData(CustomConstants.USER_NAME, ""));
			tv_mine_login.setBackgroundResource(android.R.color.transparent);
		}else {
			tv_mine_login.setText("登录/注册");
			tv_mine_login.setBackgroundResource(R.drawable.mine_login_bg);
			
		}
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		Log.i("WodeFragment", "onHiddenChanged");
		super.onHiddenChanged(hidden);
		if (!hidden) {
			Log.i("WodeFragment", "checkIfLogin");
			// checkIfLogin();
		}
	}

	/**
	 * 检查是否已经登录
	 */
	private void checkIfLogin() {
		if (!SharedPreferencesUtils.getBooleanData(CustomConstants.HASLOGIN,
				false)) {
			goActivity(LoginActivity.class);
		}

	}

	@Override
	public void onClick(View v) {
		checkIfLogin();
		switch (v.getId()) {
		case R.id.tv_mine_login:
			goActivity(LoginActivity.class);
			break;
			//我的订单
		case R.id.rl_mine_myorder:
			goActivity(Mine_MyOrder_Activity.class);
			break;
			//待购订单
		case R.id.rl_mine_shoporder:
			goActivity(Mine_ShopCar_Activity.class);
			break;
			//我的好友
//		case R.id.rl_mine_myfriends:
//			
//			break;
			//我的拼购
		case R.id.rl_mine_myyaoyue:
			goActivity(Mine_PinGou_Activity.class);
			break;
			//个人中心
		case R.id.rl_mine_personial:
			goActivity(Mine_Personal_Activity.class);
			break;
			//我的足迹
		case R.id.ll_mine_zuji:
			goActivity(Mine_FootPrint_Activity.class);
			break;
			//收藏的店铺
		case R.id.ll_mine_dianpu:
			goActivity(Mine_Stone_Activity.class);
			break;
			//收藏的商品
		case R.id.ll_mine_product:
			goActivity(Mine_Product_Activity.class);
			break;
			//退款退货
		case R.id.ll_mine_tuikuan:
			goActivity(Mine_Refund_Activity.class);
			break;
			//设置
		case R.id.rl_mine_setting:
			goActivity(Mine_Setting_Activity.class);
			break;

		default:
			break;
		}

	}

	private void goActivity(Class clazz) {
		Intent intent = new Intent(getActivity(), clazz);
		startActivity(intent);
	}
}
