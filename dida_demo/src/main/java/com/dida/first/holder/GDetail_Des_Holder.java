/**
 *
 */
package com.dida.first.holder;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.activity.ZoomImageActivity;
import com.dida.first.adapter.PingouImgAdapter;
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyListView;

/**
 * @author KingJA
 * @data 2015-8-18 下午4:52:24
 * @use
 */
public class GDetail_Des_Holder extends BaseHolder<BeanDetailPingouUser> implements OnItemClickListener, View.OnClickListener {

    private MyListView mlv_group_detail_des;
    private TextView tv_pingou_detail_des_content;
    protected Activity activity;

    public GDetail_Des_Holder(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.group_detail_des);
        mlv_group_detail_des = (MyListView) view.findViewById(R.id.mlv_group_detail_des);
        tv_pingou_detail_des_content = (TextView) view.findViewById(R.id.tv_pingou_detail_des_content);

        return view;
    }

    @Override
    public void refreshView() {
        BeanDetailPingouUser data = getData();
        tv_pingou_detail_des_content.setText(data.getRes().getPureDes());
        mlv_group_detail_des.setAdapter(new PingouImgAdapter(data.getRes().getImageJson(),activity));
        mlv_group_detail_des.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.civ_pingou_detail_des_icon:
//                ActivityUtil.goActivity(activity, Personal_Detail_Activity.class);
//                break;
        }

    }

    class DesIconAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = UIUtils.inflate(R.layout.item_group_detail_des);
            return itemView;
        }

    }

    /**
     * 打开图片浏览ZoomImageActivity
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent intent = new Intent(ActivityFactory.mainActivity, ZoomImageActivity.class);
        ActivityFactory.mainActivity.startActivity(intent);

    }

}
