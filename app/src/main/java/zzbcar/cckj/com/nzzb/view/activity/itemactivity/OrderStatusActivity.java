package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.BaseBean;
import zzbcar.cckj.com.nzzb.bean.OrderBean;
import zzbcar.cckj.com.nzzb.bean.UserOrderBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GlideApp;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

import static zzbcar.cckj.com.nzzb.R.id.tv_order_allTime;

/**
 * Created by liling on 2017/11/24.
 */

public class OrderStatusActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_details)
    ImageView ivDetails;
    @BindView(R.id.top_bar)
    RelativeLayout topBar;
    @BindView(R.id.tv_car_zj)
    TextView tvCarZj;

    /*超时*/
    @BindView(R.id.tv_car_overtime)
    TextView tvCarOvertime;
    /*超里程*/
    @BindView(R.id.tv_car_super_mileage)
    TextView tvCarSuperMileage;
    /*违章*/
    @BindView(R.id.tv_car_break_rules)
    TextView tvCarBreakRules;
    /*实际使用时长*/
    @BindView(R.id.tv_order_really_time)
    TextView tvOrderReallyTime;
    /*实际退款*/
    @BindView(R.id.tv_jurney_realy_refund)
    TextView tvJurneyRealyRefund;

    @BindView(R.id.ll_break_rules)
    LinearLayout llBreakRuls;
    @BindView(R.id.tv_cancel_break_rule)
    TextView tvCancelBreakRule;
