package com.dida.first.application;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.ErrorCode;
import io.rong.imlib.model.Conversation.ConversationType;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dida.first.rongyun.RongYunEvent;
import com.dida.first.utils.CustomConstants;
import com.dida.first.utils.SharedPreferencesUtils;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class App extends Application {
	private static Context context;
	private static int mainThreadId;
	private static Thread mainThread;
	private static Handler handler;
	private static SharedPreferences sp;
	public static RequestQueue mQueue;
	

	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化融云
		initRongYun();
		// 初始化图片加载
        initUniversalImageLoader();  
		// 清理图片缓存
		removeTempFromPref();
		// Context
		context = getApplicationContext();
		// mainThreadId
		mainThreadId = android.os.Process.myTid();
		// Thread-->object
		mainThread = Thread.currentThread();
		// Handler
		handler = new Handler();
		// SharedPreferences
		sp = getSharedPreferences(CustomConstants.APPLICATION_NAME,
				MODE_PRIVATE);
	}
	public static RequestQueue getQueue(){
		if (mQueue==null) {
			mQueue = Volley.newRequestQueue(context);
		}
		return mQueue;
	}

	private void initRongYun() {
		/**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
            sp = getSharedPreferences("config", Context.MODE_PRIVATE);
    		RongYunEvent.init(this);
    		//扩展功能只显示图片，相机
    		 ExtendProvider [] ep = {new ImageInputProvider(RongContext.getInstance()),new CameraInputProvider(RongContext.getInstance())};  
    		 //我需要让他在什么会话类型中的 bar 展示    
    		 RongIM.resetInputExtensionProvider(ConversationType.PRIVATE, ep);  
    		 Log.i("initRongYun", "initRongYun");
        }
	}

	/**
	 * 初始化Android-Universal-Image-Loader
	 */
	private void initUniversalImageLoader() {
		//创建默认的ImageLoader配置参数  
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration  
                .createDefault(this);  
          
        //Initialize ImageLoader with configuration.  
        ImageLoader.getInstance().init(configuration);
	}

	private void removeTempFromPref() {
		SharedPreferences sp = getSharedPreferences(
				CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
		sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
	}

	public static SharedPreferences getSP() {
		return sp;
	}
	public static boolean getHasLogin() {
		boolean hasLogin = sp.getBoolean(CustomConstants.HASLOGIN, false);
		return hasLogin;
	}

	public static Context getContext() {
		return context;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}

	public static Thread getMainThread() {
		return mainThread;
	}

	public static Handler getHandler() {
		return handler;
	}
	 /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
