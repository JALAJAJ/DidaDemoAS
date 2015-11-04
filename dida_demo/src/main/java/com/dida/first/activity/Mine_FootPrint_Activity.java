/**
 * 
 */
package com.dida.first.activity;

import java.util.ArrayList;
import java.util.List;

import com.dida.first.R;
import com.dida.first.fragment.Fragment_Foot_AA;
import com.dida.first.fragment.Fragment_Foot_Group;
import com.dida.first.fragment.Fragment_Foot_Real;
import com.dida.first.fragment.Fragment_Foot_Service;
import com.dida.first.utils.ToastUtil;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

/**
 * @author KingJA
 * @data 2015-9-24 下午1:35:14
 * @use
 * 
 */
public class Mine_FootPrint_Activity extends BackTitleActivity {

	private ViewPager vp_footprint;
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	private View view_footprint_real;
	private View view_footprint_service;
	private View view_footprint_group;
	private View view_footprint_aa;

	@Override
	public View setView() {
		view = View.inflate(Mine_FootPrint_Activity.this,
				R.layout.activity_mine_footprint, null);
		return view;
	}

	@Override
	public void initView() {
		ll_footprint_real = (LinearLayout) view.findViewById(R.id.ll_footprint_real);
		ll_footprint_service = (LinearLayout) view.findViewById(R.id.ll_footprint_service);
		ll_footprint_group = (LinearLayout) view.findViewById(R.id.ll_footprint_group);
		ll_footprint_aa = (LinearLayout) view.findViewById(R.id.ll_footprint_aa);
		vp_footprint = (ViewPager) view.findViewById(R.id.vp_footprint);
		view_footprint_real = view.findViewById(R.id.view_footprint_real);
		view_footprint_service = view.findViewById(R.id.view_footprint_service);
		view_footprint_group = view.findViewById(R.id.view_footprint_group);
		view_footprint_aa = view.findViewById(R.id.view_footprint_aa);
		initFragmentList();
	}

	private void initFragmentList() {
		fragmentList.add(new Fragment_Foot_Real());
		fragmentList.add(new Fragment_Foot_Service());
		fragmentList.add(new Fragment_Foot_Group());
		fragmentList.add(new Fragment_Foot_AA());
		
	}

	@Override
	public void initDoNet() {
		setBackTitle("我的足迹");

	}

	@Override
	public void initEvent() {
		ll_footprint_real.setOnClickListener(this);
		ll_footprint_service.setOnClickListener(this);
		ll_footprint_group.setOnClickListener(this);
		ll_footprint_aa.setOnClickListener(this);
		vp_footprint.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
		vp_footprint.setOnPageChangeListener(onPageChangeListener);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildClick(View v) {
		reSet();
		switch (v.getId()) {
		case R.id.ll_footprint_real:
			view_footprint_real.setVisibility(View.VISIBLE);
			vp_footprint.setCurrentItem(0);
			break;
		case R.id.ll_footprint_service:
			ll_footprint_group.setVisibility(View.VISIBLE);
			vp_footprint.setCurrentItem(1);
			break;
		case R.id.ll_footprint_group:
			ll_footprint_group.setVisibility(View.VISIBLE);
			vp_footprint.setCurrentItem(2);
			break;
		case R.id.ll_footprint_aa:
			ll_footprint_aa.setVisibility(View.VISIBLE);
			vp_footprint.setCurrentItem(3);
			break;

		default:
			break;
		}

	}

	@Override
	public void setBackClick() {
		// TODO Auto-generated method stub

	}
	class MyFragmentPagerAdapter extends FragmentPagerAdapter{

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragmentList.size();
		}
		
	}
	private int currentPosition;
	private OnPageChangeListener onPageChangeListener=new OnPageChangeListener() {
		

		@Override
		public void onPageSelected(int position) {
			currentPosition = position;
			reSet();
			switch (position) {
			case 0:
				view_footprint_real.setVisibility(View.VISIBLE);
				break;
			case 1:
				view_footprint_service.setVisibility(View.VISIBLE);
				break;
			case 2:
				view_footprint_group.setVisibility(View.VISIBLE);
				break;
			case 3:
				view_footprint_aa.setVisibility(View.VISIBLE);
				break;

			default:
				break;
			}
			
		}
		
	

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}
	};
	private LinearLayout ll_footprint_real;
	private LinearLayout ll_footprint_service;
	private LinearLayout ll_footprint_group;
	private LinearLayout ll_footprint_aa;
	private void reSet() {
		view_footprint_real.setVisibility(View.GONE);
		view_footprint_service.setVisibility(View.GONE);
		view_footprint_group.setVisibility(View.GONE);
		view_footprint_aa.setVisibility(View.GONE);
	}
}
