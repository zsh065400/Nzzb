package com.xq.calendartest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xq.calendartest.entity.DayTimeEntity;
import com.xq.calendartest.entity.MonthTimeEntity;
import com.xq.calendartest.selecttime.DayTimeAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.MonthPriceBean;

/**
 * Created by xxq on 2017/7/17.
 */

public class MonthTimeAdapter extends RecyclerView.Adapter<MonthTimeViewHolder> {
    private ArrayList<MonthTimeEntity> datas;
    private Context context;

    public interface OnDayItemClickListener {
        void OnDayItemClick(View view, int position);

        void onStartClick(View view, int position);

        void onReChoose(View view, int position);

        void onSameDay(View view, int position);
    }

    private OnLeftRightButtonListener mOnLeftRightButtonListener;

    public void setOnLeftRightButtonListener(OnLeftRightButtonListener onLeftRightButtonListener) {
        mOnLeftRightButtonListener = onLeftRightButtonListener;
    }

    public interface OnLeftRightButtonListener {
        void onLeftClick();

        void onRightClick();
    }

    private OnDayItemClickListener mOnDayItemClickListener;

    public void setOnDayItemClickListener(OnDayItemClickListener onDayItemClickListener) {
        this.mOnDayItemClickListener = onDayItemClickListener;
    }

    public MonthTimeAdapter(ArrayList<MonthTimeEntity> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public MonthTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MonthTimeViewHolder ret = null;
        // 不需要检查是否复用，因为只要进入此方法，必然没有复用
        // 因为RecyclerView 通过Holder检查复用
        View v = LayoutInflater.from(context).inflate(R.layout.item_recycler_timeplan, parent, false);
        ret = new MonthTimeViewHolder(v, context);
        return ret;
    }

    @Override
    public void onBindViewHolder(final MonthTimeViewHolder holder, int position) {
        MonthTimeEntity monthTimeEntity = datas.get(position);
        final MonthPriceBean.DataBean priceBean = monthTimeEntity.getPriceBean();
        holder.plan_time_txt_month.setText(monthTimeEntity.getYear() + "年" + monthTimeEntity.getMonth() + "月");  //显示 几年--几月
        //得到该月份的第一天
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, monthTimeEntity.getYear());          //指定年份
        calendar.set(Calendar.MONTH, monthTimeEntity.getMonth() - 1);        //指定月份 Java月份从0开始算
        calendar.set(Calendar.DAY_OF_MONTH, 1);                           // 指定天数 ，这三行是为了得到 这一年这一月的第一天

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);             //得到该月份第一天 是星期几
        ArrayList<DayTimeEntity> days = new ArrayList<DayTimeEntity>();
        for (int i = 0; i < dayOfWeek - 1; i++) {                          //
            days.add(new DayTimeEntity(0, monthTimeEntity.getMonth(), monthTimeEntity.getYear(), position, priceBean));
        }
        calendar.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        calendar.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        for (int i = 1; i <= calendar.get(Calendar.DAY_OF_MONTH); i++) {     //添加 该月份的天数   一号 到 该月的最后一天
            days.add(new DayTimeEntity(i, monthTimeEntity.getMonth(), monthTimeEntity.getYear(), position, priceBean));
        }

        DayTimeAdapter adapter = new DayTimeAdapter(days, context);
        holder.plan_time_recycler_content.setAdapter(adapter);

        adapter.setOnItemCliCkListener(new DayTimeAdapter.onItemClickListener() {
            @Override
            public void onItemCallback(View view, int position) {
                if (mOnDayItemClickListener != null)
                    mOnDayItemClickListener.OnDayItemClick(holder.itemView, position);
            }

            @Override
            public void onStartClick(View view, int position) {
                if (mOnDayItemClickListener != null)
                    mOnDayItemClickListener.onStartClick(holder.itemView, position);
            }

            @Override
            public void onReChoose(View view, int position) {
                if (mOnDayItemClickListener != null)
                    mOnDayItemClickListener.onReChoose(holder.itemView, position);
            }

            @Override
            public void onSameDay(View view, int position) {
                if (mOnDayItemClickListener != null)
                    mOnDayItemClickListener.onSameDay(holder.itemView, position);
            }
        });

        final View left = holder.itemView.findViewById(R.id.iv_previous);
        final View right = holder.itemView.findViewById(R.id.iv_next);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLeftRightButtonListener != null) mOnLeftRightButtonListener.onLeftClick();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLeftRightButtonListener != null) mOnLeftRightButtonListener.onRightClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (datas != null) {
            ret = datas.size();
        }
        return ret;
    }

}
