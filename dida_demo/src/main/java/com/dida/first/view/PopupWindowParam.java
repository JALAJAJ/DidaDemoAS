package com.dida.first.view;

import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.bean.BeanParam;
import com.dida.first.utils.UIUtils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @author		KingJA 
 * @data		2015-9-15 下午1:38:47 
 * @use			
 *
 */
public class PopupWindowParam extends PopupWindowBaseDown<List<BeanParam>> {

	/**
	 * @param parentView
	 * @param activity
	 * @param data
	 */
	public PopupWindowParam(View parentView, Activity activity,
			List<BeanParam> data) {
		super(parentView, activity, data);
		// TODO Auto-generated constructor stub
	}
	View popupView;

	/**
	 * @param parentView
	 * @param activity
	 */
	

	@Override
	public View setPopupView(Activity activity) {
		popupView = View.inflate(activity, R.layout.popup_param, null);
		return popupView;
	}

	@Override
	public void initChildView() {
		ImageView iv_market_detail_param_close = (ImageView) popupView.findViewById(R.id.iv_market_detail_param_close);
		iv_market_detail_param_close.setOnClickListener(this);
		ListView mylv_market_detail_param = (ListView) popupView.findViewById(R.id.mylv_market_detail_param);
		mylv_market_detail_param.setAdapter(new MyAdapter(data));
	}

	@Override
	public void OnChildClick(View v) {
		switch (v.getId()) {
		case R.id.iv_market_detail_param_close:
			dismiss();
			break;

		default:
			break;
		}

	}
	class MyAdapter extends MyBaseListViewAdapter<BeanParam>{

		private TextView tv_market_detail_paramName;
		private TextView tv_market_detail_paramContent;
		public MyAdapter(List<BeanParam> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getItemView(int position, View convertView, ViewGroup parent) {
			if (convertView==null) {
				convertView=View.inflate(activity, R.layout.item_market_detail_param, null);
				tv_market_detail_paramName = (TextView) convertView.findViewById(R.id.tv_market_detail_paramName);
				tv_market_detail_paramContent = (TextView) convertView.findViewById(R.id.tv_market_detail_paramContent);
			}
			tv_market_detail_paramName.setText(list.get(position).paramName);
			tv_market_detail_paramContent.setText(list.get(position).paramContent);
			return convertView;
		}
		
	}
	@Override
	public int setPopupHeight() {
		// TODO Auto-generated method stub
		return screenHeight*3/5;
	}

	

}
