package com.dida.first.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dida.first.R;
import com.dida.first.view.AbsListView.MyGridView;

import java.util.List;

public class LvAdapter extends BaseAdapter {
	private List list;
	private Context context;

	public LvAdapter(List list,Context context) {
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addData(List list){
		this.list=list;
		this.notifyDataSetChanged();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("LvAdapter getView", "position="+position);
         View itemView = View.inflate(context,
                 R.layout.item_single_gridview, null);
         MyGridView gv_item_market = (MyGridView) itemView
                 .findViewById(R.id.gv_item_market);
         GvAdapter mGvAdapter = new GvAdapter(list,context);
         gv_item_market.setAdapter(mGvAdapter);
         return itemView;
	}

}
