/**
 * 
 */
package com.dida.first.view;

import com.dida.first.R;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author KingJA
 * @data 2015-8-26 下午2:53:07
 * @use
 * 
 */
public class DialogUpLoadHead extends DialogBaseAlert {

	private OnUploadHeadListener onUploadHeadListener;
	private TextView tv_upload_head_image;
	private TextView tv_upload_head_camera;

	/**
	 * @param context
	 */
	public DialogUpLoadHead(Context context) {
		super(context);
		show();
	}

	@Override
	public void initData() {

	}

	@Override
	public void initView() {
		setContentView(R.layout.dialog_upload_head);
		tv_upload_head_image = (TextView) findViewById(R.id.tv_upload_head_image);
		tv_upload_head_camera = (TextView) findViewById(R.id.tv_upload_head_camera);
	}

	@Override
	public void initListener() {
		tv_upload_head_image.setOnClickListener(this);
		tv_upload_head_camera.setOnClickListener(this);
	}

	
	@Override
	public void childClick(View v) {
		switch (v.getId()) {
		case R.id.tv_upload_head_image:
			if (onUploadHeadListener!=null) {
				onUploadHeadListener.OnImage();
			}
			break;
		case R.id.tv_upload_head_camera:
			if (onUploadHeadListener!=null) {
				onUploadHeadListener.OnCamera();
			}
			break;

		default:
			break;
		}
			dismiss();
	}
	public interface OnUploadHeadListener{
		void OnImage();
		void OnCamera();
	}
	
	public void setOnUploadHeadListener(OnUploadHeadListener onUploadHeadListener){
		this.onUploadHeadListener=onUploadHeadListener;
	}
}
