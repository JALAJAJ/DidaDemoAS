/**
 * 
 */
package com.dida.first.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * @author		KingJA 
 * @data		2015-8-10 上午11:18:28 
 * @use			自定义	
 *
 */
public class MyViewPager extends ViewPager  {
	private List<String> urlList;
	private List<View> dotList;
	private Context context;
	private RelativeLayout relativeLayout;
	private BitmapUtils bitmapUtils;
	private RunnableTask r;

	/**
	 * @param context
	 */
	public MyViewPager(Context context,final List<String>urlList,RelativeLayout relativeLayout) {
		super(context);
		bitmapUtils=new BitmapUtils(context);
		this.urlList=urlList;
		this.context=context;
		this.relativeLayout=relativeLayout;
		this.setAdapter(new MyPagerAdapter());
		this.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				int index=position%dotList.size();
				for (int i = 0; i < dotList.size(); i++) {
					if (i==index) {
						dotList.get(index).setBackgroundResource(R.drawable.icon_sel_1);
					}else {
						dotList.get(i).setBackgroundResource(R.drawable.icon_nor_1);
					}
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
	}
public void startRoll(){
	r=new RunnableTask();
	r.start();
}
	/**
	 * 初始化点
	 */
	public void initDot() {
		dotList=new ArrayList<View>();
		LinearLayout llLayout=new LinearLayout(context);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(16, 16);
		layoutParams.setMargins(0, 0, 16, 16);
		for (int i = 0; i < urlList.size(); i++) {
			View dotView=new View(context);
			if (i==0) {
				dotView.setBackgroundResource(R.drawable.icon_sel_1);
				
			}else {
				dotView.setBackgroundResource(R.drawable.icon_nor_1);
			}
			dotList.add(dotView);
			llLayout.addView(dotView, layoutParams);
		}
		RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		rlParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		rlParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		relativeLayout.addView(llLayout, rlParams);
	
	}
	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View imageView = UIUtils.inflate(R.layout.viewpager_item);
			ImageView iv_viewpager_item = (ImageView) imageView.findViewById(R.id.iv_viewpager_item);
			bitmapUtils.display(iv_viewpager_item, urlList.get(position%urlList.size()));
			container.addView(imageView);
			return imageView;
		}
	}
	class RunnableTask implements Runnable{
		public void start() {
			UIUtils.removeCallback(r);
			UIUtils.postDelayed(r, 2000);
			
			
		}
		@Override
		public void run() {
			UIUtils.removeCallback(r);
			int currentItem = MyViewPager.this.getCurrentItem();
			MyViewPager.this.setCurrentItem(currentItem+1);
			UIUtils.postDelayed(r, 2000);
		}
		
	}
}
