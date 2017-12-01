package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
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
        new TitleBuilder(this).setTitleText("我的订单").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void parseData(String body) {


    }


}
