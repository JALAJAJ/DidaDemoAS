
package com.dida.first.activity;

import android.view.View;

import com.dida.first.R;
import com.dida.first.adapter.PingouGroupAdapter;
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.utils.UIUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-8-20 下午4:53:02
 * @use	关注列表
 * 
 */
public class AttentionActivity extends BackTitleActivity {

	private PullToRefreshListView plv_attention_list;
	private List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ParticipatesEntity> pingou_group;

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
	public void initDoNet() {
		pingou_group = (List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ParticipatesEntity>) getIntent().getSerializableExtra("PINGOU_GROUP");

	}
	@Override
	public void initEvent() {
		plv_attention_list.setAdapter(new PingouGroupAdapter(pingou_group,this));
	}

	@Override
	public void initData() {
		setBackTitle("团员列表");
	}


	@Override
	public void setBackClick() {
		finish();
		
	}
}
