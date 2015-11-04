/**
 * 
 */
package com.dida.first.holder;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author		KingJA 
 * @data		2015-8-18 上午10:15:14 
 * @use			
 *
 */
public class GDetail_Item_Holder extends BaseHolder {

	private ListView lv_group_detail_item;

	@Override
	public View initView() {
		View inView=UIUtils.inflate(R.layout.group_detail_item);
		lv_group_detail_item = (ListView) inView.findViewById(R.id.lv_group_detail_item);
		return inView;
	}

	@Override
	public void refreshView() {
		lv_group_detail_item.setAdapter(new GroupItemAdapter());

	}
	class GroupItemAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return 3;
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
			View itemView=UIUtils.inflate(R.layout.item_group_detail_item);
			return itemView;
		}
		
	}
}
