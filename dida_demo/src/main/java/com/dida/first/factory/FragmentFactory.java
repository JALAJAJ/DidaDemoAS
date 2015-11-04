package com.dida.first.factory;

import android.support.v4.app.FragmentManager;

import com.dida.first.R;
import com.dida.first.fragment.Index_Market_Fragment;
import com.dida.first.fragment.Index_Mine_Fragment;
import com.dida.first.fragment.PingGouFragment_bak;
import com.dida.first.fragment.Index_Show_Fragment;

/**
 * @author KingJA
 * @data 2015-6-19 下午12:45:42
 * @use
 * 
 */

public class FragmentFactory {
	public static PingGouFragment_bak yaoyueFragment;
	public static Index_Market_Fragment xianggouFragment;
	public static Index_Show_Fragment Index_Show_Fragment;
	public static Index_Mine_Fragment indexMineFragment;
	private static FragmentManager supportFragmentManager = ActivityFactory.mainActivity.getSupportFragmentManager();

	public static<T> void showFragment(T fragment){
	if (fragment instanceof PingGouFragment_bak) {
		if (yaoyueFragment==null) {
			yaoyueFragment=(PingGouFragment_bak) new PingGouFragment_bak();
		}
		supportFragmentManager.beginTransaction()
		.replace(R.id.fl_main_content, (PingGouFragment_bak) yaoyueFragment, "SHARE")
		.commit();
		
	}else if(fragment instanceof Index_Market_Fragment){
		if (xianggouFragment==null) {
			xianggouFragment=(Index_Market_Fragment) new Index_Market_Fragment();
		}
		supportFragmentManager.beginTransaction()
		.replace(R.id.fl_main_content, xianggouFragment, "WANT")
		.commit();
		
	}else if(fragment instanceof Index_Show_Fragment){
		if (Index_Show_Fragment ==null) {
			Index_Show_Fragment =(Index_Show_Fragment) new Index_Show_Fragment();
		}
		supportFragmentManager.beginTransaction()
		.replace(R.id.fl_main_content, (Index_Show_Fragment) Index_Show_Fragment, "INTEREST")
		.commit();
	}else {
		if (indexMineFragment ==null) {
			indexMineFragment =(Index_Mine_Fragment) new Index_Mine_Fragment();
		}
		supportFragmentManager.beginTransaction()
		.replace(R.id.fl_main_content, (Index_Mine_Fragment) indexMineFragment, "SHOW")
		.commit();
	}
	}
}
