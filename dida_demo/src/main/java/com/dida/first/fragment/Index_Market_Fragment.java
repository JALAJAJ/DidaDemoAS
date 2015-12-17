package com.dida.first.fragment;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.dida.first.R;
import com.dida.first.view.MarketSwitchButton;

public class Index_Market_Fragment extends Fragment_Base_Nomal implements MarketSwitchButton.OnSwitchListener{
    private Market_Real_Fragment mRealFragment;
    private Market_Service_Fragment mServiceFragment;
    private int mType=1;
    private MarketSwitchButton msb_market;


    @Override
    public View setFragmentView() {
        view = View.inflate(context, R.layout.fr_market, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        msb_market = (MarketSwitchButton) view.findViewById(R.id.msb_market);
    }

    @Override
    public void initFragmentNet() {

    }

    @Override
    public void initFragmentEvent() {
        msb_market.setOnSwitchListener(this);
    }

    @Override
    public void initFragmentData() {
        setTab(1);
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }

    /**
     * 选择对应的Fragment
     */
    // TODO 需要重构
    private void setTab(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (position) {
            case 1:
                if (mRealFragment == null) {
                    mRealFragment = new Market_Real_Fragment();
                    transaction.add(R.id.fl_market, mRealFragment);
                } else {
                    transaction.show(mRealFragment);
                }
                break;
            case 2:
                if (mServiceFragment == null) {
                    mServiceFragment = new Market_Service_Fragment();
                    transaction.add(R.id.fl_market, mServiceFragment);
                } else {
                    transaction.show(mServiceFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mRealFragment != null) {
            transaction.hide(mRealFragment);
        }
        if (mServiceFragment != null) {
            transaction.hide(mServiceFragment);
        }
    }

    @Override
    public void onSwitch(boolean isReal) {
       mType=isReal?1:2;
        setTab(mType);
        Log.i(TAG, "onSwitch: "+mType);
    }
}
