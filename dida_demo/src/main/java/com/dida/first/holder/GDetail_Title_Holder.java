/**
 *
 */
package com.dida.first.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dida.first.R;
import com.dida.first.interfaces.OnShareFavListener;
import com.dida.first.utils.UIUtils;

/**
 * @author KingJA
 * @data 2015-8-17 下午4:19:59
 * @use
 */
public class GDetail_Title_Holder extends BaseHolder implements View.OnClickListener{

    private ImageView iv_pingou_detail_fav;
    private OnShareFavListener onShareFavListener;
    private boolean mFaved;

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.group_detail_title);
        return view;
    }

    @Override
    public void refreshView() {
        RelativeLayout rl_pingou_detail_share = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_share);
        RelativeLayout rl_pingou_detail_fav = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_fav);
        iv_pingou_detail_fav = (ImageView) view.findViewById(R.id.iv_pingou_detail_fav);
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
