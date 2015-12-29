package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UImageLoaderUitl;

import java.util.List;

/**
 * Created by KingJA on 2015-12-29.
 * 用户发起的拼购 图片列表
 */
public class PingouImgAdapter extends BaseLvGvAdapter<String> {
    public PingouImgAdapter(List<String> list, Activity activity) {
        super(list, activity);
    }

    @Override
    protected int setImgWidth() {
        return UIUtils.getScreenWidth() - UIUtils.dip2px(12) * 2;
    }

    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.single_imageview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivsingle.setLayoutParams(layoutParams);
        UImageLoaderUitl.displayGvMidImage(list.get(position), viewHolder.ivsingle);
        return convertView;
    }

    public class ViewHolder {
        public final ImageView ivsingle;
        public final View root;

        public ViewHolder(View root) {
            ivsingle = (ImageView) root.findViewById(R.id.iv_single);
            this.root = root;
        }
    }
}
