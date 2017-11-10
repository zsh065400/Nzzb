package zzbcar.cckj.com.nzzb.view.activity.itemactivity;


import android.app.TimePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.ProductDatePrice;
import zzbcar.cckj.com.nzzb.utils.RandomUtils;
import zzbcar.cckj.com.nzzb.utils.StringUtils;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.customview.CommonCalendarView;

/**
 * Created by Admin on 2017/11/4.
 */

public class SelecTimeActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_get_car_time;
    private TextView tv_back_car_time;
    private ImageView iv_swicth;
    private ImageView red_point;
    private LinearLayout ll_get_car;
    private LinearLayout ll_back_car;
    private CommonCalendarView calendarView;
    private Map<String, List> mYearMonthMap = new HashMap<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_time;
    }

    @Override
    protected void initViews() {
        tv_get_car_time = (TextView) findViewById(R.id.tv_get_car_time);
        tv_back_car_time = (TextView) findViewById(R.id.tv_back_car_time);
        iv_swicth = (ImageView) findViewById(R.id.iv_swicth);
        red_point = (ImageView) findViewById(R.id.red_point);
        ll_get_car = (LinearLayout) findViewById(R.id.ll_get_car);
        ll_back_car = (LinearLayout) findViewById(R.id.ll_back_car);


    }

    @Override
    protected void initDatas() {
        ll_get_car.setOnClickListener(this);
        ll_back_car.setOnClickListener(this);
        ll_get_car.setEnabled(false);
        List<ProductDatePrice> mDatePriceList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {//构造12个月每天的价格数据
            for (int j = 1; j <= 31; j++) {
                ProductDatePrice price = new ProductDatePrice();
                price.setPriceDate(String.format("2017-%s-%s", StringUtils.leftPad(String.valueOf(i), 2, "0"), StringUtils.leftPad(String.valueOf(j), 2, "0")));
                price.setPrice(RandomUtils.nextInt(1000));
                mDatePriceList.add(price);
            }
        }

        for (ProductDatePrice productDatePrice : mDatePriceList) {//把价格数据改为同一个月的list 在一个key value里，减少渲染界面时循环判断数量
            productDatePrice.getPriceDate();
            String yearMonth = TextUtils.substring(productDatePrice.getPriceDate(), 0, TextUtils.lastIndexOf(productDatePrice.getPriceDate(), '-'));
            List list = mYearMonthMap.get(yearMonth);
            if (list == null) {
                list = new ArrayList();
                list.add(productDatePrice);
                mYearMonthMap.put(yearMonth, list);
            } else {
                list.add(productDatePrice);
            }
        }

        this.calendarView = (CommonCalendarView) findViewById(R.id.calendarView);
        this.calendarView.init(new CommonCalendarView.DatePickerController() {
            @Override
            public int getMaxYear() {
                return 2018;
            }

            @Override
            public void onDayOfMonthSelected(int year, int month, int day) {
                Toast.makeText(SelecTimeActivity.this,String.format("%s-%s-%s", year, StringUtils.leftPad(String.valueOf(month), 2, "0"),
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
                        Toast.makeText(SelecTimeActivity.this,datePrice.getPriceDate(), Toast.LENGTH_SHORT).show();
                        final TimePickerDialog timePickerDialog = new TimePickerDialog(SelecTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (!ll_get_car.isEnabled()){
                                    tv_back_car_time.setText(datePrice.getPriceDate() + " " + hourOfDay + ":" + minute);
                                    ll_back_car.setEnabled(false);
                                    ll_get_car.setEnabled(true);
                                }else {
                                    tv_back_car_time.setText(datePrice.getPriceDate() + " " + hourOfDay + ":" + minute);
                                    ll_back_car.setEnabled(false);
                                    ll_get_car.setEnabled(true);
                                }



                            }
                        }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
                        timePickerDialog.show();
                        timePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_get_car:
                ll_get_car.setEnabled(false);
                ll_back_car.setEnabled(true);
                break;
            case R.id.ll_back_car:
                if(tv_get_car_time.getText().equals("请设置取车时间")){
                    Toast.makeText(this, "请设置取车时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                ll_back_car.setEnabled(false);
                ll_get_car.setEnabled(true);
                break;
        }
    }
}
