package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.CarDetailBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Scout
 * Created on 2017/11/12 20:09.
 */

public class CarDetailActivity extends BaseActivity {

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
    @BindView(R.id.ll_car_collect)
    LinearLayout llCarCollect;
    private CarDetailBean.DataBean carDetailBean;

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

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initListeners() {
        tvCarRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carDetailBean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cardetail", carDetailBean);
                    toActivity(OrderConfirmActivity.class, bundle, true);
                }
            }
        });
        setBackButon(R.id.iv_back);
        llCarCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 收藏车辆(未实现，接口状态不明)
                collectCar();
            }
        });
    }
    private void collectCar() {
    }

    @Override
    protected void initDatas() {
        final int carid = getIntent().getIntExtra("carid", 0);
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_CAR_DETAIL, "carIdList", carid + "");
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                /*请求完毕优先判断*/
                final CarDetailBean detailBean = GsonUtil.parseJsonWithGson(response.body(), CarDetailBean.class);
                setViewInfo(detailBean);
            }
            @Override
            public void onError(Response<String> response) {

            }
        });
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
        Picasso.with(mContext).load(carDetailBean.getOwnerAvatar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(civ_head_portrait);

        tvCarName.setText(carDetailBean.getCarName());
        tvCarModelName.setText(carDetailBean.getModelName());
        tvCarPrice.setText(carDetailBean.getPrice() + "");
        tvCarLicenseNumber.setText(carDetailBean.getPlateNo());
//        tv_cardetail_seatnum.setText(carDetailBean.getSeatNum());
//        tv_cardrtail_handblock.setText(carDetailBean.getTransmissionCase());
        tvCarAddr.setText(carDetailBean.getAddr());
        tvCarOwnerName.setText("车主" + carDetailBean.getOwnerName());
        tvAcceptOrderRate.setText(String.valueOf(carDetailBean.getReceivePercent()));
        tvCollectTimes.setText(String.valueOf(carDetailBean.getCollectCount()));
        tvAcceptOrderNum.setText(String.valueOf(carDetailBean.getOrderCount()));
        tvCarRemark.setText(String.valueOf(carDetailBean.getRemark()));

    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
