/**
 * 
 */
package com.dida.first.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.fragment.My_PinGou_AA_Fr_Bak;
import com.dida.first.fragment.My_PinGou_Group_Fr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingJA
 * @data 2015-10-14 下午1:22:21
 * @use
 * 
 */
public class Mine_PinGou_Activity extends BackTitleActivity {
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	private LinearLayout ll_pingou_group;
	private LinearLayout ll_pingou_aa;
	private TextView tv_pingou_group;
	private TextView tv_pingou_aa;
	private View iv_pingou_group;
	private View iv_pingou_aa;
	private ViewPager vp_mine_pingou;

	@Override
	public View setView() {
		view=View.inflate(Mine_PinGou_Activity.this, R.layout.activity_mine_pingou, null);
		return view;
	}

	@Override
	public void initView() {
		ll_pingou_group = (LinearLayout) view.findViewById(R.id.ll_pingou_group);
		ll_pingou_aa = (LinearLayout) view.findViewById(R.id.ll_pingou_aa);
		tv_pingou_group = (TextView) view.findViewById(R.id.tv_pingou_group);
		tv_pingou_aa = (TextView) view.findViewById(R.id.tv_pingou_aa);
		iv_pingou_group = (View) view.findViewById(R.id.iv_pingou_group);
		iv_pingou_aa = (View) view.findViewById(R.id.iv_pingou_aa);
		vp_mine_pingou = (ViewPager) view.findViewById(R.id.vp_mine_pingou);

	}

	@Override
	public void initDoNet() {
		fragmentList.add(new My_PinGou_Group_Fr());
		fragmentList.add(new My_PinGou_AA_Fr_Bak());
	}

	@Override
	public void initEvent() {
		ll_pingou_group.setOnClickListener(this);
		ll_pingou_aa.setOnClickListener(this);
		vp_mine_pingou.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
		vp_mine_pingou.setOnPageChangeListener(onPageChangeListener);

	}

	@Override
	public void initData() {
		setBackTitle("我的拼购");

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.ll_pingou_group:
			set(0);
			vp_mine_pingou.setCurrentItem(0);
			break;
		case R.id.ll_pingou_aa:
			set(1);
			vp_mine_pingou.setCurrentItem(1);
			break;

		default:
			break;
		}

	}

	private void set(int position) {
		reSet();
		switch (position) {
		case 0:
			tv_pingou_group.setTextColor(getResources().getColor(R.color.red));
			iv_pingou_group.setVisibility(View.VISIBLE);
			break;
		case 1:
			tv_pingou_aa.setTextColor(getResources().getColor(R.color.red));
			iv_pingou_aa.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
		
		
	}

	private void reSet() {
		tv_pingou_group.setTextColor(getResources().getColor(R.color.black));
		tv_pingou_aa.setTextColor(getResources().getColor(R.color.black));
		iv_pingou_group.setVisibility(View.INVISIBLE);
		iv_pingou_aa.setVisibility(View.INVISIBLE);
	}

	@Override
	public void setBackClick() {
		finish();

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
				set(0);
				break;
			case 1:
				set(1);
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
}
