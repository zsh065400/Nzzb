package zzbcar.cckj.com.nzzb.view.fragment;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sunfusheng.marqueeview.MarqueeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.GridPagerAdapter;
import zzbcar.cckj.com.nzzb.adapter.MyGoodExperenceAdapter;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.adapter.main.CarTypeAdapter;
import zzbcar.cckj.com.nzzb.adapter.main.GridItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.main.NewCarAdapter;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.ListUtils;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.ScaleTransformer;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.MainActivity;
import zzbcar.cckj.com.nzzb.view.activity.RentActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.BrandCarActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.CarDetailActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.CarListActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.HomeMessageActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.LocationListActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.MarriedActivity;
import zzbcar.cckj.com.nzzb.view.customview.Gradient;


/**
 * Created by Admin on 2017/10/31.
 */

@RuntimePermissions
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_location)
    TextView tvLocation;

    @BindView(R.id.iv_service)
    ImageView ivService;

    @BindView(R.id.tv_self)
    TextView tvSelf;

    @BindView(R.id.tv_business)
    TextView tvBusiness;

    @BindView(R.id.tv_wedding)
    TextView tvWedding;

    @BindView(R.id.linear_notify)
    View linearDetail;

    @BindView(R.id.tv_chaozhi_all)
    TextView tvChaozhiAll;

    @BindView(R.id.tv_xinxian_all)
    TextView tvXinxianAll;

    @BindView(R.id.tv_chexing_all)
    TextView tvChexingAll;

    @BindView(R.id.vp_chaozhi)
    ViewPager vpChaozhi;

    @BindView(R.id.vp_chexing_list)
    ViewPager vpChexingList;

    @BindView(R.id.rv_xinxian)
    RecyclerView rvXinxian;

    @BindView(R.id.rv_chexing)
    RecyclerView rvChexing;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;

    @BindView(R.id.gradient)
    Gradient gradient;
    @BindView(R.id.tv_home_clicktosee_detail)
    TextView tvHomeClicktoseeDetail;
    Unbinder unbinder;

    @BindView(R.id.scroll_home)
    NestedScrollView scrollView;

    private List<MainPageBean.DataBean.ActivityBean> activityDatas;
    private List<MainPageBean.DataBean.NewCarListBean> newCarDatas;
    private List<MainPageBean.DataBean.BrandBean> brandDatas;
    private List<MainPageBean.DataBean.CarListBean> carDatas;
    private List<MainPageBean.DataBean.MarqueeBean> marqueeDatas;
    private LocationClient mLocationClient;
    private MyBDLocationListener bdLocationListener;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private Intent callIntent;

