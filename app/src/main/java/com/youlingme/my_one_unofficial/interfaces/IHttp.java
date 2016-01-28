package com.youlingme.my_one_unofficial.interfaces;

import com.loopj.android.http.RequestParams;
import com.youlingme.my_one_unofficial.common.HttpData;
import com.youlingme.my_one_unofficial.common.HttpError;

/**
 * User: youlingme
 * Date: 2016-01-28 14:36
 * 网络
 */
public interface IHttp {

    /**
     * 网络请求方法
     * @param url 请求链接
     * @param paramas 请求参数
     * @param httpData 请求返回的实体类
     */
    void getHttpData(String url, RequestParams paramas, HttpData httpData);

    /**
     * 请求成功的回调方法
     * @param url
     * @param data
     */
    void onDataOk(String url, String data);

    /**
     * 请求失败的回调方法
     * @param url
     * @param error
     */
    void onDataError(String url, HttpError error);

}
