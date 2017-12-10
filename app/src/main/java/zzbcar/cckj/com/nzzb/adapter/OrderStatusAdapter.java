package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.UserOrderBean;
import zzbcar.cckj.com.nzzb.utils.GlideApp;

/**
 * Created by Admin on 2017/11/6.
 */

public class OrderStatusAdapter extends BaseRecycleViewAdapter<UserOrderBean.DataBean> {

    /*
    * 订单状态：
    * 0 下单(付款根据payStatus字段)
    * //1 付款
    * 2 已接单未取车
    * 3 已取车未还车
    * 5 已还车（显示押金未退）
    * 6 确认收车
    * 7 用户取消
    * 8 后台取消
    * 9 关闭（未成交，取消）
    * 10 交易成功
    * */
    private String[] status = {"", "", "待取车", "已取车", "", "押金未退", "已收车", "已取消", "已取消", "已关闭", "交易成功"};
    private String[] transmissionCase = {"双离合", "手自动一体", "ISR", "AMT", "自动"};
    /*使用类型
    *
    * 1 自驾
    * 2 商务
    * 4 婚庆
    * */
    private String[] userType = {"自驾", "商务", "", "婚庆"};

    public OrderStatusAdapter(Context context, List<UserOrderBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, UserOrderBean.DataBean dataBean) {
        String endTime = dataBean.getEndTime();
        String startTime = dataBean.getStartTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date end = sdf.parse(endTime);
            Date start = sdf.parse(startTime);
            long nd = 1000 * 24 * 60 * 60;
            long betweentime = end.getTime() - start.getTime();
            final long betweenDay = betweentime / nd;
            if (betweenDay != 0) {
                holder.setText(R.id.tv_time, betweenDay + "天");
            } else {
                long nh = 1000 * 60 * 60;
                final long betweenHour = betweentime % nd / nh;
                if (betweenHour != 0) {
                    holder.setText(R.id.tv_time, betweenHour + "小时");
                } else {
                    long nm = 1000 * 60;
                    final long betweenMinu = betweentime % nd % nh / nm;
                    holder.setText(R.id.tv_time, betweenMinu + "分钟");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.setText(R.id.tv_number, "订单编号" + dataBean.getOrderNo());//订单号
        final int status = dataBean.getStatus();
        final int payStatus = dataBean.getPayStatus();
        if (status == 0) {
            if (payStatus == 0)
                holder.setText(R.id.tv_state, "待支付");//状态
            else
                holder.setText(R.id.tv_state, "未接单");//状态
        } else {
            holder.setText(R.id.tv_state, this.status[status]);//状态
        }
        ImageView ivCar = holder.getView(R.id.iv_car);

        final UserOrderBean.DataBean.CarBean car = dataBean.getCar();
        GlideApp
                .with(mContext)
                .load(car.getPics())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivCar);
        holder.setText(R.id.tv_car_name, car.getCarName());//车名
        holder.setText(R.id.tv_car_model_name, userType[car.getUseType() - 1]);//使用类型
        holder.setText(R.id.tv_car_address, car.getAddr());//地址

        holder.setText(R.id.tv_send_address, dataBean.getTakeAddress());
        holder.setText(R.id.tv_rec_address, dataBean.getReturnAddress());
        holder.setText(R.id.tv_send_time, dataBean.getStartTime() + "");
        holder.setText(R.id.tv_rec_time, dataBean.getEndTime() + "");
        holder.setText(R.id.tv_car_detail, car.getModelYear() + "款 " + "|" + car.getSeatNum() + "座位 " + "|" + transmissionCase[car.getTransmissionCase()] + "");
        holder.setText(R.id.tv_car_address, car.getAddr());
        TextView view = holder.getView(R.id.tv_sure_get_car);
        switch (status) {
            case 0:
                if (payStatus == 0)
                    view.setText("确定支付");
                else
                    view.setVisibility(View.GONE);
                break;
            case 2:
                view.setText("确定取车");
                break;
//            case 1:
//                view.setText("未接单");
//                break;
            case 3:
                view.setText("确定还车");
                break;
            case 5:
//                view.setText("确定还车");
//                break;
            case 6:
//                view.setText("确认收车");
//                break;
            case 7:
            case 8:
            case 9:
            case 10:
                view.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected int getItemView() {
        return R.layout.order_status_item;
    }
}
