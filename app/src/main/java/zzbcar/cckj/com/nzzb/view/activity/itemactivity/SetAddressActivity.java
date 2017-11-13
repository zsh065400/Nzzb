package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.DistanceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/6.
 */

public class SetAddressActivity extends BaseActivity {
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_setaddress)
    EditText etSetaddress;

    private static PoiSearch poiSearch;
    private static List<PoiInfo> poiInfo;
    @BindView(R.id.tv_setaddress_cancel)
    TextView tvSetaddressCancel;
    @BindView(R.id.lv_setaddress)
    ListView lvSetaddress;
    @BindView(R.id.fl_setaddress)
    FrameLayout flSetaddress;
    @BindView(R.id.tv_setaddress_content)
    TextView tvSetaddressContent;
    @BindView(R.id.cd_setaddress)
    CardView cdSetaddress;
    private SearchLocationAdapter searchLocationAdapter;
    private LocationClient mLocationClient;
    private MyBDLocationListener bdLocationListener;
    private double latitude;
    private double longitude;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_address;
    }

    @Override
    protected void initViews() {
        SDKInitializer.initialize(getApplicationContext());
    }

    @Override
    protected void initDatas() {
        initLocation();
        poiSearch = PoiSearch.newInstance();
        poiInfo = new ArrayList<PoiInfo>();
    }

    private void initLocation() {
        mLocationClient = new LocationClient(this.getApplicationContext());
        //声明LocationClient类
        bdLocationListener = new MyBDLocationListener();
        mLocationClient.registerLocationListener(bdLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式 高精度
        option.setCoorType("bd09ll");// 设置返回定位结果是百度经纬度 默认gcj02
        option.setScanSpan(5000);// 设置发起定位请求的时间间隔 单位ms
        option.setIsNeedAddress(true);// 设置定位结果包含地址信息
        option.setNeedDeviceDirect(true);// 设置定位结果包含手机机头 的方向
        option.setIsNeedLocationDescribe(true);
        // 设置定位参数
        mLocationClient.setLocOption(option);
        // 启动定位
        mLocationClient.start();
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        etSetaddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                lvSetaddress.setVisibility(View.VISIBLE);
                flSetaddress.setVisibility(View.GONE);
                searchPoi(etSetaddress.getText().toString());
            }
        });
        cdSetaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLocationClient!=null){
                    mLocationClient.start();
                }else{
                    initLocation();
                }
            }
        });
    }

    private void searchPoi(String s) {
        OnGetPoiSearchResultListener poiSearchResultListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                poiInfo.clear();
                if (poiResult.getAllPoi() != null) {
                    poiInfo.addAll(poiResult.getAllPoi());
                    if (searchLocationAdapter == null) {
                        searchLocationAdapter = new SearchLocationAdapter(poiInfo);
                        lvSetaddress.setAdapter(searchLocationAdapter);
                    } else {
                        searchLocationAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(poiSearchResultListener);
        String city = SPUtils.getString(this, Constant.SP_LAST_LOCATION, "");
        if (TextUtils.isEmpty(city)) {
            city = "杭州";
        }
        poiSearch.searchInCity((new PoiCitySearchOption())
                .city(city)
                .keyword(s));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //listViewAdapter
    class SearchLocationAdapter extends BaseAdapter {
        private List<PoiInfo> data;

        public SearchLocationAdapter(List<PoiInfo> list) {
            this.data = list;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(SetAddressActivity.this).
                        inflate(R.layout.item_serach_info, null);
                viewHolder = new ViewHolder();
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
                viewHolder.tvDistince = (TextView) convertView.findViewById(R.id.tv_distance);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String name = data.get(position).name;
            String temp = etSetaddress.getText().toString();
            SpannableString spannableString = new SpannableString(name);
            int index = name.indexOf(temp);
            if (index != -1)
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#f9e108")), index, temp.length() + index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.tvName.setText(spannableString);
            if(latitude!=0 && longitude!=0){
                LatLng location = data.get(position).location;
                LatLng l1 = new LatLng(latitude, longitude);
                LatLng l2 = new LatLng(location.latitude, location.longitude);
                double distance = DistanceUtil.getDistance(l1,l2);
                viewHolder.tvDistince.setText((int)distance/1000>0?(int)(distance/1000)+"千米":(int)distance+"米");
            }
            viewHolder.tvAddress.setText(data.get(position).address);
            return convertView;
        }

        class ViewHolder {
            TextView tvName;
            TextView tvAddress;
            TextView tvDistince;
        }
    }

    private class MyBDLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            SetAddressActivity.this.runOnUiThread(new Runnable() {


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
                        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 非空判断
                    if (bdLocation != null) {
                        // 根据BDLocation 对象获得经纬度以及详细地址信息
                        latitude = bdLocation.getLatitude();
                        longitude = bdLocation.getLongitude();
                        String address = bdLocation.getAddrStr();
                        String near = bdLocation.getLocationDescribe();

                        tvSetaddressContent.setText(address + near);
                        if (mLocationClient.isStarted()) {
                            // 获得位置之后停止定位
                            mLocationClient.stop();
                        }
                    }

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消监听函数
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(bdLocationListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 取消监听函数
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(bdLocationListener);
        }
    }
}
