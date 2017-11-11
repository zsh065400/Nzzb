package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.Map;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/6.
 */

public class PayActivity extends BaseActivity {
    private int minute = 29;
    private int second = 59;
    private static final int SENDING = -1;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SENDING:
                    if (second >= 10) {
                        tv_time_remain.setText(minute + ":" + second);
                    } else {
                        tv_time_remain.setText(minute + ":0" + second);
                    }
                    break;
                case 1:
                    System.out.println(msg.obj);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TextView tv_time_remain;
    /*支付*/
    private View mAlipay;
    private View mWxPay;

    private final int ALI_PAY = 1;

    private void doWxPap() {
        PayReq request = new PayReq();
        request.appId = Constant.WEIXIN_APP_ID;
        request.partnerId = "1483423462";
        request.prepayId = "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay";
        request.nonceStr = "1101000000140429eb40476f8896f4c9";
        request.timeStamp = "1398746574";
        request.sign = "6F506B7FA8F71F36291EA6496E325736";
        MyApplication.getWxApi().sendReq(request);
    }

    private void doAlipay() {
        /*测试订单*/
//        final String orderInfo = "app_id=2017061307484545&biz_content={\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.0175\",\"subject\":\"1\",\"body\"" +
//                ":\"我是测试数据\",\"out_trade_no\":\"IQJZSRC1YMQB5HU\"}&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http://192.168.1.253:8080/zzb/app/pay/request&sign_type=RSA2&timestamp=2016-08-25 20:26:31&version=1.0";
           final  String orderInfo="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017061307484545&biz_content=%7B%22body%22%3A%22%E8%87%AA%E5%B0%8A%E5%AE%9D%E8%87%AA%E9%A9%BE+%E5%85%B0%E5%8D%9A%E5%9F%BA%E5%B0%BCAventadorLP-700-4+%282017-11-10+09%3A12%3A00%7E2017-11-11+09%3A12%3A00%29%E9%80%81%E8%BD%A6%E4%B8%8A%E9%97%A8%22%2C%22out_trade_no%22%3A%22ZJ111090000030000010E17%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%87%AA%E5%B0%8A%E5%AE%9D%E8%87%AA%E9%A9%BE+%E5%85%B0%E5%8D%9A%E5%9F%BA%E5%B0%BCAventadorLP-700-4%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.02%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fapp.zzbcar.com%2Fzzb%2Fapp%2Fnotify%2Falipay&sign=CPPfE%2Flm44YeFzChtVDC2VvQU03gqgqNcMNBzKq4EZ0TEwF8NztrsMEKcumWL49k3z%2BF3abunchfrhGyy%2FoYjBjSe4wac0lY0Lcu8uFHZZGAJMNZYFiEzBD853SL4MOzIC5mm1W9Z5yvSAEVlDOCcBXlu9ZWY6ixgEIS9QE0OnfGjQabTTfz4svQPeaXWJIXOS3g%2BSyrmpwRsQyXNphagBwy5lpfl9YByp7jquUm8I3%2FkxzgbiuEzx6Ub4kVIs3RaOyF4lsi3FjlrMTAzXsS1C7qYEBxqWy4a68v0Xh8d%2B3QnkquGrEGEb6VVd8R9UEWPxpBle%2FdgSu0QZQpY4If5g%3D%3D&sign_type=RSA2&timestamp=2017-11-10+19%3A30%3A18&version=1.0";
        // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = ALI_PAY;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
        System.out.println("点击支付");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViews() {
        tv_time_remain = (TextView) findViewById(R.id.tv_time_remain);
        mAlipay = findViewById(R.id.rl_alipay);
        mWxPay = findViewById(R.id.rl_wxpay);
        mAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAlipay();
            }
        });
        mWxPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doWxPap();
            }
        });
        initTime();
    }

    private void initTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; minute >= 0; minute--) {
                    for (; second >= -1; second--) {
                        if (second == -1 && minute >= 0) {
                            mHandler.sendEmptyMessage(-1);
                            second = 59;
                            minute--;
                        }
                        SystemClock.sleep(1000);
                        mHandler.sendEmptyMessage(-1);
                    }
                }
            }
        }) {

        }.start();
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
