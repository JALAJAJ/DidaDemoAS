package com.dida.first.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.MainFragment;
import com.dida.first.R;
import com.dida.first.activity.ChatActivity;
import com.dida.first.utils.SharedPreferencesUtils;
import com.dida.first.utils.ToastUtil;

import io.rong.imkit.RongIM;

public abstract class BaseHeadFragment extends Fragment implements
		OnClickListener {
	/* 搜索框View */
	private View selectView;
	/* 各个标签内容View */
	protected View contentView;
	/* MainActivity上下文 */
	protected Context context;
	public FragmentManager fragmentManager;
	public LinearLayout ll_main;
	private TextView tv_head_title;
	private RelativeLayout rl_search;
	protected FragmentManager supportFragmentManager;

	public abstract void onSearch();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = getActivity();
		supportFragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager = MainFragment.fragmentManager;
	}


	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		selectView = View.inflate(context, R.layout.fragment_new_search, null);
		/* 在FrameLayout下添加头部搜索框 */
		addContentView();

		return selectView;
	}

	/**
	 * 初始化头部搜索框事件
	 */
	private void initContentEvent() {
		ImageView iv_chat = (ImageView) selectView.findViewById(R.id.iv_chat);
		ImageView iv_leftMenu = (ImageView) selectView
				.findViewById(R.id.iv_leftMenu);
		rl_search = (RelativeLayout) selectView.findViewById(R.id.rl_search);
		tv_head_title = (TextView) selectView.findViewById(R.id.tv_head_title);
		iv_chat.setOnClickListener(this);
		iv_leftMenu.setOnClickListener(this);
		rl_search.setOnClickListener(this);

	}

	public void setTitle(String title) {
		rl_search.setVisibility(View.GONE);
		tv_head_title.setVisibility(View.VISIBLE);
		tv_head_title.setText(title);
	}

	/**
	 * 初始化子类事件
	 */
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		initContentEvent();
		initFragmentView();
		initFragmentNet();
		initFragmentEvent();
		initFragmentData();
		super.onActivityCreated(savedInstanceState);

	}

	public abstract View setContentView();
	public abstract void initFragmentView();
	public abstract void initFragmentNet();
	public abstract void initFragmentEvent();
	public abstract void initFragmentData();

	@Override
	public void onClick(View v) {
		onChildClick(v);
		switch (v.getId()) {
		case R.id.iv_leftMenu:
			break;
		case R.id.rl_search:
			onSearch();
			break;
		case R.id.iv_chat:
			String token = SharedPreferencesUtils.getStringData("TOKEN", "");
			ToastUtil.showMyToast(token);
			if (!TextUtils.isEmpty(token)) {
				RongIM.getInstance().startConversationList(context);
			}else {
			Intent intent=new Intent(getActivity(),ChatActivity.class);
			startActivity(intent);
			}
			break;
		}

	}

	/**
	 * 子类的点击事件
	 */
	public abstract void onChildClick(View v);

	private void addContentView() {
		FrameLayout contentFrameLayout = (FrameLayout) selectView
				.findViewById(R.id.fl_fragment_content);
		contentView = setContentView();
		if (contentView != null) {
			// 导入包要主要了：父类是谁就导入谁的包
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);

			contentFrameLayout.addView(contentView, params);
		}

	}
}
