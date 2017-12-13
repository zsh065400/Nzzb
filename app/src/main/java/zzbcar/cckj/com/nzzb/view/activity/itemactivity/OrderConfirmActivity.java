package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.CarDetailBean;
import zzbcar.cckj.com.nzzb.bean.OrderBean;
import zzbcar.cckj.com.nzzb.bean.PriceListBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.bean.TimeBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.EncryptUtils;
import zzbcar.cckj.com.nzzb.utils.GlideApp;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class OrderConfirmActivity extends BaseActivity {

    @BindView(R.id.tv_car_to_pay)
    TextView tvToPay;
    @BindView(R.id.tv_car_name)
    TextView tvCarName;
    @BindView(R.id.tv_car_price)
    TextView tvCarPrice;
    @BindView(R.id.tv_car_zj)
    TextView tvCarZj;
    @BindView(R.id.tv_order_type)
    TextView tvOrderType;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_order_mark)
    TextView tvOrderMark;
    @BindView(R.id.tv_order_getaddrTime)
    TextView tvOrderGetaddrTime;
    @BindView(R.id.tv_order_backaddrTime)
    TextView tvOrderBackaddrTime;
    @BindView(R.id.tv_order_allTime)
    TextView tvOrderAllTime;
    @BindView(R.id.tv_order_deposit)
    TextView tvOrderDeposit;
    @BindView(R.id.tv_order_bzj)
    TextView tvOrderBzj;
    @BindView(R.id.tv_order_allMoney)
    TextView tvOrderAllMoney;
    @BindView(R.id.iv_order_connect_us)
    ImageView ivOrderConnectUs;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_confirm_quche)
    TextView tvConfirmQuche;
    @BindView(R.id.tv_confirm_huanche)
    TextView tvConfirmHuanche;
