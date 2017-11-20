package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.MyOrderBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/20.
 */

public class MyOrderActivity extends BaseActivity{
    private RecyclerView rv_car_myorder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myorder;
    }

    @Override
    protected void initViews() {
        rv_car_myorder = (RecyclerView) findViewById(R.id.rv_car_myorder);
        rv_car_myorder.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(this);
        OkGo.<String>get(Constant.MY_ORDER_URL)
                .params("userId",signInfo.getId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });

        new TitleBuilder(this).setTitleText("我的订单").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void parseData(String body) {
        List<MyOrderBean.DataBean> dataList = GsonUtil.parseJsonWithGson(body, MyOrderBean.class).getData();
        if (dataList.size()==0){
            Toast.makeText(mContext, "咱没有订单可查询", Toast.LENGTH_SHORT).show();
            return;

        }

    }


}
