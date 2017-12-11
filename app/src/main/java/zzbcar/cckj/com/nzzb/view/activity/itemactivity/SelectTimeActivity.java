package zzbcar.cckj.com.nzzb.view.activity.itemactivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xq.calendartest.MonthTimeAdapter;
import com.xq.calendartest.entity.DayTimeEntity;
import com.xq.calendartest.entity.MonthTimeEntity;
import com.xq.calendartest.entity.UpdataCalendar;
import com.xq.calendartest.utils.SharePreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.VpSelecTimeAdapter;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.CarDetailBean;
import zzbcar.cckj.com.nzzb.bean.MonthPriceBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/4.
 */

public class SelectTimeActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    //    @BindView(R.id.vp_select_time)
//    NoScrollViewPager vpSelectTime;
    @BindView(R.id.tv_sure_send_car)
    TextView tvSureSendCar;
    @BindView(R.id.tv_select_getAddress)
    TextView tvSelectGetAddress;

    @BindView(R.id.tv_select_sendAddress)
    TextView tvSelectSendAddress;
    @BindView(R.id.swh_status_sendcar)
    Switch swh_status_sendcar;
    @BindView(R.id.swh_status_pullcar)
    Switch swh_status_pullcar;
    @BindView(R.id.tv_set_selfGetcar_address)
    TextView tvSetSelfGetcarAddress;
    @BindView(R.id.tv_set_selfRepaycar_address)
    TextView tvSetSelfRepaycarAddress;
    private TextView tv_get_car_time;
    private TextView tv_back_car_time;
    //    private LinearLayout ll_get_car;
//    private LinearLayout ll_back_car;
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
    private boolean QuCheAddress;
    /*0为start，1为stop*/
    private int chooseType = 0;
    private int count = 0;
    private LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_time;
    }

    @Override
    protected void initViews() {
        initTimePicker();
        tv_get_car_time = (TextView) findViewById(R.id.tv_get_car_time);
        tv_back_car_time = (TextView) findViewById(R.id.tv_back_car_time);
//        ll_get_car = (LinearLayout) findViewById(R.id.ll_get_car);
//        ll_back_car = (LinearLayout) findViewById(R.id.ll_back_car);

        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);


        /*新加*/
        isPreferences = new SharePreferences(this);
        EventBus.getDefault().register(this);
        recycle = (RecyclerView) findViewById(R.id.plan_time_calender);
        // 上下文
