package zzbcar.cckj.com.nzzb.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.jaygoo.widget.RangeSeekBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.CarBrandAdapter;
import zzbcar.cckj.com.nzzb.adapter.CarSeriesItemAdapter;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.adapter.main.CarListAdapter;
import zzbcar.cckj.com.nzzb.adapter.main.CarQueryAdapter;
import zzbcar.cckj.com.nzzb.base.PopWindow;
import zzbcar.cckj.com.nzzb.bean.CarBean;
import zzbcar.cckj.com.nzzb.bean.CarSeriesBean;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.bean.QueryBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.LogUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.OkManager;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.CarDetailActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.SelectTimeActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.SetAddressActivity;
import zzbcar.cckj.com.nzzb.widget.RadioGroupEx;

import static zzbcar.cckj.com.nzzb.R.id.tv_start_time;

public class RentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_brand)
    LinearLayout ll_brand;
    @BindView(R.id.ll_price)
    LinearLayout ll_price;
    @BindView(R.id.ll_type)
    LinearLayout ll_type;
    @BindView(tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    private OkManager okManager = new OkManager();

    @BindView(R.id.ll_whole_brand)
    LinearLayout ll_whole_brand;
    @BindView(R.id.ll_type_pick)
    LinearLayout ll_type_pick;
    @BindView(R.id.ll_time_pick)
    LinearLayout ll_time_pick;

    @BindView(R.id.ll_self_activity_selelct_time)
    LinearLayout ll_self_activity_selelct_time;

    @BindView(R.id.lv_rentcar_brand)
    ListView lv_car_brand;
    @BindView(R.id.lv_carrent_series)
    ListView lv_car_series;

    @BindView(R.id.tv_rent_address)
    TextView tv_rent_address;

    @BindView(R.id.iv_rentcar_sorder)
    ImageView iv_rentcar_sorder;

    @BindView(R.id.rg_transmission)
    RadioGroupEx rgTransmission;
    @BindView(R.id.rg_seats)
    RadioGroupEx rgSeats;

    @BindView(R.id.rv_car_query)
    XRecyclerView rvCarQuery;

    @BindView(R.id.rsb_prices)
    RangeSeekBar rsbPrices;

    @BindView(R.id.tv_price_commit)
    View tvPriceCommit;

    @BindView(R.id.tv_type_commit)
    View tvSeatsCommit;

    /*参数构建*/
    private ParamsBuilder params = new ParamsBuilder();

    /*第一组单选按钮*/
    private int[] rbTransmissionIds = {R.id.rb_auto, R.id.rb_manual, R.id.rb_trans_null};
    /*第二组单选按钮*/
    private int[] rbSeatsIds = {R.id.rb_two, R.id.rb_four, R.id.rb_five, R.id.rb_six,
            R.id.rb_seven_or_higher, R.id.rb_seat_null};
    /*座位数*/
    private String[] seats = {"2", "4", "5", "6", "7", ""};

    /*品牌数据*/
    private List<CarBean.DataBean> brandData;

    public boolean IsBrandShow = true;
    private static final int CHOOSE_ADDRESS = 100;


    private CarSeriesItemAdapter carSeriesAdapter;
    private CarBrandAdapter carBrandAdapter;

    private CarListAdapter carAdapter;
    private CarQueryAdapter queryAdapter;
    private List<MainPageBean.DataBean.CarListBean> carListDatas;
    private PopWindow popWindow;
    private boolean QucheTime = true;
    private static final int SELECTIME = 200;
    private String time;
    private TimePickerView pickerView;
    private boolean isCommit = false;
    private String chooseDate = "";
    private String chooseTime = "";
    /*0为start，1为stop*/
    private int chooseType = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rent;
    }

    @Override
    protected void initViews() {
        popWindow = new PopWindow(this);

        rvCarQuery.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvCarQuery.setLoadingMoreEnabled(false);
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initListeners() {
        ll_brand.setOnClickListener(this);
        ll_time_pick.setOnClickListener(this);
        ll_type.setOnClickListener(this);
        ll_type_pick.setOnClickListener(this);
        ll_price.setOnClickListener(this);
        ll_whole_brand.setOnClickListener(this);
        ll_self_activity_selelct_time.setOnClickListener(this);
        iv_rentcar_sorder.setOnClickListener(this);
        tv_rent_address.setOnClickListener(this);

        tvSeatsCommit.setOnClickListener(this);
        tvPriceCommit.setOnClickListener(this);
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        rvCarQuery.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                params.reset();
                doCarQuery(params.buildUrl());
            }

            @Override
            public void onLoadMore() {
                return;
            }
        });

        rgTransmission.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                /*暂时用不到*/

