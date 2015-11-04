/**
 * 
 */
package com.dida.first.view;

import com.dida.first.R;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author KingJA
 * @data 2015-8-26 下午2:53:07
 * @use
 * 
 */
public class DialogText extends DialogBaseAlert {

	private RelativeLayout rl_save_image;
	private OnSaveListener onSaveListener;

	/**
	 * @param context
	 */
	public DialogText(Context context) {
		super(context);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initView() {
		setContentView(R.layout.dialog_text);
		rl_save_image = (RelativeLayout) findViewById(R.id.rl_save_image);
	}

	@Override
	public void initListener() {
		rl_save_image.setOnClickListener(this);
	}

	
	@Override
	public void childClick(View v) {
		if (v.getId()==R.id.rl_save_image) {
			onSaveListener.OnSave();
			dismiss();
		}
	}
	interface OnSaveListener{
		void OnSave();
	}
	
	public void setOnSaveListener(OnSaveListener onSaveListener){
		this.onSaveListener=onSaveListener;
	}
}
