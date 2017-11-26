package zzbcar.cckj.com.nzzb.view.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.GridItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.GridPagerAdapter;
import zzbcar.cckj.com.nzzb.adapter.ListItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.base.SpaceSize;
import zzbcar.cckj.com.nzzb.base.SpacesItemDecoration;
import zzbcar.cckj.com.nzzb.bean.BrandCarBean;
import zzbcar.cckj.com.nzzb.bean.CarDefaultBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.ListUtils;
import zzbcar.cckj.com.nzzb.utils.OkManager;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.BrandCarActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.CarDetailActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.SearchActivity;

import static zzbcar.cckj.com.nzzb.utils.Constant.SP_LAST_LOCATION;

/**
 * Created by Admin on 2017/10/31.
 */

public class FindCarFragment extends BaseFragment {

    @BindView(R.id.tv_home_local_city)
    TextView tvHomeLocalCity;
    @BindView(R.id.ll_choose_city)
    LinearLayout llChooseCity;
//    @BindView(R.id.ib_erweima)
//    ImageButton ibErweima;
    @BindView(R.id.search_find)
    LinearLayout searchFind;
    private OkManager manager = new OkManager();

    private List<CarDefaultBean.DataBean> carList = new ArrayList<>();
    private List<BrandCarBean.DataBean> brandList = new ArrayList<>();

    @BindView(R.id.vp_grid_items)
    ViewPager vp_grid_items;

    @BindView(R.id.rv_list_items)
    RecyclerView rvListItems;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initViews(View view) {
        //下部滑动
        rvListItems.setLayoutManager(new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false));
        rvListItems.addItemDecoration(new SpacesItemDecoration(new SpaceSize(10, 10, 10, 10)));
        rvListItems.setNestedScrollingEnabled(false);

        StatusBarUtil.setViewTopPadding(mActivity, view, R.id.TopTitleBar);
    }

    /**
     * 获得汽车品牌
     */
    private void getCarBrandData() {
        manager.asyncJsonObjectByURL(Constant.CAR_BRAND_URL, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("TAG", "onResponse: ====s=======>" + jsonObject.toString());
                BrandCarBean brandCarBean = GsonUtil.parseJsonWithGson(String.valueOf(jsonObject), BrandCarBean.class);
                brandList.addAll(brandCarBean.getData());
                initCarBrand();
            }
        });
    }

    private void initCarBrand() {
        List<List<BrandCarBean.DataBean>> split = ListUtils.split(brandList, 10);
        List<View> gridViews = new ArrayList<>();
        for (final List<BrandCarBean.DataBean> dataBeans : split) {
            GridItemAdapter itemAdapter = new GridItemAdapter(mActivity, dataBeans);
            GridView gridView = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
            gridView.setAdapter(itemAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Intent intent = new Intent(mActivity, BrandCarActivity.class);
                    final BrandCarBean.DataBean bean = dataBeans.get(position);
                    intent.putExtra("brandId", String.valueOf(bean.getId()));
                    intent.putExtra("brandName", String.valueOf(bean.getName()));
                    startActivity(intent);
                }
            });
            gridViews.add(gridView);
        }
        vp_grid_items.setAdapter(new GridPagerAdapter(gridViews));
        /*同步指示器*/
        indicator.setViewPager(vp_grid_items);
    }


    private void startTask() {
        manager.asyncJsonByURL(Constant.CAR_DEFAULT_URL, new OkManager.Func1() {
            @Override
            public void onResponse(String result) {
                parseDefaultCarBean(result);
            }
        });
    }

    private void parseDefaultCarBean(String result) {
        final CarDefaultBean carDefaultBean = GsonUtil.parseJsonWithGson(result, CarDefaultBean.class);
        carList = carDefaultBean.getData();

        final ListItemAdapter adapter = new ListItemAdapter(mActivity, carList);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                /*跳转详情页面*/
                final Intent intent = new Intent(mActivity, CarDetailActivity.class);
                intent.putExtra("carid", carList.get(position).getId());
                startActivity(intent);
            }
        });
        rvListItems.setAdapter(adapter);
    }

    @Override
    public void initDatas() {
        String lastCity = SPUtils.getString(mActivity, SP_LAST_LOCATION, "");
        tvHomeLocalCity.setText(lastCity);
        startTask();
        getCarBrandData();
    }
    @Override
    protected void initListeners() {


//        ibErweima.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        searchFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(SearchActivity.class);
                System.out.println("搜索栏点击");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tvHomeLocalCity.setText(SPUtils.getString(mActivity, SP_LAST_LOCATION, ""));
    }
}
