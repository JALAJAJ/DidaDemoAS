package com.dida.first.holder;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;

/**
 * @author KingJA
 * @data 2015-9-15 下午5:09:33
 * @use
 * 
 */
public class MDetail_Image_Holder extends BaseHolder<String> {

	private static final String TAG = "MDetail_Image_Holder";
	private WebView wv_market_detail;

	@Override
	public View initView() {
		view = UIUtils.inflate(R.layout.market_detail_image);
		wv_market_detail = (WebView) view.findViewById(R.id.wv_market_detail);
		return view;
	}

	@Override
	public void refreshView() {
		String url = getData();
		wv_market_detail.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}

		});
//		wv_market_detail.loadUrl("http://www.baidu.com");
		Log.i(TAG, "refreshView: "+UrlUtil.getUrl(url));
		wv_market_detail.loadUrl(UrlUtil.getUrl(url)+"&app=1");

	}

}
