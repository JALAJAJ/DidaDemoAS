/**
 * 
 */
package com.dida.first.activity;

import java.util.ArrayList;
import java.util.List;

import com.dida.first.LoadPage;
import com.dida.first.R;
import com.dida.first.LoadPage.ResultState;
import com.dida.first.bean.BeanParam;
import com.dida.first.bean.BeanParamsSelect;
import com.dida.first.bean.YaoYueBean.Res;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.holder.MDetail_Comment_Holder;
import com.dida.first.holder.MDetail_Head_Holder;
import com.dida.first.holder.MDetail_Image_Holder;
import com.dida.first.holder.MDetail_Store_Holder;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.PopupWindowParam;
import com.dida.first.view.PopupWindowSelect;
import com.dida.first.view.PopupWindowSelect2;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author KingJA
 * @data 2015-9-11 上午9:47:00
 * @use
 * 
 */
public class Detail_Market_Activity extends Activity implements OnClickListener {
	private List<Res> list = new ArrayList<Res>();
	private List<BeanParam> paramList = new ArrayList<BeanParam>();
	private LoadPage loadPage;
	private LinearLayout ll_parent_market_detail;
	private List<String> urlList = new ArrayList<String>();
	private void initUrl() {
		urlList.clear();
		urlList.add("https://img.alicdn.com/imgextra/i4/228784630/TB2e7LZeFXXXXXVXXXXXXXXXXXX-228784630.jpg");
		urlList.add("https://img.alicdn.com/imgextra/i4/327145812/TB2Kti5eFXXXXX7XpXXXXXXXXXX-327145812.jpg");
		urlList.add("https://img.alicdn.com/imgextra/i2/327145812/TB2ogG1eFXXXXaUXpXXXXXXXXXX-327145812.jpg");
		urlList.add("https://img.alicdn.com/imgextra/i1/671012022/TB2a8BOepXXXXX4XXXXXXXXXXXX-671012022.jpg");
		urlList.add("https://img.alicdn.com/imgextra/i2/671012022/TB22YtJepXXXXbDXXXXXXXXXXXX-671012022.jpg");
		urlList.add("https://img.alicdn.com/imgextra/i2/671012022/TB29QBmepXXXXcJXpXXXXXXXXXX-671012022.jpg");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityFactory.marketActivity=this;
		initData();
		loadPage = new LoadPage(UIUtils.getContext()) {

			@Override
			public ResultState onLoad() {
				return Detail_Market_Activity.this.onLoad();
			}

			@Override
			public View onCreateSuccessedView() {
				return Detail_Market_Activity.this.onCreateSuccessedView();
			}
		};
		setContentView(loadPage);
		// 手动促发onLoad方法的调用
		if (loadPage != null) {
			loadPage.show();
		}

	}

	private void initData() {
		initUrl();
		for (int i = 0; i < 20; i++) {
			paramList.add(new BeanParam("参数名"+i, "参数内容"+i));
		}
		
	}

	/**
	 * @return
	 */
	protected ResultState onLoad() {
		return ResultState.STATE_SUCCESSED;
	}

	/**
	 * @return
	 */
	protected View onCreateSuccessedView() {
		View view = UIUtils.inflate(R.layout.activity_market_detail);
		RelativeLayout rl_bottom_market_detail_addcar = (RelativeLayout) view.findViewById(R.id.rl_bottom_market_detail_addcar);
		RelativeLayout rl_bottom_market_detail_store = (RelativeLayout) view.findViewById(R.id.rl_bottom_market_detail_store);
		RelativeLayout rl_bottom_market_detail_addorder = (RelativeLayout) view.findViewById(R.id.rl_bottom_market_detail_addorder);
		rl_bottom_market_detail_addcar.setOnClickListener(this);
		rl_bottom_market_detail_store.setOnClickListener(this);
		rl_bottom_market_detail_addorder.setOnClickListener(this);
		
		ll_parent_market_detail = (LinearLayout) view
				.findViewById(R.id.ll_parent_market_detail);
		ImageView iv_market_detail_back = (ImageView) view.findViewById(R.id.iv_market_detail_back);
		ImageView iv_market_detail_chat = (ImageView) view.findViewById(R.id.iv_market_detail_chat);
		iv_market_detail_back.setOnClickListener(this);
		iv_market_detail_chat.setOnClickListener(this);
		/**
		 * head信息
		 */
		FrameLayout fl_market_detail_head = (FrameLayout) view
				.findViewById(R.id.fl_market_detail_head);
		MDetail_Head_Holder headHolder = new MDetail_Head_Holder(ll_parent_market_detail,this);
		headHolder.setContext(this);
		headHolder.setData(list);
		fl_market_detail_head.addView(headHolder.getRootView());
		/**
		 * 规格选择
		 */
		RelativeLayout rl_market_detail_param = (RelativeLayout) view
				.findViewById(R.id.rl_market_detail_param);
		rl_market_detail_param.setOnClickListener(this);
		/**
		 * 评价
		 */
		FrameLayout fl_market_detail_comment = (FrameLayout) view
				.findViewById(R.id.fl_market_detail_comment);
		MDetail_Comment_Holder commentHolder = new MDetail_Comment_Holder();
		commentHolder.setData(list);
		fl_market_detail_comment.addView(commentHolder.getRootView());
		/**
		 * 店铺信息
		 */
		FrameLayout fl_market_detail_store = (FrameLayout) view
				.findViewById(R.id.fl_market_detail_store);
		MDetail_Store_Holder storeHolder = new MDetail_Store_Holder();
		storeHolder.setData(list);
		fl_market_detail_store.addView(storeHolder.getRootView());
		
		/**
		 * 商品参数
		 */
		RelativeLayout rl_market_detail_info = (RelativeLayout) view
				.findViewById(R.id.rl_market_detail_info);
		rl_market_detail_info.setOnClickListener(this);
		/**
		 * 图片详情
		 */
		FrameLayout fl_market_detail_image = (FrameLayout) view
				.findViewById(R.id.fl_market_detail_image);
		MDetail_Image_Holder imageHolder = new MDetail_Image_Holder();
		imageHolder.setList(urlList);
		fl_market_detail_image.addView(imageHolder.getRootView());
		return view;
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_market_detail_param:
			ToastUtil.showMyToast("选择规格");
			PopupWindowSelect sharePopupWindow = new PopupWindowSelect(
					ll_parent_market_detail, this,new ArrayList<BeanParamsSelect>());
			sharePopupWindow.showPopupWindow();
			break;
		case R.id.rl_market_detail_info:
			ToastUtil.showMyToast("商品参数");
			PopupWindowParam paramPopupWindow = new PopupWindowParam(
					ll_parent_market_detail, this,paramList);
			paramPopupWindow.showPopupWindow();
			break;
		case R.id.iv_market_detail_back:
			finish();
			break;
		case R.id.iv_market_detail_chat:
			//TODO
			ToastUtil.showMyToast("聊天界面");
			break;
		case R.id.rl_bottom_market_detail_addcar:
			//TODO
			ToastUtil.showMyToast("加入待购");
			break;
		case R.id.rl_bottom_market_detail_store:
			//TODO
			ToastUtil.showMyToast("聊天界面");
			break;
		case R.id.rl_bottom_market_detail_addorder:
			//TODO
			ToastUtil.showMyToast("加入订单");
			break;

		default:
			break;
		}

	}
}
