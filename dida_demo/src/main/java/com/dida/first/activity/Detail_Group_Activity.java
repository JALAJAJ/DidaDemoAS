/**
 * 
 */
package com.dida.first.activity;

import java.util.ArrayList;
import java.util.List;

import com.dida.first.LoadPage;
import com.dida.first.LoadPage.ResultState;
import com.dida.first.R;
import com.dida.first.bean.CommentBean;
import com.dida.first.bean.CommentBean.ItemComment;
import com.dida.first.bean.YaoYueBean.Res;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.holder.GDetail_Comment_Holder;
import com.dida.first.holder.GDetail_Des_Holder;
import com.dida.first.holder.GDetail_Item_Holder;
import com.dida.first.holder.GDetail_Team_Holder;
import com.dida.first.holder.GDetail_Title_Holder;
import com.dida.first.utils.UIUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author		KingJA 
 * @data		2015-8-17 下午1:15:10 
 * @use			
 *
 */
public class Detail_Group_Activity extends Activity {
	private LoadPage loadPage;
	private List<Res> list=new ArrayList<Res>();
	private List<CommentBean> commentList=new ArrayList<CommentBean>();
	private void initCommentData() {
			CommentBean commentBean1 = new CommentBean("贝多芬的假日", "贝多芬的主要作品以九部交响曲占首要地位。代表作有降E大调第3交响曲《英雄》、c小调第5交响曲《命运》、F大调第6交响曲《田园》、A大调第7交响曲、d小调第9交响曲《合唱》（《欢乐颂》主旋律）、序曲《爱格蒙特》、《莱奥诺拉》、升c小调第14钢琴奏鸣曲《月光》、F大调第5钢琴奏鸣曲《春天》、F大调第2号浪漫曲。他集古典音乐的大成，同时开辟了浪漫时期音乐的道路，对世界音乐发展有着举足轻重的作用。", "2015-08-18");
			CommentBean commentBean2 = new CommentBean("快乐的阿斗", "买买买，哼，等我爸回来", "2015-08-18");
			commentBean1.itemComments=new ArrayList<ItemComment>();
			commentBean1.itemComments.add(commentBean1.new ItemComment("达芬奇", "贝多芬", "我觉得你还是跟我学画画吧,但是首先你得有草纸吧！", "2015-08-19"));
			commentBean1.itemComments.add(commentBean1.new ItemComment("贝多芬", "达芬奇", "你回头买台钢琴先！", "2015-08-19"));
			commentBean1.itemComments.add(commentBean1.new ItemComment("毕加索", "达芬奇", "老大，美剧开始了..", "2015-08-19"));
			commentList.add(commentBean1);
			commentList.add(commentBean2);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityFactory.groupActivity=this;
		initCommentData();
		loadPage = new LoadPage(UIUtils.getContext()) {
			
			@Override
			public View onCreateSuccessedView() {
				return Detail_Group_Activity.this.onCreateSuccessedView();
			}

			@Override
			public ResultState onLoad() {
				return Detail_Group_Activity.this.onLoad();
			}
		};
		setContentView(loadPage);
		//手动促发onLoad方法的调用
				if(loadPage!=null){
					loadPage.show();
				}
	}

	

	/**
	 * @return
	 */
	protected ResultState onLoad() {
		return ResultState.STATE_SUCCESSED;
	}

	/**
	 * @return
	 */
	protected View onCreateSuccessedView() {
		View view = UIUtils.inflate(R.layout.activity_group_detail);
		/**
		 * 标题块
		 */
		FrameLayout fl_group_detail_title = (FrameLayout) view.findViewById(R.id.fl_group_detail_title);
		GDetail_Title_Holder titleHolder = new GDetail_Title_Holder();
		fl_group_detail_title.addView(titleHolder.getRootView());
		/**·
		 * 商品列表块
		 */
		FrameLayout fl_group_detail_item = (FrameLayout) view.findViewById(R.id.fl_group_detail_item);
		GDetail_Item_Holder itemHolder = new GDetail_Item_Holder();
		itemHolder.setData(list);
		fl_group_detail_item.addView(itemHolder.getRootView());
		/**
		 * 团长块
		 */		
		FrameLayout fl_group_detail_des = (FrameLayout) view.findViewById(R.id.fl_group_detail_des);
		GDetail_Des_Holder desHolder = new GDetail_Des_Holder();
		desHolder.setData(list);
		fl_group_detail_des.addView(desHolder.getRootView());
		
		/**
		 * 团员块
		 */
		FrameLayout fl_group_detail_team = (FrameLayout) view.findViewById(R.id.fl_group_detail_team);
		GDetail_Team_Holder teamHolder = new GDetail_Team_Holder();
		teamHolder.setData(list);
		fl_group_detail_team.addView(teamHolder.getRootView());
		/**
		 * 评论块
		 */
		FrameLayout fl_group_detail_comment = (FrameLayout) view.findViewById(R.id.fl_group_detail_comment);
		GDetail_Comment_Holder commentHolder = new GDetail_Comment_Holder();
		commentHolder.setList(commentList);
		fl_group_detail_comment.addView(commentHolder.getRootView());
		
		
		
		return view;
	}

}
