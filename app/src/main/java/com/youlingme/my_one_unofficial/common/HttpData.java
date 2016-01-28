package com.youlingme.my_one_unofficial.common;

/**
 * User: youlingme
 * Date: 2016-01-28 14:40
 * 网络请求返回的实体类
 */
public class HttpData {

    public String result;

    public String data;

    public HttpData(String result, String data) {
        this.result = result == null ? "" : result;
        this.data = data == null ? "" : data;
    }
}
