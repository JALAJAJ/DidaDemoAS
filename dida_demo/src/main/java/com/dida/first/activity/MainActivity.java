package com.dida.first.activity;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import com.dida.first.MainFragment;
import com.dida.first.R;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.SharedPreferencesUtils;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.DialogDouble;
import com.dida.first.view.DialogDouble.OnBtnClickListener;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * MainActivity作用 1.完成左侧拉,右侧拉,主界面的位置布局,网络监听
 * 
 * @author KingJA
 * 
 */

public class MainActivity extends FragmentActivity {
	/**
	 * 左侧拉是否打开
	 */
	public static boolean isOpenLeft;
	/**
	 * 右侧拉是否打开
	 */
	public static boolean isOpenRight;
	/**
	 * 侧拉栏View
	 */
	public static DrawerLayout drawer_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("MainActivity", "onCreate");
		ActivityFactory.mainActivity = this;
		setContentView(R.layout.activity_main);
		registerDateTransReceiver();
		initView();
		initData();
		initListener();
		initConnection();
	}

	private void initConnection() {
		String token = SharedPreferencesUtils.getStringData("TOKEN", "");
		Log.i("initConnection", "initConnection");
		if (TextUtils.isEmpty(token)) {
			 RongIM.connect(token, new RongIMClient.ConnectCallback() {

		            /**
		             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
		             */
		            @Override
		            public void onTokenIncorrect() {

		                Log.d("LoginActivity", "--onTokenIncorrect");
		            }

		            /**
		             * 连接融云成功
		             * @param userid 当前 token
		             */
		            @Override
		            public void onSuccess(String userid) {
		            	ToastUtil.showMyToast("onSuccess=="+userid);
		            }

		            /**
		             * 连接融云失败
		             * @param errorCode 错误码，可到官网 查看错误码对应的注释
		             */
		            @Override
		            public void onError(RongIMClient.ErrorCode errorCode) {

		                Log.d("LoginActivity", "--onError" + errorCode);
		            }
		        });
		}
		
	}

	/**
	 * 
	 */
	private void initListener() {
		drawer_layout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int arg0) {

			}

			@Override
			public void onDrawerSlide(View arg0, float arg1) {

			}

			@Override
			public void onDrawerOpened(View view) {
				switch (view.getId()) {
				case R.id.ft_left_slidingmenu:

					ToastUtil.singleToast(UIUtils.getContext(), "左边打开");
					MainActivity.isOpenLeft=true;
					break;
				case R.id.ft_right_slidingmenu:

					ToastUtil.singleToast(UIUtils.getContext(), "右边打开");
					MainActivity.isOpenRight=true;
					break;
				}

			}

			@Override
			public void onDrawerClosed(View view) {
				switch (view.getId()) {
				case R.id.ft_left_slidingmenu:

					ToastUtil.singleToast(UIUtils.getContext(), "左边关闭");
					MainActivity.isOpenLeft=false;
					break;
			case R.id.ft_right_slidingmenu:

					ToastUtil.singleToast(UIUtils.getContext(), "右边关闭");
					MainActivity.isOpenRight=false;
					break;
				}

			}
		});
		
	}

	/**
	 * 查找控件
	 */
	private void initView() {
		drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
	}

	/**
	 * 初始化布局
	 */
	private void initData() {
		showMainFragment();
	}

	/**
	 * 用MainFragment填充主页面的内容
	 */

	private void showMainFragment() {
		MainFragment fragment = new MainFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment, "MENU").commit();
	}
	public MainFragment getMenuFragment(){
		return (MainFragment) getSupportFragmentManager().findFragmentByTag("MENU");
	}
	private long firstTime;

	/**
	 * 复写onBackPress方法实现“再按一次退出程序”效果。
	 */

	@Override
	public void onBackPressed() {
		if (isOpenLeft) {
			drawer_layout.closeDrawer(Gravity.LEFT);
		} else if (isOpenRight) {
			drawer_layout.closeDrawer(Gravity.RIGHT);
		} else {

			long secondTime = System.currentTimeMillis();
			if ((secondTime - firstTime) > 2000) {
				ToastUtil.singleToast(MainActivity.this, "再按一次退出程序");
				firstTime = secondTime;
			} else {
				finish();
			}
		}
	}

	public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
	private NetChanged netChanged;

	private void registerDateTransReceiver() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(CONNECTIVITY_CHANGE_ACTION);
		filter.setPriority(1000);
		netChanged = new NetChanged();
		registerReceiver(netChanged, filter);
	}

	/**
	 * 
	 * @author KingJA
	 * @data 2015-6-26 下午3:07:38
	 * @use 动态注册的监听网络状态变化的广播
	 * 
	 */
	class NetChanged extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			switch (UIUtils.getNetworkType()) {
			case 0:
				Toast.makeText(context, "没有网络:", 0).show();
				/**
				 * 弹出网络设置对话框
				 */
				setNetwork(MainActivity.this);
				break;
			case 1:
				Toast.makeText(context, "当前网络:WIFI", 0).show();
				break;
			case 2:
				Toast.makeText(context, "当前网络:WAP网络", 0).show();
				break;
			case 3:
				Toast.makeText(context, "当前网络:NET网络", 0).show();
				break;

			default:
				break;
			}
		}

		/**
		 * 获取当前网络类型
		 * 
		 * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
		 */

		private AlertDialog dialog;
		

		/**
		 * 网络未连接时，调用设置方法
		 */
		private void setNetwork(Context context) {
			final DialogDouble setNetDialog = new DialogDouble(
					MainActivity.this, "", "网络不可用，请先设置网络！", "确定", "取消");
			setNetDialog.show();
			setNetDialog.setOnBtnClickListener(new OnBtnClickListener() {

				@Override
				public void onLeft() {

					Intent intent = null;
					/**
					 * 判断手机系统的版本！如果API大于10 就是3.0+
					 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
					 */
					if (android.os.Build.VERSION.SDK_INT > 10) {
						intent = new Intent(
								android.provider.Settings.ACTION_WIFI_SETTINGS);
					} else {
						intent = new Intent();
						ComponentName component = new ComponentName(
								"com.android.settings",
								"com.android.settings.WirelessSettings");
						intent.setComponent(component);
						intent.setAction("android.intent.action.VIEW");
					}
					startActivity(intent);

				}

				@Override
				public void onRight() {

				}
			});

		}
	}

	/**
	 * 解绑动态注册的广播
	 */
	@Override
	protected void onDestroy() {
		unregisterReceiver(netChanged);
		super.onDestroy();
	}

}
