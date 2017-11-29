package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.File;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.CarDetailBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.bean.UserCollectBean;
import zzbcar.cckj.com.nzzb.bean.WeekPriceBean;
import zzbcar.cckj.com.nzzb.map.Location;
import zzbcar.cckj.com.nzzb.map.NativeDialog;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;


/**
 * Created by Scout
 * Created on 2017/11/12 20:09.
 */

public class CarDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_car_owner_name)
    TextView tvCarOwnerName;
    @BindView(R.id.tv_accept_order_rate)
    TextView tvAcceptOrderRate;
    @BindView(R.id.tv_accept_order_num)
    TextView tvAcceptOrderNum;
    @BindView(R.id.tv_collect_times)
    TextView tvCollectTimes;
    @BindView(R.id.tv_car_remark)
    TextView tvCarRemark;
    @BindView(R.id.tv_car_license_number)
    TextView tvCarLicenseNumber;
    @BindView(R.id.iv_cardetail_collect)
    ImageView iv_cardetail_collect;
    @BindView(R.id.ll_car_price_list)
    LinearLayout llCarPriceList;
    @BindView(R.id.tv_drive_model)
    TextView tvDriveModel;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_full_lose)
    TextView tvFullLose;
    @BindView(R.id.tv_accept_order)
    TextView tvAcceptOrder;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.iv_to_right2)
    ImageView ivToRight2;
    @BindView(R.id.rl_cardetail_service_center)
    RelativeLayout rlCardetailServiceCenter;

    private CarDetailBean.DataBean carDetailBean;
    private String getAddress;
    private int collectFlag = 0;
    private Location loc_now = new Location(30.671249, 104.098863, "s");
    private Location loc_end = new Location(30.862644, 103.663077, "e");

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_details;
    }

    @BindView(R.id.iv_car_pic)
    ImageView ivCarPic;

    @BindView(R.id.tv_car_name)
    TextView tvCarName;

    @BindView(R.id.tv_car_model_name)
    TextView tvCarModelName;

    @BindView(R.id.tv_car_addr)
    TextView tvCarAddr;

    @BindView(R.id.tv_cardetail_engineer)
    TextView tv_cardetail_engineer;
    @BindView(R.id.tv_cardetail_seatnum)
    TextView tv_cardetail_seatnum;
    @BindView(R.id.tv_cardrtail_handblock)
    TextView tv_cardrtail_handblock;
    @BindView(R.id.tv_car_price)
    TextView tvCarPrice;

    @BindView(R.id.tv_immediately_rent_car)
    TextView tvCarRent;
    @BindView(R.id.tv_detail_car_describe)
    TextView tvDetailCarDescribe;
    @BindView(R.id.iv_car_detail_brand)
    ImageView iv_car_detail_brand;
    @BindView(R.id.civ_head_portrait)
    CircleImageView civ_head_portrait;
    public static final String RENT_KEY = "rent";//来自于RentActivity的跳转。
    private String[] transmissionCase = {"双离合", "手自动一体", "ISR", "AMT", "自动"};

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initListeners() {
        tvCarRent.setOnClickListener(this);
        tvCarAddr.setOnClickListener(this);
        llCarPriceList.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        rlCardetailServiceCenter.setOnClickListener(this);
        setBackButon(R.id.iv_back);

        iv_cardetail_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 收藏车辆(未实现，接口状态不明)

                collectCar();


            }
        });

    }

    private void collectCar() {
        SigninBean.DataBean.MemberBean signbean = SPUtils.getSignInfo(mContext);
        if (signbean != null) {
            SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(this);
            OkGo.<String>get(Constant.COLLECT_CAR_URL)
                    .params("userId", signInfo.getId())
                    .params("token", SPUtils.getToken(mContext))
                    .params("toCollect", collectFlag)
                    .params("carId", carDetailBean.getId())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            collectFlag = (collectFlag == 0 ? 1 : 0);
                            iv_cardetail_collect.setBackgroundResource(collectFlag == 0 ? R.drawable.collect : R.drawable.collect_normal);


                            Toast.makeText(mContext, collectFlag == 0 ? "收藏成功" : "取消收藏成功", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(mContext, "请登录后再试", Toast.LENGTH_SHORT).show();
            toActivity(CarDetailActivity.class, true);
        }


    }


    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (!TextUtils.isEmpty(type)) {
            if (type.equals(RENT_KEY)) {
                getAddress = intent.getStringExtra("getAddress");
            }
        } else {
            getAddress = "请点击设置送车上门地址";
        }
        final int carid = getIntent().getIntExtra("carid", 0);
        initWeekPrice(carid + "");
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_CAR_DETAIL, "carIdList", carid + "");
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                /*请求完毕优先判断*/
                final CarDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), CarDetailBean.class);
                setViewInfo(detailBean);
                getCollectStatus();
            }

            @Override
            public void onError(Response<String> response) {

            }
        });
    }

    private void getCollectStatus() {
        SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(mContext);
        if (signInfo != null) {
            OkGo.<String>get(Constant.COLLECT_CAR_ID_URL)
                    .params("userId", signInfo.getId())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            //解析数据获取收藏列表 collectList
                            UserCollectBean userCollectBean = GsonUtil.parseJsonWithGson(response.body(), UserCollectBean.class);
                            List<Integer> collectCarList = userCollectBean.getData();
                            if (collectCarList.contains(new Integer(carDetailBean.getId()))) {
                                collectFlag = 0;
                                iv_cardetail_collect.setBackgroundResource(R.drawable.collect);
                            } else {
                                collectFlag = 1;
                                iv_cardetail_collect.setBackgroundResource(R.drawable.collect_normal);
                            }


                        }
                    });
        }

    }


    private void initWeekPrice(String carid) {
        OkGo.<String>get(Constant.API_CAR_WEEK_PRICE)
                .params("carId", carid)
                .params("token", SPUtils.getToken(mContext))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        List<WeekPriceBean.DataBean> data = GsonUtil.parseJsonWithGson(response.body(), WeekPriceBean.class).getData();
                        parseWeekData(data);
                    }
                });
    }

    private void parseWeekData(List<WeekPriceBean.DataBean> data) {
        llCarPriceList.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View inflate = getLayoutInflater().inflate(R.layout.car_detail_week_item, null, false);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            inflate.setLayoutParams(lp);
            TextView week = inflate.findViewById(R.id.tv_week_price_week);
            TextView weekDay = inflate.findViewById(R.id.tv_week_price_weekday);
            TextView money = inflate.findViewById(R.id.tv_week_price_money);
            WeekPriceBean.DataBean dataBean = data.get(i);
            week.setText(getWeekDay(dataBean.getWeekday()));
            int day = new Date().getDay() + i;
            weekDay.setText(day < 10 ? "0" + day : day + "");
            money.setText(dataBean.getPrice() + "");
            llCarPriceList.addView(inflate, i);
        }
    }


    private void setViewInfo(CarDetailBean detailBean) {
        carDetailBean = detailBean.getData().get(0);
        Picasso.with(mContext).load(carDetailBean.getPics())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivCarPic);
        Picasso.with(mContext).load(carDetailBean.getBrandLogo())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv_car_detail_brand);

        if (!TextUtils.isEmpty(carDetailBean.getOwnerName()) && !TextUtils.isEmpty(carDetailBean.getOwnerAvatar()))
            Picasso.with(mContext).load(carDetailBean.getOwnerAvatar())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(civ_head_portrait);

        tvCarName.setText(carDetailBean.getCarName());
        tvCarModelName.setText(carDetailBean.getModelName());
        tvCarPrice.setText(carDetailBean.getPrice() + "");
        tvCarLicenseNumber.setText(carDetailBean.getPlateNo());
        tv_cardetail_seatnum.setText(carDetailBean.getSeatNum() + "座");
        tv_cardrtail_handblock.setText(carDetailBean.getTransmissionCase() + "");
        tvCarAddr.setText(carDetailBean.getAddr());
        tv_cardetail_engineer.setText(carDetailBean.getEngineLiter());
        tvCarOwnerName.setText("车主" + carDetailBean.getOwnerName());
        tvAcceptOrderRate.setText(String.valueOf(carDetailBean.getReceivePercent()));
        tvCollectTimes.setText(String.valueOf(carDetailBean.getCollectCount()));
        tvAcceptOrderNum.setText(String.valueOf(carDetailBean.getOrderCount()));
        tvCarRemark.setText(String.valueOf(carDetailBean.getRemark()));
        tvDriveModel.setText(carDetailBean.getUseType() == 1 ? "自驾" : "商务");
    }


    private String getWeekDay(int week) {
        String weekDay = "";
        switch (week) {
            case 1:
                weekDay = "日";
                break;
            case 2:
                weekDay = "一";
                break;
            case 3:
                weekDay = "二";
                break;
            case 4:
                weekDay = "三";
                break;
            case 5:
                weekDay = "四";
                break;
            case 6:
                weekDay = "五";
                break;
            case 7:
                weekDay = "六";
                break;
        }
        return weekDay;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_immediately_rent_car:
                if (carDetailBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("type", SelecTimeActivity.DETAIL_KEY);
                    bundle.putSerializable("cardetail", carDetailBean);
                    bundle.putString("getAddress", getAddress);
                    toActivity(SelecTimeActivity.class, bundle);
                }
                break;

            case R.id.ll_car_price_list:
                if (carDetailBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("type", SelecTimeActivity.DETAIL_KEY);
                    bundle.putSerializable("cardetail", carDetailBean);
                    bundle.putString("getAddress", getAddress);
                    toActivity(SelecTimeActivity.class, bundle);
                }
                break;
            case R.id.tv_car_addr:
                NativeDialog msgDialog = new NativeDialog(this, loc_now, loc_end);
                msgDialog.show();

                break;
            case R.id.iv_share:
                openShared();
                break;
            case R.id.rl_cardetail_service_center:
                 toActivity(HelpCenterActivity.class,true);
                break;


        }
    }

    private void openShared() {
        new ShareAction(this)
                .withText("至尊宝豪车共享")
                .withMedia(new UMImage(this, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)))
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            asyncShowToast("成功分享");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            asyncShowToast("失败" + t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            asyncShowToast("取消了");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
