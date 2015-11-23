/**
 *
 */
package com.dida.first.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dida.first.R;
import com.dida.first.activity.Detail_Market_Activity;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.adapter.SingleItemAdapter;
import com.dida.first.bean.MarketBean;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyGridView;
import com.dida.first.view.MyViewPager;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author KingJA
 * @data 2015-10-14 下午4:14:32
 * @use
 */
public class Market_Real_Fragment extends Fragment_Base_Nomal {
    private List<String> urlList = new ArrayList<String>();
    private PullToRefreshListView plv_market_group;
    private LinearLayout.LayoutParams param;
    private int itemWidth;
    private List<MarketBean> marketBeanList = new ArrayList<MarketBean>();
    private int mPage;

    @Override
    public View setFragmentView() {
        view = View.inflate(context, R.layout.fragment_market_group, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        plv_market_group = (PullToRefreshListView) view.findViewById(R.id.plv_market_group);

    }

    @Override
    public void initFragmentNet() {
        initUrl();
        int screenWidth = UIUtils.getScreenWidth();
        itemWidth = (screenWidth - 3 * UIUtils.dip2px(4)) / 2;
        param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemWidth);
        Log.i("itemWidth", itemWidth + "");
        for (int i = 0; i < 20; i++) {
            marketBeanList.add(new MarketBean());

        }

        refresh(1);
    }

    private void getMore(int page){
        mPage++;
        refresh(page);
    }
    private void refresh(final int page) {
        StringRequest http = new StringRequest(Request.Method.POST, "http://121.40.28.206/commodity/queryAllCommodity.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("onResponse", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("onErrorResponse", volleyError.toString());
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

    @Override
    public void initFragmentEvent() {
        initIndicator();
        View lunbotuView = View.inflate(context, R.layout.view_market_lunbotu, null);
        RelativeLayout rl_lunbotu = (RelativeLayout) lunbotuView.findViewById(R.id.rl_lunbotu);
        initViewPager(context, urlList, rl_lunbotu);
        // 加入轮播图布局
        ListView refreshableView = plv_market_group.getRefreshableView();
        refreshableView.addHeaderView(lunbotuView, null, false);

        plv_market_group.setAdapter(new MySingleAdapter());
        plv_market_group.setOnRefreshListener(new OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
//                new GetDataTask().execute();
                refresh(1);
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
//                new GetDataTask().execute();
                getMore(mPage);
            }
        });
    }

    @Override
    public void initFragmentData() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onChildClick(View v) {

    }

    private void initUrl() {
        urlList.clear();
        urlList.add("http://pic74.nipic.com/file/20150809/21430351_115107197205_2.jpg");
        urlList.add("http://pic40.nipic.com/20140423/5523845_164638406332_2.jpg");
        urlList.add("http://pic74.nipic.com/file/20150807/21430351_223621960871_2.jpg");
        urlList.add("http://pic74.nipic.com/file/20150807/21430351_224952168084_2.jpg");
        urlList.add("http://pic74.nipic.com/file/20150803/21430351_175936202461_2.jpg");
    }

    /**
     * 初始化刷新加载的内容
     */
    private void initIndicator() {
        ILoadingLayout downLabels = plv_market_group
                .getLoadingLayoutProxy(true, false);
        downLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        downLabels.setRefreshingLabel("正在刷新...");// 刷新时
        downLabels.setReleaseLabel("释放刷新...");// 下来达到一定距离时，显示的提示
        ILoadingLayout upLabels = plv_market_group.getLoadingLayoutProxy(false, true);
        upLabels.setPullLabel("上拉加载...");// 刚上拉时，显示的提示
        upLabels.setRefreshingLabel("正在加载...");// 加载时
        upLabels.setReleaseLabel("释放加载...");// 上来达到一定距离时，显示的提示
    }

    /**
     * 初始化轮播图
     */
    private void initViewPager(Context context, List<String> urlList,
                               RelativeLayout relativeLayout) {
        MyViewPager myViewPager = new MyViewPager(context, urlList,
                relativeLayout);
        relativeLayout.addView(myViewPager);
        myViewPager.initDot();
        myViewPager.setCurrentItem(2000);
        myViewPager.startRoll();
    }

    class MyGridViewAdapter extends MyBaseListViewAdapter<MarketBean> {

        public MyGridViewAdapter(List<MarketBean> list) {
            super(list);
        }

        @Override
        public View getItemView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = view.inflate(context, R.layout.item_marketlist,
                        null);
                ImageView iv_market_item = (ImageView) convertView
                        .findViewById(R.id.iv_market_item);

                iv_market_item.setLayoutParams(param);
            }

            return convertView;
        }
    }

    private class GetDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            ToastUtil.showMyToast("完成");
            plv_market_group.onRefreshComplete();
        }
    }

    class MySingleAdapter extends SingleItemAdapter {

        @Override
        public View getSingleView(int position, View convertView,
                                  ViewGroup parent) {
            View itemView = View.inflate(context,
                    R.layout.item_single_gridview, null);
            MyGridView gv_item_market = (MyGridView) itemView
                    .findViewById(R.id.gv_item_market);
            gv_item_market.setAdapter(new MyGridViewAdapter(
                    marketBeanList));
            gv_item_market.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    ToastUtil.showMyToast(position + "");
                    Intent intent = new Intent(ActivityFactory.mainActivity, Detail_Market_Activity.class);
                    startActivity(intent);

                }
            });
            return itemView;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Market_Real_Fragment", "onDestroy");
    }
}
