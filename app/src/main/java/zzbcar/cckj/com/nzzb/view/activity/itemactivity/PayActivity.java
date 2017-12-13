package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.bean.AliPayInfoBean;
import zzbcar.cckj.com.nzzb.bean.OrderBean;
import zzbcar.cckj.com.nzzb.bean.WxPayInfoBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.PayResult;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/6.
 */

public class PayActivity extends BaseActivity implements View.OnClickListener {
    private int minute = 29;
    private int second = 59;
    private static final int SENDING = -1;
    private boolean canPay = true;

    /*接受订单信息*/

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SENDING:
                    if (minute == 0 && second == 0) {
                        canPay = false;
                        return;
                    }
                    if (second >= 10) {
                        tv_time_remain.setText(minute + ":" + second);
                    } else {
                        tv_time_remain.setText(minute + ":0" + second);
                    }
                    break;
                case 1:
                    /*final AliPayResultBean resultBean =
                            GsonUtil.parseJsonWithGson((String) msg.obj, AliPayResultBean.class);
                    final String result = resultBean.getResult();*/
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String result = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // TODO: 2017/11/18 支付状态确认
                    if (resultStatus.equals("9000")) {
                        Toast.makeText(mContext, "订单支付成功", Toast.LENGTH_SHORT).show();
                    } else if (resultStatus.equals("4000")) {
                        Toast.makeText(mContext, "订单支付失败", Toast.LENGTH_SHORT).show();
                    } else if (resultStatus.equals("6001")) {
                        Toast.makeText(mContext, "用户取消", Toast.LENGTH_SHORT).show();
                    }
                    System.out.println(msg.obj);
                    finish();
                    MyApplication.appManager.finishActivity(CarDetailActivity.class);
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
    private OrderBean.DataBean payInfo;
    private TextView tv_rend_car_money;
    private ImageView wxpay_iv;
    private ImageView alipay_iv;
    private LinearLayout mSureCommit;

    private void doWxPay() {
        /*测试订单*/
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_ORDER_PAY,
                "payWay", "2",
                "orderId", String.valueOf(payInfo.getId()),
                "amount", String.valueOf(payInfo.getLeasePrice()));
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final String body = response.body();
                final WxPayInfoBean wxPayInfoBean = GsonUtil.parseJsonWithGson(body, WxPayInfoBean.class);
                if (wxPayInfoBean.getErrno() == 0) {
                    final WxPayInfoBean.DataBean data = wxPayInfoBean.getData();
                    System.out.println(data);
                    excuteRealWxPay(data);
                } else asyncShowToast(wxPayInfoBean.getMessage());
            }
        });
    }

    /*拉起微信支付*/
    private void excuteRealWxPay(WxPayInfoBean.DataBean data) {
        PayReq request = new PayReq();
        request.appId = data.getAppid();
        request.partnerId = data.getPartnerid();
        request.prepayId = data.getPrepayid();
        request.packageValue = data.getPackageX();
        request.nonceStr = data.getNoncestr();
        request.timeStamp = data.getTimestamp();
        request.sign = data.getSign();
        MyApplication.getWxApi().sendReq(request);
    }

    /*付款请求*/
    private void doAlipay() {
        /*测试订单*/
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_ORDER_PAY,
                "payWay", "1",
                "orderId", String.valueOf(payInfo.getId()),
                "amount", String.valueOf(payInfo.getLeasePrice()));
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final AliPayInfoBean aliPayInfoBean = GsonUtil.parseJsonWithGson(response.body(), AliPayInfoBean.class);
                if (aliPayInfoBean.getErrno() == 0) {
                    final String orderInfo = aliPayInfoBean.getData();
                    System.out.println(orderInfo);
                    excuteRealALiPay(orderInfo);
                } else asyncShowToast(aliPayInfoBean.getMessage());
            }
        });
    }

    /*实际调用*/
    private void excuteRealALiPay(final String orderInfo) {
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
        tv_rend_car_money = findViewById(R.id.tv_rend_car_money);
        mAlipay = findViewById(R.id.rl_alipay);
        mWxPay = findViewById(R.id.rl_wxpay);
        wxpay_iv = findViewById(R.id.wxpay_iv);
        alipay_iv = findViewById(R.id.alipay_iv);
        mSureCommit = findViewById(R.id.ll_sure_pay);
        /*mAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAlipay();
            }
        });
        mWxPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doWxPay();
            }
        });*/
        mAlipay.setOnClickListener(this);
        mWxPay.setOnClickListener(this);
        mSureCommit.setOnClickListener(this);
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    /**
     * 计算两个日期之间相差的小时数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差分钟
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate, int type) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = 0;
        if (type == 1) {//分钟
            between_days = (time2 - time1) / (1000 * 60);//改动此处计算相差周期
        } else if (type == 2) {//秒
            between_days = (time2 - time1) % (1000 * 60) / 1000;
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    private void initTime() {
        try {
            final Date createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(payInfo.getCreateTime());
            final Date date = new Date();
            /*计算时间*/
            final int betweenMinute = daysBetween(createTime, date, 1);//分钟
            final int betweenSecond = daysBetween(createTime, date, 2);//秒
            if (betweenMinute > 30 || betweenMinute == 30 && betweenSecond == 0) {
                minute = 0;
                second = 0;
                canPay = false;
                return;
            }
            minute = 30 - betweenMinute;
            second = betweenSecond;
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
            }).start();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initDatas() {
        payInfo = (OrderBean.DataBean) getIntent().getExtras().getSerializable("payinfo");
        double leasePrice = payInfo.getLeasePrice();
        double trafficDepositMoney = payInfo.getTrafficDepositMoney();
        double TotalMoney = leasePrice + trafficDepositMoney;
        tv_rend_car_money.setText("¥ " + TotalMoney);
        alipay_iv.setEnabled(true);
        wxpay_iv.setEnabled(false);
        setBackButon(R.id.iv_back);
        initTime();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_alipay:
                alipay_iv.setEnabled(true);
                wxpay_iv.setEnabled(false);
                break;
            case R.id.rl_wxpay:
                alipay_iv.setEnabled(false);
                wxpay_iv.setEnabled(true);
                break;
            case R.id.ll_sure_pay:
                if (canPay)
                    if (alipay_iv.isEnabled()) {
                        doAlipay();
                    } else {
                        doWxPay();
                    }
                else
                    Toast.makeText(PayActivity.this, "已超时，不可支付", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
