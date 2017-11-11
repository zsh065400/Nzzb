package zzbcar.cckj.com.nzzb.view.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.FindcarDefaultItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.GridItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.GridPagerAdapter;
import zzbcar.cckj.com.nzzb.adapter.ListItemAdapter;
import zzbcar.cckj.com.nzzb.base.SpaceSize;
import zzbcar.cckj.com.nzzb.base.SpacesItemDecoration;
import zzbcar.cckj.com.nzzb.bean.BrandCarBean;
import zzbcar.cckj.com.nzzb.bean.CarDefaultBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkManager;
import zzbcar.cckj.com.nzzb.view.customview.ViewPagerIndicator;

/**
 * Created by Admin on 2017/10/31.
 */

public class FindCarFragment extends BaseFragment {

    private GridView gv_findcarfragment_defaut;
    private GridView gv_findcarfragment_carbrand;
    private OkManager manager=new OkManager();
    private FindcarDefaultItemAdapter adapter;
    private List<CarDefaultBean.DataBean> list=new ArrayList<>();
    private   List<BrandCarBean.DataBean> brandLists=new ArrayList<>();
    private ViewPager vp_grid_items;
    private RecyclerView rvListItems;
    private   GridItemAdapter itemAdapter;
    @Override
    public View initView(LayoutInflater inflater) {
        View view = View.inflate(mActivity, R.layout.fragment_find, null);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
//   gv_findcarfragment_defaut = view.findViewById(R.id.gv_findcarfragment_defaut);
        vp_grid_items = view.findViewById(R.id.vp_grid_items);
        itemAdapter=new GridItemAdapter(mActivity,brandLists);
//        final GridItemAdapter itemAdapter = new GridItemAdapter(getContext(), null);
        GridView gridView1 = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
        gridView1.setAdapter(itemAdapter);
        GridView gridView2 = (GridView)mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
        gridView2.setAdapter(itemAdapter);
        List<View> views = new ArrayList<>();
        views.add(gridView1);
        views.add(gridView2);
        vp_grid_items.setAdapter(new GridPagerAdapter(views) );

        //下部上下滑动布局
        rvListItems = view.findViewById(R.id.rv_list_items);
        rvListItems.setLayoutManager(new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false));
        rvListItems.addItemDecoration(new SpacesItemDecoration(new SpaceSize(10, 10, 10, 10)));
        rvListItems.setNestedScrollingEnabled(false);
        //开启网络下载数据的方法


        //滑动圆点
       final ViewPagerIndicator indicator = view.findViewById(R.id.indicator);
        //滑动监听
        vp_grid_items.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.setOffset(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getCarBrandData();

    }

    private void getCarBrandData() {
        manager.asyncJsonObjectByURL(Constant.CAR_BRAND_URL, new OkManager.Fun4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("TAG", "onResponse: ====s=======>"+jsonObject.toString());
                BrandCarBean brandCarBean = GsonUtil.parseJsonWithGson(String.valueOf(jsonObject), BrandCarBean.class);
                brandLists.addAll(brandCarBean.getData());


                itemAdapter.notifyDataSetChanged();
            }
        });
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
        list= carDefaultBean.getData();

        rvListItems.setAdapter(new ListItemAdapter(list,mActivity));
    }

    @Override
    public void initData() {
        startTask();

    }





    @Override
    protected void initEvent() {

    }



}
