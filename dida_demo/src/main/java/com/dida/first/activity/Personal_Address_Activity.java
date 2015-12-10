package com.dida.first.activity;

import java.util.ArrayList;
import java.util.List;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.entity.BeanAddress;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.HolderAddress;
import com.dida.first.utils.ToastUtil;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author		KingJA 
 * @data		2015-9-21 下午2:30:35 
 * @use			
 *
 */
public class Personal_Address_Activity extends BackTitleActivity implements OnItemClickListener {

	private ListView lv_mine_personal_address;
	private List<BeanAddress> addressesList=new ArrayList<BeanAddress>();

	@Override
	public View setView() {
		view=View.inflate(this, R.layout.activity_mine_personal_address, null);
		return view;
	}

	@Override
	public void initView() {
		lv_mine_personal_address = (ListView) view.findViewById(R.id.lv_mine_personal_address);
	
	}

	@Override
	public void initDoNet() {
		for (int i = 0; i < 10; i++) {
			if (i==1) {
				addressesList.add(new BeanAddress(true,"马云"+i,"18888886688","浙江省","温州市","龙湾区","农业开发区文昌路209号红连文创园811"));	
			}else {
				addressesList.add(new BeanAddress(false,"马云"+i,"18888886688","浙江省","温州市","龙湾","农业开发区文昌路209号红连文创园811"));	
			}
		}

	}

	@Override
	public void initEvent() {
		lv_mine_personal_address.setAdapter(new AddressAdapter(addressesList));
		lv_mine_personal_address.setOnItemClickListener(this);

	}

	@Override
	public void initData() {
		setBackTitle("收货地址管理");
		setOnTextClickListener("添加", new OnTextClickListener() {

			@Override
			public void onTextClick() {
				ToastUtil.showMyToast("添加");
				Intent intent=new Intent(Personal_Address_Activity.this,Personal_AddAddress_Activity.class);
				startActivity(intent);

			}
		});
	}

	@Override
	public void onChildClick(View v) {
		
	}

	@Override
	public void setBackClick() {
		finish();

	}

	class AddressAdapter extends MyBaseAdapter<BeanAddress>{

		/**
		 * @param list
		 */
		public AddressAdapter(List<BeanAddress> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<BeanAddress> getHolder() {
			return new HolderAddress();
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent=new Intent(Personal_Address_Activity.this,Detail_Address_Activity.class);
		startActivity(intent);
		
	}
}
