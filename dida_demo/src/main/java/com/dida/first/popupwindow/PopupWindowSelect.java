/**
 * 
 */
package com.dida.first.popupwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.bean.BeanParamsSelect;
import com.dida.first.bean.BeanParamsSelect.ParamItem;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.FlowLayout;
import com.dida.first.view.AbsListView.MyListView;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author KingJA
 * @data 2015-9-18 下午3:18:50
 * @use
 * 
 */
public class PopupWindowSelect extends
		PopupWindowBaseDown<List<BeanParamsSelect>> {
	private static String[] paramsArr = { "深黄色", "绿色", "紫色", "红色", "橘色", "天蓝色",
			"黑色", "玫瑰色", "普通版", "美版", "港版", "超人内衣版", "美人鱼版", "蝙蝠侠紧身版",
			"无敌绿巨人狂化版", "蜘蛛侠版", "咸蛋超人升级版", "洪七公版" };
	private MyListView mylv_market_detail_select;
	private static List<BeanParamsSelect> paramsSelectList = new ArrayList<BeanParamsSelect>();
	private MyAdapter myAdapter;
	private TextView tv_param_select_count;
	private int count = 1;

	public PopupWindowSelect(View parentView, Activity activity,
			List<BeanParamsSelect> data) {
		super(parentView, activity, data);

	}

	/**
	 * 虚拟数据
	 */
	private void initData() {
		paramsSelectList.clear();
		for (int i = 0; i < 3; i++) {
			List<ParamItem> arrayList = new ArrayList<ParamItem>();
			BeanParamsSelect beanParamsSelect = new BeanParamsSelect();
			beanParamsSelect.selectString = "选择参数";
			beanParamsSelect.paramList = arrayList;
			int count = new Random().nextInt(8) + 4;
			Log.i("count", count + "");
			for (int j = 0; j < count; j++) {
				int nextInt = new Random().nextInt(paramsArr.length);
				Log.i("nextInt", paramsArr[nextInt]);
				arrayList.add(beanParamsSelect.new ParamItem(
						paramsArr[nextInt], false));
			}
			paramsSelectList.add(new BeanParamsSelect("参数分类", arrayList));
		}

	}

	@Override
	public int setPopupHeight() {
		return screenHeight * 3 / 5;
	}

	@Override
	public View setPopupView(Activity activity) {
		popupView = View.inflate(activity, R.layout.popup_select, null);
		return popupView;
	}

	@Override
	public void initChildView() {
		initData();
		tv_param_select_count = (TextView) popupView
				.findViewById(R.id.tv_param_select_count);
		ImageView iv_param_select_add = (ImageView) popupView
				.findViewById(R.id.iv_param_select_add);
		ImageView iv_param_select_reduce = (ImageView) popupView
				.findViewById(R.id.iv_param_select_reduce);
		ImageView iv_mark_detail_close = (ImageView) popupView
				.findViewById(R.id.iv_mark_detail_close);
		mylv_market_detail_select = (MyListView) popupView
				.findViewById(R.id.mylv_market_detail_select);
		myAdapter = new MyAdapter(paramsSelectList);
		mylv_market_detail_select.setAdapter(myAdapter);
		iv_mark_detail_close.setOnClickListener(this);
		TextView tv_market_select = (TextView) popupView
				.findViewById(R.id.tv_market_select);
		tv_market_select.setOnClickListener(this);
		iv_param_select_add.setOnClickListener(this);
		iv_param_select_reduce.setOnClickListener(this);
	}

	@Override
	public void OnChildClick(View v) {
		switch (v.getId()) {
		case R.id.tv_market_select:
			String params = myAdapter.getParams();
			ToastUtil.showMyToast(params + count + "个");
			dismiss();
			break;
		case R.id.iv_mark_detail_close:
			dismiss();
			break;
		case R.id.iv_param_select_add:
			count++;
			tv_param_select_count.setText(String.valueOf(count));
			break;
		case R.id.iv_param_select_reduce:
			if (count > 1) {
				count--;
				tv_param_select_count.setText(String.valueOf(count));
			}
			break;

		default:
			break;
		}

	}

	class MyAdapter extends MyBaseListViewAdapter<BeanParamsSelect> {
		public MyAdapter(List<BeanParamsSelect> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getItemView(final int position, View convertView,
				ViewGroup parent) {
			View view = UIUtils.inflate(R.layout.item_param_select);
			TextView tv_param_slecet_name = (TextView) view
					.findViewById(R.id.tv_param_slecet_name);
			FlowLayout fl_param_slecet_params = (FlowLayout) view
					.findViewById(R.id.fl_param_slecet_params);

			String paramName = list.get(position).selectString;
			List<ParamItem> paramList = list.get(position).paramList;

			tv_param_slecet_name.setText(paramName);
			for (final ParamItem paramItem : paramList) {
				final TextView tv = (TextView) UIUtils
						.inflate(R.layout.textview_flowlayout);
				tv.setText(paramItem.paramString);
				if (paramItem.isCheck) {
					tv.setBackgroundResource(R.drawable.shape_lnull_bred_r4);
					tv.setTextColor(activity.getResources().getColor(
							R.color.white));
				} else {
					tv.setBackgroundResource(R.drawable.shape_l1grayd_bwhite_r4);
					tv.setTextColor(activity.getResources().getColor(
							R.color.gray_deep));
				}
				tv.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						setFalse(position);
						paramItem.isCheck = true;
						notifyDataSetChanged();

					}
				});
				fl_param_slecet_params.addView(tv);
			}
			return view;
		}

		public void setFalse(int position) {
			BeanParamsSelect beanParamsSelect = list.get(position);
			List<ParamItem> paramList = beanParamsSelect.paramList;
			for (ParamItem paramItem : paramList) {
				paramItem.isCheck = false;
			}
			notifyDataSetChanged();
		}

		public String getParams() {
			StringBuffer sb = new StringBuffer();
			for (BeanParamsSelect beanParamsSelect : list) {
				List<ParamItem> paramList = beanParamsSelect.paramList;
				for (ParamItem paramItem : paramList) {
					if (paramItem.isCheck) {
						Log.i("paramItem.paramString", paramItem.paramString);
						sb.append(paramItem.paramString);
					}
				}
			}
			return sb.toString();
		}
	}
}
