package com.dida.first.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dida.first.R;
import com.dida.first.adapter.LvAdapter;
import com.dida.first.bean.MarketBean;
import com.dida.first.utils.GsonUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market_Real_Fragment extends Fragment_Base_Nomal {
    private View contentView;
    private PullToRefreshListView plv;
    private List<MarketBean.ResEntity.ProductsEntity.StEntity> realProList = new ArrayList<MarketBean.ResEntity.ProductsEntity.StEntity>();
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Log.i("1.更新数据", "realProList=" + realProList.size());
            rl_loading.setVisibility(View.GONE);
            lvAdapter.addData(realProList);
        }
    };
    private LvAdapter lvAdapter;
    private RelativeLayout rl_loading;

    @Override
    public View setFragmentView() {
        view = View.inflate(getActivity(), R.layout.fr_market_real, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        rl_loading = (RelativeLayout) view.findViewById(R.id.rl_loading);
        plv = (PullToRefreshListView) view.findViewById(R.id.plv);
    }

    @Override
    public void initFragmentNet() {

    }

    @Override
    public void initFragmentEvent() {
        lvAdapter = new LvAdapter(realProList, context);
        plv.setAdapter(lvAdapter);
    }

    @Override
    public void initFragmentData() {
        refresh(1);
    }

    @Override
    public void onChildClick(View v) {

    }


    private void initView() {


    }

    private void initEvent() {

    }

    private void refresh(final int page) {
        StringRequest http = new StringRequest(Request.Method.POST, "http://121.40.28.206/commodity/queryAllCommodity.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                MarketBean marketBean = GsonUtil.json2Bean(s, MarketBean.class);
                realProList = marketBean.getRes().getProducts().getSt();
                Log.i("1.获取数据", "realProList=" + realProList.size());
                handler.sendEmptyMessage(0);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("onErrorResponse", "VolleyError=" + volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("app", "1");
                map.put("type", "1");
                map.put("page", page+"");
                return map;
            }
        };

        mQueue.add(http);

    }
}
