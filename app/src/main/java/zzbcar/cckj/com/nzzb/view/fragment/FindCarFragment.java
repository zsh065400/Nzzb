package zzbcar.cckj.com.nzzb.view.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.GridItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.GridPagerAdapter;
import zzbcar.cckj.com.nzzb.adapter.ListItemAdapter;
import zzbcar.cckj.com.nzzb.base.SpaceSize;
import zzbcar.cckj.com.nzzb.base.SpacesItemDecoration;
import zzbcar.cckj.com.nzzb.bean.BrandCarBean;
import zzbcar.cckj.com.nzzb.bean.CarDefaultBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.ListUtils;
import zzbcar.cckj.com.nzzb.utils.OkManager;

/**
 * Created by Admin on 2017/10/31.
 */

public class FindCarFragment extends BaseFragment {

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
        rvListItems.addItemDecoration(new SpacesItemDecoration(new SpaceSize(25, 10, 10, 10)));
        rvListItems.setNestedScrollingEnabled(false);
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
        for (List<BrandCarBean.DataBean> dataBeans : split) {
            GridItemAdapter itemAdapter = new GridItemAdapter(mActivity, dataBeans);
            GridView gridView = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
            gridView.setAdapter(itemAdapter);
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
        CarDefaultBean carDefaultBean = GsonUtil.parseJsonWithGson(result, CarDefaultBean.class);
        carList = carDefaultBean.getData();

        final ListItemAdapter adapter = new ListItemAdapter(carList, mActivity);
        rvListItems.setAdapter(adapter);
    }

    @Override
    public void initDatas() {
        startTask();
        getCarBrandData();
    }

    @Override
    protected void initListeners() {

    }

}
