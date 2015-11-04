package com.dida.first.fragment;

import android.view.View;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.bean.BeanPinGouGroup;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.MyPinGou_AA_Holder;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author		KingJA 
 * @data		2015-10-14 下午1:38:58 
 * @use			
 *
 */
public class Fragment_PinGou_AA extends Fragment_Base_Nomal {
	private List<BeanPinGouGroup> pinGouGroups=new ArrayList<BeanPinGouGroup>();
	private PullToRefreshListView plv_mine_pingou_aa;

	@Override
	public View setFragmentView() {
		view=View.inflate(context, R.layout.fragment_mine_pingou_aa, null);
		return view;
	}

	@Override
	public void initFragmentView() {
		plv_mine_pingou_aa = (PullToRefreshListView) view.findViewById(R.id.plv_mine_pingou_aa);
		
	}

	@Override
	public void initFragmentNet() {
		for (int i = 0; i < 20; i++) {
			pinGouGroups.add(new BeanPinGouGroup());
		}
	}

	@Override
	public void initFragmentEvent() {
		plv_mine_pingou_aa.setAdapter(new MyAdapter(pinGouGroups));
		
	}

	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChildClick(View v) {

	}

	class MyAdapter extends MyBaseAdapter<BeanPinGouGroup>{

		public MyAdapter(List<BeanPinGouGroup> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<BeanPinGouGroup> getHolder() {
			// TODO Auto-generated method stub
			return new MyPinGou_AA_Holder();
		}
		
	}
}