//    @BindView(R.id.tv_order_really_time)
//    TextView tvOrderReallyTime;

    /*车辆信息*/
    private CarDetailBean.DataBean cardetail;
    /*订单信息*/
    private OrderBean.DataBean orderBeanData;
    private int userId;
    private String startTime;
    private String endTime;
    private ImageView iv_order_car_pic;
    private String getAddress;
    private String sendAddress;
    private String selfGetAddress;
    private String selfRepayAddress;
    private double amount;
    private Bundle bundle;
    private SigninBean bean;
    private AlertDialog alertDialog;
    private CarDetailBean.DataBean carDetailBean;
    private int mBetween;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_confirm;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
        iv_order_car_pic = (ImageView) findViewById(R.id.iv_order_car_pic);
    }

    @Override
    protected void initListeners() {
        tvToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new Double(amount) != null) {
                    getServerTime(amount);
                } else {
                    Toast.makeText(mContext, "加载数据中，请稍后", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ivOrderConnectUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectUs();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void ConnectUs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        AlertDialog alertDialog = builder.setMessage("13295815771")
                .setTitle("要拨打电话给客服么?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callService();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }

                }).create();
        alertDialog.show();
    }

    private void callService() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13295815771"));
        startActivity(callIntent);
    }

    @Override
    protected void initDatas() {
        /*确认租车信息，同步更新界面，可加loading*/
        bundle = getIntent().getExtras();
        cardetail = (CarDetailBean.DataBean) bundle.getSerializable("cardetail");
        startTime = bundle.getString("getTime") + ":00";
        endTime = bundle.getString("backTime") + ":00";
        /*取还车*/
        getAddress = bundle.getString("getAddress");
        sendAddress = bundle.getString("sendAddress");
        /*自行取还车*/
        selfGetAddress = bundle.getString("selfGetAddress");
        selfRepayAddress = bundle.getString("selfRepayAddress");


        final String userJson = SPUtils.getString(mContext, "User", "");
        if (userJson != "") {
            bean = GsonUtil.parseJsonWithGson(userJson, SigninBean.class);
            userId = bean.getData().getMember().getId();
        } else {
            Toast.makeText(mContext, "您还未登录", Toast.LENGTH_SHORT).show();
            finish();
        }
        calcPrice();

        mBetween = SPUtils.getInt(this, "Between", 0);
    }

    /*逻辑调用位置需要优化，此处根据后台逻辑暂定*/
    /*计算租金*/
    private void calcPrice() {
        //startTime = getCurrentTime(false);
        //endTime = getCurrentTime(true);
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_QUERY_PRICE,
                "carId", String.valueOf(cardetail.getId()),
                "date1", startTime,
                "date2", endTime);
        OkGo.<String>get(url).execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                final String body = response.body();
                Log.d(TAG, "onSuccess: " + body);
                final PriceListBean priceListBean = GsonUtil.parseJsonWithGson(body, PriceListBean.class);
                if (priceListBean.getErrno() == 0) {
                    amount = priceListBean.getData().getAmount();
                    initOrderData();
                } else asyncShowToast(priceListBean.getMessage());
            }
        });
    }

    private void initOrderData() {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date end = sdf.parse(endTime);
            Date start = sdf.parse(startTime);
            long nd = 1000 * 24 * 60 * 60;
            long betweentime = end.getTime() - start.getTime();
            final long betweenDay = betweentime / nd;
            if (betweenDay != 0) {
                tvOrderAllTime.setText(betweenDay + "天");
            } else {
                long nh = 1000 * 60 * 60;
                final long betweenHour = betweentime % nd / nh;
                if (betweenHour != 0) {
                    tvOrderAllTime.setText(betweenHour + "小时");
                } else {
                    long nm = 1000 * 60;
                    final long betweenMinu = betweentime % nd % nh / nm;
                    tvOrderAllTime.setText(betweenMinu + "分钟");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        GlideApp
                .with(mContext)
                .load(cardetail.getPics())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv_order_car_pic);

        tvCarName.setText(cardetail.getCarName());
        tvOrderNumber.setText(cardetail.getPlateNo());
        tvOrderGetaddrTime.setText(getAddress + "\n" + bundle.getString("getTime"));
        tvOrderBackaddrTime.setText(sendAddress + "\n" + bundle.getString("backTime"));
        tvConfirmQuche.setText(selfGetAddress);
        tvConfirmHuanche.setText(selfRepayAddress);

        tvOrderDeposit.setText(bean.getData().getSysdata().getTrafficDeposit() + "元");

        tvCarPrice.setText(amount + "元");
        tvOrderBzj.setText(cardetail.getDeposit() + "元");

        tvOrderAllMoney.setText("合计：" + (bean.getData().getSysdata().getTrafficDeposit() + amount) + "元");
        tvOrderType.setText(cardetail.getUseType() == 1 ? "自 驾" : "商 务");
        //    tvOrderType.setText(cardetail.getUseType()==1?R.mipmap.car_type1:R.mipmap.car_type_2);
    }

    /*获取服务器时间，开单校准*/
    private void getServerTime(final double amount) {
        OkGo.<String>get(Constant.API_SERVER_TIME).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final TimeBean timeBean = GsonUtil.parseJsonWithGson(response.body(), TimeBean.class);
                if (timeBean.getErrno() == 0) {
                    final long serverTime = timeBean.getData();
                    final Date date = new Date();
                    final long currentTime = date.getTime() / 1000;
                    Log.d(TAG, "server: " + serverTime + " current" + currentTime);
                    if (currentTime - serverTime > 8) {
                        asyncShowToast("校时失败，无法提交订单");
                    } else {
                        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        final String format = sdf.format(new Date(currentTime*1000 + mBetween * 1000));
                        openOrder(amount, format);
                    }
                } else {

                    asyncShowToast("获取服务器时间失败");
                }
            }
        });
    }


    /*开单*/
    private void openOrder(double amount, String time) {

        final String url = OkHttpUtil.obtainGetUrl(Constant.API_ADD_ORDER,
                "carId", String.valueOf(cardetail.getId()),
                "userId", String.valueOf(userId),
                "money", String.valueOf(bean.getData().getSysdata().getTrafficDeposit()),
                "type", String.valueOf(cardetail.getUseType()),
                "startTime", startTime,
                "endTime", endTime,
                "channel", "1",
//                "city",SPUtils.getString(mContext,Constant.SP_LAST_LOCATION, ""),
                "city", "3",
                "takeAddress", getAddress,
                "takeHome", "0",
                "token", SPUtils.getToken(mContext),
                //"returnAddress", "杭州拱墅区翠苑13栋",
                "takeTime", startTime,
                "returnHome", "0",
                "leasePrice", String.valueOf(amount),
                "returnTime", endTime,
                "timestamp", time,
                "seat", EncryptUtils.getMD5("zzb" + String.valueOf(cardetail.getId() + String.valueOf(userId) + time)),
                "ver", "1");
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                final String body = response.body();
                final OrderBean orderBean = GsonUtil.parseJsonWithGson(body, OrderBean.class);
                if (orderBean.getErrno() == 0) {
                    orderBeanData = orderBean.getData();

                    System.out.println(body);
                    /*启动*/
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("payinfo", orderBeanData);
                    toActivity(PayActivity.class, bundle);
                    finish();
                } else asyncShowToast(orderBean.getMessage());

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }


//    private String getCurrentTime(boolean next) {
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date now = new Date();
//        if (next) {
//            return sdf.format(new Date(now.getTime() + 86400000));
//        } else {
//            return sdf.format(new Date(now.getTime() + 300000));
//        }
//    }

//    private String getFormatTime(String data) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date parse = null;
//        try {
//            parse = formatter.parse(data);
//            return sdf.format(parse);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
}
