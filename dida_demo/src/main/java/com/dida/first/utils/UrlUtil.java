package com.dida.first.utils;

public class UrlUtil {
    public  enum InterfaceName{
        I_MARKET_LIST,I_MARKET_DETAIL,I_MARKET_ADD_CANCLE_COLLECT,I_PINGOU_LIST,I_PINGOU_DETAIL,I_SHOW_LIST,I_SHOW_EDIT,I_REGISTER_SMS
    }


    public final static String HOST = "http://121.40.28.206";//主机名
//    public final static String HOST = "http://192.168.1.178:8080";//主机名
    /*==============================================集市==============================================*/
    public final static String MARKET_LIST = "/commodity/queryAllCommodity.do";//集市列表
    public final static String MARKET_DETAIL = "/commodity/queryDetailCommodity.do";//集市详情
    public final static String MARKET_IF_COLLECT = "/collection/isCollected.do";//集市是否加入过代购单
    public final static String MARKET_SEARCH = "/commodity/searchCommodityByName.do";//集市商品搜索
    public final static String MARKET_ADD_CANCLE_COLLECT = "/collection/addOrDelProduct.do";//集市添加删除收藏
    /*==============================================拼购==============================================*/
    public final static String PINGOU_LIST = "/service/queryAllTask.do";//拼购列表
    public final static String PINGOU_DETAIL = "/service/detailSerivce.do";//拼购详情
    public final static String PINGOU_SEARCH = "/service/queryAllTask.do";//拼购搜索
    public final static String PINGOU_DO_COLLECT = "/collection/addTask.do";//拼购收藏
    public final static String PINGOU_NO_COLLECT = "/collection/deleteTask.do";//拼购取消收藏
    public final static String PINGOU_IF_COLLECT = "/collection/isTaskCollection.do";//拼购判断是否收藏
    /*==============================================晒单==============================================*/
    public final static String SHOW_LIST = "/prepayorder/getPrepayOrders.do";//晒单列表
    public final static String SHOW_EDIT = "/prepayorder/selectPrepayOrder.do";//晒单编辑
    public final static String SHOW_PUBLISH = "/group/saveGroup.do";//晒单发布
    public final static String REGISTER_SMS = "/collection/deleteProduct.do";

    /**
     * 获取接口地址
     * @param interfaceName
     * @return
     */
    public static String getIUrl(InterfaceName interfaceName){
        String url=HOST;
        switch (interfaceName){
            //集市列表
            case I_MARKET_LIST:
                url+=MARKET_LIST;
                break;
            //集市详情
            case I_MARKET_DETAIL:
                url+=MARKET_DETAIL;
                break;
            //拼购列表
            case I_PINGOU_LIST:
                url+=PINGOU_LIST;
                break;
            //拼购详情
            case I_PINGOU_DETAIL:
                url+=PINGOU_DETAIL;
                break;
            //集市添加删除收藏
            case I_MARKET_ADD_CANCLE_COLLECT:
                url+=MARKET_ADD_CANCLE_COLLECT;
                break;
            //晒单列表
            case I_SHOW_LIST:
                url+=SHOW_LIST;
                break;
            //晒单编辑
            case I_SHOW_EDIT:
                url += SHOW_EDIT;
                break;
            default:
                break;
        }
        return url;

    }

    /**
     * 给没有域名的Url加上域名
     * @param url
     * @return
     */
    public static String getUrl(String url) {
        if (!url.startsWith("http")) {
            url = UrlUtil.HOST + url;
        }
        return url;
    }


}
