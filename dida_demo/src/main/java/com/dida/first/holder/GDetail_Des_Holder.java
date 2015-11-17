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
import android.widget.GridView;

import com.dida.first.R;
import com.dida.first.activity.Personal_Detail_Activity;
import com.dida.first.activity.ZoomImageActivity;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.UIUtils;
import com.meg7.widget.CircleImageView;

/**
 * @author KingJA
 * @data 2015-8-18 下午4:52:24
 * @use
 */
public class GDetail_Des_Holder extends BaseHolder implements OnItemClickListener, View.OnClickListener {

    private GridView gv_group_detail_des;
    private CircleImageView civ_pingou_detail_leader;
    protected Activity activity;

    public GDetail_Des_Holder(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View initView() {
        View initView = UIUtils.inflate(R.layout.group_detail_des);
        gv_group_detail_des = (GridView) initView.findViewById(R.id.gv_group_detail_des);
        civ_pingou_detail_leader = (CircleImageView) initView.findViewById(R.id.civ_pingou_detail_leader);

        return initView;
    }

    @Override
    public void refreshView() {
        gv_group_detail_des.setAdapter(new DesIconAdapter());
        gv_group_detail_des.setOnItemClickListener(this);
        civ_pingou_detail_leader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civ_pingou_detail_leader:
                ActivityUtil.goActivity(activity, Personal_Detail_Activity.class);
                break;
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
