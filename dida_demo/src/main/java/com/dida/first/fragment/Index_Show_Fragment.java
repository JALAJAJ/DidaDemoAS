package com.dida.first.fragment;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.activity.Detail_Market_Activity;
import com.dida.first.activity.Publish_Activity;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.entity.ShaiDanItemBean;
import com.dida.first.interfaces.OnCheckListener;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class Index_Show_Fragment extends BaseHeadFragment implements
        OnCheckedChangeListener, OnCheckListener<ShaiDanItemBean>, AdapterView.OnItemClickListener {
    private PullToRefreshListView prlv_show;
    private List<ShaiDanItemBean> beanList;
    private CheckBox cb_shaidan_checkall;
    private ShaidanAdapter shaidanAdapter;
    private RelativeLayout rl_shaidan_yaoyue;
    private OnCheckListener onCheckListener;
    private StringBuilder sb;

    @Override
    public View setContentView() {
        contentView = View.inflate(context, R.layout.fragment_shaidan, null);
        return contentView;
    }

    @Override
    public void initFragmentView() {
        rl_shaidan_yaoyue = (RelativeLayout) contentView.findViewById(R.id.rl_show_publish);
        prlv_show = (PullToRefreshListView) contentView.findViewById(R.id.lv_shaidan);
        cb_shaidan_checkall = (CheckBox) contentView
                .findViewById(R.id.cb_shaidan_checkall);

    }

    @Override
    public void initFragmentNet() {
        initBean();
    }

    private void initBean() {
        beanList = new ArrayList<ShaiDanItemBean>();
        for (int i = 0; i < 15; i++) {
            ShaiDanItemBean bean = new ShaiDanItemBean(false, i);
            beanList.add(bean);
        }

    }

    @Override
    public void initFragmentEvent() {
        cb_shaidan_checkall.setOnCheckedChangeListener(this);
        rl_shaidan_yaoyue.setOnClickListener(this);
        shaidanAdapter = new ShaidanAdapter(beanList);
        shaidanAdapter.setOnShowCheckListener(this);
        prlv_show.setAdapter(shaidanAdapter);
        prlv_show.setOnItemClickListener(this);

    }

    @Override
    public void initFragmentData() {
        setTitle("晒单");
    }

    /**
     * 弹出搜索页面
     */
    @Override
    public void onSearch() {

    }

    @Override
    public void onChildClick(View v) {
        if (v.getId() == R.id.rl_show_publish) {
            String checkedIds = getCheckedIds(shaidanAdapter.getList());
            if (TextUtils.isEmpty(checkedIds)) {
                ToastUtil.showMyToast("亲，请选择要发起的商品");
            } else {
                ToastUtil.showMyToast(checkedIds);
                /**
                 * 跳转到发布Activity
                 */
                ActivityUtil.goActivity(getActivity(), Publish_Activity.class);
            }

        }

    }

    private String getCheckedIds(List<ShaiDanItemBean> list) {
        sb = new StringBuilder();
        for (ShaiDanItemBean bean : list) {

            if (bean.isChecked()) {
                sb.append(bean.getProductId() + "#");
            }
        }
        return sb.toString();
    }

    /**
     * 单个选择/取消
     *
     * @param shaiDanItemBean
     * @param isChecked
     */
    @Override
    public void onCheck(ShaiDanItemBean shaiDanItemBean, boolean isChecked) {
        shaiDanItemBean.setIsChecked(isChecked);
    }


    class ShaidanAdapter extends MyBaseListViewAdapter<ShaiDanItemBean> {

        public ShaidanAdapter(List<ShaiDanItemBean> list) {
            super(list);
        }

        public void setOnShowCheckListener(OnCheckListener onCheckListener) {
            Index_Show_Fragment.this.onCheckListener = onCheckListener;
        }

        @Override
        public View getItemView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View
                        .inflate(context, R.layout.item_shaidan, null);
                viewHolder.cb_item_shaidan = (CheckBox) convertView
                        .findViewById(R.id.cb_item_shaidan);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.cb_item_shaidan.setChecked(list.get(position).isChecked());
            viewHolder.cb_item_shaidan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //屏蔽非点击设置触发，如sheChecked(boolean);
                    if (!buttonView.isPressed())
                        return;
                    onCheckListener.onCheck(list.get(position), isChecked);
                }
            });
            return convertView;
        }

        /**
         * 全选/全不选
         *
         * @param checked
         */
        public void checkAll(boolean checked) {
            for (ShaiDanItemBean shaiDanItemBean : list) {
                shaiDanItemBean.setIsChecked(checked);
            }
            this.notifyDataSetChanged();
        }

        class ViewHolder {
            CheckBox cb_item_shaidan;
            TextView tv_share_title;
            TextView tv_share_state;
            TextView tv_share_des;
            TextView tv_share_price;
            TextView tv_share_createTime;
            TextView tv_share_deadTime;
        }
    }

    /**
     * 全选/全不选
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //屏蔽非点击设置触发，如sheChecked(boolean);
        if (!buttonView.isPressed())
            return;
        switch (buttonView.getId()) {
            case R.id.cb_shaidan_checkall:
                shaidanAdapter.checkAll(isChecked);
                break;
            default:
                break;
        }
    }

    /**
     * 点击跳转到集市详情页
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /**
         * 跳转到发布Activity
         */
        ActivityUtil.goActivity(getActivity(), Detail_Market_Activity.class);
    }
}
