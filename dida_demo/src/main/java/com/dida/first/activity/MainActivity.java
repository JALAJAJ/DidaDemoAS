package com.dida.first.activity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dida.first.R;
import com.dida.first.dialog.DialogDouble;
import com.dida.first.dialog.DialogDouble.OnDoubleClickListener;
import com.dida.first.fragment.Index_Market_Fragment;
import com.dida.first.fragment.Index_Mine_Fragment;
import com.dida.first.fragment.Index_Pingou_Fragment;
import com.dida.first.fragment.Index_Show_Fragment;
import com.dida.first.utils.SharedPreferencesUtils;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * MainActivity作用 1.完成左侧拉,右侧拉,主界面的位置布局,网络监听
 *
 * @author KingJA
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    public FragmentManager mFragmentManager;
    private LinearLayout ll_tab_yaoyue;
    private LinearLayout ll_tab_xianggou;
    private LinearLayout ll_tab_saidan;
    private LinearLayout ll_tab_wode;
    private ImageView iv_tab_yaoyue;
    private ImageView iv_tab_xianggou;
    private ImageView iv_tab_saidan;
    private ImageView iv_tab_wode;
    private TextView tv_tab_yaoyue;
    private TextView tv_tab_xianggou;
    private TextView tv_tab_saidan;
    private TextView tv_tab_wode;
    private Index_Pingou_Fragment yaoyueFragment;
    private Index_Market_Fragment xianggouFragment;
    private com.dida.first.fragment.Index_Show_Fragment Index_Show_Fragment;
    private Index_Mine_Fragment indexMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        registerDateTransReceiver();
        mFragmentManager = getSupportFragmentManager();
        initView();
        initEvent();
        initData();
        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        Log.i(TAG, "heapSize: "+heapSize);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                Log.i(TAG, "onTrimMemory: ");  
                break;
        }
    }

    /**
     * 查找控件
     */
    private void initView() {
        ll_tab_yaoyue = (LinearLayout) findViewById(R.id.ll_tab_share);
        ll_tab_xianggou = (LinearLayout) findViewById(R.id.ll_tab_want);
        ll_tab_saidan = (LinearLayout) findViewById(R.id.ll_tab_interest);
        ll_tab_wode = (LinearLayout) findViewById(R.id.ll_tab_show);
        iv_tab_yaoyue = (ImageView) findViewById(R.id.iv_tab_share);
        iv_tab_xianggou = (ImageView) findViewById(R.id.iv_tab_want);
        iv_tab_saidan = (ImageView) findViewById(R.id.iv_tab_interest);
        iv_tab_wode = (ImageView) findViewById(R.id.iv_tab_show);
        tv_tab_yaoyue = (TextView) findViewById(R.id.tv_tab_share);
        tv_tab_xianggou = (TextView) findViewById(R.id.tv_tab_want);
        tv_tab_saidan = (TextView) findViewById(R.id.tv_tab_interest);
        tv_tab_wode = (TextView) findViewById(R.id.tv_tab_show);
    }

    /**
     * 添加事件
     */
    private void initEvent() {
        ll_tab_yaoyue.setOnClickListener(this);
        ll_tab_xianggou.setOnClickListener(this);
        ll_tab_saidan.setOnClickListener(this);
        ll_tab_wode.setOnClickListener(this);
    }

    /**
     * 初始化布局
     */
    public void initData() {
        setTab(0);
    }


    private void initConnection() {
        String token = SharedPreferencesUtils.getStringData("TOKEN", "");
        if (TextUtils.isEmpty(token)) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    ToastUtil.showMyToast("onSuccess==" + userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }

    }

    private long firstTime;

    @Override
    public void onBackPressed() {

        long secondTime = System.currentTimeMillis();
        if ((secondTime - firstTime) > 2000) {
            ToastUtil.singleToast(MainActivity.this, "再按一次退出程序");
            firstTime = secondTime;
        } else {
            finish();
        }
    }

    public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private NetChanged netChanged;

    private void registerDateTransReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(CONNECTIVITY_CHANGE_ACTION);
        filter.setPriority(1000);
        netChanged = new NetChanged();
        registerReceiver(netChanged, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_tab_share:
                setTab(0);
                break;
            case R.id.ll_tab_want:
                setTab(1);
                break;
            case R.id.ll_tab_interest:
                setTab(2);
                break;
            case R.id.ll_tab_show:
                setTab(3);
                break;

        }
    }

    /**
     * 选择对应的Fragment
     */
    // TODO 需要重构
    private void setTab(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        // clearPopuWindow();
        resetState();
        switch (position) {
            case 0:
                iv_tab_yaoyue.setBackgroundResource(R.drawable.yaoyue_selected);
                tv_tab_yaoyue.setTextColor(getResources().getColor(
                        R.color.red));
                if (yaoyueFragment == null) {
                    // 如果yaoyueFragment为空，则创建一个并添加到界面上
                    yaoyueFragment = new Index_Pingou_Fragment();
                    transaction.add(R.id.fl_main_content, yaoyueFragment);
                } else {
                    // 如果yaoyueFragment不为空，则直接将它显示出来
                    transaction.show(yaoyueFragment);
                }
                break;
            case 1:
                iv_tab_xianggou.setBackgroundResource(R.drawable.xianggou_sel);
                tv_tab_xianggou.setTextColor(getResources().getColor(
                        R.color.red));
                if (xianggouFragment == null) {
                    xianggouFragment = new Index_Market_Fragment();
                    transaction.add(R.id.fl_main_content, xianggouFragment);
                } else {
                    transaction.show(xianggouFragment);
                }
                break;
            case 2:
                iv_tab_saidan.setBackgroundResource(R.drawable.saidan_sel);
                tv_tab_saidan.setTextColor(getResources().getColor(
                        R.color.red));
                if (Index_Show_Fragment == null) {
                    // 如果ShaidanFragment为空，则创建一个并添加到界面上
                    Index_Show_Fragment = new Index_Show_Fragment();
                    transaction.add(R.id.fl_main_content, Index_Show_Fragment);
                } else {
                    // 如果ShaidanFragment不为空，则直接将它显示出来
                    transaction.show(Index_Show_Fragment);
                }
                break;
            case 3:
                iv_tab_wode.setBackgroundResource(R.drawable.wode_sel);
                tv_tab_wode.setTextColor(getResources().getColor(
                        R.color.red));
                if (indexMineFragment == null) {
                    indexMineFragment = new Index_Mine_Fragment();
                    transaction.add(R.id.fl_main_content, indexMineFragment);
                } else {
                    transaction.show(indexMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 复位TAB状态
     */
    private void resetState() {
        iv_tab_yaoyue.setBackgroundResource(R.drawable.yaoyue_nor);
        iv_tab_xianggou.setBackgroundResource(R.drawable.xianggou_nomal);
        iv_tab_saidan.setBackgroundResource(R.drawable.saidan_nomal);
        iv_tab_wode.setBackgroundResource(R.drawable.wode_nomal);
        tv_tab_yaoyue.setTextColor(getResources().getColor(
                R.color.tab_text_nomal));
        tv_tab_xianggou.setTextColor(getResources().getColor(
                R.color.tab_text_nomal));
        tv_tab_saidan.setTextColor(getResources().getColor(
                R.color.tab_text_nomal));
        tv_tab_wode.setTextColor(getResources()
                .getColor(R.color.tab_text_nomal));

    }

    private void hideFragments(FragmentTransaction transaction) {
        if (yaoyueFragment != null) {
            transaction.hide(yaoyueFragment);
        }
        if (xianggouFragment != null) {
            transaction.hide(xianggouFragment);
        }
        if (Index_Show_Fragment != null) {
            transaction.hide(Index_Show_Fragment);
        }
        if (indexMineFragment != null) {
            transaction.hide(indexMineFragment);
        }
    }

    /**
     * @author KingJA
     * @data 2015-6-26 下午3:07:38
     * @use 动态注册的监听网络状态变化的广播
     */
    class NetChanged extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (UIUtils.getNetworkType()) {
                case 0:
                    Toast.makeText(context, "没有网络:", Toast.LENGTH_SHORT).show();
                    /**
                     * 弹出网络设置对话框
                     */
                    setNetwork(MainActivity.this);
                    break;
                case 1:
                    Toast.makeText(context, "当前网络:WIFI", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(context, "当前网络:WAP网络", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(context, "当前网络:NET网络", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        /**
         * 获取当前网络类型
         *
         * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
         */

        private AlertDialog dialog;


        /**
         * 网络未连接时，调用设置方法
         */
        private void setNetwork(Context context) {
            final DialogDouble setNetDialog = new DialogDouble(
                    MainActivity.this, "网络不可用，请先设置网络！", "确定", "取消");
            setNetDialog.show();
            setNetDialog.setOnDoubleClickListener(new OnDoubleClickListener() {

                @Override
                public void onLeft() {

                    Intent intent = null;
                    /**
                     * 判断手机系统的版本！如果API大于10 就是3.0+
                     * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                     */
                    if (android.os.Build.VERSION.SDK_INT > 10) {
                        intent = new Intent(
                                android.provider.Settings.ACTION_WIFI_SETTINGS);
                    } else {
                        intent = new Intent();
                        ComponentName component = new ComponentName(
                                "com.android.settings",
                                "com.android.settings.WirelessSettings");
                        intent.setComponent(component);
                        intent.setAction("android.intent.action.VIEW");
                    }
                    startActivity(intent);

                }

                @Override
                public void onRight() {

                }
            });

        }
    }

    /**
     * 解绑动态注册的广播
     */
    @Override
    protected void onDestroy() {
        unregisterReceiver(netChanged);
        super.onDestroy();
    }

}
