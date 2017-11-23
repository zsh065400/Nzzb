package zzbcar.cckj.com.nzzb.view.activity.itemactivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.VpSelecTimeAdapter;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.CarDetailBean;
import zzbcar.cckj.com.nzzb.bean.MonthPriceBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.customview.CommonCalendarView;
import zzbcar.cckj.com.nzzb.widget.NoScrollViewPager;

/**
 * Created by Admin on 2017/11/4.
 */

public class SelecTimeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.vp_select_time)
    NoScrollViewPager vpSelectTime;
    @BindView(R.id.tv_sure_send_car)
    TextView tvSureSendCar;
    @BindView(R.id.tv_select_getAddress)
    TextView tvSelectGetAddress;
    private TextView tv_get_car_time;
    private TextView tv_back_car_time;
    private ImageView iv_swicth;
    private ImageView red_point;
    private LinearLayout ll_get_car;
    private LinearLayout ll_back_car;
    private CommonCalendarView calendarView;
    private Map<String, List> mYearMonthMap = new HashMap<>();
    private TimePickerView pvCustomTime;
    private String chooseDate = "";
    private String chooseTime = "";
    private TextView tv_picker_time;
    private TextView tv_picker_date;
    private VpSelecTimeAdapter vpSelecTimeAdapter;
    private boolean isCommit = false;
    public static final String RENT_KEY = "rentActivty";//来自于RenActivity的跳转
    public static final String DETAIL_KEY = "carDetail";//来自于CarDetialActivity页面的跳转
    private String type;
    private CarDetailBean.DataBean cardetail;
    private List<MonthPriceBean.DataBean> monthPriceList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_time;
    }

    @Override
    protected void initViews() {
        initTimePicker();
        tv_get_car_time = (TextView) findViewById(R.id.tv_get_car_time);
        tv_back_car_time = (TextView) findViewById(R.id.tv_back_car_time);
        ll_get_car = (LinearLayout) findViewById(R.id.ll_get_car);
        ll_back_car = (LinearLayout) findViewById(R.id.ll_back_car);
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initListeners() {
        new TitleBuilder(this).setTitleText("选择时间").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ll_get_car.setOnClickListener(this);
        ll_back_car.setOnClickListener(this);
        ll_get_car.setEnabled(false);
        tvSureSendCar.setOnClickListener(this);
        tvSelectGetAddress.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        if (type.equals(DETAIL_KEY)) {
            cardetail = (CarDetailBean.DataBean) bundle.getSerializable("cardetail");
            tvSelectGetAddress.setText(bundle.getString("getAddress"));
            Calendar calendar = Calendar.getInstance();
            getPrice(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,0);
        } else {
            tvSelectGetAddress.setText(bundle.getString("getAddress"));
            setVpData();
        }
        /*this.calendarView.init(new CommonCalendarView.DatePickerController() {
            @Override
            public int getMaxYear() {
                return 2018;
            }

            @Override
            public void onDayOfMonthSelected(int year, int month, int day) {
                Toast.makeText(SelecTimeActivity.this, String.format("%s-%s-%s", year, StringUtils.leftPad(String.valueOf(month), 2, "0"),
                        StringUtils.leftPad(String.valueOf(day), 2, "0")), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDayOfMonthAndDataSelected(int year, int month, int day, List obj) {
                if (obj == null) {
                    return;
                }
                String priceDate = String.format("%s-%s-%s", year,
                        StringUtils.leftPad(month + "", 2, "0"), StringUtils.leftPad(String.valueOf(day), 2, "0"));
                for (int i = 0; i < obj.size(); i++) {
                    final ProductDatePrice datePrice = (ProductDatePrice) obj.get(i);
                    if (datePrice == null) {
                        continue;
                    }
                    if (TextUtils.equals(datePrice.getPriceDate(), priceDate)) {
                        Toast.makeText(SelecTimeActivity.this, datePrice.getPriceDate(), Toast.LENGTH_SHORT).show();
                        final TimePickerDialog timePickerDialog = new TimePickerDialog(SelecTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (!ll_get_car.isEnabled()) {
                                    tv_back_car_time.setText(datePrice.getPriceDate() + " " + hourOfDay + ":" + minute);
                                    ll_back_car.setEnabled(false);
                                    ll_get_car.setEnabled(true);
                                } else {
                                    tv_back_car_time.setText(datePrice.getPriceDate() + " " + hourOfDay + ":" + minute);
                                    ll_back_car.setEnabled(false);
                                    ll_get_car.setEnabled(true);
                                }


                            }
                        }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
                    }
                }
            }

            @Override
            public void showOtherFields(Object obj, View view, int gridItemYear, int gridItemMonth, int gridItemDay) {
                //当你设置了数据源之后，界面渲染会循环调用showOtherFields方法，在该方法中实现同一日期设置界面显示效果。
                ProductDatePrice productDatePrice = (ProductDatePrice) obj;
                if (TextUtils.equals(productDatePrice.getPriceDate(), String.format("%s-%s-%s", gridItemYear,
                        StringUtils.leftPad(gridItemMonth + "", 2, "0"), StringUtils.leftPad(String.valueOf(gridItemDay), 2, "0")))) {
                    CommonCalendarView.GridViewHolder viewHolder = (CommonCalendarView.GridViewHolder) view.getTag();
                    viewHolder.mPriceTv.setText(String.format("¥ %s", productDatePrice.getPrice()));
                    view.setEnabled(true);
                    viewHolder.mTextView.setEnabled(true);
                }
            }

            @Override
            public Map<String, List> getDataSource() {
                return mYearMonthMap;
            }
        });*/

    }

    private void getPrice(final int year, final int month, final int index) {
       //递归获取往后一年的价格。
        if (monthPriceList==null)
            monthPriceList = new ArrayList<>();
        if(index==13){
            setVpData();
            return;
        }
        OkGo.<String>get(Constant.API_PRICE_MONTH)
                .params("year",year+"")
                .params("month",month+"")
                .params("carId",cardetail.getId()+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //return list.addAll(getPrice((month<12?year:year+1),(month<12?month+1:1)));
                        monthPriceList.addAll(GsonUtil.parseJsonWithGson(response.body(), MonthPriceBean.class).getData());
                        getPrice((month<12?year:year+1),(month<12?month+1:1),index+1);
                    }
                });
    }

    public void setVpData() {

        if (vpSelectTime!=null){
            vpSelecTimeAdapter = new VpSelecTimeAdapter(mContext, 365, vpSelectTime, monthPriceList);
            vpSelectTime.setAdapter(vpSelecTimeAdapter);
            vpSelecTimeAdapter.setOnCalendarOrderListener(new VpSelecTimeAdapter.OnCalendarOrderListener() {
                @Override
                public void onOrder(String orderInfo) {
                    //Toast.makeText(mContext, orderInfo, Toast.LENGTH_SHORT).show();
                    chooseDate = orderInfo;
                    tv_picker_date.setText(orderInfo);
                    pvCustomTime.show();

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_get_car:
                ll_get_car.setEnabled(false);
                ll_back_car.setEnabled(true);
                break;
            case R.id.ll_back_car:
                if (tv_get_car_time.getText().equals("请设置取车时间")) {
                    Toast.makeText(this, "请设置取车时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                ll_back_car.setEnabled(false);
                ll_get_car.setEnabled(true);
                chooseDate = "";
                chooseTime = "";
                ll_back_car.setBackgroundColor(getColor(R.color.white));
                ll_get_car.setBackgroundColor(getColor(R.color.light_while));
                break;
            case R.id.tv_sure_send_car:
                submitTime();
                break;
            case R.id.tv_select_getAddress:
                Intent intent = new Intent(mContext, SetAddressActivity.class);
                intent.putExtra("type",SetAddressActivity.GET_CAR);
                startActivityForResult(intent,150);
                break;
        }
    }

    private void submitTime() {
        String getTime = tv_get_car_time.getText().toString();
        String backTime = tv_back_car_time.getText().toString();
        String getAddress =tvSelectGetAddress.getText().toString();
        if (getTime.equals("请设置取车时间")) {
            Toast.makeText(this, "请设置取车时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (backTime.equals("请设置还车时间")) {
            Toast.makeText(this, "请设置还车时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if(type.equals(DETAIL_KEY) && getAddress.equals("请点击设置送车上门地址")){
            Toast.makeText(this, "请点击设置送车上门地址", Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("getTime", getTime);
        bundle.putString("backTime", backTime);
        bundle.putString("getAddress", getAddress);
        if (type.equals(RENT_KEY)) {
            setResult(RESULT_OK, new Intent().putExtras(bundle));
            finish();
        } else {
            bundle.putSerializable("cardetail", cardetail);
            bundle.putString("startTime",getTime);
            bundle.putString("endTime",backTime);
            bundle.putString("takeAddress",getAddress);
            toActivity(OrderConfirmActivity.class, bundle, true);
            //Toast.makeText(mContext, "租车逻辑", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //btn_CustomTime.setText(getTime(date));
                chooseTime = date.getHours() + ":" + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
                if (isCommit) {
                    if (!ll_get_car.isEnabled()) {
                        tv_get_car_time.setText(chooseDate + " " + chooseTime);
                    } else {
                        if (checkDateAfter(chooseDate + " " + chooseTime, tv_get_car_time.getText().toString())) {
                            tv_back_car_time.setText(chooseDate + " " + chooseTime);
                        } else {
                            Toast.makeText(mContext, "还车时间不能在取车时间之前", Toast.LENGTH_SHORT).show();
                        }
                    }

                    isCommit = false;
                } else {
                    tv_picker_time.setText(chooseTime);
                }
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setOutSideCancelable(false)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tv_cancel_add = (TextView) v.findViewById(R.id.tv_cancel);
                        tv_picker_date = v.findViewById(R.id.tv_picker_date);
                        tv_picker_time = v.findViewById(R.id.tv_picker_time);
                        WheelView hour = v.findViewById(R.id.hour);
                        WheelView second = v.findViewById(R.id.min);
                        hour.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(int i) {
                                pvCustomTime.returnData();
                            }
                        });
                        second.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(int i) {
                                pvCustomTime.returnData();
                            }
                        });
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isCommit = true;
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        tv_cancel_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pvCustomTime.dismiss();
                                chooseTime = "";
                            }
                        });
                    }
                })
                .setContentSize(18)
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .isDialog(true)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    private boolean checkDateAfter(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return format.parse(date1).after(format.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (type.equals(RENT_KEY)) {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            String address = data.getStringExtra("address");
            tvSelectGetAddress.setText(address);
        }
    }
}
