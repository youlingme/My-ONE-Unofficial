package com.youlingme.my_one_unofficial.common;

/**
 * User: youlingme
 * Date: 2016-01-28 14:39
 * 网络请求出错的实体类
 */
public class HttpError {
    /**
     * 错误码
     */
    public int statusCode;
    /**
     * 原因
     */
    public String cause;
    /**
     * 返回结果
     */
    public String response;

    public HttpError() {
    }

    public HttpError(int statusCode, String cause, String response) {
        this.statusCode = statusCode;
        this.cause = cause;
        this.response = response;
    }
}
