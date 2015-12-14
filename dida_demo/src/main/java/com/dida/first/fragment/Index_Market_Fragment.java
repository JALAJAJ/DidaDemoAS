package com.dida.first.fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.dida.first.R;

public class Index_Market_Fragment extends Fragment_Base_Nomal {
    private Market_Real_Fragment mRealFragment;


    @Override
    public View setFragmentView() {
        view = View.inflate(context, R.layout.fr_market, null);
        return view;
    }

    @Override
    public void initFragmentView() {

    }

    @Override
    public void initFragmentNet() {

    }

    @Override
    public void initFragmentEvent() {

    }

    @Override
    public void initFragmentData() {
        setTab(0);
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
            case 0:
                if (mRealFragment == null) {
                    // 如果yaoyueFragment为空，则创建一个并添加到界面上
                    mRealFragment = new Market_Real_Fragment();
                    transaction.add(R.id.fl_market, mRealFragment);
                } else {
                    // 如果yaoyueFragment不为空，则直接将它显示出来
                    transaction.show(mRealFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mRealFragment != null) {
            transaction.hide(mRealFragment);
        }
    }
}
