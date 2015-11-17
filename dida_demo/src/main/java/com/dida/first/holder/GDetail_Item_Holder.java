/**
 * 
 */
package com.dida.first.holder;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dida.first.R;
import com.dida.first.activity.Detail_Market_Activity;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-8-18 上午10:15:14 
 * @use			
 *
 */
public class GDetail_Item_Holder extends BaseHolder implements AdapterView.OnItemClickListener{
	private ListView lv_group_detail_item;
	protected Activity activity;

	public GDetail_Item_Holder(Activity activity) {
		this.activity=activity;
	}

	@Override
	public View initView() {
		View inView=UIUtils.inflate(R.layout.group_detail_item);
		lv_group_detail_item = (ListView) inView.findViewById(R.id.lv_group_detail_item);
		return inView;
	}

	@Override
	public void refreshView() {
		lv_group_detail_item.setAdapter(new GroupItemAdapter());
		lv_group_detail_item.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ActivityUtil.goActivity(activity, Detail_Market_Activity.class);
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
