/**
 * 
 */
package com.dida.first.holder;

import android.view.View;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-8-17 下午4:19:59 
 * @use			
 *
 */
public class GDetail_Title_Holder extends BaseHolder {

	@Override
	public View initView() {
View inflate = UIUtils.inflate(R.layout.group_detail_title);
		return inflate;
	}

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub

	}

}
