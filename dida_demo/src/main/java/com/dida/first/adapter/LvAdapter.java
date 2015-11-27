package com.dida.first.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.dida.first.R;
import com.dida.first.bean.MarketBean;
import com.dida.first.view.AbsListView.MyGridView;

import java.util.List;

public class LvAdapter extends BaseAdapter {
	private List<MarketBean.ResEntity.ProductsEntity.StEntity> list;
	private Context context;
	private MyGridView gv_item_market;

	public LvAdapter(List<MarketBean.ResEntity.ProductsEntity.StEntity> list,Context context) {
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

	public void addData(List<MarketBean.ResEntity.ProductsEntity.StEntity> list){
		this.list.addAll(list);
		this.notifyDataSetChanged();
	}

	public void setData(List list) {
		this.list = list;
		this.notifyDataSetChanged();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("LvAdapter getView", "position="+position);
         View itemView = View.inflate(context,
                 R.layout.item_single_gridview, null);
		gv_item_market = (MyGridView) itemView
                .findViewById(R.id.gv_item_market);
         GvAdapter mGvAdapter = new GvAdapter(list,context);
         gv_item_market.setAdapter(mGvAdapter);
		gv_item_market.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				Log.i("GV onScroll","firstVisibleItem="+firstVisibleItem);
			}
		});
         return itemView;
	}

}