//垂直布局,
        layoutManager = new LinearLayoutManager(this,   // 上下文
                LinearLayout.HORIZONTAL,  //垂直布局,
                false);
        recycle.setLayoutManager(layoutManager);
        recycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                scrollState = newState;
            }
        });

    }

    @Override
    protected void initListeners() {
        new TitleBuilder(this).setTitleText("选择时间").setLeftIco(R.mipmap.row_back).setLeftIcoListening(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        tvSureSendCar.setOnClickListener(this);
        tvSelectGetAddress.setOnClickListener(this);
        tvSelectSendAddress.setOnClickListener(this);
    }

    //直接显示第n个item内容
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

    /*EventBus事件*/
    public void onEventMainThread(UpdataCalendar event) {
        if (scrollState == RecyclerView.SCROLL_STATE_IDLE)
            adapter.notifyDataSetChanged();
    }

    private void initNewCalendar() {
        startDay = new DayTimeEntity(0, 0, 0, 0, 0.0);
        stopDay = new DayTimeEntity(-1, -1, -1, -1, 0.0);
        datas = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;

        /*控制显示的月数*/
        datas.add(new MonthTimeEntity(year, month, monthPriceList.get(0)));                //
        for (int i = 0; i < 12; i++) {
            MonthTimeEntity monthTimeEntity = new MonthTimeEntity();
            if (month >= 12) {//明年
                year = c.get(Calendar.YEAR) + 1;
                month = 1;
                monthTimeEntity.setYear(year);
                monthTimeEntity.setMonth(month);
            } else {
                month = month + 1;
                monthTimeEntity.setYear(year);
                monthTimeEntity.setMonth(month);
            }
            monthTimeEntity.setPriceBean(monthPriceList.get(i + 1));
            datas.add(monthTimeEntity);
        }
        adapter = new MonthTimeAdapter(datas, SelectTimeActivity.this);
        recycle.setAdapter(adapter);
        /*模拟viewpager*/
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recycle);

        adapter.setOnDayItemClickListener(new MonthTimeAdapter.OnDayItemClickListener() {
            @Override
            public void OnDayItemClick(View view, int position) {
                isPreferences.updateSp("start_month_position", startDay.getMonthPosition());
                isPreferences.updateSp("start_day_position", startDay.getDayPosition());
                isPreferences.updateSp("end_month_position", stopDay.getMonthPosition());
                isPreferences.updateSp("end_day_position", stopDay.getDayPosition());

                isPreferences.updateSp("start_year", startDay.getYear());
                isPreferences.updateSp("start_month", startDay.getMonth());
                isPreferences.updateSp("start_day", startDay.getDay());

                isPreferences.updateSp("end_year", stopDay.getYear());
                isPreferences.updateSp("end_month", stopDay.getMonth());
                isPreferences.updateSp("end_day", stopDay.getDay());

                String timeStop = String.format("%s-%s-%s", stopDay.getYear(), stopDay.getMonth(), stopDay.getDay());
                System.out.println(timeStop);
                chooseDate = timeStop;
                tv_picker_date.setText(chooseDate);
                chooseType = 1;
                pvCustomTime.show();
            }

            @Override
            public void onStartClick(View view, int position) {
                String timeStart = String.format("%s-%s-%s", startDay.getYear(), startDay.getMonth(), startDay.getDay());
                System.out.println(timeStart);
                chooseDate = timeStart;
                tv_picker_date.setText(chooseDate);
                chooseType = 0;
                pvCustomTime.show();
            }

            @Override
            public void onReChoose(View view, int position) {
                isCommit = false;
                tv_back_car_time.setText("请设置还车时间");
                tv_get_car_time.setText("请设置取车时间");
                tv_picker_date.setText("");
                tv_picker_time.setText("");

                String timeStart = String.format("%s-%s-%s", startDay.getYear(), startDay.getMonth(), startDay.getDay());
                System.out.println(timeStart);
                chooseDate = timeStart;
                tv_picker_date.setText(chooseDate);
                chooseType = 0;
                pvCustomTime.show();
            }

            @Override
            public void onSameDay(View view, int position) {
                String timeStop = String.format("%s-%s-%s", stopDay.getYear(), stopDay.getMonth(), stopDay.getDay());
                System.out.println(timeStop);
                chooseDate = timeStop;
                tv_picker_date.setText(chooseDate);
                chooseType = 1;
                pvCustomTime.show();
            }
        });
    }

    /*新修改*/
    private SharePreferences isPreferences;
    private RecyclerView recycle;
    private MonthTimeAdapter adapter;
    private ArrayList<MonthTimeEntity> datas;

    public static DayTimeEntity startDay;
    public static DayTimeEntity stopDay;
    private int scrollState;

    @Override
    protected void initDatas() {
        swh_status_pullcar.setOnCheckedChangeListener(this);
        swh_status_sendcar.setOnCheckedChangeListener(this);
        swh_status_sendcar.setChecked(false);
        swh_status_pullcar.setChecked(false);

        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        if (type.equals(DETAIL_KEY)) {
            cardetail = (CarDetailBean.DataBean) bundle.getSerializable("cardetail");
            if (!bundle.getString("getAddress").equals("请点击设置送车上门地址")) {
                swh_status_sendcar.setChecked(true);
                tvSelectGetAddress.setText(bundle.getString("getAddress"));
            }
        } else {
            if (!bundle.getString("getAddress").equals("请点击设置送车上门地址")) {
                swh_status_sendcar.setChecked(true);
                tvSelectGetAddress.setText(bundle.getString("getAddress"));
            }
        }
        Calendar calendar = Calendar.getInstance();
        getPrice(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 0);
    }

    private void getPrice(final int year, final int month, final int index) {
        //递归获取往后一年的价格。
        if (monthPriceList == null)
            monthPriceList = new ArrayList<>();
        if (index == 13) {
            initNewCalendar();
            MoveToPosition(layoutManager, recycle, isPreferences.getSp().getInt("start_month_position", 0));
            return;
        }
        OkGo.<String>get(Constant.API_PRICE_MONTH)
                .params("year", year + "")
                .params("month", month + "")
                .params("carId", cardetail.getId() + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        return list.addAll(getPrice((month<12?year:year+1),(month<12?month+1:1)));
                        monthPriceList.addAll(GsonUtil.parseJsonWithGson(response.body(), MonthPriceBean.class).getData());
                        getPrice((month < 12 ? year : year + 1), (month < 12 ? month + 1 : 1), index + 1);
                    }
                });
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_sure_send_car:
                submitTime();
                break;
            case R.id.tv_select_getAddress:
                QuCheAddress = true;
                intent = new Intent(mContext, SetAddressActivity.class);
                intent.putExtra("type", SetAddressActivity.GET_CAR);
                startActivityForResult(intent, 0);
                break;
            case R.id.tv_select_sendAddress:
                QuCheAddress = false;
                intent = new Intent(mContext, SetAddressActivity.class);
                intent.putExtra("type", SetAddressActivity.SEND_CAR);
                startActivityForResult(intent, 1);
                break;
        }

    }

    private void submitTime() {
        String getTime = tv_get_car_time.getText().toString();
        String backTime = tv_back_car_time.getText().toString();
        String getAddress = tvSelectGetAddress.getText().toString();
        String sendAddress = tvSelectSendAddress.getText().toString();
        if (getTime.equals("请设置取车时间")) {
            Toast.makeText(this, "请设置取车时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (backTime.equals("请设置还车时间")) {
            Toast.makeText(this, "请设置还车时间", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (type.equals(DETAIL_KEY) && getAddress.equals("请点击设置送车上门地址")) {
//            Toast.makeText(this, "请点击设置送车上门地址", Toast.LENGTH_SHORT).show();
//            return;
//        }
        Bundle bundle = new Bundle();
        bundle.putString("getTime", getTime);
        bundle.putString("backTime", backTime);
        bundle.putString("getAddress", getAddress);
        bundle.putString("sendAddress", sendAddress);
        if (type.equals(RENT_KEY)) {
            setResult(RESULT_OK, new Intent().putExtras(bundle));
            finish();
        } else {
            bundle.putSerializable("cardetail", cardetail);
            bundle.putString("startTime", getTime);
            bundle.putString("endTime", backTime);
            bundle.putString("takeAddress", getAddress);
            bundle.putString("sendAddress", sendAddress);
            toActivity(OrderConfirmActivity.class, bundle, true);
            //Toast.makeText(mContext, "租车逻辑", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        final Calendar selectedDate = Calendar.getInstance();//系统当前时间
        final Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder
                (this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        //btn_CustomTime.setText(getTime(date));
                        chooseTime = date.getHours() + ":" + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes());
                        if (isCommit) {
                            final String selectTime = chooseDate + " " + chooseTime;
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            /*如果当前时间在选择时间后*/
                            if (checkDateAfter(format.format(new Date()), selectTime)) {
                                Toast.makeText(mContext, "选择时间不能早与当前时间", Toast.LENGTH_SHORT).show();
                                doCancel();
                                return;
                            }
                            if (chooseType == 0) {
                                tv_get_car_time.setText(selectTime);
                            } else {
                                if (checkDateAfter(selectTime, tv_get_car_time.getText().toString())) {
                                    try {
                                        if (daysBetween(format.parse(tv_get_car_time.getText().toString()), format.parse(selectTime)) < 6) {
                                            doCancel();
                                            Toast.makeText(mContext, "租赁时长不低于6小时", Toast.LENGTH_SHORT).show();
                                            return;
                                        } else
                                            tv_back_car_time.setText(selectTime);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        doCancel();
                                        return;
                                    }
                                } else {
                                    Toast.makeText(mContext, "还车时间不能在取车时间之前", Toast.LENGTH_SHORT).show();
                                    doCancel();
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
//                                if (tv_back_car_time.getText().equals("请设置还车时间")) {
//                                    ll_back_car.performClick();
//                                }
                            }
                        });
                        tv_cancel_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pvCustomTime.dismiss();
                                chooseTime = "";
                                doCancel();
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

    private void doCancel() {
        if (chooseType == 0) {
            clearStart();

        } else {
            clearStop();

        }
        EventBus.getDefault().post(new UpdataCalendar());
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    /**
     * 计算是date1是否在date2的后面
     */
    private boolean checkDateAfter(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 60 * 60);//改动此处计算相差周期

        return Integer.parseInt(String.valueOf(between_days));
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
        if (resultCode == RESULT_OK) {
            String address = data.getStringExtra("address");
            if (requestCode == 0) {
                swh_status_pullcar.setEnabled(true);

                tvSelectGetAddress.setText(address);
            } else if (requestCode == 1) {
                swh_status_sendcar.setEnabled(true);

                tvSelectSendAddress.setText(address);
            }

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {
            case R.id.swh_status_pullcar:
                tvSelectSendAddress.setEnabled(b);
                if (b) {
                    tvSetSelfRepaycarAddress.setText("上门收车地址");
                    tvSelectSendAddress.setText("请点击设置上门收车地址");
                } else {

                    tvSetSelfRepaycarAddress.setText("自行还车地址");
                    tvSelectSendAddress.setText("华源创意工长48幢");
                }
                break;

            case R.id.swh_status_sendcar:
                tvSelectGetAddress.setEnabled(b);
                if (!b) {
                    tvSetSelfGetcarAddress.setText("自行取车地址");
                    tvSelectGetAddress.setText("华源创意工长48幢");
                } else {
                    tvSetSelfGetcarAddress.setText("送车上门地址");
                    tvSelectGetAddress.setText("请点击设置送车上门地址");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        /*重置信息*/
        isPreferences.updateSp("start_month_position", 0);
        isPreferences.updateSp("start_day_position", 0);

        isPreferences.updateSp("start_year", 0);
        isPreferences.updateSp("start_month", 0);
        isPreferences.updateSp("start_day", 0);

        isPreferences.updateSp("end_month_position", -1);
        isPreferences.updateSp("end_day_position", -1);

        isPreferences.updateSp("end_year", -1);
        isPreferences.updateSp("end_month", -1);
        isPreferences.updateSp("end_day", -1);
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void clearStop() {
        stopDay.setDay(-1);
        stopDay.setMonth(-1);
        stopDay.setYear(-1);
        stopDay.setMonthPosition(-1);
        stopDay.setDayPosition(-1);
    }

    private void clearStart() {
        startDay.setDay(0);
        startDay.setMonth(0);
        startDay.setYear(0);
        startDay.setMonthPosition(0);
        startDay.setDayPosition(0);
    }
}
