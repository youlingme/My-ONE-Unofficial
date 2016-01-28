package com.youlingme.my_one_unofficial.utils;

import android.os.Environment;

import java.io.File;

/**
 * User: youlingme
 * Date: 2016-01-28 18:09
 * 图片换成路径管理类
 */
public class FileManager {

    public static final String DIR_PIC_CACHE = "img";
    private static String appDir;

    /**
     * 获得图片缓存路径
     * @return
     */
    public static String getAppDir(){
        if (appDir == null) {
            if (SysUtil.isSdExist()) {
                appDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/One";
            } else {
                appDir = Environment.getRootDirectory().getAbsolutePath() + "/One";
            }
        } else {
            File file = new File(appDir);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return appDir;
    }

}
