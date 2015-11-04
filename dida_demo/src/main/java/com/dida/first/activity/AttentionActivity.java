/**
 * 
 */
package com.dida.first.activity;

import java.util.ArrayList;
import java.util.List;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.bean.YaoYueBean.Res;
import com.dida.first.holder.AttentionHolder;
import com.dida.first.holder.BaseHolder;
import com.dida.first.utils.UIUtils;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author KingJA
 * @data 2015-8-20 下午4:53:02
 * @use	关注列表
 * 
 */
public class AttentionActivity extends BackTitleActivity {

	private View inflate;
	private ListView lv_attention_list;
	private List<String> attentionList=new ArrayList<String>();

	@Override
	public void onChildClick(View v) {
		
	}

	@Override
	public View setView() {
		inflate = UIUtils.inflate(R.layout.list_attention);
		return inflate;
	}

	@Override
	public void initView() {
		lv_attention_list = (ListView) inflate.findViewById(R.id.lv_attention_list);
	}

	@Override
	public void initEvent() {
		for (int i = 0; i < 20; i++) {
			attentionList.add("别让我转回头"+i);
		}
		lv_attention_list.setAdapter(new AttentionAdapter(attentionList));
	}

	@Override
	public void initData() {

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
		// TODO Auto-generated method stub
		
	}
}
