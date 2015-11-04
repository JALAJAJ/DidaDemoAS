package com.dida.first.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dida.first.R;

import java.util.ArrayList;
import java.util.List;

public class Index_Market_Fragment extends BaseHeadFragment {
    private FrameLayout fl_market;
    private TextView tv_market_product;
    private TextView tv_market_service;
    private List<Fragment> marketList = new ArrayList<Fragment>();

    private Market_Real_Fragment real_Fragment;
    private Market_Service_Fragment service_Fragment;

    @Override
    public View setContentView() {
        contentView = View.inflate(context, R.layout.fragment_market, null);
        return contentView;
    }

    @Override
    public void initFragmentView() {
        tv_market_product = (TextView) contentView.findViewById(R.id.tv_market_product);
        tv_market_service = (TextView) contentView.findViewById(R.id.tv_market_service);
        fl_market = (FrameLayout) contentView.findViewById(R.id.fl_market);

    }

    @Override
    public void initFragmentEvent() {
        tv_market_product.setOnClickListener(this);
        tv_market_service.setOnClickListener(this);
    }


    @Override
    public void onSearch() {

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.tv_market_product:
                setTab(0);
                break;
            case R.id.tv_market_service:
                setTab(1);
                break;

            default:
                break;
        }

    }

    @Override
    public void initFragmentNet() {
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int position) {
            return marketList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return marketList.size();
        }

    }

    @Override
    public void initFragmentData() {
        setTab(0);

    }


    /**
     * 选择对应的Fragment
     */
    // TODO 需要重构
    private void setTab(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        // clearPopuWindow();
        resetState();
        switch (position) {
            case 0:
                tv_market_product.setBackgroundColor(getResources().getColor(R.color.red));
                tv_market_product.setTextColor(getResources().getColor(
                        R.color.white));
                if (real_Fragment == null) {
                    // 如果yaoyueFragment为空，则创建一个并添加到界面上
                    real_Fragment = new Market_Real_Fragment();
                    transaction.add(R.id.fl_market, real_Fragment);
                } else {
                    // 如果yaoyueFragment不为空，则直接将它显示出来
                    transaction.show(real_Fragment);
                }
                break;
            case 1:
                tv_market_service.setBackgroundColor(getResources().getColor(R.color.red));
                tv_market_service.setTextColor(getResources().getColor(
                        R.color.white));
                if (service_Fragment == null) {
                    service_Fragment = new Market_Service_Fragment();
                    transaction.add(R.id.fl_market, service_Fragment);
                } else {
                    transaction.show(service_Fragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 复位TAB状态
     */
    private void resetState() {
        tv_market_product.setBackgroundColor(getResources().getColor(R.color.white));
        tv_market_product.setTextColor(getResources().getColor(
                R.color.black));
        tv_market_service.setBackgroundColor(getResources().getColor(R.color.white));
        tv_market_service.setTextColor(getResources().getColor(
                R.color.black));
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (real_Fragment != null) {
            transaction.hide(real_Fragment);
        }
        if (service_Fragment != null) {
            transaction.hide(service_Fragment);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Index_Market_Fragment", "onDestroy");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Index_Market_Fragment", "onCreate");
    }
}
