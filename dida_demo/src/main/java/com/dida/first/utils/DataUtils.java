package com.dida.first.utils;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @author KingJA
 * @data 2015-8-7 上午10:09:35
 * @use 网络访问，数据解析，数据缓存工具类
 * 
 */
public abstract class DataUtils<T> {
	/**
	 * 普通加载页面的数据访问
	 * 
	 * @return
	 */
	public void initData(String url, RequestParams RequestParams, String spName,
			Class<T> clazz) {
		String cache = SharedPreferencesUtils.getStringData(spName, "");

			if (!TextUtils.isEmpty(cache)) {
				T bean = json2Bean(cache, clazz);
//				Log.i("从缓存取", cache);
				onCache(bean);
			}
		initDataFromNet(url, RequestParams,spName, clazz);

	}

	/**
	 * @param bean
	 */
	public abstract void onCache(T bean);

	/**
	 * @param url
	 * @param requestParams
	 */
	private void initDataFromNet(String url, RequestParams requestParams,final String spName,
			final Class<T> clazz) {

		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.POST, url, requestParams,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
//						Log.i("网络读取", responseInfo.result);
						T bean = json2Bean(responseInfo.result, clazz);
						SharedPreferencesUtils.saveStringData(spName, responseInfo.result);
						onSucc(bean);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						ToastUtil.showMyToast("服务器君被外星人绑架了");
						onFail(error, msg);
					}
				});
	}
	/**
	 * @param url
	 * @param requestParams
	 */
public void getDataFromNet(String url, RequestParams requestParams,
			final Class<T> clazz) {
		
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.POST, url, requestParams,
				new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
//				Log.i("网络读取", responseInfo.result);
				T bean = json2Bean(responseInfo.result, clazz);
				onSucc(bean);
			}
			
			@Override
			public void onFailure(HttpException error, String msg) {
//				Log.i("onFailure", msg);
				ToastUtil.showMyToast("服务器君被外星人绑架了");
				onFail(error, msg);
			}
		});
	}

	/**
	 * @param error
	 * @param msg
	 */
	public abstract void onFail(HttpException error, String msg);

	/**
	 * @param bean
	 */
	public abstract void onSucc(T bean);

	public T json2Bean(String json, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}

	public RequestParams getYaoYueParams(int type, int page) {
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("name", "");
		requestParams.addBodyParameter("type", type + "");
		requestParams.addBodyParameter("page", page + "");
		return requestParams;

	}
}
