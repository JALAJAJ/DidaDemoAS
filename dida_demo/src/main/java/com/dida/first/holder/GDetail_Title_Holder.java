/**
 *
 */
package com.dida.first.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.bean.BeanDetailPingou;
import com.dida.first.interfaces.OnShareFavListener;
import com.dida.first.utils.TimeUtils;
import com.dida.first.utils.UIUtils;

/**
 * @author KingJA
 * @data 2015-8-17 下午4:19:59
 * @use
 */
public class GDetail_Title_Holder extends BaseHolder<BeanDetailPingou>  implements View.OnClickListener{

    private ImageView iv_pingou_detail_fav;
    private OnShareFavListener onShareFavListener;
    private boolean mFaved;
    private TextView tv_pingou_detail_head_day;
    private TextView tv_pingou_detail_head_hour;
    private TextView tv_pingou_detail_head_minute;
    private TextView tv_pingou_detail_head_price;
    private TextView tv_pingou_detail_head_oldPrice;
    private TextView tv_pingou_detail_head_title;
    private TextView tv_pingou_detail_head_subject;
    private TextView tv_pingou_detail_head_left;
    private RelativeLayout rl_pingou_detail_share;
    private RelativeLayout rl_pingou_detail_fav;

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.group_detail_title);
        tv_pingou_detail_head_subject = (TextView) view.findViewById(R.id.tv_pingou_detail_head_subject);
        tv_pingou_detail_head_left = (TextView) view.findViewById(R.id.tv_pingou_detail_head_left);
        tv_pingou_detail_head_day = (TextView) view.findViewById(R.id.tv_pingou_detail_head_day);
        tv_pingou_detail_head_hour = (TextView) view.findViewById(R.id.tv_pingou_detail_head_hour);
        tv_pingou_detail_head_minute = (TextView) view.findViewById(R.id.tv_pingou_detail_head_minute);
        tv_pingou_detail_head_price = (TextView) view.findViewById(R.id.tv_pingou_detail_head_price);
        tv_pingou_detail_head_oldPrice = (TextView) view.findViewById(R.id.tv_pingou_detail_head_oldPrice);
        tv_pingou_detail_head_title = (TextView) view.findViewById(R.id.tv_pingou_detail_head_title);
        rl_pingou_detail_share = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_share);
        rl_pingou_detail_fav = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_fav);
        iv_pingou_detail_fav = (ImageView) view.findViewById(R.id.iv_pingou_detail_fav);
        return view;
    }

    @Override
    public void refreshView() {
        BeanDetailPingou data = getData();
        BeanDetailPingou.ResEntity.ShareEntity share = data.getRes().getShare();
        int[] deadTime = TimeUtils.getDeadTime(share.getCustomDueDate());
        tv_pingou_detail_head_day.setText(deadTime[0]+"");
        tv_pingou_detail_head_hour.setText(deadTime[1]+"");
        tv_pingou_detail_head_minute.setText(deadTime[2]+"");
        tv_pingou_detail_head_price.setText(share.getPrice()+"");
        tv_pingou_detail_head_oldPrice.setText(share.getOldPrice()+"");
        tv_pingou_detail_head_title.setText(share.getName());
        tv_pingou_detail_head_subject.setText(share.getCount()+"");
        tv_pingou_detail_head_left.setText(share.getCount()-share.getNewCount()+"");
        iv_pingou_detail_fav.setBackgroundResource(data.getRes().getIsCollection()==0?R.drawable.shoucang_nor:R.drawable.shoucang_sel);
        rl_pingou_detail_share.setOnClickListener(this);
        rl_pingou_detail_fav.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_pingou_detail_share:
                if (onShareFavListener!=null){
                    onShareFavListener.onShare();
                }
            break;
            case R.id.rl_pingou_detail_fav:
                if (onShareFavListener!=null){
                    mFaved=!mFaved;
                    onShareFavListener.onFav(mFaved,iv_pingou_detail_fav);
                }
                break;
        }
    }
    public void setOnShareFavListener(OnShareFavListener onShareFavListener){
        this.onShareFavListener=onShareFavListener;

    }
}
