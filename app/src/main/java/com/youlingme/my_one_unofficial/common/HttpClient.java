package com.youlingme.my_one_unofficial.common;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * User: youlingme
 * Date: 2016-01-28 16:49
 * FIXME
 */
public class HttpClient {
    private static Context context;
    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    public static void init(Context ctx) {
        context = ctx;
        //最大重连2次
        asyncHttpClient.setMaxConnections(2);
    }

    public static void postByForm(String url, RequestParams params, TextHttpResponseHandler textHttpResponseHandler) {
        asyncHttpClient.post(context, url, params, textHttpResponseHandler);
    }
}
