package com.dida.first.utils;

public class UrlUtil {
	public final static String HOST="http://121.40.28.206";//主机名
	/*==============================================集市==============================================*/
	public final static String MARKET_LIST ="/commodity/queryAllCommodity.do";//集市列表
	public final static String MARKET_DETAIL="/commodity/queryDetailCommodity.do";//集市详情
	public final static String MARKET_DO_COLLECT="/collection/addProduct.do";//集市加入代购单
	public final static String MARKET_IF_COLLECT="/collection/isCollected.do";//集市是否加入过代购单
	public final static String MARKET_NO_COLLECT="/collection/deleteProduct.do";//集市取消加入代购单
	public final static String MARKET_SEARCH="/commodity/searchCommodityByName.do";//集市商品搜索
	/*==============================================拼购==============================================*/
	public final static String PINGOU_LIST="/service/queryAllTask.do";//拼购列表
	public final static String PINGOU_DETAIL="/service/detailSerivce.do";//拼购详情
	public final static String PINGOU_SEARCH="/service/queryAllTask.do";//拼购搜索
	public final static String PINGOU_DO_COLLECT="/collection/addTask.do";//拼购收藏
	public final static String PINGOU_NO_COLLECT="/collection/deleteTask.do";//拼购取消收藏
	public final static String PINGOU_IF_COLLECT="/collection/isTaskCollection.do";//拼购判断是否收藏

	public final static String REGISTER_SMS="/collection/deleteProduct.do";
}
