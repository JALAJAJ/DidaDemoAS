package com.dida.first.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dida.first.R;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.CustomConstants;
import com.dida.first.utils.StringUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author KingJA
 * @data 2015-6-25 下午1:16:41
 * @use
 * 
 */
public class NameActivity extends BackTitleActivity {

	private ImageView iv_suiji;
	private EditText et_name_name;
	private Button btn_name;
	private List<String> list;
	private String name;
	private List<String> minganList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 子线程读取随机名字和敏感词汇存入内存(List)
		 */
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ReadAssert(CustomConstants.RANDOMNAMES_FILE);
				ReadMinGan(CustomConstants.MINGANWORDS_FILE);
			}
		}){}.start();
		
	}
	/**
	 * 返回按钮等同于X按钮
	 */
	@Override
	public void onBackPressed() {
		if (checkName()) {
			ActivityUtil.goActivityAndFinish(NameActivity.this, MainActivity.class);
			finish();
		}
	}

	/**
	 * 检查是否可以注册
	 */
	private boolean checkName() {
		name = et_name_name.getText().toString().trim();
		/**
		 * 非空判断
		 */
		if (TextUtils.isEmpty(name)) {
			ToastUtil.showMyToast("请输入大名");
			return false;
		}
		/**
		 * 超长判断(<=15个字符)
		 */
		if (name.length() > 15) {
			ToastUtil.showMyToast( "亲，整个短点名字成不");
			return false;
		}
		/**
		 * 检查名字是否包含敏感字符
		 */
		for (String s : minganList) {
			if (s.contains(name)) {
				ToastUtil.showMyToast("包含敏感词:"+name);
				return false;
			}
		}
		
		/**
		 * TODO 检查名字是否已经注册过
		 */

		ToastUtil.showMyToast("你的名字:" + name);
		return true;

	}

	/**
	 * 设定随机名字
	 */
	private void setRandomName() {
		et_name_name.setText(list.get(new Random().nextInt(list.size())));
	}

	@Override
	public void initDoNet() {

	}

	@Override
	public View setView() {
		view = View.inflate(NameActivity.this, R.layout.activity_name, null);
		return view;
	}

	@Override
	public void initView() {
		btn_name = (Button) view.findViewById(R.id.btn_name);
		et_name_name = (EditText) view.findViewById(R.id.et_name_name);
		iv_suiji = (ImageView) view.findViewById(R.id.iv_suiji);

	}

	@Override
	public void initEvent() {
		iv_suiji.setOnClickListener(this);
		btn_name.setOnClickListener(this);

	}

	/**
	 * 初始名字为UUID去"-"后前10位字符
	 */
	@Override
	public void initData() {
		setBackTitle("2/2  完善用户名");
		et_name_name.setText(StringUtil.getUUIDString());

	}



	

	/**
	 * 读取assets下的随机名字文件
	 * 
	 * @param fileName
	 */
	private void ReadAssert(String fileName) {
		AssetManager am = this.getResources().getAssets();
		InputStream is = null;

		try {
			is = am.open(fileName);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"));
			String lineString = "";
			list=new ArrayList<String>();
			while ((lineString = reader.readLine()) != null) {
				list.add(lineString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取assets下的敏感词文件
	 * 
	 * @param fileName
	 */
	private void ReadMinGan(String fileName) {
		AssetManager am = this.getAssets();
		InputStream is = null;
		
		try {
			is = am.open(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"));
			String lineString = "";
			minganList=new ArrayList<String>();
			while ((lineString = reader.readLine()) != null) {
				String s[] = lineString.split("\\|");
				if(s.length!=2){
					continue;
				}
				minganList.add(s[0].trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void setBackClick() {
		if (checkName()) {
			ActivityUtil.goActivityAndFinish(NameActivity.this, MainActivity.class);
			finish();
		}
		
	}
	@Override
	public void onChildClick(View v) {

		switch (v.getId()) {
		case R.id.iv_suiji:
			/**
			 * 点击更换随机名字
			 */
			setRandomName();
			break;

		case R.id.btn_name:
			/**
			 * 检查是否可以注册
			 */
			Bundle bundle = getIntent().getExtras();
			String phone = bundle.getString("phone");
			String password = bundle.getString("password");
			if (checkName()) {
				ActivityUtil.goActivityAndFinish(NameActivity.this, MainActivity.class);
				ToastUtil.showMyToast(phone+"\n"+password+"\n"+name);
			}
			break;
		}

	
	}
}
