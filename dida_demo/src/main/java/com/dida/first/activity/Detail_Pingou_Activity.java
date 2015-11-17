/**
 *
 */
package com.dida.first.activity;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.LoadPage;
import com.dida.first.R;
import com.dida.first.bean.CommentBean;
import com.dida.first.bean.CommentBean.ItemComment;
import com.dida.first.bean.YaoYueBean.Res;
import com.dida.first.holder.GDetail_Comment_Holder;
import com.dida.first.holder.GDetail_Des_Holder;
import com.dida.first.holder.GDetail_Item_Holder;
import com.dida.first.holder.GDetail_Title_Holder;
import com.dida.first.interfaces.OnShareFavListener;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingJA
 * @data 2015-8-17 下午1:15:10
 * @use
 */
public class Detail_Pingou_Activity extends BaseNomalActivity implements OnShareFavListener {
    private LoadPage loadPage;
    private List<Res> list = new ArrayList<Res>();
    private List<CommentBean> commentList = new ArrayList<CommentBean>();
    private FrameLayout fl_group_detail_title;
    private FrameLayout fl_group_detail_item;
    private FrameLayout fl_group_detail_des;
    private FrameLayout fl_group_detail_comment;
    private GDetail_Comment_Holder commentHolder;
    private GDetail_Des_Holder desHolder;
    private GDetail_Item_Holder itemHolder;
    private RelativeLayout rl_pingou_detail_team_more;
    private TextView tv_pingou_detail_team_count;
    private RelativeLayout rl_pingou_detail_pb;
    private GDetail_Title_Holder titleHolder;
    private RelativeLayout rl_pingou_detail_join;

    private void initCommentData() {
        CommentBean commentBean1 = new CommentBean("贝多芬的假日", "贝多芬的主要作品以九部交响曲占首要地位。代表作有降E大调第3交响曲《英雄》、c小调第5交响曲《命运》、F大调第6交响曲《田园》、A大调第7交响曲、d小调第9交响曲《合唱》（《欢乐颂》主旋律）、序曲《爱格蒙特》、《莱奥诺拉》、升c小调第14钢琴奏鸣曲《月光》、F大调第5钢琴奏鸣曲《春天》、F大调第2号浪漫曲。他集古典音乐的大成，同时开辟了浪漫时期音乐的道路，对世界音乐发展有着举足轻重的作用。", "2015-08-18");
        CommentBean commentBean2 = new CommentBean("快乐的阿斗", "买买买，哼，等我爸回来", "2015-08-18");
        commentBean1.itemComments = new ArrayList<ItemComment>();
        commentBean1.itemComments.add(commentBean1.new ItemComment("达芬奇", "贝多芬", "我觉得你还是跟我学画画吧,但是首先你得有草纸吧！", "2015-08-19"));
        commentBean1.itemComments.add(commentBean1.new ItemComment("贝多芬", "达芬奇", "你回头买台钢琴先！", "2015-08-19"));
        commentBean1.itemComments.add(commentBean1.new ItemComment("毕加索", "达芬奇", "老大，美剧开始了..", "2015-08-19"));
        commentList.add(commentBean1);
        commentList.add(commentBean2);

    }

    @Override
    protected void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.rl_pingou_detail_team_more:
                ActivityUtil.goActivity(Detail_Pingou_Activity.this, AttentionActivity.class);
                break;
            case R.id.rl_pingou_detail_join:
                onJoin();
                break;
        }
    }

    /**
     * 加入拼购
     */
    private void onJoin() {
        ToastUtil.showMyToast("加入拼购");
    }

    /**
     * 加入订单
     */
    private void addOrder() {
        ToastUtil.showMyToast("成功加入订单！");
    }

    @Override
    protected View setView() {
        View view = UIUtils.inflate(R.layout.activity_group_detail);
        /**
         * 标题块
         */
        fl_group_detail_title = (FrameLayout) view.findViewById(R.id.fl_group_detail_title);
        titleHolder = new GDetail_Title_Holder();
        fl_group_detail_title.addView(titleHolder.getRootView());
        /**·
         * 商品列表块
         */
        fl_group_detail_item = (FrameLayout) view.findViewById(R.id.fl_group_detail_item);
        itemHolder = new GDetail_Item_Holder(this);
        fl_group_detail_item.addView(itemHolder.getRootView());
        /**
         * 团长块
         */
        fl_group_detail_des = (FrameLayout) view.findViewById(R.id.fl_group_detail_des);
        desHolder = new GDetail_Des_Holder(this);
        fl_group_detail_des.addView(desHolder.getRootView());

        /**
         * 团员块
         */
        rl_pingou_detail_team_more = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_team_more);
        tv_pingou_detail_team_count = (TextView) view.findViewById(R.id.tv_pingou_detail_team_count);
        /**
         * 评论块
         */
        fl_group_detail_comment = (FrameLayout) view.findViewById(R.id.fl_group_detail_comment);
        commentHolder = new GDetail_Comment_Holder();
        fl_group_detail_comment.addView(commentHolder.getRootView());
        return view;
    }

    @Override
    protected void initView() {

        rl_pingou_detail_pb = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_pb);
        rl_pingou_detail_join = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_join);

    }

    @Override
    protected void initEvent() {
        rl_pingou_detail_team_more.setOnClickListener(this);
        rl_pingou_detail_join.setOnClickListener(this);
        titleHolder.setOnShareFavListener(this);
    }

    @Override
    protected void initNet() {
        initCommentData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rl_pingou_detail_pb.setVisibility(View.GONE);
            }
        }, 2000);
    }

    @Override
    protected void initData() {
        titleHolder.setData(list);
        itemHolder.setData(list);
        desHolder.setData(list);
        commentHolder.setList(commentList);
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
}