//    @BindView(R.id.tv_car_overtime)
//    TextView tvCarOvertime;
//    @BindView(R.id.tv_car_super_mileage)
//    TextView tvCarSuperMileage;
//    @BindView(R.id.tv_car_break_rules)
//    TextView tvCarBreakRules;

    private UserOrderBean.DataBean databean;
    private int status;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                calcTime();
            }
        }
    };
    private UserOrderBean.DataBean.CarBean carBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
        setBackButon(R.id.iv_back);
    }

    @Override
    protected void initDatas() {
        //接受详情页面数据
        databean = (UserOrderBean.DataBean) getIntent().getExtras().getSerializable("data");

        carBean = databean.getCar();

        //显示数据
        status = databean.getStatus();

        initOrderInfo();
        if (databean.getStatus() == 0 || databean.getStatus() == 1 || databean.getStatus() == 2) {
            tvOrderCancel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
        handler = null;
    }

    @BindView(R.id.tv_order_id)
    TextView tvOrderId;/*订单号*/
    @BindView(R.id.tv_car_name)
    TextView tvCarName;/*车名*/
    @BindView(R.id.tv_order_type)
    TextView tvOrderType;/*车辆类型*/
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;/*车辆牌照*/
    @BindView(R.id.tv_order_mark)
    TextView tvOrderMark;/*车辆简介*/
    @BindView(R.id.tv_order_getaddrTime)
    TextView tvOrderGetAddrTime;/*取车时间和地址*/
    @BindView(R.id.tv_order_backaddrTime)
    TextView tvOrderBackAddrTime;/*还车时间和地址*/
    @BindView(tv_order_allTime)
    TextView tvOrderAllTime;/*还车时间*/
    @BindView(R.id.tv_car_price)
    TextView tvCarPrice;/*车辆租金*/
    @BindView(R.id.tv_order_deposit)
    TextView tvOrderDeposit;/*违章押金*/
    @BindView(R.id.tv_order_bzj)
    TextView tvOrderBzj;/*保证金*/
    @BindView(R.id.tv_order_allMoney)
    TextView tvOrderAllMoney;/*合计*/
    @BindView(R.id.tv_car_status)
    TextView tvCarStatus;/*车辆状态操作*/
    @BindView(R.id.tv_order_cancel)
    TextView tvOrderCancel;/*取消订单*/
    @BindView(R.id.iv_order_car_pic)
    ImageView ivCarPic;

    @BindView(R.id.ll_count_down_time)
    View vCountDownTime;

    @BindView(R.id.ll_two_btn)
    LinearLayout llTwoButtons;

    @BindView(R.id.tv_time_Surplus)
    TextView tvSurplus;
    @BindView(R.id.tv_time)
    TextView tvTime;


    private String[] useType = {"自驾", "商务", "", "婚庆"};

    private void initOrderInfo() {
        tvOrderId.setText(String.valueOf(databean.getOrderNo()));
        UserOrderBean.DataBean.CarBean car = databean.getCar();
        /*加载界面数据*/
        GlideApp
                .with(mContext)
                .load(car.getPics())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivCarPic);
        tvCarName.setText(car.getCarName());
        tvOrderType.setText(useType[car.getUseType() - 1]);
        tvOrderNumber.setText(car.getPlateNo());
        tvCarPrice.setText(databean.getLeasePrice() + "");
        tvOrderBzj.setText(carBean.getDeposit() + "");
        tvOrderDeposit.setText(databean.getTrafficDepositMoney() + "");
        tvOrderAllMoney.setText(databean.getOnlineAmount() + "");
        tvOrderGetAddrTime.setText(databean.getTakeAddress() + "\n\n" + databean.getStartTime() + "");
        tvOrderBackAddrTime.setText(databean.getReturnAddress() + "\n\n" + databean.getEndTime() + "");



        /*实际使用时间*/
        String endTime = databean.getEndTime();
        String startTime = databean.getStartTime();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        String returnTime = (String) databean.getReturnTime();
        String createTime = databean.getCreateTime();
        try {
            Date end = sdf.parse(returnTime);
            Date start = sdf.parse(createTime);
            long nd = 1000 * 24 * 60 * 60;
            long betweentime = end.getTime() - start.getTime();
            final long betweenDay = betweentime / nd;
            if (betweenDay != 0) {


                tvOrderReallyTime.setText("实际使用时间：" + betweenDay + "天");
            } else {
                long nh = 1000 * 60 * 60;
                final long betweenHour = betweentime % nd / nh;
                if (betweenHour != 0) {
                    tvOrderReallyTime.setText("实际使用时间：" + betweenHour + "小时");
                } else {
                    long nm = 1000 * 60;
                    final long betweenMinu = betweentime % nd % nh / nm;
                    tvOrderReallyTime.setText("实际使用时间：" + betweenMinu + "分钟");
                }
            }
        } catch (Exception e) {
            tvOrderReallyTime.setVisibility(View.GONE);
            Log.d(TAG, "initOrderInfo: 还车时间数据不存在");
            e.printStackTrace();
        }


        //已完成状态违章设置
        if (databean.getStatus() == 10) {
            /*隐藏部分状态*/
            tvOrderAllMoney.setVisibility(View.GONE);
            llTwoButtons.setVisibility(View.VISIBLE);
            tvCarStatus.setVisibility(View.GONE);


            final double timeoutMoney = databean.getTimeoutMoney();
            final double exceedMoney = databean.getExceedMoney();
            final double trafficPunlishMoney = databean.getTrafficPunlishMoney();
            final double lastReturnMoney = databean.getLastReturnMoney();
            final double AbolishMoney = databean.getAbolishMoney();
            if (timeoutMoney == 0 && exceedMoney == 0 && lastReturnMoney == 0) {
                llBreakRuls.setVisibility(View.GONE);
            } else {
                /*如果有违章就全部显示*/
                llBreakRuls.setVisibility(View.VISIBLE);
                tvCarOvertime.setText(String.format("-%s", timeoutMoney));
                tvCarSuperMileage.setText(String.format("-%s", exceedMoney));
                tvCarBreakRules.setText(String.format("-%s", trafficPunlishMoney));
                tvCancelBreakRule.setText(String.format("-%s", AbolishMoney));
            }
            /*实际退款*/
            tvJurneyRealyRefund.setVisibility(View.VISIBLE);
            tvJurneyRealyRefund.setText(String.format("%s", lastReturnMoney));
        }

        final int payStatus = databean.getPayStatus();


        switch (status) {
            case 0:
                if (payStatus == 0)
                    tvCarStatus.setText("确定支付");
                else
                    tvCarStatus.setVisibility(View.GONE);
                break;
//            case 1:
//                tvCarStatus.setText("待接单");
//                tvCarStatus.setEnabled(false);
//                break;
            case 2:
                tvCarStatus.setText("确定取车");
                break;
            case 3:
                calcTime();
                tvCarStatus.setText("确定还车");
                break;
            case 5:
//                tvCarStatus.setText("确认还车");
//                break;
            case 6:
//                tvCarStatus.setText("确认收车");
//                break;
            case 7:
            case 8:
            case 9:
            case 10:
                tvCarStatus.setVisibility(View.GONE);
                break;
        }
    }

    /*进行中的状态需要计算剩余时间和超时*/
    private void calcTime() {
        /*计算时间*/
        String endTime = databean.getEndTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            /*计算差值*/
            Date end = sdf.parse(endTime);
            Date current = new Date();
            long between = end.getTime() - current.getTime();//剩余时间
            /*未超时*/
            vCountDownTime.setVisibility(View.VISIBLE);
            if (between > 0) {
                tvSurplus.setText(getDatePoor(end, current, true));
                handler.sendEmptyMessageDelayed(0, 1000);
            } else {
                /*超时*/
                tvTime.setText("已超时");
                tvSurplus.setText(getDatePoor(current, end, false));
                handler.sendEmptyMessageDelayed(0, 60000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取两个时间的时间查 如1天2小时30分钟
     */
    public static String getDatePoor(Date endDate, Date nowDate, boolean haveSec) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        if (haveSec)
            return day + "天" + hour + "时" + min + "分" + sec + "秒";
        else
            return day + "天" + hour + "时" + min + "分";

//        Picasso.with(mContext).load(carBean.getPics()).placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.a).fit().into(ivOrderCarPic);//车图片
//        tvCarName.setText(carBean.getCarName());
//        tvOrderNumber.setText(carBean.getPlateNo());
//        tvOrderGetaddrTime.setText(databean.getTakeAddress()+"\n\n"+databean.getStartTime()+"");
//        tvOrderBackaddrTime.setText(databean.getReturnAddress()+"\n\n"+databean.getEndTime()+"");
//        tvOrderId.setText(databean.getOrderNo());
//        tvCarPrice.setText(databean.getLeasePrice()+"");
//        tvOrderDeposit.setText(carBean.getDeposit()+"");
//        tvOrderBzj.setText(databean.getOnlineAmount()+"");
//        tvOrderAllMoney.setText(databean.getTotalAmount()+"");
//        tvOrderMark.setText(carBean.getModelYear()+"款 "+"|"+ carBean.getSeatNum()+" "+"|");
//        tvOrderType.setText(carBean.getUseType()==1?"自驾":"商务");


    }

    @OnClick(R.id.tv_car_status)
    public void onViewClicked() {
        // 车的类型  例如我要还车  跳转相应的界面
        final int payStatus = databean.getPayStatus();
        switch (status) {
            case 0:/*去支付*/
                convertOrderInfo();
                tvCarStatus.setText("确定支付");
                break;
            case 2:/*取车*/
                takeReturnCar("1");
                tvCarStatus.setText("确定取车");
                break;
            case 3:/*还车*/
                takeReturnCar("2");
                tvCarStatus.setText("确定还车");
                break;
            case 5:
//                tvCarStatus.setText("确认还车");
                break;
            case 6:
//                tvCarStatus.setText("确认收车");
                break;
        }
    }

    /**
     * 撤销订单
     */
    @OnClick(R.id.tv_order_cancel)
    public void cancelOrder() {
        // TODO: 2017/12/4 违约需添加逻辑
        OkGo.<String>get(Constant.BACK_OUT_ORDER)
                .params("orderId", databean.getId())
                .params("token", SPUtils.getToken(mContext))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final String body = response.body();
                        Log.d(TAG, "onSuccess: " + body);
                        final BaseBean baseBean = GsonUtil.parseJsonWithGson(body, BaseBean.class);
                        if (baseBean.getErrno() == 0) {
                            doCancel(Integer.parseInt(baseBean.getData()));
                        } else {
                            asyncShowToast(baseBean.getMessage());
                        }
                    }
                });
    }

    private void doCancel(int money) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        AlertDialog alertDialog = builder.setMessage("1：交易开始72小时内，需扣除订单租金的的50%，交易开始后取消订单, 扣除租金的100%，最高上限3000。此次扣除" + money + "元违约金")
                .setTitle("您确定要取消订单么?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String url = OkHttpUtil.obtainGetUrl(Constant.API_CANCEL_ORDER,
                                "orderId", String.valueOf(databean.getId()),
                                "token", SPUtils.getToken(mContext));
                        OkGo.<String>get(url).execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                final String body = response.body();
                                final BaseBean baseBean = GsonUtil.parseJsonWithGson(body, BaseBean.class);
                                if (baseBean.getErrno() == 0) {
                                    asyncShowToast("取消订单成功");
                                    finish();
                                }
                            }
                        });
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }

                }).create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
    }

    @OnClick(R.id.iv_details)
    public void toOrderDetail() {
        final Bundle bundle = new Bundle();
        bundle.putSerializable("details", databean);
        toActivity(OrderTrackingActivity.class, bundle);
    }

    /*取还车*/
    private void takeReturnCar(final String type) {
        /*经纬度*/
        String longtiude = SPUtils.getString(mContext, Constant.SP_LONGITUDE, "");
        String latitude = SPUtils.getString(mContext, Constant.SP_LATITUDE, "");
        String url = OkHttpUtil.obtainGetUrl(Constant.API_TAKE_RETURN_CAR,
                "type", type,
                "orderId", String.valueOf(databean.getId()),
                "map", longtiude + "," + latitude,
                "token", SPUtils.getToken(mContext));
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseBean baseBean = GsonUtil.parseJsonWithGson(response.body(), BaseBean.class);
                if (baseBean.getErrno() == 0) {
                    String use = type.equals("1") ? "取车" : "还车";
                    asyncShowToast(use + "成功");
                    finish();
                } else asyncShowToast(baseBean.getMessage());
            }
        });
    }


    private void convertOrderInfo() {
        OrderBean.DataBean payInfo = new OrderBean.DataBean();
        payInfo.setCarId(databean.getCarId());
        payInfo.setCreateTime(databean.getCreateTime());
        payInfo.setDetail(databean.getDetail());
        payInfo.setEndTime(databean.getEndTime());
        payInfo.setId(databean.getId());
        payInfo.setLeasePrice(databean.getLeasePrice());
        payInfo.setOnlineAmount(databean.getOnlineAmount());
        payInfo.setOrderNo(databean.getOrderNo());
        payInfo.setPayStatus(databean.getPayStatus());
        payInfo.setRemark("");
        payInfo.setReturnAddress(databean.getReturnAddress());
        payInfo.setReturnAddrId(databean.getReturnAddrId());
        payInfo.setReturnHome(databean.getReturnHome());
        payInfo.setReturnMapLocation(databean.getReturnMapLocation());
        payInfo.setReturnTime(databean.getReturnTime());
        payInfo.setStartTime(databean.getStartTime());
        payInfo.setStatus(databean.getStatus());
        payInfo.setSubject(databean.getSubject());
        payInfo.setTakeAddress(databean.getTakeAddress());
        payInfo.setTakeAddrId(databean.getTakeAddrId());
        payInfo.setTakeHome(databean.getTakeHome());
        payInfo.setTakeTime(databean.getTakeTime());
        payInfo.setTakeMapLocation(databean.getTakeMapLocation());
        payInfo.setTotalAmount(databean.getTotalAmount());
        payInfo.setTrafficDepositMoney(databean.getTrafficDepositMoney());
        payInfo.setType(databean.getType());
        payInfo.setUserId(databean.getUserId());
        Bundle bundle = new Bundle();
        bundle.putSerializable("payinfo", payInfo);
        toActivity(PayActivity.class, bundle);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
