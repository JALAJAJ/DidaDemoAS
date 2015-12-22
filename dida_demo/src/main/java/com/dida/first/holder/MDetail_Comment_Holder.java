/**
 * 
 */
package com.dida.first.holder;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanDetailMarket;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyGridView;
import com.meg7.widget.CircleImageView;


/**
 * @author		KingJA 
 * @data		2015-9-18 下午1:24:00 
 * @use			
 *
 */
public class MDetail_Comment_Holder extends BaseHolder<BeanDetailMarket> implements OnClickListener{


	private TextView tv_comment_market_name;
	private TextView tv_comment_market_content;
	private TextView tv_comment_market_after;
	private TextView tv_comment_market_date;
	private TextView tv_comment_market_param;
	private TextView tv_market_detail_comment_count;

	private CircleImageView civ_comment_market_icon;
	private MyGridView mgv_comment_market_image;
	private MyGridView mgv_comment_market_afterimage;

	private RelativeLayout rl_market_detail_comment_more;

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
		tv_comment_market_name = (TextView) view.findViewById(R.id.tv_comment_market_name);
		tv_comment_market_content = (TextView) view.findViewById(R.id.tv_comment_market_content);
		tv_comment_market_after = (TextView) view.findViewById(R.id.tv_comment_market_after);
		tv_comment_market_date = (TextView) view.findViewById(R.id.tv_comment_market_date);
		tv_comment_market_param = (TextView) view.findViewById(R.id.tv_comment_market_param);
		tv_market_detail_comment_count = (TextView) view.findViewById(R.id.tv_market_detail_comment_count);
		civ_comment_market_icon = (CircleImageView) view.findViewById(R.id.civ_comment_market_icon);
		mgv_comment_market_image = (MyGridView) view.findViewById(R.id.mgv_comment_market_image);
		mgv_comment_market_afterimage = (MyGridView) view.findViewById(R.id.mgv_comment_market_afterimage);
		rl_market_detail_comment_more = (RelativeLayout) view.findViewById(R.id.rl_market_detail_comment_more);
		rl_market_detail_comment_more.setOnClickListener(this);
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
