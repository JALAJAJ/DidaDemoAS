package com.dida.first.holder;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.bean.BeanDetailMarket;
import com.dida.first.interfaces.OnShowItemListener;
import com.dida.first.popupwindow.PopupWindowShare;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;
import com.dida.first.view.MyLunBoTu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingJA
 * @data 2015-9-14 下午4:41:42
 * @use
 */
public class MDetail_Head_Holder extends BaseHolder<BeanDetailMarket> implements OnClickListener {
    private LinearLayout rootView;
    private Activity activity;

    public MDetail_Head_Holder(LinearLayout rootView, Activity activity) {
        this.rootView = rootView;
        this.activity = activity;
    }


    private FrameLayout fl_market_head;
    private TextView tv_market_detail_head_title;
    private TextView tv_market_detail_head_count;
    private TextView tv_market_detail_head_price;
    private TextView tv_market_detail_head_stock;
    private TextView tv_market_detail_head_address;
    private PopupWindowShare popupWindowShare;
    private RelativeLayout ll_market_detail_head_favorite;
    private RelativeLayout ll_market_detail_head_share;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.market_detail_head);
        fl_market_head = (FrameLayout) view.findViewById(R.id.fl_market_head);
        tv_market_detail_head_title = (TextView) view.findViewById(R.id.tv_market_detail_head_title);
        tv_market_detail_head_price = (TextView) view.findViewById(R.id.tv_market_detail_head_price);
        tv_market_detail_head_count = (TextView) view.findViewById(R.id.tv_market_detail_head_count);
        tv_market_detail_head_stock = (TextView) view.findViewById(R.id.tv_market_detail_head_stock);
        tv_market_detail_head_address = (TextView) view.findViewById(R.id.tv_market_detail_head_address);
        ll_market_detail_head_favorite = (RelativeLayout) view.findViewById(R.id.ll_market_detail_head_favorite);
        ll_market_detail_head_share = (RelativeLayout) view.findViewById(R.id.ll_market_detail_head_share);
        return view;
    }

    @Override
    public void refreshView() {
        BeanDetailMarket data = getData();
        tv_market_detail_head_title.setText(data.getRes().getTimeOrPhyProduct().getName());
        tv_market_detail_head_price.setText(data.getRes().getTimeOrPhyProduct().getPrice()+"");
        tv_market_detail_head_count.setText(data.getRes().getTimeOrPhyProduct().getSalesCount()+"");
        tv_market_detail_head_stock.setText(data.getRes().getTimeOrPhyProduct().getStock()+"");
        tv_market_detail_head_address.setText(data.getRes().getShop().getProvince()+" "+data.getRes().getShop().getCity());


        ll_market_detail_head_share.setOnClickListener(this);
        ll_market_detail_head_favorite.setOnClickListener(this);
        MyLunBoTu<BeanDetailMarket.ResEntity.ProductImgsEntity> mLunBoTu = new MyLunBoTu<BeanDetailMarket.ResEntity.ProductImgsEntity>(activity);
        mLunBoTu.setOnShowItemListener(new OnShowItemListener<BeanDetailMarket.ResEntity.ProductImgsEntity>() {
            @Override
            public String onShowItem(List<BeanDetailMarket.ResEntity.ProductImgsEntity> mData, int position) {
                return UrlUtil.HOST+mData.get(position % mData.size()).getImageURL();
            }
        });
        mLunBoTu.show(data.getRes().getProductImgs());
        fl_market_head.addView(mLunBoTu);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_market_detail_head_share:
                if (popupWindowShare == null) {
                    popupWindowShare = new PopupWindowShare(rootView, activity, new ArrayList<String>());
                }
                popupWindowShare.showPopupWindow();
                break;
            case R.id.ll_market_detail_head_favorite:
                ToastUtil.showMyToast("收藏");
                break;
            default:
                break;
        }
    }
}
