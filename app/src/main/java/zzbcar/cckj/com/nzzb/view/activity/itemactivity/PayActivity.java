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

        final String orderInfo = "app_id=2017061307484545";   // 订单信息
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
