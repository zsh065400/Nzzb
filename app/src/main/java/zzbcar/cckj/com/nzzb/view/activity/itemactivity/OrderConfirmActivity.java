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
                Bundle bundle = new Bundle();
                bundle.putSerializable("payinfo", orderBeanData);
                toActivity(PayActivity.class, bundle);
            }
        });
    }

    @Override
    protected void initDatas() {
        /*此处开单并更新界面，可加loading*/
        cardetail = (CarDetailBean.DataBean) getIntent().getExtras().getSerializable("cardetail");
        final String userJson = SPUtils.getString(mContext, "User", "");
        if (userJson != "") {
            final SigninBean bean = GsonUtil.parseJsonWithGson(userJson, SigninBean.class);
            final int userId = bean.getData().getMember().getId();
            final String url = OkHttpUtil.obtainGetUrl(Constant.API_ADD_ORDER,
                    "carId", String.valueOf(cardetail.getId()),
                    "userId", String.valueOf(userId),
                    "money", "0.01",
                    "type", "1",
                    "startTime", getCurrentTime(false),
                    "endTime", getCurrentTime(true),
                    "takeAddress", "杭州拱墅区翠苑13栋",
                    "takeHome", "0",
                    "returnAddress", "杭州拱墅区翠苑13栋",
                    "takeTime", getCurrentTime(false),
                    "returnHome", "0",
                    "leasePrice", "0.01",
                    "returnTime", getCurrentTime(true));
            OkGo.<String>get(url).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    final String body = response.body();
                    final OrderBean orderBean = GsonUtil.parseJsonWithGson(body, OrderBean.class);
                    orderBeanData = orderBean.getData();
                    System.out.println(body);
                }
            });

        } else {
            Toast.makeText(mContext, "您还未登录", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentTime(boolean next) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        if (next) {
            return sdf.format(new Date(now.getTime() + 600000));
        } else {
            return sdf.format(new Date(now.getTime() + 300000));
        }
    }
}
