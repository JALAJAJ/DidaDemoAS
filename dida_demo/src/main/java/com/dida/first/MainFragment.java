package com.dida.first;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.activity.MainActivity;
import com.dida.first.fragment.Index_Market_Fragment;
import com.dida.first.fragment.Index_Pingou_Fragment;
import com.dida.first.fragment.Index_Show_Fragment;
import com.dida.first.fragment.Index_Mine_Fragment;

/**
 * @author KingJA
 * @data 2015-6-19 上午10:54:30
 * @use MainFragment逻辑实现 1.主界面布局。
 *      2.底部Tab标签的切换，同时联动主界面布局fl_main_content的切换不同的Fragment
 */
public class MainFragment extends BaseFragment implements OnClickListener {
	public static FragmentManager fragmentManager;
	private LinearLayout ll_tab_yaoyue;
	private LinearLayout ll_tab_xianggou;
	private LinearLayout ll_tab_saidan;
	private LinearLayout ll_tab_wode;
	private ImageView iv_tab_yaoyue;
	private ImageView iv_tab_xianggou;
	private ImageView iv_tab_saidan;
	private ImageView iv_tab_wode;
	private TextView tv_tab_yaoyue;
	private TextView tv_tab_xianggou;
	private TextView tv_tab_saidan;
	private TextView tv_tab_wode;
	private Index_Pingou_Fragment yaoyueFragment;
	private Index_Market_Fragment xianggouFragment;
	private Index_Show_Fragment Index_Show_Fragment;
	private Index_Mine_Fragment indexMineFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragmentManager = ((MainActivity) context).getSupportFragmentManager();
	}

	@Override
	public View initView() {
		view = View.inflate(context, R.layout.fragment_main, null);
		findView();
		return view;
	}

	/**
	 * 查找控件
	 */
	private void findView() {
		ll_tab_yaoyue = (LinearLayout) view.findViewById(R.id.ll_tab_share);
		ll_tab_xianggou = (LinearLayout) view.findViewById(R.id.ll_tab_want);
		ll_tab_saidan = (LinearLayout) view.findViewById(R.id.ll_tab_interest);
		ll_tab_wode = (LinearLayout) view.findViewById(R.id.ll_tab_show);
		iv_tab_yaoyue = (ImageView) view.findViewById(R.id.iv_tab_share);
		iv_tab_xianggou = (ImageView) view.findViewById(R.id.iv_tab_want);
		iv_tab_saidan = (ImageView) view.findViewById(R.id.iv_tab_interest);
		iv_tab_wode = (ImageView) view.findViewById(R.id.iv_tab_show);
		tv_tab_yaoyue = (TextView) view.findViewById(R.id.tv_tab_share);
		tv_tab_xianggou = (TextView) view.findViewById(R.id.tv_tab_want);
		tv_tab_saidan = (TextView) view.findViewById(R.id.tv_tab_interest);
		tv_tab_wode = (TextView) view.findViewById(R.id.tv_tab_show);
	}

	/**
	 * 添加事件
	 */
	private void inivListener() {
		ll_tab_yaoyue.setOnClickListener(this);
		ll_tab_xianggou.setOnClickListener(this);
		ll_tab_saidan.setOnClickListener(this);
		ll_tab_wode.setOnClickListener(this);
	}

	/**
	 * 初始化布局
	 */
	@Override
	public void initDate() {
		inivListener();
		setTab(0);
	}

	/**
	 * 底部状态栏TAB的点击事件
	 */

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ll_tab_share:
			setTab(0);
			break;
		case R.id.ll_tab_want:
			setTab(1);
			break;
		case R.id.ll_tab_interest:
			setTab(2);
			break;
		case R.id.ll_tab_show:
			setTab(3);
			break;

		}

	}
/**
 * 回到第一个标签页
 */
	public void setFirst() {
		setTab(0);
	}

	/**
	 * 选择对应的Fragment
	 */
	// TODO 需要重构
	private void setTab(int position) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		// clearPopuWindow();
		resetState();
		switch (position) {
		case 0:
			iv_tab_yaoyue.setBackgroundResource(R.drawable.yaoyue_selected);
			tv_tab_yaoyue.setTextColor(getResources().getColor(
					R.color.red));
			if (yaoyueFragment == null) {
				// 如果yaoyueFragment为空，则创建一个并添加到界面上
				yaoyueFragment = new Index_Pingou_Fragment();
				transaction.add(R.id.fl_main_content, yaoyueFragment);
			} else {
				// 如果yaoyueFragment不为空，则直接将它显示出来
				transaction.show(yaoyueFragment);
			}
			break;
		case 1:
			iv_tab_xianggou.setBackgroundResource(R.drawable.xianggou_sel);
			tv_tab_xianggou.setTextColor(getResources().getColor(
					R.color.red));
			if (xianggouFragment == null) {
				xianggouFragment = new Index_Market_Fragment();
				transaction.add(R.id.fl_main_content, xianggouFragment);
			} else {
				transaction.show(xianggouFragment);
			}
			break;
		case 2:
			iv_tab_saidan.setBackgroundResource(R.drawable.saidan_sel);
			tv_tab_saidan.setTextColor(getResources().getColor(
					R.color.red));
			if (Index_Show_Fragment == null) {
				// 如果ShaidanFragment为空，则创建一个并添加到界面上
				Index_Show_Fragment = new Index_Show_Fragment();
				transaction.add(R.id.fl_main_content, Index_Show_Fragment);
			} else {
				// 如果ShaidanFragment不为空，则直接将它显示出来
				transaction.show(Index_Show_Fragment);
			}
			break;
		case 3:
			iv_tab_wode.setBackgroundResource(R.drawable.wode_sel);
			tv_tab_wode.setTextColor(getResources().getColor(
					R.color.red));
			if (indexMineFragment == null) {
				indexMineFragment = new Index_Mine_Fragment();
				transaction.add(R.id.fl_main_content, indexMineFragment);
			} else {
				transaction.show(indexMineFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * 复位TAB状态
	 */
	private void resetState() {
		iv_tab_yaoyue.setBackgroundResource(R.drawable.yaoyue_nor);
		iv_tab_xianggou.setBackgroundResource(R.drawable.xianggou_nomal);
		iv_tab_saidan.setBackgroundResource(R.drawable.saidan_nomal);
		iv_tab_wode.setBackgroundResource(R.drawable.wode_nomal);
		tv_tab_yaoyue.setTextColor(getResources().getColor(
				R.color.tab_text_nomal));
		tv_tab_xianggou.setTextColor(getResources().getColor(
				R.color.tab_text_nomal));
		tv_tab_saidan.setTextColor(getResources().getColor(
				R.color.tab_text_nomal));
		tv_tab_wode.setTextColor(getResources()
				.getColor(R.color.tab_text_nomal));

	}

	private void hideFragments(FragmentTransaction transaction) {
		if (yaoyueFragment != null) {
			transaction.hide(yaoyueFragment);
		}
		if (xianggouFragment != null) {
			transaction.hide(xianggouFragment);
		}
		if (Index_Show_Fragment != null) {
			transaction.hide(Index_Show_Fragment);
		}
		if (indexMineFragment != null) {
			transaction.hide(indexMineFragment);
		}
	}

}
