package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.view.View;
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
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class OrderConfirmActivity extends BaseActivity {

    @BindView(R.id.tv_car_to_pay)
    TextView tvToPay;
    /*车辆信息*/
    private CarDetailBean.DataBean cardetail;
    /*订单信息*/
    private OrderBean.DataBean orderBeanData;
    private int userId;
    private String startTime;
    private String endTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_confirm;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        tvToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcPrice();
            }
        });
    }

    @Override
    protected void initDatas() {
        /*确认租车信息，同步更新界面，可加loading*/
        cardetail = (CarDetailBean.DataBean) getIntent().getExtras().getSerializable("cardetail");
        final String userJson = SPUtils.getString(mContext, "User", "");
        if (userJson != "") {
            final SigninBean bean = GsonUtil.parseJsonWithGson(userJson, SigninBean.class);
            userId = bean.getData().getMember().getId();
        } else {
            Toast.makeText(mContext, "您还未登录", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /*逻辑调用位置需要优化，此处根据后台逻辑暂定*/
    // TODO: 2017/11/13 梳理api调用逻辑和界面关系
    /*计算租金*/
    private void calcPrice() {
        startTime = getCurrentTime(false);
        endTime = getCurrentTime(true);
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_QUERY_PRICE,
                "carId", String.valueOf(cardetail.getId()),
                "date1", startTime,
                "date2", endTime);
        OkGo.<String>get(url).execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                final String body = response.body();
                final PriceListBean priceListBean = GsonUtil.parseJsonWithGson(body, PriceListBean.class);
                if (priceListBean.getErrno() == 0) {
                    final double amount = priceListBean.getData().getAmount();
                    openOrder(amount);
                } else asyncShowToast(priceListBean.getMessage());
            }
        });
    }

    /*开单*/
    private void openOrder(double amount) {
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_ADD_ORDER,
                "carId", String.valueOf(cardetail.getId()),
                "userId", String.valueOf(userId),
                "money", String.valueOf(amount),
                "type", "1",
                "startTime", startTime,
                "endTime", endTime,
                "takeAddress", "杭州拱墅区翠苑13栋",
                "takeHome", "0",
                "returnAddress", "杭州拱墅区翠苑13栋",
                "takeTime", startTime,
                "returnHome", "0",
                "leasePrice", String.valueOf(amount),
                "returnTime", endTime);
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final String body = response.body();
                final OrderBean orderBean = GsonUtil.parseJsonWithGson(body, OrderBean.class);
                orderBeanData = orderBean.getData();
                System.out.println(body);
                /*启动*/
                Bundle bundle = new Bundle();
                bundle.putSerializable("payinfo", orderBeanData);
                toActivity(PayActivity.class, bundle);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }

    private String getCurrentTime(boolean next) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        if (next) {
            return sdf.format(new Date(now.getTime() + 86400000));
        } else {
            return sdf.format(new Date(now.getTime() + 300000));
        }
    }
}
