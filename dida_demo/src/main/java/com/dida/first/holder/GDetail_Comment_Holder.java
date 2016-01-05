/**
 * 
 */
package com.dida.first.holder;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.activity.Detail_Pingou_Comment_Activity;
import com.dida.first.adapter.ImgAdapter;
import com.dida.first.adapter.PingouCommentInAdapter;
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.SizeUtil;
import com.dida.first.utils.TimeUtils;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UImageLoaderUitl;
import com.dida.first.view.AbsListView.MyGridView;
import com.dida.first.view.AbsListView.MyListView;
import com.meg7.widget.CircleImageView;

import java.util.List;

/**
 * @author		KingJA 
 * @data		2015-8-19 上午11:12:37 
 * @use			
 *
 */
public class GDetail_Comment_Holder extends BaseHolder<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity> implements View.OnClickListener{

	private Activity activity;
	private LinearLayout ll_comment_empty;
	private LinearLayout ll_pingou_comment_content;
	private TextView tv_pingou_comment_commentCount;
	private TextView tv_pingou_detail_comment_name;
	private TextView tv_pingou_detail_comment_floor;
	private TextView tv_pingou_detail_comment_date;
	private TextView tv_pingou_detail_comment_content;
	private TextView tv_pingou_comment_more;
	private CircleImageView civ_pingou_detail_comment_icon;
	private MyGridView mgv_pingou_detail_comment_imgs;
	private MyListView mlv_pingou_detail_comment_second;
	public GDetail_Comment_Holder(Activity activity){
		this.activity=activity;
	}

	@Override
	public View initView() {
		view=UIUtils.inflate(R.layout.group_detail_comment);
		ll_comment_empty = (LinearLayout) view.findViewById(R.id.ll_comment_empty);
		ll_pingou_comment_content = (LinearLayout) view.findViewById(R.id.ll_pingou_comment_content);
		tv_pingou_comment_commentCount = (TextView) view.findViewById(R.id.tv_pingou_comment_commentCount);
		tv_pingou_detail_comment_name = (TextView) view.findViewById(R.id.tv_pingou_detail_comment_name);
		tv_pingou_detail_comment_floor = (TextView) view.findViewById(R.id.tv_pingou_detail_comment_floor);
		tv_pingou_detail_comment_date = (TextView) view.findViewById(R.id.tv_pingou_detail_comment_date);
		tv_pingou_detail_comment_content = (TextView) view.findViewById(R.id.tv_pingou_detail_comment_content);
		tv_pingou_comment_more = (TextView) view.findViewById(R.id.tv_pingou_comment_more);
		civ_pingou_detail_comment_icon = (CircleImageView) view.findViewById(R.id.civ_pingou_detail_comment_icon);
		mgv_pingou_detail_comment_imgs = (MyGridView) view.findViewById(R.id.mgv_pingou_detail_comment_imgs);
		mlv_pingou_detail_comment_second = (MyListView) view.findViewById(R.id.mlv_pingou_detail_comment_second);
		tv_pingou_comment_more.setOnClickListener(this);
		return view;
	}

	@Override
	public void refreshView() {
		BeanDetailPingouUser.ResEntity.ComGroupDetailEntity data = getData();
		List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ReplysEntity> replys = data.getReplys();
		tv_pingou_comment_commentCount.setText(replys.size()==0?"暂无评论(0)":"评论("+data.getTaskcount()+")");
		ll_pingou_comment_content.setVisibility(replys.size()==0?View.GONE:View.VISIBLE);
		ll_comment_empty.setVisibility(replys.size()==0?View.VISIBLE:View.GONE);
		mlv_pingou_detail_comment_second.setVisibility(View.GONE);

		if (replys.size()>0){
			BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ReplysEntity latestComment = replys.get(0);
			tv_pingou_detail_comment_name.setText(latestComment.getUserName());
			UImageLoaderUitl.displaySmallImage(latestComment.getThumb(),civ_pingou_detail_comment_icon);
			tv_pingou_detail_comment_floor.setText(latestComment.getFloorNo()+"楼");
			tv_pingou_detail_comment_date.setText(TimeUtils.getDayTime(latestComment.getCreateTime()));
			tv_pingou_detail_comment_content.setText(latestComment.getContent());
			if (latestComment.getReplyThumbList().size()>0){
				ImgAdapter commentImgAdapter = new ImgAdapter(latestComment.getReplyThumbList(), activity);
				commentImgAdapter.setImgSize(SizeUtil.getSize(SizeUtil.ImgSize.COMMENT),SizeUtil.getSize(SizeUtil.ImgSize.COMMENT));
				mgv_pingou_detail_comment_imgs.setAdapter(commentImgAdapter);

			}
			if (latestComment.getSubReplys().size()>0){
				mlv_pingou_detail_comment_second.setVisibility(View.VISIBLE);
				List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ReplysEntity.SubReplysEntity> subReplys = latestComment.getSubReplys();
				PingouCommentInAdapter pingouCommentInAdapter = new PingouCommentInAdapter(subReplys, activity);
				mlv_pingou_detail_comment_second.setAdapter(pingouCommentInAdapter);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tv_pingou_comment_more:
				ActivityUtil.goActivity(activity, Detail_Pingou_Comment_Activity.class);
				ToastUtil.showMyToast("全部评论");
				break;
		}
	}


}