//                for (int i=0;i<rbTransmissionIds.length;i++){
//                    if(checkedId==rbTransmissionIds[i]){
//
//                    }
//                }
                    }
                });

        rgSeats.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < rbSeatsIds.length; i++) {
                    if (checkedId == rbSeatsIds[i]) {
                        params.setSeatNum(String.valueOf(seats[i]));
                    }
                }
            }
        });

        popWindow.setItemListener(new PopWindow.OnPopItemClickListener()

        {
            @Override
            public void onLocationNearSort() {
                /*依据距离定位*/
//                asyncShowToast("暂未开发");
                String longtiude = SPUtils.getString(mContext, Constant.SP_LONGITUDE, "");
                String latitude = SPUtils.getString(mContext, Constant.SP_LATITUDE, "");
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

        setBackButon(R.id.iv_back);
    }

    @Override
    protected void initDatas() {


        initTimePicker();
        final Intent intent = getIntent();
        carListDatas = (List<MainPageBean.DataBean.CarListBean>) intent.getSerializableExtra("carlist");
        final int useType = intent.getIntExtra("useType", 1);
        params.setUseType(String.valueOf(useType));

        initRecycleView(carListDatas);
    }

    /**
     * 初始化车辆数据
     */
    private void initRecycleView(final List<MainPageBean.DataBean.CarListBean> extras) {
        if (carAdapter == null) {
            if (extras == null) return;
            carAdapter = new CarListAdapter(mContext, extras);
            carAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                /*跳转详情页面*/
                    toCarDetail(extras.get(position - 1).getId());
                }
            });
            rvCarQuery.setAdapter(carAdapter);
        } else {
            carAdapter.refresh(extras);
            rvCarQuery.refreshComplete();
        }
    }

    /*调用筛选*/
    private void doCarQuery(String url) {
        okManager.asyncJsonByURL(url, new OkManager.Func1() {
            @Override
            public void onResponse(String result) {
                final QueryBean queryBean = GsonUtil.parseJsonWithGson(result, QueryBean.class);
                if (queryBean.getErrno() == 0) {
                    final List<QueryBean.DataBean> data = queryBean.getData();
                    System.out.println(result);
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
            Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ll_whole_brand.setVisibility(View.GONE);
        }
        if (queryAdapter == null) {
            queryAdapter = new CarQueryAdapter(mContext, data);
            queryAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    /*跳转详情页面*/
                    toCarDetail
                            (data.get(position - 1).getId());
                }
            });
            rvCarQuery.setAdapter(queryAdapter);
        } else {

            queryAdapter.refresh(data);
        }
        rvCarQuery.refreshComplete();
    }

    /**
     * 解析车系
     *
     * @param result
     */
    private void parseSeriesData(String result) {
        LogUtil.e(result + "车系数据");
        final CarSeriesBean bean = GsonUtil.parseJsonWithGson(result, CarSeriesBean.class);
        final List<CarSeriesBean.DataBean> data = bean.getData();
        if (carSeriesAdapter == null) {
            carSeriesAdapter = new CarSeriesItemAdapter(this, data);
            lv_car_series.setAdapter(carSeriesAdapter);
            lv_car_series.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    /*请求数据,刷新车辆信息,每次点击都刷新*/
                    params.setSeries(String.valueOf(data.get(position).getId()));
                    LogUtil.e(params.buildUrl() + "请求url");
                    doCarQuery(params.buildUrl());
                    ll_whole_brand.performClick();


                }
            });
        } else {

            carSeriesAdapter.reset(data);
        }
    }

    /**
     * 解析车品牌
     *
     * @param result
     */

    private void parseBrandData(String result) {
        CarBean bean = GsonUtil.parseJsonWithGson(result, CarBean.class);
        brandData = bean.getData();
        brandData.remove(0);
        carBrandAdapter = new CarBrandAdapter(brandData, this);
        lv_car_brand.setAdapter(carBrandAdapter);
        lv_car_brand.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                params.setBrand(String.valueOf(brandData.get(i).getId()));
                okManager.asyncJsonByURL(Constant.API_CAR_SERIES + "?brandId=" + brandData.get(i).getId(), new OkManager.Func1() {
                    @Override
                    public void onResponse(String result) {
                        parseSeriesData(result);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            /*选择品牌车型*/
            case R.id.ll_brand:
                if (ll_time_pick.getVisibility() == View.VISIBLE || ll_type_pick.getVisibility() == View.VISIBLE) {
                    IsBrandShow = true;
                }
                if (IsBrandShow) {
                    IsBrandShow = !IsBrandShow;
                    ll_whole_brand.setVisibility(View.VISIBLE);
                    ll_time_pick.setVisibility(View.INVISIBLE);
                    ll_type_pick.setVisibility(View.INVISIBLE);
//                    Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                    if (carBrandAdapter == null) {
                        okManager = new OkManager();
                        okManager.asyncJsonByURL(Constant.API_CAR_BRAND, new OkManager.Func1() {
                            @Override
                            public void onResponse(String result) {
                                parseBrandData(result);
                            }
                        });
                    }
                } else {
                    ll_whole_brand.setVisibility(View.INVISIBLE);
                    IsBrandShow = !IsBrandShow;
                }

                break;

            /*右上角排序选择*/
            case R.id.iv_rentcar_sorder:

                popWindow.showPopupWindow(view);
                break;
            /*价格*/
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
            /*类型*/
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
            case R.id.tv_rent_address:
                intent = new Intent(this, SetAddressActivity.class);
                intent.putExtra("type", SetAddressActivity.GET_CAR);
                //startActivity(intent);
                startActivityForResult(intent, CHOOSE_ADDRESS);
                break;
            /*选择时间*/
            case R.id.ll_self_activity_selelct_time:
                //intent = new Intent(this, );
                //startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putString("type", SelectTimeActivity.RENT_KEY);
                bundle.putString("getAddress", tv_rent_address.getText().toString());
                toActivityWithResult(SelectTimeActivity.class, bundle, 0);
                break;

            /*价格选择完毕*/
            case R.id.tv_price_commit:
                float min = rsbPrices.getMin();
                float max = rsbPrices.getMax();
                if (max == 10000){
                    max = 200000000f;
                }
                params.setPrice(min + "~" + max);
                doCarQuery(params.buildUrl());
                ll_price.performClick();
                break;
            /*类型选择完毕*/
            case R.id.tv_type_commit:
                doCarQuery(params.buildUrl());
                ll_type_pick.performClick();
                ll_type_pick.setVisibility(View.GONE);
                break;

            case tv_start_time:
                pickerView.show(view);
                break;
            case R.id.tv_end_time:
                pickerView.show(view);
                break;
        }

    }

    private String startTime = "";
    private String endTime = "";

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        final Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 23);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 11, 28);
        //时间选择器
        //选中事件回调
