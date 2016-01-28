package com.youlingme.my_one_unofficial.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * User: youlingme
 * Date: 2016-01-28 15:36
 * 网络工具类
 */
public class NetworkUtil {

    private static ConnectivityManager conManager;
    private static NetworkUtil instance;
    private static Context context;

    private NetworkUtil(Context context) {
        NetworkUtil.conManager = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
    }

    public static void init(Context ctx) {
        NetworkUtil.context = ctx;
    }

    public static NetworkUtil getInstance() {
        if (NetworkUtil.instance == null) {
            NetworkUtil.instance = new NetworkUtil(context);
        }
        return NetworkUtil.instance;
    }

    /**
     * 检测服务端是否连接正常
     * @return
     */
    public boolean checkNetworkAvilable() {
        return checkMobileActivie() || checkWifeActivie();
    }

    /**
     * 检测Wifi是否可用
     * @return
     */
    private boolean checkWifeActivie() {
        return conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    /**
     * 检测移动网络是否可用
     * @return
     */
    private boolean checkMobileActivie() {
        return conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED;
    }

    /**
     * 判断网络是否漫游
     * @param context
     * @return
     */
    public boolean isNetworkRoaming(Context context) {
        if (conManager != null){
            NetworkInfo info = conManager.getActiveNetworkInfo();
            if (info != null && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (tm != null && tm.isNetworkRoaming()) {
                    Log.d("Tag", "network is roaming");
                    return true;
                } else {
                    Log.d("Tag", "network is not roaming");
                }
            }
        }
        return false;
    }

}
