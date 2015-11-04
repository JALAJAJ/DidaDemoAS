package com.dida.first.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.dida.first.R;

import java.util.ArrayList;
import java.util.List;

public class PingGouFragment extends BaseHeadFragment {
//	private TextView tv_market_product;
//	private TextView tv_market_service;
	private ViewPager vp_pinggou;
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	@Override
	public View setContentView() {
		contentView = View.inflate(context, R.layout.fragment_pinggou, null);
		return contentView;
	}

	@Override
	public void initFragmentView() {
//		tv_market_product = (TextView) contentView.findViewById(R.id.tv_market_product);
//		tv_market_service = (TextView) contentView.findViewById(R.id.tv_market_service);
		vp_pinggou = (ViewPager) contentView.findViewById(R.id.vp_pinggou);
	
	}
	@Override
	public void initFragmentEvent() {
//		tv_market_product.setOnClickListener(this);
//		tv_market_service.setOnClickListener(this);
		vp_pinggou.setAdapter(new MyFragmentPagerAdapter(supportFragmentManager));
	}


	

	@Override
	public void onSearch() {

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
//		case R.id.tv_market_product:
//			break;
//		case R.id.tv_market_service:
//			break;

		default:
			break;
		}

	}

	@Override
	public void initFragmentNet() {
		fragmentList.add(new Fragment_PingGou_Group());

	}
	class MyFragmentPagerAdapter extends FragmentPagerAdapter{

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			Log.i("getItem", fragmentList.size()+"");
			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragmentList.size();
		}
		
	}
	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub
		
	}
	

}
