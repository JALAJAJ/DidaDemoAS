package com.dida.first.fragment;

import java.util.ArrayList;
import java.util.List;

import com.dida.first.R;
import com.dida.first.activity.ShaiDanSelectActivity;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.bean.ShaiDanItemBean;
import com.dida.first.bean.YaoYueBean.Res;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.GroupHolder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Index_Show_Fragment extends BaseHeadFragment implements
		OnCheckedChangeListener {
	private ListView lv_shaidan;
	private List<ShaiDanItemBean> beanList;
	private CheckBox cb_shaidan_checkall;
	private ShaidanAdapter shaidanAdapter;
	private RelativeLayout rl_shaidan_yaoyue;

	@Override
	public View setContentView() {

		contentView = View.inflate(context, R.layout.fragment_shaidan, null);
		return contentView;
	}

	@Override
	public void initFragmentNet() {

	}

	@Override
	public void onStart() {
		super.onStart();
		
		cb_shaidan_checkall.setChecked(false);
	}

	@Override
	public void initFragmentView() {
		setTitle("晒单");
		initBean();
		rl_shaidan_yaoyue = (RelativeLayout) contentView.findViewById(R.id.rl_shaidan_yaoyue);
		lv_shaidan = (ListView) contentView.findViewById(R.id.lv_shaidan);
		cb_shaidan_checkall = (CheckBox) contentView
				.findViewById(R.id.cb_shaidan_checkall);

	}

	/**
	 * 
	 */
	private void initBean() {
		beanList = new ArrayList<ShaiDanItemBean>();
		for (int i = 0; i < 15; i++) {
			ShaiDanItemBean bean = new ShaiDanItemBean();
			beanList.add(bean);
		}

	}

	@Override
	public void initFragmentEvent() {
		cb_shaidan_checkall.setOnCheckedChangeListener(this);
		rl_shaidan_yaoyue.setOnClickListener(this);
		shaidanAdapter = new ShaidanAdapter(beanList);
		lv_shaidan.setAdapter(shaidanAdapter);

	}

	/**
	 * 弹出搜索页面
	 */
	@Override
	public void onSearch() {

	}

	@Override
	public void onChildClick(View v) {
		if (v.getId()==R.id.rl_shaidan_yaoyue) {
			Intent intent=new Intent(ActivityFactory.mainActivity,ShaiDanSelectActivity.class);
			startActivity(intent);
		}

	}
	class ShaidanAdapter extends MyBaseListViewAdapter<ShaiDanItemBean> {
		public ShaidanAdapter(List<ShaiDanItemBean> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getItemView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = View
						.inflate(context, R.layout.item_shaidan, null);
				viewHolder.cb_item_shaidan = (CheckBox) convertView
						.findViewById(R.id.cb_item_shaidan);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.cb_item_shaidan.setChecked(list.get(position).isChecked);
			return convertView;
		}

		public void checkAll(boolean checked) {
			for (ShaiDanItemBean shaiDanItemBean : list) {
				shaiDanItemBean.isChecked = checked;
			}
			this.notifyDataSetChanged();
		}

		class ViewHolder {
			CheckBox cb_item_shaidan;
			TextView tv_share_title;
			TextView tv_share_state;
			TextView tv_share_des;
			TextView tv_share_price;
			TextView tv_share_createTime;
			TextView tv_share_deadTime;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		//屏蔽非点击设置触发，如sheChecked(boolean);
		if (!buttonView.isPressed())
			return;
		switch (buttonView.getId()) {
		case R.id.cb_shaidan_checkall:
			shaidanAdapter.checkAll(isChecked);
			break;
		default:
			break;
		}
	}

	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub
		
	}
}
