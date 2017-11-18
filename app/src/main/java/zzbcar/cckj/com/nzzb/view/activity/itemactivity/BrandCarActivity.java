package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.BrandCarAdapter;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.base.SpaceSize;
import zzbcar.cckj.com.nzzb.base.SpacesItemDecoration;
import zzbcar.cckj.com.nzzb.bean.QueryBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class BrandCarActivity extends BaseActivity {

    private QueryBean queryBean;

    @BindView(R.id.rv_brand_car_list)
    RecyclerView rvBrandCarList;

    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_brand_car;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        final String brandId = getIntent().getStringExtra("brandId");
        final String brandName = getIntent().getStringExtra("brandName");
        tvBrandName.setText(brandName);
        doQuery(brandId);
    }

    @Override
    protected void initListeners() {
        setBackButon(R.id.iv_back);
    }

    /**
     * 车辆查询
     */
    private void doQuery(String brandId) {
        // TODO: 2017/11/16 车辆查询Builder提取，目标RentActivity
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_CAR_QUERY,
                "brand", brandId,
                "pageNum", "0",
                "pageSize", "20");
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final String body = response.body();
                queryBean = GsonUtil.parseJsonWithGson(body, QueryBean.class);
                if (queryBean.getErrno() == 0) {
                    final List<QueryBean.DataBean> data = queryBean.getData();
                    initRecycleView(data);
                } else {
                    asyncShowToast("查询出错");
                }
            }
        });
    }

    private void initRecycleView(final List<QueryBean.DataBean> queryBean) {
        if (queryBean == null || queryBean.size() == 0) {
            Toast.makeText(mContext, "暂无该品牌车辆", Toast.LENGTH_SHORT).show();
            return;
        }
        rvBrandCarList.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        rvBrandCarList.addItemDecoration(new SpacesItemDecoration(new SpaceSize(10, 10, 10, 10)));
        final BrandCarAdapter adapter = new BrandCarAdapter(mContext, queryBean);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                /*跳转详情页面*/
                final Intent intent = new Intent(mContext, CarDetailActivity.class);
                intent.putExtra("carid", queryBean.get(position).getId());
                startActivity(intent);
            }
        });
        rvBrandCarList.setAdapter(adapter);
    }
}
