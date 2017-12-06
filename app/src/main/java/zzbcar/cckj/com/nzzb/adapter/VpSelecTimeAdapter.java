package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.MonthPriceBean;
import zzbcar.cckj.com.nzzb.utils.CalendarUtils;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.widget.NoScrollGridView;

/**
 * Created by hegeyang on 2017/11/21.
 */

public class VpSelecTimeAdapter extends PagerAdapter implements View.OnClickListener {
    private List<MonthPriceBean.DataBean> monthPriceList;
    private ViewPager vp;
    private Context context;
    private LayoutInflater mInflater;
    private int daysOfSelect;
    private OnCalendarOrderListener listener;
    private CalendarAdapter cAdapter;
    private View lastView;
    private int lastColor;

    public VpSelecTimeAdapter(Context context, int daysOfSelect, ViewPager vp, List<MonthPriceBean.DataBean> monthPriceList) {
        this.context = context;
        this.daysOfSelect = daysOfSelect;
        this.vp = vp;
        this.monthPriceList = monthPriceList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return CalendarUtils.throughMonth(Calendar.getInstance(), daysOfSelect) + 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = mInflater.inflate(R.layout.calendar, null, false);
        TextView yearAndMonth = (TextView) inflate.findViewById(R.id.tv_year_month);
        NoScrollGridView calendarGrid = (NoScrollGridView) inflate.findViewById(R.id.gv_calendar_layout);
        inflate.findViewById(R.id.left_month_btn).setOnClickListener(this);
        inflate.findViewById(R.id.right_month_btn).setOnClickListener(this);
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, position);
        yearAndMonth.setText(c.get(Calendar.YEAR) + context.getString(R.string.year)
                + (c.get(Calendar.MONTH) + 1) + context.getString(R.string.month));

        cAdapter = null;
        if (position == 0) {
            cAdapter = new CalendarAdapter(context, c, daysOfSelect, null, monthPriceList, 0);
        } else {
            //每个月需要填充的日期数量不同，需要重新计算
            int d = daysOfSelect - CalendarUtils.currentMonthRemainDays() - CalendarUtils.getFlowMonthDays(position - 1);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            cAdapter = new CalendarAdapter(context, c, d, null, monthPriceList,
                    CalendarUtils.getAfFlowMonthDays(month, position - 1));
        }
        calendarGrid.setAdapter(cAdapter);
        calendarGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test Tag", "onStartClick: " + position + ":" + id);
                Calendar cl = (Calendar) c.clone();
                cl.set(Calendar.DAY_OF_MONTH, 1);
                int day = position + 2 - cl.get(Calendar.DAY_OF_WEEK);
                TextView dayTv = (TextView) view.findViewById(R.id.tv_calendar_item);
                if (day <= 0 || !dayTv.isEnabled())
                    return;
                String orderInfo = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + day;
                if (lastView != null) {
                    TextView viewById = (TextView) lastView.findViewById(R.id.tv_calendar_item);
                    viewById.setBackgroundResource(R.drawable.normal_calendar_order_item_bg);
                    viewById.setTextColor(lastColor);
                }
                /*判断逻辑，用于效果展示*/
                final String start = SPUtils.getString(context, "start", "");
                final String end = SPUtils.getString(context, "end", "");
                if (start.equals("")) {
                    SPUtils.saveString(context, "start", orderInfo);
                } else if (end.equals("")) {
                    SPUtils.saveString(context, "end", orderInfo);
                }


                lastColor = dayTv.getCurrentTextColor();
                dayTv.setTextColor(Color.WHITE);
                dayTv.setBackgroundResource(R.drawable.calendar_order_item_bg);
                lastView = view;
                cl.clear();
                cl = null;
                if (listener != null)
                    listener.onOrder(orderInfo);
            }
        });
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setOnCalendarOrderListener(OnCalendarOrderListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_month_btn:
                if (vp.getCurrentItem() != 0) {
                    vp.setCurrentItem(vp.getCurrentItem() - 1, false);
                }
                break;
            case R.id.right_month_btn:
                if (vp.getCurrentItem() + 1 < getCount()) {
                    vp.setCurrentItem(vp.getCurrentItem() + 1, false);
                }
                break;
        }
    }

    public interface OnCalendarOrderListener {
        void onOrder(String orderInfo);
    }
}
