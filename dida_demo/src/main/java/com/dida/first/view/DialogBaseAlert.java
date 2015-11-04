package com.dida.first.view;



import com.dida.first.R;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class DialogBaseAlert extends AlertDialog implements android.view.View.OnClickListener{

	protected DialogBaseAlert(Context context) {
		super(context,R.style.CustomDialog);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
		initListener();
		initData();
		
	}
	public abstract void initData();
	public abstract void initView();
	public abstract void initListener();
	public abstract void childClick(View v);
	@Override
	public void onClick(View v) {
		childClick(v);
		
	}

}
