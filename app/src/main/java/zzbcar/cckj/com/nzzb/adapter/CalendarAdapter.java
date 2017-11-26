package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.MonthPriceBean;
import zzbcar.cckj.com.nzzb.utils.CalendarUtils;
import zzbcar.cckj.com.nzzb.utils.Day;


/**
 *  Created by hegeyang on 2017/11/21.
 */
public class CalendarAdapter extends BaseAdapter {

	private int index;
	private List<MonthPriceBean.DataBean> monthPriceList;
	private ArrayList<Day> days;
	private LayoutInflater mInflater;
	private Calendar c;
	private Context context;
	private String orderDay;

	public CalendarAdapter(Context context, Calendar c, int passDays, String orderDay, List<MonthPriceBean.DataBean> monthPriceList, int index) {
		this.c = c;
		this.context = context;
		this.orderDay = orderDay;
		this.monthPriceList = monthPriceList;
		days = CalendarUtils.getDaysOfMonth(this.c, passDays, orderDay);
		this.index = index;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return days.size();
	}

	@Override
	public Day getItem(int arg0) {
		return days.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View v = arg1;
		ViewHolder holder = null;
		if (v == null) {
			v = mInflater.inflate(R.layout.calendar_item, arg2, false);
			holder = new ViewHolder();
			holder.tv = (TextView) v.findViewById(R.id.tv_calendar_item);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		Day d = getItem(arg0);
		switch (d.getType()) {
		case TODAY:
			setOrderThreeDayStyle(holder.tv, d.isOrdered(), context.getString(R.string.today));
			break;
		case TOMORROW:
			setOrderThreeDayStyle(holder.tv, d.isOrdered(), context.getString(R.string.tomorrow));
			break;
		case T_D_A_T:
			setOrderThreeDayStyle(holder.tv, d.isOrdered(), context.getString(R.string.t_d_a_t));
			break;
		case ENABLE:
			holder.tv.setText(d.isOrdered() ? d.getName() + context.getString(R.string.order_day) : d.getName());
			holder.tv.setEnabled(true);
			holder.tv.setTextColor(d.isOrdered() ? Color.WHITE : context.getResources().getColor(
					R.color.calendar_enable_color));
			holder.tv.setBackgroundResource(d.isOrdered() ? R.drawable.calendar_order_item_bg
					: R.drawable.normal_calendar_order_item_bg);
			holder.tv.setTextSize(d.isOrdered() ? context.getResources().getDimension(
					R.dimen.calendar_item_order_day_size) : context.getResources().getDimension(
					R.dimen.calendar_item_nonorder_day_size));
			break;
		case NOT_ENABLE:
			holder.tv.setText(d.getName());
			holder.tv.setEnabled(false);
			holder.tv.setTextColor(context.getResources().getColor(R.color.calendar_disable_color));
			holder.tv.setBackgroundColor(Color.WHITE);
			holder.tv.setTextSize(context.getResources().getDimension(R.dimen.calendar_item_nonorder_day_size));
			break;
		}
		/*if(d.getName().equals("1")){
			LogUtil.e(index+"----");
		}*/
		if((d.getType()== Day.DayType.NOT_ENABLE && !TextUtils.isEmpty(d.getName()))){
			index++;
		}
		//Todo 这里可以处理单个价格的逻辑 这个index指的就是价格数据的下标
		if(!(d.getType()== Day.DayType.NOT_ENABLE) && !TextUtils.isEmpty(d.getName()) && monthPriceList!=null){
			SpannableString spannableString = new SpannableString(holder.tv.getText().toString()+"\n¥"+monthPriceList.get(index).getPrice());
			spannableString.setSpan(new ForegroundColorSpan(context.getColor(R.color.divider)), holder.tv.getText().toString().length(),spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			holder.tv.setText(spannableString);
			holder.tv.setTextSize(context.getResources().getDimension(R.dimen.calendar_item_order_day_size));
			index++;
		}
		return v;
	}

	private void setOrderThreeDayStyle(TextView tv, boolean ordered, String dayStr) {
		tv.setText(ordered ? dayStr + context.getString(R.string.order_day) : dayStr);
		tv.setEnabled(true);
		tv.setTextColor(ordered ? Color.WHITE : context.getResources().getColor(R.color.calendar_threeday_color));
		tv.setBackgroundResource(ordered ? R.drawable.calendar_order_item_bg : R.drawable.normal_calendar_order_item_bg);
		tv.setTextSize(ordered ? context.getResources().getDimension(R.dimen.calendar_item_order_day_size) : context
				.getResources().getDimension(R.dimen.calendar_item_nonorder_day_size));
	}

	static class ViewHolder {
		TextView tv;
	}

	public void previous() {
		if (Integer.valueOf(c.get(Calendar.MONTH)) == Integer.valueOf(c.getActualMinimum(Calendar.MONTH))) {
			c.set((c.get(Calendar.YEAR) - 1), c.getActualMaximum(Calendar.MONTH), 1);
		} else {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		}
		days = CalendarUtils.getDaysOfMonth(c, 0, orderDay);
		notifyDataSetChanged();
	}

	public void next() {
		if (Integer.valueOf(c.get(Calendar.MONTH)) == c.getActualMaximum(Calendar.MONTH)) {
			c.set((c.get(Calendar.YEAR) + 1), c.getActualMinimum(Calendar.MONTH), 1);
		} else {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		}
		days = CalendarUtils.getDaysOfMonth(c, 0, orderDay);
		notifyDataSetChanged();
	}

}
