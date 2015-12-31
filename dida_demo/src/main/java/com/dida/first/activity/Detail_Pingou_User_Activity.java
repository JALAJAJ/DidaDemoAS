package com.dida.first.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.holder.GDetail_Comment_Holder;
import com.dida.first.holder.GDetail_Des_Holder;
import com.dida.first.holder.GDetail_Item_Holder;
import com.dida.first.holder.GDetail_User_Head_Holder;
import com.dida.first.interfaces.OnShareFavListener;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author KingJA
 * @data 2015-8-17 下午1:15:10
 * @use
 */
public class Detail_Pingou_User_Activity extends BaseNomalActivity implements OnShareFavListener {
    private static final int RES_OK = 1;
    private static final int RES_ERR = -1;
    private static final int RES_COLLECT_OK = 2;
    private static final String TAG = "Detail_Pingou_User_Activity";
    private static final int RES_COLLECT_ERR = -2;
    private FrameLayout fl_group_detail_head;
    private FrameLayout fl_group_detail_item;
    private FrameLayout fl_group_detail_des;
    private FrameLayout fl_group_detail_comment;
    private GDetail_Comment_Holder commentHolder;
    private GDetail_Des_Holder desHolder;
    private GDetail_Item_Holder itemHolder;
    private RelativeLayout rl_pingou_detail_team_more;
    private TextView tv_pingou_detail_team_count;
    private GDetail_User_Head_Holder titleHolder;
    private TextView tv_pingou_user_join;
    private BeanDetailPingouUser mDetailPingouUser = new BeanDetailPingouUser();
    private RelativeLayout rl_loading;
    private int mIfFav;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RES_OK:
                    mLoadingAndRetryManager.showContent();
                    setData(mDetailPingouUser);
                    break;
            }
        }
    };
    private String serviceId;
    private LinearLayout ll_pingou_user_fav;
    private ImageView iv_pingou_user_fav;
    private TextView tv_pingou_user_fav;
    private Bundle mBundle=new Bundle();


    @Override
    protected void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.rl_pingou_detail_team_more:
                List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ParticipatesEntity> participates = mDetailPingouUser.getRes().getComGroupDetail().getParticipates();
                mBundle.putSerializable("PINGOU_GROUP", (Serializable) participates);
                ActivityUtil.goActivityWithBundle(Detail_Pingou_User_Activity.this, AttentionActivity.class, mBundle);
                break;
            case R.id.tv_pingou_user_join:
                onJoin();
                break;
        }
    }

    /**
     * 加入拼购
     */
    private void onJoin() {
        ToastUtil.showMyToast("加入拼购");
        ActivityUtil.goActivity(this, Pingou_Show_Activity.class);
    }

    /**
     * 加入订单
     */
    private void addOrder() {
        ToastUtil.showMyToast("成功加入订单！");
    }

    @Override
    protected View setView() {
        View view = UIUtils.inflate(R.layout.activity_group_detail_user);

        /**
         * 标题块
         */
        fl_group_detail_head = (FrameLayout) view.findViewById(R.id.fl_group_detail_head);
        titleHolder = new GDetail_User_Head_Holder();
        fl_group_detail_head.addView(titleHolder.getRootView());
        /**·
         * 商品列表块
         */
        fl_group_detail_item = (FrameLayout) view.findViewById(R.id.fl_group_detail_item);
        itemHolder = new GDetail_Item_Holder(this);
        fl_group_detail_item.addView(itemHolder.getRootView());
        /**
         * 团员块
         */
        rl_pingou_detail_team_more = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_team_more);
        tv_pingou_detail_team_count = (TextView) view.findViewById(R.id.tv_pingou_detail_team_count);
        /**
         * 评论块
         */
        fl_group_detail_comment = (FrameLayout) view.findViewById(R.id.fl_group_detail_comment);
        commentHolder = new GDetail_Comment_Holder(this);
        fl_group_detail_comment.addView(commentHolder.getRootView());
        /**
         * 团长块
         */
        fl_group_detail_des = (FrameLayout) view.findViewById(R.id.fl_group_detail_des);
        desHolder = new GDetail_Des_Holder(this);
        fl_group_detail_des.addView(desHolder.getRootView());


        return view;
    }

    @Override
    protected void initView() {

        tv_pingou_user_join = (TextView) view.findViewById(R.id.tv_pingou_user_join);
        ll_pingou_user_fav = (LinearLayout) view.findViewById(R.id.ll_pingou_user_fav);
        iv_pingou_user_fav = (ImageView) view.findViewById(R.id.iv_pingou_user_fav);
        tv_pingou_user_fav = (TextView) view.findViewById(R.id.tv_pingou_user_fav);

    }

    @Override
    protected void initNet() {
        mLoadingAndRetryManager.showLoading();
        serviceId = getIntent().getStringExtra("serviceId");
        ToastUtil.showMyToast("serviceId=" + serviceId);

    }

    @Override
    protected void initEvent() {
        rl_pingou_detail_team_more.setOnClickListener(this);
        tv_pingou_user_join.setOnClickListener(this);
        titleHolder.setOnShareFavListener(this);
        ll_pingou_user_fav.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        doNetInit(serviceId, "fb9a38d82cd3405a9b60ec54cdb5ecdf");

    }

    /**
     * 访问网络-初始化页面
     *
     * @param serviceId
     * @param userId
     */
    private void doNetInit(final String serviceId, final String userId) {
        VolleyGsonRequest<BeanDetailPingouUser> initRequest = new VolleyGsonRequest<BeanDetailPingouUser>(UrlUtil.HOST + UrlUtil.PINGOU_DETAIL, BeanDetailPingouUser.class, new Response.Listener<BeanDetailPingouUser>() {
            @Override
            public void onResponse(BeanDetailPingouUser bean) {
                mDetailPingouUser = bean;
                mIfFav=mDetailPingouUser.getRes().getComGroupDetail().getIsCollection();
                mHandler.sendEmptyMessage(RES_OK);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mHandler.sendEmptyMessage(RES_ERR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("serviceId", serviceId);
                map.put("userId", userId);
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(initRequest);
    }

    private void setData(BeanDetailPingouUser bean) {
        titleHolder.setData(bean);
        itemHolder.setData(bean);
        desHolder.setData(bean);
        commentHolder.setData(bean);
        tv_pingou_detail_team_count.setText(bean.getRes().getComGroupDetail().getParticipates().size()+"");
        showFav(mIfFav);
    }

    /**
     * 分享
     */
    @Override
    public void onShare() {
        ToastUtil.showMyToast("分享");
    }

    /**
     * 收藏
     */
    @Override
    public void onFav(boolean faved, ImageView imageView) {
        imageView.setBackgroundResource(faved ? R.drawable.shoucang_sel : R.drawable.shoucang_nor);
        ToastUtil.showMyToast("收藏");

    }

    private void showFav(int ifFav) {
        iv_pingou_user_fav.setBackgroundResource(ifFav == 0 ? R.drawable.btn_fav_sel : R.drawable.btn_fav_nor);
        tv_pingou_user_fav.setTextColor(ifFav == 0 ? ContextCompat.getColor(this, R.color.red) : ContextCompat.getColor(this, R.color.gray_tip));
        tv_pingou_user_fav.setText(ifFav == 0 ? "收藏" : "已收藏");
    }

}
