/**
 * 
 */
package com.dida.first.holder;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.activity.Detail_Pingou_Comment_Activity;
import com.dida.first.adapter.ItemCommentAdapter;
import com.dida.first.bean.CommentBean;
import com.dida.first.bean.CommentBean.ItemComment;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import java.util.List;

/**
 * @author		KingJA 
 * @data		2015-8-19 上午11:12:37 
 * @use			
 *
 */
public class GDetail_Comment_Holder extends BaseHolder<CommentBean> implements View.OnClickListener{

	private ListView lv_group_detail_comment;
	private Activity activity;
	private LinearLayout ll_pingou_comment_more;
	public GDetail_Comment_Holder(Activity activity){
		this.activity=activity;
	}

	@Override
	public View initView() {
		view=UIUtils.inflate(R.layout.group_detail_comment);
		lv_group_detail_comment = (ListView) view.findViewById(R.id.lv_group_detail_comment);
		ll_pingou_comment_more = (LinearLayout) view.findViewById(R.id.ll_pingou_comment_more);
		ll_pingou_comment_more.setOnClickListener(this);
		return view;
	}

	@Override
	public void refreshView() {
		List<CommentBean> list = getList();
		lv_group_detail_comment.setAdapter(new GroupCommentAdapter(list));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.ll_pingou_comment_more:
				ActivityUtil.goActivity(activity, Detail_Pingou_Comment_Activity.class);
				ToastUtil.showMyToast("全部评论");
				break;
		}
	}

	class GroupCommentAdapter extends BaseAdapter{
		private List<CommentBean> list;
		public GroupCommentAdapter(List<CommentBean> list) {
			this.list=list;
		}
		@Override
		public int getCount() {
			if (list==null) {
				return 0;
			}else {
				return 1;
			}
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			View itemView=UIUtils.inflate(R.layout.item_group_detail_comment);
			ListView lv_group_detail_itemcomment = (ListView) itemView.findViewById(R.id.lv_group_detail_itemcomment);
			LinearLayout ll_comment = (LinearLayout) itemView.findViewById(R.id.ll_comment);
			TextView tv_group_detail_comment_rootname = (TextView) itemView.findViewById(R.id.tv_group_detail_comment_rootname);
			TextView tv_group_detail_comment_rootcomment = (TextView) itemView.findViewById(R.id.tv_group_detail_comment_rootcomment);
			TextView tv_group_detail_comment_rootdate = (TextView) itemView.findViewById(R.id.tv_group_detail_comment_rootdate);
			tv_group_detail_comment_rootname.setText(list.get(position).rootName);
			tv_group_detail_comment_rootcomment.setText(list.get(position).rootComment);
			tv_group_detail_comment_rootdate.setText(list.get(position).rootDate);
			
			
			final List<ItemComment> itemComment=list.get(position).itemComments;
			
			if (itemComment!=null) {
				ll_comment.setVisibility(View.VISIBLE);
				lv_group_detail_itemcomment.setAdapter(new ItemCommentAdapter(itemComment));
				lv_group_detail_itemcomment.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						ToastUtil.showMyToast(itemComment.get(position).fromName);
						
					}
				});
			}
			
			return itemView;
		}
		
	}
}
