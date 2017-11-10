package zzbcar.cckj.com.nzzb.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.CarBrandAdapter;
import zzbcar.cckj.com.nzzb.adapter.CarSeriesItemAdapter;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.base.PopWindow;
import zzbcar.cckj.com.nzzb.bean.CarBean;
import zzbcar.cckj.com.nzzb.bean.CarSeriesBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkManager;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.SelecTimeActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.SetAddressActivity;

public class RentActivity extends BaseActivity implements View.OnClickListener {


    private OkManager okManager;
    private LinearLayout ll_brand;
    private LinearLayout ll_whole_brand;
    private LinearLayout ll_price;
    private LinearLayout ll_type_pick;
    private LinearLayout ll_type;
    private LinearLayout ll_address;
    private LinearLayout ll_self_activity_selelct_time;
    private LinearLayout ll_time_pick;
    private ListView lv_rentcar_brand;
    private List<CarBean.DataBean> data;
    private ImageView iv_rentcar_sorder;
    private ListView lv_carrent_series;
    public boolean IsBrandShow = true;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_rent;
    }

    @Override
    protected void initView() {
        ll_brand = (LinearLayout) findViewById(R.id.ll_brand);
        ll_whole_brand = (LinearLayout) findViewById(R.id.ll_whole_brand);
        ll_price = (LinearLayout) findViewById(R.id.ll_price);
        ll_type_pick = (LinearLayout) findViewById(R.id.ll_type_pick);
        ll_type = (LinearLayout) findViewById(R.id.ll_type);
        ll_address = (LinearLayout) findViewById(R.id.ll_address);
        ll_time_pick = (LinearLayout) findViewById(R.id.ll_time_pick);
        ll_self_activity_selelct_time = (LinearLayout) findViewById(R.id.ll_self_activity_selelct_time);

        lv_rentcar_brand = (ListView) findViewById(R.id.lv_rentcar_brand);

        iv_rentcar_sorder = (ImageView) findViewById(R.id.iv_rentcar_sorder);
        lv_carrent_series = (ListView) findViewById(R.id.lv_carrent_series);


    }

    @Override
    protected void initData() {
        ll_brand.setOnClickListener(this);
        ll_time_pick.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        ll_type.setOnClickListener(this);
        ll_type_pick.setOnClickListener(this);
        ll_price.setOnClickListener(this);
        ll_whole_brand.setOnClickListener(this);
        ll_self_activity_selelct_time.setOnClickListener(this);


        iv_rentcar_sorder.setOnClickListener(this);
        lv_rentcar_brand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(RentActivity.this, "点击了第一个条目", Toast.LENGTH_SHORT).show();
                        okManager.asyncJsonByURL(Constant.CAR_SERIES_URL, new OkManager.Func1() {
                            @Override
                            public void onResponse(String result) {
                                parseSeriesData(result);
                            }
                        });
                        break;
                }
            }
        });

    }

    /**
     * 解析车系
     *
     * @param result
     */
    private void parseSeriesData(String result) {
        CarSeriesBean bean = GsonUtil.parseJsonWithGson(result, CarSeriesBean.class);
        List<CarSeriesBean.DataBean> data = bean.getData();
        lv_carrent_series.setAdapter(new CarSeriesItemAdapter(this, data));

    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.ll_brand:
                if(ll_time_pick.getVisibility()==View.VISIBLE||ll_type_pick.getVisibility()==View.VISIBLE){
                    IsBrandShow=true;
                }
                if(IsBrandShow){
                    IsBrandShow =!IsBrandShow;
                    ll_whole_brand.setVisibility(View.VISIBLE);
                    ll_time_pick.setVisibility(View.INVISIBLE);
                    ll_type_pick.setVisibility(View.INVISIBLE);

                    Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                    okManager = new OkManager();
                    okManager.asyncJsonByURL(Constant.CAR_BRAND_URL, new OkManager.Func1() {
                        @Override
                        public void onResponse(String result) {
                            parseBrandData(result);


                        }
                    });
                }else {
                    ll_whole_brand.setVisibility(View.INVISIBLE);
                    IsBrandShow =!IsBrandShow;
                }

                break;

            case R.id.iv_rentcar_sorder:
                PopWindow popWindow = new PopWindow(this);
                popWindow.showPopupWindow(findViewById(R.id.iv_rentcar_sorder));

                break;
            case R.id.ll_whole_brand:

                break;
            case R.id.ll_price:
                if (ll_whole_brand.getVisibility() == View.VISIBLE || ll_type_pick.getVisibility() == View.VISIBLE) {
                    IsBrandShow = true;
                }
                if (IsBrandShow) {
                    IsBrandShow = !IsBrandShow;
                    ll_whole_brand.setVisibility(View.INVISIBLE);
                    ll_time_pick.setVisibility(View.VISIBLE);
                    ll_type_pick.setVisibility(View.INVISIBLE);
                } else {
                    ll_time_pick.setVisibility(View.INVISIBLE);
                    IsBrandShow = !IsBrandShow;
                }
                break;

            case R.id.ll_type:
                if (ll_whole_brand.getVisibility() == View.VISIBLE || ll_time_pick.getVisibility() == View.VISIBLE) {
                    IsBrandShow = true;
                }
                if (IsBrandShow) {
                    IsBrandShow = !IsBrandShow;
                    ll_whole_brand.setVisibility(View.INVISIBLE);
                    ll_time_pick.setVisibility(View.INVISIBLE);
                    ll_type_pick.setVisibility(View.VISIBLE);

                } else {
                    ll_type_pick.setVisibility(View.INVISIBLE);
                    IsBrandShow = !IsBrandShow;
                }
                break;
            case R.id.ll_address:
                intent  = new Intent(MyApplication.getContext(), SetAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_type_pick:

                break;
            case R.id.ll_self_activity_selelct_time:
                intent=new Intent(this, SelecTimeActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * 解析车品牌
     *
     * @param result
     */

    private void parseBrandData(String result) {
        CarBean bean = GsonUtil.parseJsonWithGson(result, CarBean.class);
        data = bean.getData();
        lv_rentcar_brand.setAdapter(new CarBrandAdapter(data, this));


    }


}
