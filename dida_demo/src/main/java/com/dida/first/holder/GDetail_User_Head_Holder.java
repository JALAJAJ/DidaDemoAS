/**
 *
 */
package com.dida.first.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanDetailPingou;
import com.dida.first.interfaces.OnShareFavListener;
import com.dida.first.utils.TimeUtils;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UImageLoaderUitl;
import com.meg7.widget.CircleImageView;

/**
 * @author KingJA
 * @data 2015-8-17 下午4:19:59
 * @use
 */
public class GDetail_User_Head_Holder extends BaseHolder<BeanDetailPingou>  implements View.OnClickListener{

    private OnShareFavListener onShareFavListener;
    private TextView tv_pingou_user_detail_head_day;
    private TextView tv_pingou_user_detail_head_hour;
    private TextView tv_pingou_user_detail_head_minute;
    private TextView tv_pingou_user_detail_head_price;
    private TextView tv_pingou_user_detail_head_oldPrice;
    private TextView tv_pingou_user_detail_head_title;
    private TextView tv_pingou_user_detail_head_subject;
    private TextView tv_pingou_user_detail_head_left;
    private LinearLayout rl_pingou_user_detail_share;
    private CircleImageView civ_pingou_user_detail_icon;

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.pingou_detail_user_head);
        civ_pingou_user_detail_icon = (CircleImageView) view.findViewById(R.id.civ_pingou_user_detail_icon);
        tv_pingou_user_detail_head_subject = (TextView) view.findViewById(R.id.tv_pingou_user_detail_head_subject);
        tv_pingou_user_detail_head_left = (TextView) view.findViewById(R.id.tv_pingou_user_detail_head_left);
        tv_pingou_user_detail_head_day = (TextView) view.findViewById(R.id.tv_pingou_user_detail_head_day);
        tv_pingou_user_detail_head_hour = (TextView) view.findViewById(R.id.tv_pingou_user_detail_head_hour);
        tv_pingou_user_detail_head_minute = (TextView) view.findViewById(R.id.tv_pingou_user_detail_head_minute);
        tv_pingou_user_detail_head_price = (TextView) view.findViewById(R.id.tv_pingou_user_detail_head_price);
        tv_pingou_user_detail_head_oldPrice = (TextView) view.findViewById(R.id.tv_pingou_user_detail_head_oldPrice);
        tv_pingou_user_detail_head_title = (TextView) view.findViewById(R.id.tv_pingou_user_detail_head_title);
        rl_pingou_user_detail_share = (LinearLayout) view.findViewById(R.id.rl_pingou_user_detail_share);
        return view;
    }

    @Override
    public void refreshView() {
        BeanDetailPingou data = getData();
        BeanDetailPingou.ResEntity.ShareEntity share = data.getRes().getShare();
        int[] deadTime = TimeUtils.getDeadTime(share.getCustomDueDate());
        UImageLoaderUitl.displaySmallImage(share.getThumb(),civ_pingou_user_detail_icon);
        tv_pingou_user_detail_head_day.setText(deadTime[0]+"");
        tv_pingou_user_detail_head_hour.setText(deadTime[1]+"");
        tv_pingou_user_detail_head_minute.setText(deadTime[2]+"");
        tv_pingou_user_detail_head_price.setText(share.getPrice()+"");
        tv_pingou_user_detail_head_oldPrice.setText(share.getOldPrice()+"");
        tv_pingou_user_detail_head_title.setText(share.getName());
        tv_pingou_user_detail_head_subject.setText(share.getCount()+"");
        tv_pingou_user_detail_head_left.setText(share.getCount()-share.getNewCount()+"");
        rl_pingou_user_detail_share.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_pingou_user_detail_share:
                if (onShareFavListener!=null){
                    onShareFavListener.onShare();
                }
            break;
        }
    }
    public void setOnShareFavListener(OnShareFavListener onShareFavListener){
        this.onShareFavListener=onShareFavListener;

    }
}
