package com.dida.first.factory;

import com.dida.first.R;
import com.dida.first.fragment.ShaidanFragment;
import com.dida.first.fragment.PingGouFragment_bak;
import com.dida.first.fragment.WodeFragment;
import com.dida.first.fragment.JiShiFragment;

import android.support.v4.app.FragmentManager;

/**
 * @author KingJA
 * @data 2015-6-19 下午12:45:42
 * @use
 * 
 */

public class FragmentFactory {
	public static PingGouFragment_bak yaoyueFragment;
	public static JiShiFragment xianggouFragment;
	public static ShaidanFragment ShaidanFragment;
	public static WodeFragment wodeFragment;
	private static FragmentManager supportFragmentManager = ActivityFactory.mainActivity.getSupportFragmentManager();

	public static<T> void showFragment(T fragment){
	if (fragment instanceof PingGouFragment_bak) {
		if (yaoyueFragment==null) {
			yaoyueFragment=(PingGouFragment_bak) new PingGouFragment_bak();
		}
		supportFragmentManager.beginTransaction()
		.replace(R.id.fl_main_content, (PingGouFragment_bak) yaoyueFragment, "SHARE")
		.commit();
		
	}else if(fragment instanceof JiShiFragment){
		if (xianggouFragment==null) {
			xianggouFragment=(JiShiFragment) new JiShiFragment();
		}
		supportFragmentManager.beginTransaction()
		.replace(R.id.fl_main_content, xianggouFragment, "WANT")
		.commit();
		
	}else if(fragment instanceof ShaidanFragment){
		if (ShaidanFragment==null) {
			ShaidanFragment=(ShaidanFragment) new ShaidanFragment();
		}
		supportFragmentManager.beginTransaction()
		.replace(R.id.fl_main_content, (ShaidanFragment) ShaidanFragment, "INTEREST")
		.commit();
	}else {
		if (wodeFragment==null) {
			wodeFragment=(WodeFragment) new WodeFragment();
		}
		supportFragmentManager.beginTransaction()
		.replace(R.id.fl_main_content, (WodeFragment) wodeFragment, "SHOW")
		.commit();
	}
	}
}
