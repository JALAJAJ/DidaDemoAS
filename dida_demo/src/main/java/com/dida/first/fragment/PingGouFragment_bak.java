package com.dida.first.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.activity.Detail_Pingou_Activity;
import com.dida.first.activity.YaoYueSearchActivity;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.bean.YaoYueBean;
import com.dida.first.bean.YaoYueBean.Res;
import com.dida.first.holder.AAHolder;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.GroupHolder;
import com.dida.first.utils.ToastUtil;
import com.dida.first.view.AbsListView.PullPushListView;
import com.dida.first.view.AbsListView.PullPushListView.OnBackTop;
import com.dida.first.view.AbsListView.PullPushListView.OnFrushLoadMore;
import com.dida.first.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class PingGouFragment_bak extends BaseHeadFragment implements
		OnFrushLoadMore, OnBackTop {
	private PullPushListView lv_yaoyue_group;
	private PullPushListView lv_yaoyue_aa;
	private YaoYueAdapter groupAdapter;
	private YaoYueAdapter aaAdapter;
	private List<Res> groupList = new ArrayList<Res>();
	private List<Res> aaList = new ArrayList<Res>();
	private List<String> urlList = new ArrayList<String>();
	private int page = 1;
	private View headLunBoTu;
	private RelativeLayout rl_yaoyue_group;
	private RelativeLayout rl_yaoyue_aa;
	private ImageView iv_yaoyue_group;
	private ImageView iv_yaoyue_aa;
	private TextView tv_yaoyue_group;
	private TextView tv_yaoyue_aa;
	private ImageView yaoyue_back_top;
	private RelativeLayout rl_lunbotu;
	private boolean isGroup = true;

	/**
	 * 设置内容布局
	 */
	@Override
	public View setContentView() {
		contentView = View.inflate(context, R.layout.fragment_yaoyue, null);
		return contentView;
	}

	/**
	 * 查找控件
	 */
	@Override
	public void initFragmentView() {
		yaoyue_back_top = (ImageView) contentView
				.findViewById(R.id.yaoyue_back_top);
		headLunBoTu = View.inflate(context, R.layout.head_lunbotu, null);
		rl_lunbotu = (RelativeLayout) headLunBoTu.findViewById(R.id.rl_lunbotu);
		initLunBoTu();
		lv_yaoyue_group = (PullPushListView) contentView
				.findViewById(R.id.lv_yaoyue_group);
		lv_yaoyue_aa = (PullPushListView) contentView
				.findViewById(R.id.lv_yaoyue_aa);
		lv_yaoyue_group.initHead(context, headLunBoTu);
	}

	/**
	 * 初始化选择栏
	 */
	private void initLunBoTu() {
		rl_yaoyue_group = (RelativeLayout) headLunBoTu
				.findViewById(R.id.rl_yaoyue_group);
		rl_yaoyue_aa = (RelativeLayout) headLunBoTu
				.findViewById(R.id.rl_yaoyue_aa);
		iv_yaoyue_group = (ImageView) headLunBoTu
				.findViewById(R.id.iv_yaoyue_group);
		iv_yaoyue_aa = (ImageView) headLunBoTu.findViewById(R.id.iv_yaoyue_aa);
		tv_yaoyue_group = (TextView) headLunBoTu
				.findViewById(R.id.tv_yaoyue_group);
		tv_yaoyue_aa = (TextView) headLunBoTu.findViewById(R.id.tv_yaoyue_aa);
		rl_yaoyue_group.setOnClickListener(this);
		rl_yaoyue_aa.setOnClickListener(this);

	}

	/**
	 * 清除选择栏状态
	 */
	public void clearSelect() {
		rl_yaoyue_group.setBackgroundResource(R.color.select_color_nomal);
		rl_yaoyue_aa.setBackgroundResource(R.color.select_color_nomal);
		iv_yaoyue_group.setBackgroundResource(R.drawable.tuangou_nor);
		iv_yaoyue_aa.setBackgroundResource(R.drawable.aa_nor);
		tv_yaoyue_group.setTextColor(getResources().getColor(
				R.color.select_color_nomal));
		tv_yaoyue_aa.setTextColor(getResources().getColor(
				R.color.select_color_nomal));
	}

	/**
	 * 内容布局点击事件
	 */
	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.rl_yaoyue_group:
			clearSelect();
			groupSelect();
			break;
		case R.id.rl_yaoyue_aa:
			clearSelect();
			aaSelect();
			break;
		case R.id.yaoyue_back_top:
			if (isGroup) {
				lv_yaoyue_group.setSelection(0);
			}else {
				lv_yaoyue_aa.setSelection(0);
			}
			yaoyue_back_top.setVisibility(View.GONE);
			break;
		}
	}

	/**
	 * 团购选中执行逻辑
	 */
	private void groupSelect() {
		rl_yaoyue_group.setBackgroundResource(R.color.select_color_selected);
		iv_yaoyue_group.setBackgroundResource(R.drawable.tuangou_red_sel);
		tv_yaoyue_group.setTextColor(getResources().getColor(
				R.color.select_color_selected));
		isGroup = true;
		lv_yaoyue_aa.clearHead();
		lv_yaoyue_group.initHead(context, headLunBoTu);
		if (groupList.size() == 0) {
			getData(1, 1);
		} else {
			showGroupAdapter();
		}
	}

	/**
	 * 选中AA执行逻辑
	 */
	private void aaSelect() {
		rl_yaoyue_aa.setBackgroundResource(R.color.select_color_selected);
		iv_yaoyue_aa.setBackgroundResource(R.drawable.aa_red_sel);
		tv_yaoyue_aa.setTextColor(getResources().getColor(
				R.color.select_color_selected));
		isGroup = false;
		lv_yaoyue_group.clearHead();
		lv_yaoyue_aa.initHead(context, headLunBoTu);
		if (aaList.size() == 0) {
			getData(3, 1);
		} else {
			showAAAdapter();
		}
	}

	/**
	 * 设置事件
	 */
	@Override
	public void initFragmentEvent() {
		yaoyue_back_top.setOnClickListener(this);
		lv_yaoyue_group.setOnBackTop(this);
		lv_yaoyue_group.setOnItemClickListener(onItemClickListener);
		lv_yaoyue_group.setOnFrushLoadMore(this);
		lv_yaoyue_aa.setOnBackTop(this);
		lv_yaoyue_aa.setOnItemClickListener(onItemClickListener);
		lv_yaoyue_aa.setOnFrushLoadMore(this);
	}

	/**
	 * 初始化数据
	 */
	@Override
	public void initFragmentNet() {
		initGroupData();
		initAAData();

	}


	/**
	 * 初始化AA数据
	 */
	private void initAAData() {
//		DataUtil<YaoYueBean> dataUtil = new DataUtil<YaoYueBean>() {
//			@Override
//			public void onCache(YaoYueBean bean) {
//			}
//
//			@Override
//			public void onFail(HttpException error, String msg) {
//			}
//
//			@Override
//			public void onSucc(YaoYueBean bean) {
//				aaList = bean.res;
//			}
//		};
//		dataUtil.getDataFromNet(UrlUtil.YAOYUE_PATH,
//				dataUtil.getYaoYueParams(3, 1), YaoYueBean.class);

	}

	/**
	 * 初始化团购数据
	 */
	private void initGroupData() {
//		DataUtil<YaoYueBean> dataUtil = new DataUtil<YaoYueBean>() {
//			@Override
//			public void onCache(YaoYueBean bean) {
//				setData(bean, false);
//			}
//			@Override
//			public void onFail(HttpException error, String msg) {
//				lv_yaoyue_group.setFrushViewGone();
//			}
//
//			@Override
//			public void onSucc(YaoYueBean bean) {
//				setData(bean, false);
//				lv_yaoyue_group.setFrushViewGone();
//			}
//		};
//		dataUtil.initData(UrlUtil.YAOYUE_PATH,
//				dataUtil.getYaoYueParams(1, 1),
//				CustomConstants.YAOYUE_GROUP_CACHE, YaoYueBean.class);
	}

	/**
	 * 设置团购数据
	 * 
	 * @param bean
	 * @param ifLoad
	 *            是否加载更多
	 */
	protected void setData(YaoYueBean bean, boolean ifLoad) {
		initUrl();
		if (isGroup) {
			if (!ifLoad) {
				groupList.clear();
			}
			List<Res> data = bean.res;
			groupList.addAll(data);
			initViewPager(context, urlList, rl_lunbotu);
			showGroupAdapter();
		} else {
			if (!ifLoad) {
				aaList.clear();
			}
			List<Res> data = bean.res;
			aaList.addAll(data);
			initViewPager(context, urlList, rl_lunbotu);
			showAAAdapter();
		}
	}

	/**
	 * 加载更多
	 */
	private void getMoreData(int type) {
//		page += 1;
//		DataUtil<YaoYueBean> dataUtil = new DataUtil<YaoYueBean>() {
//			@Override
//			public void onCache(YaoYueBean bean) {
//			}
//
//			@Override
//			public void onFail(HttpException error, String msg) {
//				lv_yaoyue_group.setLoadViewGone();
//			}
//
//			@Override
//			public void onSucc(YaoYueBean bean) {
//				Log.i("getMoreData", bean.res.size()+"");
//				setMoreView(bean);
//			}
//
//		};
//		dataUtil.getDataFromNet(UrlUtil.YAOYUE_PATH,
//				dataUtil.getYaoYueParams(type, page), YaoYueBean.class);
	}

	/**
	 * @param bean
	 */
	private void setMoreView(YaoYueBean bean) {
		if (isGroup) {
			if (bean.res.size() == 10) {
				setData(bean, true);
				lv_yaoyue_group.setLoadViewGone();
			} else if (bean.res.size() < 10 && bean.res.size() >= 0) {
				setData(bean, true);
				lv_yaoyue_group.setNoMoreVisibility();
			}
		} else {
			if (bean.res.size() == 10) {
				setData(bean, true);
				lv_yaoyue_aa.setLoadViewGone();
			} else if (bean.res.size() < 10 && bean.res.size() >= 0) {
				setData(bean, true);
				lv_yaoyue_aa.setNoMoreVisibility();
			}
		}
	}

	/**
	 * 展示团购数据
	 */
	private void showGroupAdapter() {
		lv_yaoyue_aa.setVisibility(View.GONE);
		lv_yaoyue_group.setVisibility(View.VISIBLE);
		if (groupAdapter == null) {
			groupAdapter = new YaoYueAdapter(groupList, true);
			lv_yaoyue_group.setAdapter(groupAdapter);
		} else {
			groupAdapter.setNotyfyData(groupList);
		}
	}

	/**
	 * 展示AA数据
	 */
	private void showAAAdapter() {
		lv_yaoyue_group.setVisibility(View.GONE);
		lv_yaoyue_aa.setVisibility(View.VISIBLE);
		if (aaAdapter == null) {
			aaAdapter = new YaoYueAdapter(aaList, false);
			lv_yaoyue_aa.setAdapter(aaAdapter);
		} else {
			aaAdapter.setNotyfyData(aaList);
		}
	}

	private void getData(int type, int page) {
//		DataUtil<YaoYueBean> dataUtil = new DataUtil<YaoYueBean>() {
//			@Override
//			public void onCache(YaoYueBean bean) {
//
//			}
//
//			@Override
//			public void onFail(HttpException error, String msg) {
//				lv_yaoyue_group.setFrushViewGone();
//			}
//
//			@Override
//			public void onSucc(YaoYueBean bean) {
//				setData(bean, false);
//				if (isGroup) {
//					lv_yaoyue_group.setFrushViewGone();
//				} else {
//					lv_yaoyue_aa.setFrushViewGone();
//				}
//			}
//		};
//		dataUtil.getDataFromNet(UrlUtil.YAOYUE_PATH,
//				dataUtil.getYaoYueParams(type, page), YaoYueBean.class);
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
	 * 初始化轮播图
	 */
	private void initViewPager(Context context, List<String> urlList,
			RelativeLayout relativeLayout) {
		MyViewPager myViewPager = new MyViewPager(context, urlList,
				relativeLayout);
		rl_lunbotu.addView(myViewPager);
		myViewPager.initDot();
		myViewPager.setCurrentItem(2000);
		myViewPager.startRoll();
	}

	/**
	 * ListView的Item点击事件
	 */
	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			ToastUtil.singleToast(context, "点击了条目" + (position - 1));
			Intent intent =new Intent(context,Detail_Pingou_Activity.class);
			startActivity(intent);
			
			
		}
	};

	@Override
	public void onFrush() {
		page = 1;
		if (isGroup) {
			lv_yaoyue_group.setNoMoreGone();
			getData(1, 1);
		} else {
			lv_yaoyue_aa.setNoMoreGone();
			getData(3, 1);
		}
	}

	@Override
	public void onLoadMore() {
		if (isGroup) {
			getMoreData(1);
		} else {
			getMoreData(3);
		}
	}

	@Override
	public void onSearch() {
		Intent intent = new Intent(context, YaoYueSearchActivity.class);
		startActivity(intent);
	}

	class YaoYueAdapter extends MyBaseAdapter<Res> {
		private boolean isGroup;

		public YaoYueAdapter(List<Res> list, boolean isGroup) {
			super(list);
			this.isGroup = isGroup;
		}

		@Override
		public BaseHolder<Res> getHolder() {
			if (isGroup) {
				return new GroupHolder();
			} else {
				return new AAHolder();
			}
		}

	}

	@Override
	public void onTopVisible() {
		yaoyue_back_top.setVisibility(View.VISIBLE);
	}

	@Override
	public void onTopGone() {
		yaoyue_back_top.setVisibility(View.GONE);
	}

	@Override
	public void initFragmentData() {
		
	}
}
