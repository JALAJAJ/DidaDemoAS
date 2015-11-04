package com.dida.first.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dida.first.R;

import java.util.ArrayList;
import java.util.List;

public class Index_Market_Fragment extends BaseHeadFragment {
	private TextView tv_market_product;
	private TextView tv_market_service;
	private ViewPager vp_market;
	private List<Fragment> marketList=new ArrayList<Fragment>();
	@Override
	public View setContentView() {
		contentView = View.inflate(context, R.layout.fragment_market, null);
		Log.i("Index_Market_Fragment","setContentView");
		return contentView;
	}

	@Override
	public void initFragmentView() {
		tv_market_product = (TextView) contentView.findViewById(R.id.tv_market_product);
		tv_market_service = (TextView) contentView.findViewById(R.id.tv_market_service);
		vp_market = (ViewPager) contentView.findViewById(R.id.vp_market);
	
	}
	@Override
	public void initFragmentEvent() {
		tv_market_product.setOnClickListener(this);
		tv_market_service.setOnClickListener(this);
		vp_market.setAdapter(new MyFragmentPagerAdapter(supportFragmentManager));
	}


	

	@Override
	public void onSearch() {

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.tv_market_product:
			break;
		case R.id.tv_market_service:
			break;

		default:
			break;
		}

	}

	@Override
	public void initFragmentNet() {
		marketList.add(new Market_Real_Fragment());
		Log.i("Index_Market_Fragment", "add Market_Real_Fragment()");
	}
	class MyFragmentPagerAdapter extends FragmentPagerAdapter{

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			return marketList.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return marketList.size();
		}
		
	}
	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub
		
	}
	

}
