package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.adapter.main.CarListAdapter;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class CarListActivity extends BaseActivity {

    @BindView(R.id.rv_car_list)
    RecyclerView rvCarList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_list;
    }

    @Override
    protected void initViews() {
        rvCarList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        final List<MainPageBean.DataBean.CarListBean> extras = (List<MainPageBean.DataBean.CarListBean>) getIntent().getSerializableExtra("carlist");
        initCarList(extras);
    }

    private void initCarList(final List<MainPageBean.DataBean.CarListBean> extras) {
        final CarListAdapter adapter = new CarListAdapter(mContext, extras);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                /*跳转详情页面*/
                final Intent intent = new Intent(mContext, CarDetailActivity.class);
                intent.putExtra("carid", extras.get(position).getId());
                startActivity(intent);
            }
        });
        rvCarList.setAdapter(adapter);
    }

}
