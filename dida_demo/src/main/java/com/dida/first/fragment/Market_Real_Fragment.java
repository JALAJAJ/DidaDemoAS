package com.dida.first.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dida.first.R;
import com.dida.first.adapter.MarketLvAdapter;
import com.dida.first.bean.MarketBean;
import com.dida.first.utils.GsonUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.dida.first.view.AutoLunBoTu;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market_Real_Fragment extends Fragment_Base_Nomal {

    private MarketLvAdapter marketLvAdapter;
    private RelativeLayout rl_loading;
    private boolean hasInitHead;//初始化轮播图
    private boolean mHasMore = true;//有无更多
    private static final int NET_REFRESH = 0;//刷新
    private static final int NET_MORE = 1;//加载更多
    private static final int NET_ERROR = -1;//错误
    private static final int NET_NOMORE = -3;//没有更多
    private int mInitPager = 1;//初始化页面Position
    private PullToRefreshListView plv;
    private List<MarketBean.ResEntity.ProductAdsEntity> productAds = new ArrayList<MarketBean.ResEntity.ProductAdsEntity>();
    private List<MarketBean.ResEntity.ProductsEntity.StEntity> realProList = new ArrayList<MarketBean.ResEntity.ProductsEntity.StEntity>();
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //关闭刷新
            setRefreshComplete();
            switch (msg.what) {
                case NET_REFRESH:
                    mInitPager = 1;
                    ToastUtil.showMyToast("更新数据 page=" + mInitPager + " 数据量=" + realProList.size());
                    //隐藏加载进度条
                    rl_loading.setVisibility(rl_loading.getVisibility() == View.VISIBLE ? View.GONE : View.GONE);
                    //避免多次加Head
                    if (!hasInitHead) {
                        addLVHead(productAds);
                        hasInitHead = true;
                    }
                    marketLvAdapter.setData(realProList);
                    break;
                case NET_MORE:
                    ToastUtil.showMyToast("加载更多数据 page=" + mInitPager + " 数据量=" + realProList.size());
                    marketLvAdapter.addData(realProList);
                    break;
                case NET_ERROR:
                    ToastUtil.showMyToast("服务器被都敏俊拐走了！");
                    break;
                case NET_NOMORE:
                    ToastUtil.showMyToast("更多商品敬请期待！");
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public View setFragmentView() {
        view = View.inflate(getActivity(), R.layout.fr_market_real, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        rl_loading = (RelativeLayout) view.findViewById(R.id.rl_loading);
        plv = (PullToRefreshListView) view.findViewById(R.id.plv_market_real);
    }

    @Override
    public void initFragmentNet() {
    }

    @Override
    public void initFragmentEvent() {
        marketLvAdapter = new MarketLvAdapter(realProList, getActivity());
        plv.setAdapter(marketLvAdapter);
        plv.setOnRefreshListener(onRefreshListener);
    }

    @Override
    public void initFragmentData() {
        refresh(1, NET_REFRESH);
    }

    /**
     * 刷新结束，隐藏刷新布局
     */
    private void setRefreshComplete() {
        if (plv.isRefreshing()) {
            plv.onRefreshComplete();
        }
    }

    /**
     * 刷新加载监听器
     */
    private PullToRefreshBase.OnRefreshListener2 onRefreshListener = new PullToRefreshBase.OnRefreshListener2<ListView>() {

        @Override
        public void onPullDownToRefresh(
                PullToRefreshBase<ListView> refreshView) {
            refresh(1, NET_REFRESH);

        }

        @Override
        public void onPullUpToRefresh(
                PullToRefreshBase<ListView> refreshView) {
            if (mHasMore) {
                refresh(++mInitPager, NET_MORE);
            } else {
                handler.sendEmptyMessage(NET_NOMORE);
            }
        }
    };

    /**
     * ListView加入轮播图Head
     *
     * @param adsList
     */
    private void addLVHead(List<MarketBean.ResEntity.ProductAdsEntity> adsList) {
        AutoLunBoTu mLunBo = (AutoLunBoTu) View.inflate(context, R.layout.view_vp_xml, null);
        mLunBo.show(adsList);//显示轮播图
        mLunBo.startRoll();//轮播图自动切换
        ListView refreshableView = plv.getRefreshableView();
        refreshableView.addHeaderView(mLunBo, null, false);
    }


    @Override
    public void onChildClick(View v) {
        switch (v.getId()){
        }

    }

    /**
     * 刷洗·加载 网络操作
     *
     * @param page
     * @param requestCode
     */
    private void refresh(final int page, final int requestCode) {
        StringRequest http = new StringRequest(Request.Method.POST, UrlUtil.HOST + UrlUtil.MARKET_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                MarketBean marketBean = GsonUtil.json2Bean(s, MarketBean.class);
                productAds = marketBean.getRes().getProductAds();
                realProList = marketBean.getRes().getProducts().getSt();
                if (requestCode == NET_MORE && realProList.isEmpty()) {
                    ToastUtil.showMyToast("没有更多商品");
                    mHasMore = false;
                    handler.sendEmptyMessage(NET_NOMORE);
                }
                handler.sendEmptyMessage(requestCode);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                handler.sendEmptyMessage(NET_ERROR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("app", "1");
                map.put("type", "1");
                map.put("page", page + "");
                return map;
            }
        };

        mQueue.add(http);

    }
}
