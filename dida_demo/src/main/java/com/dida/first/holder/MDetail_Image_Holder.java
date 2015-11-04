package com.dida.first.holder;

import java.util.ArrayList;
import java.util.List;

import com.dida.first.R;
import com.dida.first.activity.MyZoomImageActivity;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.MyGridView;
import com.lidroid.xutils.BitmapUtils;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author KingJA
 * @data 2015-9-15 下午5:09:33
 * @use
 * 
 */
public class MDetail_Image_Holder extends BaseHolder<String> {

	private MyGridView mygv_item_market_image;
	private RelativeLayout.LayoutParams param;
	private ArrayList<String> list=new ArrayList<String>();

	@Override
	public View initView() {
		View inView = UIUtils.inflate(R.layout.market_detail_image);
		mygv_item_market_image = (MyGridView) inView
				.findViewById(R.id.mygv_item_market_image);
		return inView;
	}

	@Override
	public void refreshView() {
		list = (ArrayList<String>) getList();
		
		int screenWidth = UIUtils.getScreenWidth();
		int itemWidth = (screenWidth - UIUtils.dip2px(8)) / 2;
		param = new RelativeLayout.LayoutParams(itemWidth, itemWidth);
		mygv_item_market_image.setAdapter(new MyGridViewAdapter(list));
		mygv_item_market_image.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(ActivityFactory.mainActivity,MyZoomImageActivity.class);
				intent.putStringArrayListExtra("IMAGE_LIST", list);
				ActivityFactory.mainActivity.startActivity(intent);
			}
		});
	}

	class MyGridViewAdapter extends BaseAdapter {
		private List<String>list;
		private BitmapUtils bitmapUtils;
		private ImageView iv_market_item;
		private RelativeLayout iv_market_item_count;
		private TextView tv_market_item_count;
		public MyGridViewAdapter(List<String> list) {
			bitmapUtils = new BitmapUtils(UIUtils.getContext());
			this.list=list;
		}

		@Override
		public int getCount() {
			if (list.size()>4) {
				return 4;
			}else {
				return list.size();
			}
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = view.inflate(UIUtils.getContext(),
						R.layout.item_market_gridview, null);
				iv_market_item = (ImageView) convertView
						.findViewById(R.id.iv_market_item_image);
				iv_market_item_count = (RelativeLayout) convertView
						.findViewById(R.id.iv_market_item_count);
				iv_market_item.setLayoutParams(param);
				tv_market_item_count = (TextView) convertView.findViewById(R.id.tv_market_item_count);
			}
			bitmapUtils.display(iv_market_item, list.get(position));
			if (position==getCount()-1) {
				iv_market_item_count.setVisibility(View.VISIBLE);
				iv_market_item_count.setLayoutParams(param);
				tv_market_item_count.setText(String.valueOf(list.size()));
			}
			return convertView;

		}
	}
	
	
	
	
}
