package com.dida.first.fragment;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.bean.BeanPingou;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.MyViewPager;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class Index_Pingou_Fragment extends Fragment_Base_Nomal {
	private List<String> urlList = new ArrayList<String>();
	private int itemWidth;
	private List<BeanPingou> pingouList = new ArrayList<BeanPingou>();
	private RelativeLayout.LayoutParams layoutParams;
	private PullToRefreshListView plv_pingou;

	@Override
	public View setFragmentView() {
		view=View.inflate(context, R.layout.fr_pingou,null);
		return view;
	}

	@Override
	public void initFragmentView() {
		plv_pingou = (PullToRefreshListView) view.findViewById(R.id.plv_pingou);
	}

	@Override
	public void initFragmentNet() {
		initUrl();
		int imgWidth = UIUtils.getScreenWidth();
		int imgHeight = imgWidth / 2;
		layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, imgHeight);
		Log.i("initFragmentNet", "imgWidth=" + imgWidth + ",imgHeight=" + imgHeight);
		for (int i = 0; i < 20; i++) {
			pingouList.add(new BeanPingou(Math.random() > 0.5 ? true : false, Math.random() > 0.5 ? true : false));
		}
	}

	@Override
	public void initFragmentEvent() {
		initIndicator();
		View lunbotuView = View.inflate(context, R.layout.view_pinggou_lunbotu, null);
		RelativeLayout rl_lunbotu = (RelativeLayout) lunbotuView.findViewById(R.id.rl_lunbotu);
		initViewPager(context, urlList, rl_lunbotu);
		// 加入轮播图布局
		ListView refreshableView = plv_pingou.getRefreshableView();
		refreshableView.addHeaderView(lunbotuView, null, false);
		MyPingouAdapter pingouAdapter = new MyPingouAdapter(pingouList);
		plv_pingou.setAdapter(pingouAdapter);
	}

	@Override
	public void initFragmentData() {

	}

	@Override
	public void onChildClick(View v) {

	}
	class MyPingouAdapter extends android.widget.BaseAdapter {
		private List<BeanPingou> list;

		public MyPingouAdapter(List<BeanPingou> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
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
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = View.inflate(getActivity(), R.layout.item_pingou, null);
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.ivitempingouicon.setLayoutParams(layoutParams);
			if (list.get(position).isUser()) {
				viewHolder.ivitempingouisuser.setBackgroundResource(R.drawable.yonghu);
				viewHolder.ivitempingouqiang.setBackgroundColor(Color.TRANSPARENT);
			} else {
				viewHolder.ivitempingouisuser.setBackgroundResource(R.drawable.shangjia);
				if (list.get(position).isPin()) {
					viewHolder.llitempingoudeadline.setVisibility(View.VISIBLE);
					viewHolder.ivitempingouqiang.setBackgroundResource(R.drawable.pin);
				} else {
					viewHolder.llitempingoudeadline.setVisibility(View.GONE);
					viewHolder.ivitempingouqiang.setBackgroundResource(R.drawable.qiang);
				}
			}
			return convertView;
		}


		public class ViewHolder {
			public final LinearLayout llitempingoudeadline;
			public final ImageView ivitempingouicon;
			public final ImageView ivitempingouisuser;
			public final ImageView ivitempingouqiang;
			public final TextView tvitempingoutitle;
			public final TextView tvitempingousubject;
			public final TextView tvitempingouleft;
			public final TextView tvitempingouhour;
			public final TextView tvitempingoumin;
			public final TextView tvitempingouprice;
			public final TextView tvitempingouoldprice;
			public final TextView tvitempingoucomment;
			public final View root;

			public ViewHolder(View root) {
				llitempingoudeadline = (LinearLayout) root.findViewById(R.id.ll_item_pingou_deadline);
				ivitempingouicon = (ImageView) root.findViewById(R.id.iv_item_pingou_icon);
				ivitempingouisuser = (ImageView) root.findViewById(R.id.iv_item_pingou_isuser);
				ivitempingouqiang = (ImageView) root.findViewById(R.id.iv_item_pingou_qiang);
				tvitempingoutitle = (TextView) root.findViewById(R.id.tv_item_pingou_title);
				tvitempingousubject = (TextView) root.findViewById(R.id.tv_item_pingou_subject);
				tvitempingouleft = (TextView) root.findViewById(R.id.tv_item_pingou_left);
				tvitempingouhour = (TextView) root.findViewById(R.id.tv_item_pingou_hour);
				tvitempingoumin = (TextView) root.findViewById(R.id.tv_item_pingou_min);
				tvitempingouprice = (TextView) root.findViewById(R.id.tv_item_pingou_price);
				tvitempingouoldprice = (TextView) root.findViewById(R.id.tv_item_pingou_oldprice);
				tvitempingoucomment = (TextView) root.findViewById(R.id.tv_item_pingou_comment);
				this.root = root;
			}
		}
	}

	private void initUrl() {
		urlList.clear();
		urlList.add("http://pic74.nipic.com/file/20150809/21430351_115107197205_2.jpg");
		urlList.add("http://pic40.nipic.com/20140423/5523845_164638406332_2.jpg");
		urlList.add("http://pic74.nipic.com/file/20150807/21430351_223621960871_2.jpg");
		urlList.add("http://pic74.nipic.com/file/20150807/21430351_224952168084_2.jpg");
		urlList.add("http://pic74.nipic.com/file/20150803/21430351_175936202461_2.jpg");
	}

	/**
	 * 初始化刷新加载的内容
	 */
	private void initIndicator() {
		ILoadingLayout downLabels = plv_pingou
				.getLoadingLayoutProxy(true, false);
		downLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
		downLabels.setRefreshingLabel("正在刷新...");// 刷新时
		downLabels.setReleaseLabel("释放刷新...");// 下来达到一定距离时，显示的提示
		ILoadingLayout upLabels = plv_pingou.getLoadingLayoutProxy(false, true);
		upLabels.setPullLabel("上拉加载...");// 刚上拉时，显示的提示
		upLabels.setRefreshingLabel("正在加载...");// 加载时
		upLabels.setReleaseLabel("释放加载...");// 上来达到一定距离时，显示的提示
	}

	/**
	 * 初始化轮播图
	 */
	private void initViewPager(Context context, List<String> urlList,
							   RelativeLayout relativeLayout) {
		MyViewPager myViewPager = new MyViewPager(context, urlList,
				relativeLayout);
		relativeLayout.addView(myViewPager);
		myViewPager.initDot();
		myViewPager.setCurrentItem(2000);
		myViewPager.startRoll();
	}
}
