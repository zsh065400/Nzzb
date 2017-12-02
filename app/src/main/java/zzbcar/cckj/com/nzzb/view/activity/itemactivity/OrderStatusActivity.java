package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
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
    @BindView(R.id.tv_order_allTime)
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
    @BindView(R.id.iv_order_car_pic)
    ImageView ivCarPic;

    @BindView(R.id.ll_count_down_time)
    View vCountDownTime;


    @BindView(R.id.tv_time_Surplus)
    TextView tvSurplus;
    @BindView(R.id.tv_time)
    TextView tvTime;


    private String[] useType = {"自驾", "商务", "", "婚庆"};

    private void initOrderInfo() {
        tvOrderId.setText(String.valueOf(databean.getOrderNo()));
        UserOrderBean.DataBean.CarBean car = databean.getCar();
//        Picasso.with(this).load(car.getPics()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).fit().into(ivCarPic);
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
        tvOrderDeposit.setText(carBean.getDeposit() + "");
        tvOrderBzj.setText(databean.getOnlineAmount() + "");
        tvOrderAllMoney.setText(databean.getTotalAmount() + "");
        tvOrderGetAddrTime.setText(databean.getTakeAddress() + "\n\n" + databean.getStartTime() + "");
        tvOrderBackAddrTime.setText(databean.getReturnAddress() + "\n\n" + databean.getEndTime() + "");
        switch (status) {
            case 0:
                tvCarStatus.setText("确定支付");
                break;
            case 1:
                tvCarStatus.setText("待接单");
                tvCarStatus.setEnabled(false);
                break;
            case 2:
                tvCarStatus.setText("确定取车");
                break;
            case 3:
                calcTime();
                tvCarStatus.setText("我要还车");
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
        switch (status) {
            case 0:/*去支付*/
                convertOrderInfo();
                tvCarStatus.setText("确认支付");
                break;
            case 2:/*取车*/
                takeReturnCar("1");
                tvCarStatus.setText("待取车");
                break;
            case 3:/*还车*/
                takeReturnCar("2");
                tvCarStatus.setText("我要还车");
                break;
            case 5:
                tvCarStatus.setText("确认还车");
                break;
            case 6:
                tvCarStatus.setText("确认收车");
                break;
        }
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

}
