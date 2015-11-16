/**
 * 
 */
package com.dida.first.holder;


import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-8-21 下午1:08:22 
 * @use			关注列表
 *
 */
public class AttentionHolder extends BaseHolder {

	private CheckBox cb_attention;

	@Override
	public View initView() {
		View inflate = UIUtils.inflate(R.layout.item_attention);
		cb_attention = (CheckBox) inflate.findViewById(R.id.cb_attention);
		cb_attention.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!buttonView.isPressed())
					return;
				if (isChecked) {
					//TODO
					ToastUtil.showMyToast("已关注");
				}else {
					ToastUtil.showMyToast("取消关注");
				}
			}
		});
		return inflate;
	}

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub

	}

}