//    private void initMarquee(List<MainPageBean.DataBean.MarqueeBean> marqueeDatas) {
//        List<String> marqueeText = new ArrayList<>();
//        List<ImageView> marqueeImage = new ArrayList<>();
//         for (MainPageBean.DataBean.MarqueeBean bean : marqueeDatas) {
//            marqueeText.add(bean.getTitle());
//            ImageView imageView = new ImageView(mActivity);
////            Picasso.with(mActivity).load(bean.getPicUrl())
////                    .placeholder(R.mipmap.ic_launcher)
////                    .error(R.mipmap.ic_launcher)
////                    .fit()
////                    .into(imageView);
//            GlideApp
//                    .with(mActivity)
//                    .load(bean.getPicUrl())
//                    .centerCrop()
//                    .placeholder(R.mipmap.ic_launcher)
//                    .error(R.mipmap.ic_launcher)
//                    .into(imageView);
//            marqueeImage.add(imageView);
//        }
//        marqueeView.startWithList(marqueeText);
//        gradient.setImageViews(marqueeImage);
//    }

    /**
     * 汽车类型
     *
     * @param brandDatas
     */
    private void initCarTypeList(List<MainPageBean.DataBean.BrandBean> brandDatas) {
        List<List<MainPageBean.DataBean.BrandBean>> split = ListUtils.split(brandDatas, 5);
        List<View> gridViews = new ArrayList<>();
        for (final List<MainPageBean.DataBean.BrandBean> dataBeans : split) {
            GridItemAdapter itemAdapter = new GridItemAdapter(mActivity, dataBeans);
            GridView gridView = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
            gridView.setAdapter(itemAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Intent intent = new Intent(mActivity, BrandCarActivity.class);
                    final MainPageBean.DataBean.BrandBean bean = dataBeans.get(position);
                    intent.putExtra("brandId", String.valueOf(bean.getId()));
                    intent.putExtra("brandName", String.valueOf(bean.getName()));
                    startActivity(intent);
                }
            });
            gridViews.add(gridView);
        }
        vpChexingList.setAdapter(new GridPagerAdapter(gridViews));
        /*同步指示器*/
        indicator.setViewPager(vpChexingList);
    }

    /**
     * 车型专区
     *
     * @param carDatas
     */
    private void initCarType(final List<MainPageBean.DataBean.CarListBean> carDatas) {
        rvChexing.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        final CarTypeAdapter adapter = new CarTypeAdapter(mActivity, carDatas);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                /*跳转详情页面*/
                final Intent intent = new Intent(mActivity, CarDetailActivity.class);
                intent.putExtra("carid", carDatas.get(position).getId());
                startActivity(intent);
            }
        });
        rvChexing.setAdapter(adapter);
    }

    /**
     * 新鲜车型
     *
     * @param newCarDatas
     */
    private void initFreshCarType(final List<MainPageBean.DataBean.NewCarListBean> newCarDatas) {
        rvXinxian.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        final NewCarAdapter adapter = new NewCarAdapter(mActivity, newCarDatas);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                /*跳转详情页面*/
                final Intent intent = new Intent(mActivity, CarDetailActivity.class);
                intent.putExtra("carid", newCarDatas.get(position).getId());
                startActivity(intent);
            }
        });
        rvXinxian.setAdapter(adapter);
    }

    /**
     * 超值体验
     *
     * @param activityDatas
     */
    private void initGoodExperence(final List<MainPageBean.DataBean.ActivityBean> activityDatas) {
        MyGoodExperenceAdapter myGoodExperenceAdapter = new MyGoodExperenceAdapter(mActivity, activityDatas);
        myGoodExperenceAdapter.setItemClickListener(new MyGoodExperenceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                /*跳转详情页面*/
                final Intent intent = new Intent(mActivity, CarDetailActivity.class);
                intent.putExtra("carid", activityDatas.get(position).getId());
                startActivity(intent);
            }
        });
        vpChaozhi.setAdapter(myGoodExperenceAdapter);
        vpChaozhi.setPageTransformer(false, new ScaleTransformer());
        vpChaozhi.setOffscreenPageLimit(Integer.MAX_VALUE);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initDatas() {

        OkGo.<String>get(Constant.API_MAIN_PAGE).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                /*此处应加判断，不应直接使用*/
                System.out.println(response.body());
                final MainPageBean pageBean = GsonUtil.parseJsonWithGson(response.body(), MainPageBean.class);
                final MainPageBean.DataBean data = pageBean.getData();
                marqueeDatas = data.getMarquee();
                /*超值*/
                activityDatas = data.getActivity();
                /*新鲜*/
                newCarDatas = data.getNewCarList();
                /*品牌*/
                brandDatas = data.getBrand();
                /*车辆*/
                carDatas = data.getCarList();
                initGoodExperence(activityDatas);
                initFreshCarType(newCarDatas);
                initCarTypeList(brandDatas);
                initCarType(carDatas);




//                initMarquee(marqueeDatas);

                initMarquee();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.e(TAG, ": " + response.getException().getLocalizedMessage());
            }
        });
        initLocation();
    }




    private void initMarquee() {
        //创建imageview
        ImageView imageView = new ImageView(mActivity);
        imageView.setImageResource(R.mipmap.image1);
        ImageView imageView2 = new ImageView(mActivity);
        imageView2.setImageResource(R.mipmap.image2);
        ImageView imageView3 = new ImageView(mActivity);
        imageView3.setImageResource(R.mipmap.image3);
        ImageView imageView4 = new ImageView(mActivity);
        imageView4.setImageResource(R.mipmap.image4);
        List<ImageView> list = new ArrayList<>();
        list.add(imageView);
        list.add(imageView2);
        list.add(imageView3);
        list.add(imageView4);
        //设置图片即可
        gradient.setImageViews(list);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initLocation() {
        HomeFragmentPermissionsDispatcher.showLocationWithPermissionCheck(this);
    }

    @Override
    public void initViews(View view) {
        StatusBarUtil.setViewTopPadding(mActivity, view, R.id.top_bar);
        final int height = topBar.getHeight();
        view.setMinimumHeight(height);
    }


    @Override
    protected void initListeners() {
        tvSelf.setOnClickListener(this);
        tvBusiness.setOnClickListener(this);
        tvWedding.setOnClickListener(this);
        tvXinxianAll.setOnClickListener(this);
        tvChaozhiAll.setOnClickListener(this);
        tvChexingAll.setOnClickListener(this);
        tvLocation.setOnClickListener(this);

        tvHomeClicktoseeDetail.setOnClickListener(this);
        //拨打客服电话
        ivService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(mActivity);
                alertDialog = builder.setMessage("13295815771")
                        .setTitle("要拨打电话给客服么?")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                callService();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        }).create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);

            }
        });






        /*滑动改变颜色*/
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                /*以图片为基准，超过图片高度则固定颜色*/
                if (scrollY >= gradient.getTop() + gradient.getMeasuredHeight()) {
                    topBar.setBackgroundColor(Color.rgb(10, 27, 43));
                    /*其余情况动态计算百分比改变颜色*/
                } else if (scrollY >= 0) {
                    //计算透明度，滑动到的距离（即当前滑动坐标）/图片高度（底部坐标）
                    float persent = scrollY * 1f / (gradient.getTop() + gradient.getMeasuredHeight());
                    //255==1，即不透明，计算动态透明度
                    int alpha = (int) (255 * persent);
                    //计算颜色值，将16进制颜色值转换为rgb颜色后填入
                    int color = Color.argb(alpha, 10, 27, 43);
                    //动态设置
                    topBar.setBackgroundColor(color);
                }
            }
        });
    }

    private void toFindFragment() {
        MainActivity mActivity = (MainActivity) this.mActivity;
        mActivity.setViewPager(1);
    }


    @BindView(R.id.top_bar)
    View topBar;

    private void callService() {
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13295815771"));
        startActivity(callIntent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_self:
                intent = new Intent(mActivity, RentActivity.class);
                intent.putExtra("carlist", (Serializable) carDatas);
                intent.putExtra("useType", 1);
                startActivity(intent);
                break;
            case R.id.tv_business:
                intent = new Intent(mActivity, RentActivity.class);
                intent.putExtra("carlist", (Serializable) carDatas);
                intent.putExtra("useType", 2);
                startActivity(intent);
                break;
            case R.id.tv_wedding:
                intent = new Intent(mActivity, MarriedActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_xinxian_all://暂时替代
                intent = new Intent(mActivity, CarListActivity.class);
               intent.putExtra("carlist", (Serializable) carDatas);
                   startActivity(intent);
                break;
            case R.id.tv_chaozhi_all://暂时替代
                intent = new Intent(mActivity, CarListActivity.class);
                intent.putExtra("carlist", (Serializable) carDatas);
                startActivity(intent);
                break;
            case R.id.tv_chexing_all:
                toFindFragment();
                break;
            case R.id.tv_location:
                intent = new Intent(mActivity, LocationListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_home_clicktosee_detail:
                intent = new Intent(mActivity, HomeMessageActivity.class);
                intent = intent.putExtra("marquee", marqueeDatas.get(1));
                startActivity(intent);
                break;

        }


    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE})
    void showLocation() {
        String lastLocation = SPUtils.getString(mActivity, Constant.SP_LAST_LOCATION, "");

        if (!TextUtils.isEmpty(lastLocation)) {
            tvLocation.setText(lastLocation);
        }
        mLocationClient = new LocationClient(mActivity.getApplicationContext());
        //声明LocationClient类
        bdLocationListener = new MyBDLocationListener();
        mLocationClient.registerLocationListener(bdLocationListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        int span = 1000;
        option.setIsNeedAddress(true);
        option.setScanSpan(span);
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HomeFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);

    }

    @OnShowRationale({Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE})
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(mActivity)
                .setMessage("拒绝授权后，程序将无法正常使用，请重试")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //再次执行请求
                        request.proceed();
                    }
                })
                .show();
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE})
    void permissionDenied() {
        Toast.makeText(mActivity, "授权失败，请稍后重试", Toast.LENGTH_SHORT).show();
        mActivity.finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private class MyBDLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int locType = bdLocation.getLocType();
                    String error = "";
                    switch (locType) {
                        case 62:
                        case 63:
                            error = "定位失败，请检查网络";
                            break;
                        case 167:
                            error = "定位失败，请检查权限";
                            break;
                    }
                    if (!TextUtils.isEmpty(error)) {
                        if (mLocationClient != null)
                            mLocationClient.stop();
                        return;
                    }
                    if (!TextUtils.isEmpty(bdLocation.getCity()) && tvLocation != null) {
                        //Todo 判断本地定位和上次定位的不同，切换不同的城市
                        mLocationClient.stop();
                        String city = bdLocation.getCity();
                        city = city.substring(0, city.length() - 1);
                        final String fincity = city;
                        String last = SPUtils.getString(mActivity, Constant.SP_LAST_LOCATION, "");
                        final double latitude = bdLocation.getLatitude();
                        final double longitude = bdLocation.getLongitude();
                        if (TextUtils.isEmpty(last)) {
                            SPUtils.saveString(mActivity, Constant.SP_LAST_LOCATION, fincity);
                            SPUtils.saveString(mActivity, Constant.SP_LATITUDE, latitude + "");
                            SPUtils.saveString(mActivity, Constant.SP_LONGITUDE, longitude + "");
                        }
                        if (!last.equals(city)) {
                            new AlertDialog.Builder(mActivity)
                                    .setMessage("定位到当前城市有变更，是否改变城市")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            tvLocation.setText(fincity);
                                            SPUtils.saveString(mActivity, Constant.SP_LAST_LOCATION, fincity);
                                            SPUtils.saveString(mActivity, Constant.SP_LATITUDE, latitude + "");
                                            SPUtils.saveString(mActivity, Constant.SP_LONGITUDE, longitude + "");
                                        }
                                    })
                                    .setNegativeButton("取消", null)
                                    .show();
                        }

                    }
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(bdLocationListener);
        }
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mLocationClient != null) {
            mLocationClient.stop();
            mLocationClient.unRegisterLocationListener(bdLocationListener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stop();
            mLocationClient.unRegisterLocationListener(bdLocationListener);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String lastLocation = SPUtils.getString(mActivity, Constant.SP_LAST_LOCATION, "");

        if (!TextUtils.isEmpty(lastLocation)) {
            tvLocation.setText(lastLocation);
        }
    }
}