// 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
/*btn_Time.setText(getTime(date));*///年月日时分秒 的显示与否，不设置则默认全部显示
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
        pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                TextView tv = (TextView) v;
                final String time = getTime(date);
                tv.setText(time);
                if (v.getId() == R.id.tv_start_time) {
                    startTime = time;
                } else if (v.getId() == R.id.tv_end_time) {
                    endTime = time;
                }
                if (!startTime.equals("") && !endTime.equals("")) {
                    /*判断日期是否合规*/
                    if (daysBetween(startTime, endTime) >= 0) {
                        params.setSpan(startTime + "~" + endTime);
                        doCarQuery(params.buildUrl());
                    } else {
                        asyncShowToast("还车日期在取车日期前");
                    }
                }

                if (tvStartTime.getText().equals("取车时间")) {
                    Toast.makeText(mContext, "请设置取车时间", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (tvEndTime.getText().equals("还车时间")) {
                    Toast.makeText(mContext, "请设置还车时间", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .setSubmitColor(0xff24AD9D)
                .setCancelColor(0xff24AD9D)
                .setOutSideCancelable(false)
                .isDialog(true)
                .build();
    }


    private String getTime(Date date) {//可根据需要自行截取数据显示
        //"YYYY-MM-DD HH:MM:SS"        "yyyy-MM-dd"
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_ADDRESS:
                if (resultCode == RESULT_OK) {
                    String address = data.getStringExtra("address");
                    tv_rent_address.setText(address);
                }
                break;
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    tvStartTime.setText(extras.getString("getTime"));
                    tvEndTime.setText(extras.getString("backTime"));
                    String address = data.getStringExtra("getAddress");
                    tv_rent_address.setText(address);
                }
                break;

            case SELECTIME:
                if (resultCode == RESULT_OK) {
                    if (requestCode == 0) {

                        tvStartTime.setText(time);
                    } else if (requestCode == 1) {
                        tvEndTime.setText(time);
                    }
                }
        }

    }

    private void toCarDetail(int id) {
        final Intent intent = new Intent(mContext, CarDetailActivity.class);
        intent.putExtra("carid", id);
        intent.putExtra("type", CarDetailActivity.RENT_KEY);
        intent.putExtra("getAddress", tv_rent_address.getText().toString());
        startActivity(intent);
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

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }


    /**
     * 计算是date1是否在date2的后面
     */
    private boolean checkDateAfter(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date1).after(format.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 计算两个日期之间相差的小时数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 60 * 60);//改动此处计算相差周期

            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

}
