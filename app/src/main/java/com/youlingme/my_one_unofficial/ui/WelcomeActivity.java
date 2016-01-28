package com.youlingme.my_one_unofficial.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youlingme.my_one_unofficial.R;
import com.youlingme.my_one_unofficial.abs.AbsBaseActivity;
import com.youlingme.my_one_unofficial.common.HttpError;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: youlingme
 * Date: 2016-01-28 14:21
 * 欢迎页面
 */
public class WelcomeActivity extends AbsBaseActivity {

    @Bind(R.id.dv_welcome)
    SimpleDraweeView dvWelcome;
    @Bind(R.id.text_countdown)
    TextView textCountdown;

    private int count = 7;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    public void init() {
//        Uri uri = Uri.parse("http://img.huofar.com/data/jiankangrenwu/shizi.gif");
        Uri uri = Uri.parse("asset://com.youlingme.my_one_unofficial/welcome.gif");
        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true).setUri(uri).build();
        dvWelcome.setController(draweeController);

        textCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //必须移除Message，否则Handler会继续执行，导致空指针异常
                handler.removeMessages(0);
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });
        handler.sendEmptyMessageDelayed(0, 1000);

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (count > 0) {
                    textCountdown.setText(count + "");
                    count--;
                    sendEmptyMessageDelayed(0, 1000);
                } else {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }
            }
        }
    };

    @Override
    public void onDataOk(String url, String data) {

    }

    @Override
    public void onDataError(String url, HttpError error) {

    }
}
