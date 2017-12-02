package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.MyCollectBean;
import zzbcar.cckj.com.nzzb.utils.GlideApp;


/**
 * Created by Admin on 2017/11/21.
 */

public class MyCollectAdapter extends BaseRecycleViewAdapter<MyCollectBean.DataBean> {

    public MyCollectAdapter(Context context, List <MyCollectBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MyCollectBean.DataBean dataBean) {
        final String carName = dataBean.getCarName();
        final String pics = dataBean.getPics();
        final double price = dataBean.getPrice();
        final String addr = dataBean.getAddr();
        final String orderCount = dataBean.getOrderCount();
        /*收藏暂无*/
        holder.setText(R.id.tv_car_name, carName);
        holder.setText(R.id.tv_car_location, addr);
        holder.setText(R.id.tv_car_price, "￥" + price + "/天");
        holder.setText(R.id.tv_car_detail, String.format("接单%s次 收藏0次", orderCount));
        final ImageView imageView = holder.getView(R.id.iv_car_pic);
        GlideApp
                .with(mContext)
                .load(pics)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
        ImageView iv_type = holder.getView(R.id.iv_car_type);
        iv_type.setBackgroundResource(dataBean.getUseType()==1?R.mipmap.car_type1:R.mipmap.car_type_2);
    }

    @Override
    protected int getItemView() {
        return R.layout.car_list_item;
    }

}
