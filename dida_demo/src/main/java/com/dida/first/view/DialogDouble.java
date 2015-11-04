package com.dida.first.view;



import com.dida.first.R;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogDouble extends DialogBaseAlert{
private Context context;
private String message;
private TextView tv_doubledialog_title;
private TextView tv_doubledialog_message;
private Button btn_doubledialog_confirm;
private Button btn_doubledialog_cancel;
private String title;
private String confirmString;
private String cancleString;
private OnBtnClickListener onBtnClickListener;
public DialogDouble(Context context,String title,String message,String confirmString,String cancleString) {
		super(context);
		this.context=context;
		this.message=message;
		this.title=title;
		this.confirmString=confirmString;
		this.cancleString=cancleString;
		this.show();
	}
@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void initData() {
		tv_doubledialog_title.setText(title);
		tv_doubledialog_message.setText(message);
		btn_doubledialog_confirm.setText(confirmString);
		btn_doubledialog_cancel.setText(cancleString);
		
	}

	@Override
	public void initView() {
		setContentView(R.layout.dialog_double);
		tv_doubledialog_title = (TextView) findViewById(R.id.tv_doubledialog_title);
		tv_doubledialog_message = (TextView) findViewById(R.id.tv_doubledialog_message);
		btn_doubledialog_confirm = (Button) findViewById(R.id.btn_doubledialog_confirm);
		btn_doubledialog_cancel = (Button) findViewById(R.id.btn_doubledialog_cancel);
	
		
	}

	@Override
	public void initListener() {
		btn_doubledialog_confirm.setOnClickListener(this);
		btn_doubledialog_cancel.setOnClickListener(this);
		
	}

	@Override
	public void childClick(View v) {
		switch (v.getId()) {
		case R.id.btn_doubledialog_confirm:
			dismiss();
			onBtnClickListener.onLeft();
			break;
		case R.id.btn_doubledialog_cancel:
			dismiss();
			onBtnClickListener.onRight();
			break;
		}
	}
	public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener){
		this.onBtnClickListener=onBtnClickListener;
	}
	public interface OnBtnClickListener{
		public void onLeft();
		public void onRight();
	}
	
}
