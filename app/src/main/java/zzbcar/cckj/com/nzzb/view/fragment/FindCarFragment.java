package zzbcar.cckj.com.nzzb.view.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.FindcarDefaultItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.GridItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.GridPagerAdapter;
import zzbcar.cckj.com.nzzb.adapter.ListItemAdapter;
import zzbcar.cckj.com.nzzb.base.SpaceSize;
import zzbcar.cckj.com.nzzb.base.SpacesItemDecoration;
import zzbcar.cckj.com.nzzb.utils.OkManager;
import zzbcar.cckj.com.nzzb.view.customview.ViewPagerIndicator;

/**
 * Created by Admin on 2017/10/31.
 */

public class FindCarFragment extends BaseFragment {

    //    private GridView gv_findcarfragment_defaut;
//    private GridView gv_findcarfragment_carbrand;
    private OkManager manager = new OkManager();
    private FindcarDefaultItemAdapter adapter;
    private ViewPager vp_grid_items;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {
//        gv_findcarfragment_defaut = view.findViewById(R.id.gv_findcarfragment_defaut);
        vp_grid_items = view.findViewById(R.id.vp_grid_items);
        final GridItemAdapter itemAdapter = new GridItemAdapter(getContext(), null);
        GridView gridView1 = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
        gridView1.setAdapter(itemAdapter);
        GridView gridView2 = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
        gridView2.setAdapter(itemAdapter);
        List<View> views = new ArrayList<>();
        views.add(gridView1);
        views.add(gridView2);
        vp_grid_items.setAdapter(new GridPagerAdapter(views));

        //下部上下滑动布局
        RecyclerView rvListItems = view.findViewById(R.id.rv_list_items);
        rvListItems.setLayoutManager(new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false));
        rvListItems.addItemDecoration(new SpacesItemDecoration(new SpaceSize(10, 10, 10, 10)));
        rvListItems.setNestedScrollingEnabled(false);
        rvListItems.setAdapter(new ListItemAdapter(null, mActivity));

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
    }

//    @Override
//    public void initDatas() {
//
//        //默认汽车
//        manager.asyncJsonByURL(Constant.CAR_DEFAULT_URL, new OkManager.Func1() {
//            @Override
//            public void onResponse(String result) {
//                parseData(result);
//
//            }
//        });
//    }


//    private void parseData(String result) {
//        SearchCarDefaultBean bean = GsonUtil.parseJsonWithGson(result, SearchCarDefaultBean.class);
//        List<SearchCarDefaultBean.DataBean> dataList = bean.getData();
//        gv_findcarfragment_defaut.setAdapter(new FindcarDefaultItemAdapter(dataList,mActivity));
//
//
//
//    }

    @Override
    protected void initListeners() {

    }


}
