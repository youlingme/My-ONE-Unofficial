package com.youlingme.my_one_unofficial.abs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.youlingme.my_one_unofficial.common.HttpClient;
import com.youlingme.my_one_unofficial.common.HttpData;
import com.youlingme.my_one_unofficial.common.HttpError;
import com.youlingme.my_one_unofficial.interfaces.IHttp;
import com.youlingme.my_one_unofficial.interfaces.IInit;
import com.youlingme.my_one_unofficial.interfaces.OnNetConnChangeListener;
import com.youlingme.my_one_unofficial.utils.NetworkUtil;
import com.youlingme.my_one_unofficial.utils.TextToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;

/**
 * User: youlingme
 * Date: 2016-01-28 14:28
 * 抽象的Activity基类
 */
public abstract class AbsBaseActivity extends Activity implements IInit, IHttp, OnNetConnChangeListener{

    private InputMethodManager softManager;
    private NetworkStateChangeReceiver mNetworkStateChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() == 0) {
            return;
        }
        setContentView(getLayoutId());

        //获取软键盘管理器
        softManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //注册监听器
        mNetworkStateChangeReceiver = new NetworkStateChangeReceiver();
        IntentFilter mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkStateChangeReceiver, mIntentFilter);

        ButterKnife.bind(this);
        init();
    }

    @Override
    public void getHttpData(final String url, RequestParams paramas, final HttpData httpData) {

        HttpClient.postByForm(url, paramas, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, String responseString) {
                if (statusCode == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(responseString);

                        if (httpData == null) {
                            return;
                        }

                        String success = jsonObject.optString(httpData.result);
                        if (success.equals("SUCCESS")) {
                            String data = jsonObject.optString(httpData.data);
                            onDataOk(url, data);
                        } else {
                            HttpError error = new HttpError(statusCode, "", responseString);
                            onDataError(url, error);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, String responseString, Throwable throwable) {
                HttpError error = new HttpError(statusCode, throwable.toString(), responseString);
                onDataError(url, error);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                if (softManager != null) {
                    softManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        //必不可少， 否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getY() < right && event.getY() > top && event.getY() < bottom) {
                //点击的尸输入框区域,保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onNetworkDisconnected() {
        TextToast.shortShow("网络连接异常");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销监听器
        unregisterReceiver(mNetworkStateChangeReceiver);
    }

    /**
     * 检测网络连接状态的广播接收器
     */
    private class NetworkStateChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isAvilable = NetworkUtil.getInstance().checkNetworkAvilable();
            if (!isAvilable) {
                onNetworkDisconnected();
            }
        }
    }

}
