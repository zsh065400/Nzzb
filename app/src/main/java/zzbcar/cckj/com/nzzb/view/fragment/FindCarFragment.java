package zzbcar.cckj.com.nzzb.view.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import zzbcar.cckj.com.nzzb.base.PopWindow;
import zzbcar.cckj.com.nzzb.base.SpaceSize;
import zzbcar.cckj.com.nzzb.base.SpacesItemDecoration;
import zzbcar.cckj.com.nzzb.bean.BrandCarBean;
import zzbcar.cckj.com.nzzb.bean.QueryBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.ListUtils;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
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
    @BindView(R.id.ib_sort)
    ImageButton ibSort;
    @BindView(R.id.search_find)
    LinearLayout searchFind;
    private OkManager manager = new OkManager();

    private List<QueryBean.DataBean> carList = new ArrayList<>();
    private List<BrandCarBean.DataBean> brandList = new ArrayList<>();

    @BindView(R.id.vp_grid_items)
    ViewPager vp_grid_items;

    @BindView(R.id.rv_list_items)
    RecyclerView rvListItems;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    /*参数构建*/
    private ParamsBuilder params = new ParamsBuilder();
    private ListItemAdapter itemAdapter;

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
        popWindow = new PopWindow(mActivity);
    }

    /**有
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

    @Override
    public void initDatas() {
        String lastCity = SPUtils.getString(mActivity, SP_LAST_LOCATION, "");
        tvHomeLocalCity.setText(lastCity);
        doCarQuery(params.buildUrl());
        getCarBrandData();
    }

    private PopWindow popWindow;

    /*调用筛选*/
    private void doCarQuery(String url) {
        manager.asyncJsonByURL(url, new OkManager.Func1() {
            @Override
            public void onResponse(String result) {
                final QueryBean queryBean = GsonUtil.parseJsonWithGson(result, QueryBean.class);
                if (queryBean.getErrno() == 0) {
                    final List<QueryBean.DataBean> data = queryBean.getData();
                    System.out.println(result);
                    carList = data;
                    refreshRecycleView(data);
                } else {
                    Log.e(TAG, queryBean.getMessage());
                    asyncShowToast(queryBean.getMessage());
                }
            }
        });
    }

    /*刷新数据*/
    private void refreshRecycleView(final List<QueryBean.DataBean> data) {
        if (data == null || data.size() == 0) {
            Toast.makeText(mActivity, "暂无数据", Toast.LENGTH_SHORT).show();
            return;
        }
        if (itemAdapter == null) {
            itemAdapter = new ListItemAdapter(mActivity, data);
            itemAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    /*跳转详情页面*/
                    final Intent intent = new Intent(mActivity, CarDetailActivity.class);
                    intent.putExtra("carid", carList.get(position).getId());
                    startActivity(intent);
                }
            });
            rvListItems.setAdapter(itemAdapter);
        } else {
            itemAdapter.refresh(data);
        }
    }

    @Override
    protected void initListeners() {
        /*点击排序*/
        ibSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.showPopupWindow(v);
            }
        });
        /*搜索栏*/
        searchFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(SearchActivity.class);
                System.out.println("搜索栏点击");
            }
        });
        /*排序*/
        popWindow.setItemListener(new PopWindow.OnPopItemClickListener()

        {
            @Override
            public void onLocationNearSort() {
                /*依据距离定位*/
//                asyncShowToast("暂未开发");
                String longtiude = SPUtils.getString(mActivity, Constant.SP_LONGITUDE, "");
                String latitude = SPUtils.getString(mActivity, Constant.SP_LATITUDE, "");
                params.setLoc(longtiude + "," + latitude);
                doCarQuery(params.buildUrl());
            }

            @Override
            public void onPriceLowerSort() {
                /*价格排序*/
                params.setOrderBy("1a");
                doCarQuery(params.buildUrl());
            }

            @Override
            public void onShareMoreSort() {
                /*共享最多*/
                params.setOrderBy("3d");
                doCarQuery(params.buildUrl());
            }

            @Override
            public void onNianKuanNewSort() {
                /*年款最新*/
                params.setOrderBy("4d");
                doCarQuery(params.buildUrl());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tvHomeLocalCity.setText(SPUtils.getString(mActivity, SP_LAST_LOCATION, ""));
    }

    private class ParamsBuilder {
        /*品牌*/
        private String brand = "";
        /*车系*/
        private String series = "";
        /*车型*/
        private String model = "";
        /*用车城市ID*/
        private String city = "";
        /*品牌,车系,车型,车名模糊搜索*/
        private String text = "";
        /*价格 格式如: 200~500*/
        private String price = "";
        /*使用类型:个人,商务和婚庆 1,2,4,*/
        private String useType = "";
        /*使用时间.格式如: 2017-05-17 08:00~2017-05-17 15:00*/
        private String span = "";
        /*经纬度,格式:经度,纬度*/
        private String loc = "";
        /*座位数,取值2,3,4,5,6,7.见参数说明*/
        private String seatNum = "";
        /*	排序条件(非筛选)
        * 参数由排序字符连接而成,每两个字符为一组,组数根据需求指定
        * 第一字符(排序字段)取值为
        * 1=price     2=create_time(记录创建时间,即新车旧车)
        * 3=接单数量   4=年款
        * 5=距离(loc参数必填)
        * 第二字符,排序规则 a.升序(从小到大)  d.降序
        * 示例:1a2d表示返回记录按价格从低到高,创建时间从迟到早排列(2排序规则)
        * 4d1a3d表示按年款从新到旧,价格从低到高,接单数量从高到低的顺序排列(3组排序规则)
        * */
        private String orderBy = "";
        /*分页页码,必须参数*/
        private String pageNum = "0";
        /*分页条数,必须参数*/
        private String pageSize = "20";

        public ParamsBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public ParamsBuilder setSeries(String series) {
            this.series = series;
            return this;
        }

        public ParamsBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public ParamsBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public ParamsBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public ParamsBuilder setPrice(String price) {
            this.price = price;
            return this;
        }

        public ParamsBuilder setUseType(String useType) {
            this.useType = useType;
            return this;
        }

        public ParamsBuilder setSpan(String span) {
            this.span = span;
            return this;
        }

        public ParamsBuilder setLoc(String loc) {
            this.loc = loc;
            return this;
        }

        public ParamsBuilder setSeatNum(String seatNum) {
            this.seatNum = seatNum;
            return this;
        }

        public ParamsBuilder setOrderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public ParamsBuilder setPageNum(String pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public ParamsBuilder setPageSize(String pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public void reset() {
            brand = "";
            series = "";
            model = "";
            city = "";
            text = "";
            price = "";
            useType = "";
            span = "";
            loc = "";
            seatNum = "";
            orderBy = "";
            pageNum = "0";
            pageSize = "20";
        }

        public String buildUrl() {
            return OkHttpUtil.obtainGetUrl(Constant.API_CAR_QUERY,
                    "brand", brand,
                    "series", series,
                    "model", model,
                    "city", city,
                    "text", text,
                    "price", price,
                    "useType", useType,
                    "span", span,
                    "loc", loc,
                    "seatNum", seatNum,
                    "orderBy", orderBy,
                    "pageNum", pageNum,
                    "pageSize", pageSize);
        }
    }
}
