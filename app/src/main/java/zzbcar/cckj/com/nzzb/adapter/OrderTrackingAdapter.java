package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.utils.ScreenUtils;

/**
 * Created by Scout
 * Created on 2017/12/1 16:51.
 */

public class OrderTrackingAdapter extends BaseRecycleViewAdapter<OrderTrackingAdapter.StatusTime> {

    /*数据格式为status, time*/
    public OrderTrackingAdapter(Context context, List<StatusTime> datas) {
        super(context, datas);
    }

    /*共4中状态时间，createTime订单创建时间 takeTime取车时间 returnTime还车时间 rtdepositTime退还押金时间*/
    @Override
    protected void convert(MyViewHolder holder, int position, StatusTime times) {
        ImageView imageView = holder.getView(R.id.iv_line);
        final int itemCount = getItemCount();

        holder.setText(R.id.tv_status, times.getStatus());
        holder.setText(R.id.tv_time, times.getTime());

        if (itemCount == 1) {
            imageView.setImageResource(R.mipmap.ic_line_end);
            ((TextView) holder.getView(R.id.tv_status)).setTextColor(Color.parseColor("#ff515a"));
        } else if (itemCount > 1) {
            /*第一个项目*/
            if (position == 0) {
                imageView.setImageResource(R.mipmap.ic_line_start_2);
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, (int) ScreenUtils.dp2px(18),
                        layoutParams.rightMargin, layoutParams.bottomMargin);
                imageView.setLayoutParams(layoutParams);
            } else if (position == itemCount - 1) {
                imageView.setImageResource(R.mipmap.ic_line_end_2);
                ((TextView) holder.getView(R.id.tv_status)).setTextColor(Color.parseColor("#ff515a"));
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,
                        layoutParams.rightMargin, (int) ScreenUtils.dp2px(16));
                imageView.setLayoutParams(layoutParams);
            }
        }
    }

    @Override
    protected int getItemView() {
        return R.layout.item_order_status;
    }

    public static class StatusTime {
        public String status;
        public String time;

        public StatusTime(String status, String time) {
            this.status = status;
            this.time = time;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public String getTime() {
            return time;
        }

    }
}
