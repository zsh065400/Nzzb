package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
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

    @BindView(R.id.tv_car_price)
    TextView tvCarPrice;

    @BindView(R.id.tv_immediately_rent_car)
    TextView tvCarRent;
    @BindView(R.id.tv_detail_car_describe)
    TextView tvDetailCarDescribe;
    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initListeners() {
        tvCarRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cardetail", carDetailBean);
                toActivity(OrderConfirmActivity.class, bundle, true);
            }
        });
        setBackButon(R.id.iv_back);
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
        tvCarName.setText(carDetailBean.getCarName());
        tvCarModelName.setText(carDetailBean.getModelName());
        tvCarPrice.setText(carDetailBean.getPrice() + "");
        tvCarAddr.setText(carDetailBean.getAddr());

    }
    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this,null);
    }
}
