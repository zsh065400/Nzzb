package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.UserOrderBean;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by liling on 2017/11/24.
 */

public class OrderStatusActivity extends BaseActivity {



    private UserOrderBean.DataBean databean;

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
        //显示数据

    }

    @OnClick(R.id.tv_car_status)
    public void onViewClicked() {
        // 车的类型  例如我要还车  跳转相应的界面
    }
}
