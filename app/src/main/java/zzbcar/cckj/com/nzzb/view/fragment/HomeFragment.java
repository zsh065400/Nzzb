package zzbcar.cckj.com.nzzb.view.fragment;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import com.squareup.picasso.Picasso;
import com.sunfusheng.marqueeview.MarqueeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
import zzbcar.cckj.com.nzzb.view.activity.RentActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.CarDetailActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.CarListActivity;
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

    private List<MainPageBean.DataBean.ActivityBean> activityDatas;
    private List<MainPageBean.DataBean.NewCarListBean> newCarDatas;
    private List<MainPageBean.DataBean.BrandBean> brandDatas;
    private List<MainPageBean.DataBean.CarListBean> carDatas;
    private List<MainPageBean.DataBean.MarqueeBean> marqueeDatas;
    private LocationClient mLocationClient;
    private MyBDLocationListener bdLocationListener;

    private void initMarquee(List<MainPageBean.DataBean.MarqueeBean> marqueeDatas) {
        List<String> marqueeText = new ArrayList<>();
        List<ImageView> marqueeImage = new ArrayList<>();
        for (MainPageBean.DataBean.MarqueeBean bean : marqueeDatas) {
            marqueeText.add(bean.getTitle());
            ImageView imageView = new ImageView(mActivity);
            Picasso.with(mActivity).load(bean.getPicUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .into(imageView);
            marqueeImage.add(imageView);
        }
        marqueeView.startWithList(marqueeText);
        gradient.setImageViews(marqueeImage);
    }

    /**
     * 汽车类型
     *
     * @param brandDatas
     */
    private void initCarTypeList(List<MainPageBean.DataBean.BrandBean> brandDatas) {
        List<List<MainPageBean.DataBean.BrandBean>> split = ListUtils.split(brandDatas, 5);
        List<View> gridViews = new ArrayList<>();
        for (List<MainPageBean.DataBean.BrandBean> dataBeans : split) {
            GridItemAdapter itemAdapter = new GridItemAdapter(mActivity, dataBeans);
            GridView gridView = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
            gridView.setAdapter(itemAdapter);
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
        vpChaozhi.setOffscreenPageLimit(5);
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
                initMarquee(marqueeDatas);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.e(TAG, ": " + response.getException().getLocalizedMessage());
            }
        });
        initLocation();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initLocation() {
        HomeFragmentPermissionsDispatcher.showLocationWithPermissionCheck(this);
    }

    @Override
    public void initViews(View view) {

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
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_self:
                intent = new Intent(mActivity, RentActivity.class);
                intent.putExtra("carlist", (Serializable) carDatas);
                intent.putExtra("useType", 1);
                break;
            case R.id.tv_business:
                intent = new Intent(mActivity, RentActivity.class);
                intent.putExtra("carlist", (Serializable) carDatas);
                // TODO: 2017/11/15 商务用车为2
                intent.putExtra("useType", 1);
                break;
            case R.id.tv_wedding:
                intent = new Intent(mActivity, MarriedActivity.class);
                break;

            case R.id.tv_xinxian_all://暂时替代
//                intent = new Intent(mActivity, LoginActivity.class);
//                break;
            case R.id.tv_chaozhi_all://暂时替代
//                intent = new Intent(mActivity, PayActivity.class);
//                break;
            case R.id.tv_chexing_all:
                intent = new Intent(mActivity, CarListActivity.class);
                intent.putExtra("carlist", (Serializable) carDatas);
                break;
            case R.id.tv_location:
                intent = new Intent(mActivity, LocationListActivity.class);
                break;
        }
        startActivity(intent);
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
                        if (!last.equals(city)) {
                            new AlertDialog.Builder(mActivity)
                                    .setMessage("定位到当前城市有变更，是否改变城市")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            tvLocation.setText(fincity);
                                            SPUtils.saveString(mActivity, Constant.SP_LAST_LOCATION, fincity);
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
