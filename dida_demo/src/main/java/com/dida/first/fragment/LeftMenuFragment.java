package com.dida.first.fragment;

import java.util.ArrayList;
import java.util.List;

import com.dida.first.R;
import com.dida.first.activity.MainActivity;
import com.dida.first.adapter.LeftSindingMenuAdapter;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.CustomConstants;
import com.dida.first.utils.ToastUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LeftMenuFragment extends Base4Fragment {
	private List<String> leftTitleList;
	private ListView lv_left_slidingmenu;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initLeftSindingMenuTitle();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public View initFragmentView() {
		view=View.inflate(context, R.layout.fragment_left_slidingmenu, null);
		return view;
	}

	@Override
	public void initFragmentData() {
		initView();
		initListener();
	}

	private void initView() {
		lv_left_slidingmenu = (ListView) view.findViewById(R.id.lv_left_slidingmenu);
		
	}
	private void initListener() {
		lv_left_slidingmenu.setAdapter(new LeftSindingMenuAdapter(context, leftTitleList));
		lv_left_slidingmenu.setOnItemClickListener(onItemClickListener);
	}


	@Override
	public void doNet() {
		// TODO Auto-generated method stub

	}
	
	private OnItemClickListener onItemClickListener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			switch (position) {
			//团购单
			case 0:
				ToastUtil.singleToast(context, "团购单");
				break;
				//AA单
			case 1:
				ToastUtil.singleToast(context, "AA单");
				break;
				//直购单
			case 2:
				ToastUtil.singleToast(context, "直购单");
				break;
				//求购单
			case 3:
				ToastUtil.singleToast(context, "求购单");
				break;
				//我的订单
			case 4:
				break;
				//收藏商品
			case 5:
				break;
				//收藏任务
			case 6:
				ToastUtil.singleToast(context, "收藏任务");
				break;
				//我的足迹
			case 7:
				ToastUtil.singleToast(context, "我的足迹");
				break;
				//猜你喜欢
			case 8:
				ToastUtil.singleToast(context, "猜你喜欢");
				break;
			}
//			关闭左侧拉栏
//MainActivity.drawer_layout.closeDrawer(Gravity.LEFT);
//MainActivity.isOpenLeft=false;
			
		}
	};
private void initLeftSindingMenuTitle() {
		
		leftTitleList=new ArrayList<String>();
		leftTitleList.add("团购单");
		leftTitleList.add("AA单");
		leftTitleList.add("直购单");
		leftTitleList.add("求购单");
		leftTitleList.add("我的订单");
		leftTitleList.add("收藏商品");
		leftTitleList.add("收藏任务");
		leftTitleList.add("我的足迹");
		leftTitleList.add("猜你喜欢");
		
	}
}
