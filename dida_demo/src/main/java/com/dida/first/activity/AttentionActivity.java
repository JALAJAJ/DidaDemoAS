
package com.dida.first.activity;

import android.view.View;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.holder.AttentionHolder;
import com.dida.first.holder.BaseHolder;
import com.dida.first.utils.UIUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingJA
 * @data 2015-8-20 下午4:53:02
 * @use	关注列表
 * 
 */
public class AttentionActivity extends BackTitleActivity {

	private PullToRefreshListView plv_attention_list;
	private List<String> attentionList=new ArrayList<String>();

	@Override
	public void onChildClick(View v) {
		
	}

	@Override
	public View setView() {
		view = UIUtils.inflate(R.layout.list_attention);
		return view;
	}

	@Override
	public void initView() {
		plv_attention_list = (PullToRefreshListView) view.findViewById(R.id.plv_attention_list);
	}

	@Override
	public void initEvent() {
		for (int i = 0; i < 20; i++) {
			attentionList.add("别让我转回头"+i);
		}
		plv_attention_list.setAdapter(new AttentionAdapter(attentionList));
	}

	@Override
	public void initData() {
		setBackTitle("团员列表");

	}

	@Override
	public void initDoNet() {

	}
	class AttentionAdapter extends MyBaseAdapter<String>{

		public AttentionAdapter(List<String> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<String> getHolder() {
			// TODO Auto-generated method stub
			return new AttentionHolder();
		}
		
	}
	@Override
	public void setBackClick() {
		finish();
		
	}
}
