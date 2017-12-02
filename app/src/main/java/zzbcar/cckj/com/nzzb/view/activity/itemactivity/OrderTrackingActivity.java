package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.OrderTrackingAdapter;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.UserOrderBean;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class OrderTrackingActivity extends BaseActivity {

    private UserOrderBean.DataBean databean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_tracking;
    }

    @BindView(R.id.rv_status)
    RecyclerView rvStatus;

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);

        rvStatus.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("订单跟踪").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databean = (UserOrderBean.DataBean) getIntent().getExtras().getSerializable("details");

        showStatus();
    }


    /*显示状态*/
    private void showStatus() {
        List<OrderTrackingAdapter.StatusTime> times = new ArrayList<>();
        final String createTime = databean.getCreateTime();
        final String takeTime = (String) databean.getTakeTime();
        final String returnTime = (String) databean.getReturnTime();
        final String rtdepositTime = (String) databean.getRtdepositTime();
        if (createTime != null && !createTime.equals(""))
            times.add(new OrderTrackingAdapter.StatusTime("下单时间", createTime));
        if (takeTime != null && !takeTime.equals(""))
            times.add(new OrderTrackingAdapter.StatusTime("取车时间", takeTime));
        if (returnTime != null && !returnTime.equals(""))
            times.add(new OrderTrackingAdapter.StatusTime("还车时间", returnTime));
        if (rtdepositTime != null && !rtdepositTime.equals(""))
            times.add(new OrderTrackingAdapter.StatusTime("退还押金", rtdepositTime));

        final OrderTrackingAdapter adapter = new OrderTrackingAdapter(this, times);
        rvStatus.setAdapter(adapter);
    }
}
