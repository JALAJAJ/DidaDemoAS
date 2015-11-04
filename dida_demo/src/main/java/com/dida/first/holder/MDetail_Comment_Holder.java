/**
 * 
 */
package com.dida.first.holder;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author		KingJA 
 * @data		2015-9-18 下午1:24:00 
 * @use			
 *
 */
public class MDetail_Comment_Holder extends BaseHolder implements OnClickListener{

	private View view;
	private TextView tv_market_detail_comment_name;
	private TextView tv_market_detail_comment_content;
	private TextView tv_market_detail_comment_zhuijia;
	private TextView tv_market_detail_comment_date;
	private TextView tv_market_detail_comment_param;

	@Override
	public View initView() {
		view = UIUtils.inflate(R.layout.market_detail_comment);
		return view;
	}

	@Override
	public void refreshView() {
		init();

	}
	private void init() {
		LinearLayout ll_item_market_comment = (LinearLayout) view.findViewById(R.id.ll_item_market_comment);
		ll_item_market_comment.setOnClickListener(this);
		tv_market_detail_comment_name = (TextView) view.findViewById(R.id.tv_market_detail_comment_name);
		tv_market_detail_comment_content = (TextView) view.findViewById(R.id.tv_market_detail_comment_content);
		tv_market_detail_comment_zhuijia = (TextView) view.findViewById(R.id.tv_market_detail_comment_zhuijia);
		tv_market_detail_comment_date = (TextView) view.findViewById(R.id.tv_market_detail_comment_date);
		tv_market_detail_comment_param = (TextView) view.findViewById(R.id.tv_market_detail_comment_param);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_item_market_comment:
			ToastUtil.showMyToast("打开评价列表");
			break;

		default:
			break;
		}
		
	}

}
