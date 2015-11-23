/**
 * 
 */
package com.dida.first.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author		KingJA 
 * @data		2015-10-14 下午2:11:29 
 * @use			
 *
 */
public abstract class Fragment_Base_Nomal extends Fragment implements View.OnClickListener{
	protected Context context;
	protected View view;
	protected RequestQueue mQueue;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=getActivity();
		mQueue = Volley.newRequestQueue(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view=setFragmentView();
		return view;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initFragmentView();
		initFragmentNet();
		initFragmentEvent();
		initFragmentData();
	}
	public abstract View setFragmentView();
	public abstract void initFragmentView();
	public abstract void initFragmentNet();
	public abstract void initFragmentEvent();
	public abstract void initFragmentData();
	public abstract void onChildClick(View v);

	@Override
	public void onClick(View v) {
		onChildClick(v);
	}
}
