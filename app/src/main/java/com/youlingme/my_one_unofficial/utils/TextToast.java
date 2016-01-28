package com.youlingme.my_one_unofficial.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * User: youlingme
 * Date: 2016-01-28 16:20
 * 吐司工具类
 */
public class TextToast {

    private static Context context;

    public static void init(Context ctx) {
        context = ctx;
    }

    public static void shortShow(String content) {
        if (context == null) {
            throw new IllegalStateException("TextToast未被初始化");
        }
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void longShow(String content) {
        if (context == null) {
            throw new IllegalStateException("TextToast未被初始化");
        }
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
