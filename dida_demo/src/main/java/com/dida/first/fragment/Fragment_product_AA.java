/**
 * 
 */
package com.dida.first.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * @author		KingJA 
 * @data		2015-10-16 下午1:23:10 
 * @use			
 *
 */
public class Fragment_product_AA extends Fragment_Base_Nomal {

	@Override
	public View setFragmentView() {
		TextView tv = new TextView(context);
		tv.setText("AA");
		return tv;
	}

	@Override
	public void initFragmentView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFragmentNet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFragmentEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildClick(View v) {

	}

}
