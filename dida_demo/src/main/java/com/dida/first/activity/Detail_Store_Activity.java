/**
 * 
 */
package com.dida.first.activity;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author KingJA
 * @data 2015-10-14 上午9:31:42
 * @use
 * 
 */
public class Detail_Store_Activity extends BackTitleActivity {

	private LinearLayout ll_store_favorite;
	private ImageView iv_store_chat;
	private ImageView iv_store_tel;
	private ImageView iv_store_star;
	private TextView tv_store_favorite;
	private boolean mFavorite;
	private TextView tv_store_phone;

	@Override
	public View setView() {
		view = View.inflate(Detail_Store_Activity.this,
				R.layout.activity_store_des, null);
		return view;
	}

	@Override
	public void initView() {
		ll_store_favorite = (LinearLayout) view.findViewById(R.id.ll_store_favorite);
		iv_store_chat = (ImageView) view.findViewById(R.id.iv_store_chat);
		iv_store_tel = (ImageView) view.findViewById(R.id.iv_store_tel);
		iv_store_star = (ImageView) view.findViewById(R.id.iv_store_star);
		tv_store_favorite = (TextView) view.findViewById(R.id.tv_store_favorite);
		tv_store_phone = (TextView) view.findViewById(R.id.tv_store_phone);

	}

	@Override
	public void initDoNet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initEvent() {
		ll_store_favorite.setOnClickListener(this);
		iv_store_tel.setOnClickListener(this);
		iv_store_chat.setOnClickListener(this);
	}

	private void setFavorite(boolean iFavorite) {
		
		if (!iFavorite) {
			ll_store_favorite.setBackgroundResource(R.drawable.shape_lnull_bred_r8);
			iv_store_star.setBackgroundResource(R.drawable.fav_line);
			tv_store_favorite.setText("收藏");
			tv_store_favorite.setTextColor(getResources().getColor(R.color.white));
		}else {
			ll_store_favorite.setBackgroundResource(R.drawable.shape_lnull_bgray_r8);
			iv_store_star.setBackgroundResource(R.drawable.fav_red);
			tv_store_favorite.setText("已收藏");
			tv_store_favorite.setTextColor(getResources().getColor(R.color.red));
		}
	}

	@Override
	public void initData() {
		setFavorite(mFavorite);
		setBackTitle("店铺简介");
		setOnTextClickListener("进店", new OnTextClickListener() {

			@Override
			public void onTextClick() {
				ToastUtil.showMyToast("进店");

			}
		});
	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.ll_store_favorite:
			mFavorite=!mFavorite;
			setFavorite(mFavorite);
			break;
		case R.id.iv_store_chat:
			
			break;
		case R.id.iv_store_tel:
			String phone = tv_store_phone.getText().toString().trim();
			Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
			startActivity(intentPhone);
			break;

		default:
			break;
		}

	}

	@Override
	public void setBackClick() {
		finish();

	}

}
