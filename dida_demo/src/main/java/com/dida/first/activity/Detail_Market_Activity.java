/**
 *
 */
package com.dida.first.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.entity.BeanDetailMarket;
import com.dida.first.entity.BeanRes;
import com.dida.first.holder.MDetail_Comment_Holder;
import com.dida.first.holder.MDetail_Head_Holder;
import com.dida.first.holder.MDetail_Image_Holder;
import com.dida.first.holder.MDetail_Store_Holder;
import com.dida.first.popupwindow.PopupWindowParam;
import com.dida.first.popupwindow.PopupWindowSelect;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KingJA
 * @data 2015-9-11 上午9:47:00
 * @use
 */
public class Detail_Market_Activity extends BaseNomalActivity {

    private static final int RES_OK=1;
    private static final int RES_ERR=-1;
    private static final int RES_COLLECT_OK=2;
    private static final String TAG = "Detail_Market_Activity";
    private static final int RES_COLLECT_ERR = -2;
    private LinearLayout ll_parent_market_detail;
    private RelativeLayout rl_bottom_market_detail_addcar;
    private RelativeLayout rl_bottom_market_detail_addorder;
    private RelativeLayout rl_market_detail_buy;
    private ImageView iv_market_detail_back;
    private ImageView iv_market_detail_chat;
    private MDetail_Head_Holder headHolder;
    private MDetail_Image_Holder imageHolder;
    private MDetail_Store_Holder storeHolder;
    private MDetail_Comment_Holder commentHolder;
    private BeanDetailMarket mDetailMarket=new BeanDetailMarket();
    private PopupWindowSelect sharePopupWindow;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            rl_loading.setVisibility(View.GONE);
            switch (msg.what){
                case RES_OK:
                    setData(mDetailMarket);
                    //初始化参数列表
                    initPopupWindow();
                    break;
                case RES_ERR:
                    ToastUtil.showMyToast("服务器君被绑架啦！");
                    break;
                case RES_COLLECT_OK:
                    ToastUtil.showMyToast("成功添加到待购订单！");
                    break;
                case RES_COLLECT_ERR:
                    ToastUtil.showMyToast("添加到待购订单失败！");
                    break;
            }

        }
    };
    private RelativeLayout rl_loading;
    private String productNo;
    private String type;

    private void initPopupWindow() {
        sharePopupWindow = new PopupWindowSelect(
                ll_parent_market_detail, Detail_Market_Activity.this, mDetailMarket.getRes().getPurchaseAttrList());
        paramPopupWindow = new PopupWindowParam(
                ll_parent_market_detail, Detail_Market_Activity.this, mDetailMarket.getRes().getProductCustomAttrList());
    }

    private PopupWindowParam paramPopupWindow;

    @Override
    protected void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.rl_market_detail_param:
                ToastUtil.showMyToast("选择规格");
                sharePopupWindow.showPopupWindow();
                break;
            case R.id.rl_market_detail_info:
                ToastUtil.showMyToast("商品参数");

                paramPopupWindow.showPopupWindow();
                break;
            case R.id.iv_market_detail_back:
                finish();
                break;
            case R.id.iv_market_detail_chat:
                //TODO
                ToastUtil.showMyToast("聊天界面");
                break;
            case R.id.rl_market_detail_addOrder:
                //TODO
                ToastUtil.showMyToast("加入订单");
                doNetCollect(productNo,type,"fb9a38d82cd3405a9b60ec54cdb5ecdf");
                break;
            case R.id.rl_market_detail_addShow:
                //TODO
                ToastUtil.showMyToast("加入晒单");
                break;
            case R.id.rl_market_detail_buy:
                //TODO
                ToastUtil.showMyToast("立即购买");
                break;

            default:
                break;
        }
    }

    @Override
    protected View setView() {
        View view = UIUtils.inflate(R.layout.activity_market_detail);
        rl_loading = (RelativeLayout) view.findViewById(R.id.rl_loading);

        /**
         * head信息
         */
        FrameLayout fl_market_detail_head = (FrameLayout) view
                .findViewById(R.id.fl_market_detail_head);
        headHolder = new MDetail_Head_Holder(ll_parent_market_detail, this);
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
        commentHolder = new MDetail_Comment_Holder();
        fl_market_detail_comment.addView(commentHolder.getRootView());
        /**
         * 店铺信息
         */
        FrameLayout fl_market_detail_store = (FrameLayout) view
                .findViewById(R.id.fl_market_detail_store);
        storeHolder = new MDetail_Store_Holder();
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
        imageHolder = new MDetail_Image_Holder();
        fl_market_detail_image.addView(imageHolder.getRootView());
        return view;
    }

    @Override
    protected void initView() {
        ll_parent_market_detail = (LinearLayout) view
                .findViewById(R.id.ll_parent_market_detail);
        rl_bottom_market_detail_addcar = (RelativeLayout) view.findViewById(R.id.rl_market_detail_addOrder);
        rl_bottom_market_detail_addorder = (RelativeLayout) view.findViewById(R.id.rl_market_detail_addShow);
        rl_market_detail_buy = (RelativeLayout) view.findViewById(R.id.rl_market_detail_buy);
        iv_market_detail_back = (ImageView) view.findViewById(R.id.iv_market_detail_back);
        iv_market_detail_chat = (ImageView) view.findViewById(R.id.iv_market_detail_chat);
    }

    @Override
    protected void initNet() {
        Bundle bundle = getIntent().getExtras();
        productNo = bundle.getString("productNo");
        type = bundle.getString("type");
        Log.i(TAG, "productNo: "+ productNo +"type: "+ type);

    }

    /**
     * 访问网络-初始化页面
     * @param productNo
     * @param type
     */
    private void doNetInit(final String productNo, final String type) {
        VolleyGsonRequest<BeanDetailMarket> initRequest = new VolleyGsonRequest<BeanDetailMarket>(UrlUtil.HOST+UrlUtil.MARKET_DETAIL, BeanDetailMarket.class, new Response.Listener<BeanDetailMarket>() {
            @Override
            public void onResponse(BeanDetailMarket beanDetailMarket) {
                Log.i(TAG, "beanDetailMarket: "+beanDetailMarket.getRes().getTimeOrPhyProduct().getName());
                mDetailMarket=beanDetailMarket;
                mHandler.sendEmptyMessage(RES_OK);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mHandler.sendEmptyMessage(RES_ERR);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("productNo", productNo);
                map.put("type",type);
                map.put("userId","fb9a38d82cd3405a9b60ec54cdb5ecdf");
                map.put("app","1");
                return map;
            }
        };
        mQueue.add(initRequest);
    }

    private void doNetCollect(final String productId, final String productType,final String userId) {
        VolleyGsonRequest<BeanRes> collectRequest = new VolleyGsonRequest<BeanRes>(UrlUtil.HOST+UrlUtil.MARKET_DO_COLLECT, BeanRes.class, new Response.Listener<BeanRes>() {
            @Override
            public void onResponse(BeanRes res) {
                if (res.getCode()==1){
                    Log.i(TAG, "onResponse: "+res.getCode());
                    mHandler.sendEmptyMessage(RES_COLLECT_OK);
                }else{
                    mHandler.sendEmptyMessage(RES_COLLECT_ERR);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                mHandler.sendEmptyMessage(RES_ERR);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("productId", productId);
                map.put("productType",productType);
                map.put("userId",userId);
                map.put("app","1");
                Log.d(TAG, "productId" + productId+ "productType" + productId+ "userId" + userId);
                return map;
            }
        };
        mQueue.add(collectRequest);
    }


    @Override
    protected void initEvent() {
        rl_market_detail_buy.setOnClickListener(this);
        rl_bottom_market_detail_addcar.setOnClickListener(this);
        rl_bottom_market_detail_addorder.setOnClickListener(this);
        iv_market_detail_back.setOnClickListener(this);
        iv_market_detail_chat.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        doNetInit(productNo, type);
    }

    private void setData(BeanDetailMarket bean) {
        headHolder.setData(bean);
//        commentHolder.setData(bean);
        storeHolder.setData(bean);
//        imageHolder.setData(bean);
    }
}
