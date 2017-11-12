package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class CarListActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_car_list)
    RecyclerView rvCarList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_list;
    }

    @Override
    protected void initViews() {
        rvCarList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initDatas() {
        final Bundle extras = getIntent().getExtras();
    }

    private void initCarList() {

    }
}
