package com.youlingme.my_one_unofficial.utils;

import android.app.Activity;
import android.os.Environment;
import android.util.DisplayMetrics;

/**
 * User: youlingme
 * Date: 2016-01-28 18:14
 * 有关设备信息的管理类
 */
public class SysUtil {

    /**
     * H获取设备屏幕的相关信息
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * SD卡是否存在
     * @return
     */
    public static boolean isSdExist(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}
