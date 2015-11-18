package com.dida.first.holder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.MyViewPager;
import com.dida.first.popupwindow.PopupWindowShare;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingJA
 * @data 2015-9-14 下午4:41:42
 * @use
 */
public class MDetail_Head_Holder extends BaseHolder implements OnClickListener {
    private List<String> urlList = new ArrayList<String>();
    private Context context;
    private LinearLayout rootView;
    private Activity activity;

    public MDetail_Head_Holder(LinearLayout rootView, Activity activity) {
        this.rootView = rootView;
        this.activity = activity;
    }


    private RelativeLayout rl_market_head_lunbotu;
    private ImageView iv_market_detail_head_icon;
    private TextView tv_market_detail_head_title;
    private TextView tv_market_detail_head_count;
    private TextView tv_market_detail_head_price;
    private TextView tv_market_detail_head_oldprice;
    private PopupWindowShare popupWindowShare;
    private RelativeLayout ll_market_detail_head_favorite;
    private RelativeLayout ll_market_detail_head_share;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.market_detail_head);
        rl_market_head_lunbotu = (RelativeLayout) view.findViewById(R.id.rl_market_head_lunbotu);
        tv_market_detail_head_title = (TextView) view.findViewById(R.id.tv_market_detail_head_title);
        tv_market_detail_head_count = (TextView) view.findViewById(R.id.tv_market_detail_head_count);
        tv_market_detail_head_price = (TextView) view.findViewById(R.id.tv_market_detail_head_price);
        tv_market_detail_head_oldprice = (TextView) view.findViewById(R.id.tv_market_detail_head_oldprice);
        ll_market_detail_head_favorite = (RelativeLayout) view.findViewById(R.id.ll_market_detail_head_favorite);
        ll_market_detail_head_share = (RelativeLayout) view.findViewById(R.id.ll_market_detail_head_share);
        return view;
    }

    @Override
    public void refreshView() {
        tv_market_detail_head_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        ll_market_detail_head_share.setOnClickListener(this);
        ll_market_detail_head_favorite.setOnClickListener(this);
        initUrl();
        initViewPager(activity, urlList, rl_market_head_lunbotu);

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
    }

    private void initUrl() {
        urlList.clear();
        urlList.add("https://img.alicdn.com/imgextra/i4/228784630/TB2e7LZeFXXXXXVXXXXXXXXXXXX-228784630.jpg");
        urlList.add("https://img.alicdn.com/imgextra/i4/327145812/TB2Kti5eFXXXXX7XpXXXXXXXXXX-327145812.jpg");
        urlList.add("https://img.alicdn.com/imgextra/i2/327145812/TB2ogG1eFXXXXaUXpXXXXXXXXXX-327145812.jpg");
        urlList.add("https://img.alicdn.com/imgextra/i1/671012022/TB2a8BOepXXXXX4XXXXXXXXXXXX-671012022.jpg");
        urlList.add("https://img.alicdn.com/imgextra/i2/671012022/TB22YtJepXXXXbDXXXXXXXXXXXX-671012022.jpg");
        urlList.add("https://img.alicdn.com/imgextra/i2/671012022/TB29QBmepXXXXcJXpXXXXXXXXXX-671012022.jpg");
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
