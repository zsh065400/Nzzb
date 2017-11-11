package zzbcar.cckj.com.nzzb.view.fragment;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.GridPagerAdapter;
import zzbcar.cckj.com.nzzb.adapter.MyGoodExperenceAdapter;
import zzbcar.cckj.com.nzzb.adapter.main.CarTypeAdapter;
import zzbcar.cckj.com.nzzb.adapter.main.CarTypeItemAdapter;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.ScaleTransformer;
import zzbcar.cckj.com.nzzb.view.activity.LoginActivity;
import zzbcar.cckj.com.nzzb.view.activity.RentActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.MarriedActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.PayActivity;

/**
 * Created by Admin on 2017/10/31.
 */

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
    private List<MainPageBean.DataBean.ActivityBean> activityDatas;
    private List<MainPageBean.DataBean.NewCarListBean> newCarDatas;
    private List<MainPageBean.DataBean.BrandBean> brandDatas;
    private List<MainPageBean.DataBean.CarListBean> carDatas;
    private List<MainPageBean.DataBean.MarqueeBean> marqueeDatas;

    /**汽车类型
     * @param brandDatas*/
    private void initCarTypeList(List<MainPageBean.DataBean.BrandBean> brandDatas) {
        final CarTypeItemAdapter itemAdapter = new CarTypeItemAdapter(getContext(), null);
        GridView gridView1 = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
        gridView1.setAdapter(itemAdapter);
        GridView gridView2 = (GridView) mActivity.getLayoutInflater().inflate(R.layout.gridview, null, false);
        gridView2.setAdapter(itemAdapter);
        List<View> views = new ArrayList<>();
        views.add(gridView1);
        views.add(gridView2);
        vpChexingList.setAdapter(new GridPagerAdapter(views));

        indicator.setViewPager(vpChexingList);
    }

    /**车型专区
     * @param carDatas*/
    private void initCarType(List<MainPageBean.DataBean.CarListBean> carDatas) {
        rvChexing.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        final ArrayList datas = new ArrayList();
        datas.add(1);
        datas.add(1);
        datas.add(1);
        datas.add(1);
        rvChexing.setAdapter(new CarTypeAdapter(mActivity, datas));
    }

    /**新鲜车型
     * @param newCarDatas*/
    private void initFreshCarType(List<MainPageBean.DataBean.NewCarListBean> newCarDatas) {
        rvXinxian.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        final ArrayList datas = new ArrayList();
        datas.add(1);
        datas.add(1);
        datas.add(1);
        datas.add(1);
        rvXinxian.setAdapter(new CarTypeAdapter(mActivity, datas));
    }

    /**超值体验
     * @param activityDatas*/
    private void initGoodExperence(List<MainPageBean.DataBean.ActivityBean> activityDatas) {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.p001);
        list.add(R.drawable.p002);
        list.add(R.drawable.p003);
        list.add(R.drawable.p004);
        list.add(R.drawable.p005);
        MyGoodExperenceAdapter myGoodExperenceAdapter = new MyGoodExperenceAdapter(mActivity, list);
        vpChaozhi.setAdapter(myGoodExperenceAdapter);
        vpChaozhi.setPageTransformer(false, new ScaleTransformer());
        vpChaozhi.setOffscreenPageLimit(5);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_new;
    }

    @Override
    public void initDatas() {
        OkGo.<String>get(Constant.API_MAIN_PAGE).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                /*此处应加判断，不应直接使用*/
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
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.e(TAG, ": " + response.getException().getLocalizedMessage());
            }
        });
    }

    @Override
    public void initViews(View view) {
        initGoodExperence(activityDatas);
        initFreshCarType(newCarDatas);
        initCarTypeList(brandDatas);
        initCarType(carDatas);
    }


    @Override
    protected void initListeners() {
        tvSelf.setOnClickListener(this);
        tvBusiness.setOnClickListener(this);
        tvWedding.setOnClickListener(this);
        tvXinxianAll.setOnClickListener(this);
        tvChaozhiAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_self:
                intent = new Intent(getContext(), RentActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_business:
                intent = new Intent(getContext(), RentActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_wedding:
                intent = new Intent(getContext(), MarriedActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_xinxian_all://暂时替代
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_chaozhi_all://暂时替代
                intent = new Intent(getContext(), PayActivity.class);
                startActivity(intent);
                break;
        }
    }
}
