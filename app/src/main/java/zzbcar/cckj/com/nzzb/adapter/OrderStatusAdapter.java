package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.UserOrderBean;

/**
 * Created by Admin on 2017/11/6.
 */

public class OrderStatusAdapter extends BaseRecycleViewAdapter<UserOrderBean.DataBean> {

    /*
    * 订单状态：
    * 0 下单
    * 1 付款
    * 2 接单未取车
    * 3 取车
    * 5 用户还车
    * 6 车主收车
    * 7 用户取消
    * 8 后台取消
    * 9 关闭
    * 10 交易成功
    * */
    private String[] status = {"已下单", "已付款", "未取车", "已取车", "", "已还车", "已收车", "已取消", "已取消", "已关闭", "交易成功"};

    /*使用类型
    *
    * 1 自驾
    * 2 商务
    * 4 婚庆
    * */
    private String[] userType = {"自驾", "商务", "", "婚庆"};
//    private long betweentimeDay;
   private long betweentimeHour;
//    private long betweentimeMin;
//    private long betweentimeSec;

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
            betweentimeHour = (end.getTime() - start.getTime())/1000/60/60;
//           if(betweentimeDay!=0){
//               betweentimeHour = betweentimeDay/24;
//           }
//            if(betweentimeHour!=0){
//                betweentimeMin = betweentimeHour/60;
//            }
//         if(betweentimeHour!=0){
//             betweentimeSec = betweentimeMin/60;
//         }


        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.setText(R.id.tv_number, "订单编号" + dataBean.getOrderNo());//订单号
        holder.setText(R.id.tv_state, status[dataBean.getStatus()]);//状态
        ImageView ivCar = holder.getView(R.id.iv_car);

        final UserOrderBean.DataBean.CarBean car = dataBean.getCar();
        Picasso.with(mContext).load(car.getPics()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.a).fit().into(ivCar);//车图片
        holder.setText(R.id.tv_car_name, car.getCarName());//车名
        holder.setText(R.id.tv_car_model_name, userType[car.getUseType() - 1]);//使用类型
        holder.setText(R.id.tv_car_address, car.getAddr());//地址
         holder.setText(R.id.tv_time,betweentimeHour+"小时");

        holder.setText(R.id.tv_send_address,dataBean.getTakeAddress());
        holder.setText(R.id.tv_rec_address,dataBean.getReturnAddress());
        holder.setText(R.id.tv_send_time,dataBean.getStartTime()+"");
        holder.setText(R.id.tv_rec_time,dataBean.getEndTime()+"");
        holder.setText(R.id.tv_car_detail,car.getModelYear()+"款 "+"|"+car.getSeatNum()+"座位 "+"|"+car.getUseType()+"");
        holder.setText(R.id.tv_car_address,car.getAddr());
        final int status = dataBean.getStatus();
        TextView view = holder.getView(R.id.tv_sure_get_car);
        switch (status) {
            case 0:
                view.setText("确定支付");
                break;
            case 1:
                view.setText("未接单");
                break;
            case 2:
                view.setText("确定取车");
                break;
            case 3:
                view.setText("我要还车");
                break;
            case 5:
//                view.setText("确认还车");
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
