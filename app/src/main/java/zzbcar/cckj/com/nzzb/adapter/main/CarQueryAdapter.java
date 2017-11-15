package zzbcar.cckj.com.nzzb.adapter.main;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.QueryBean;

/**
 * Created by Scout
 * Created on 2017/11/12 17:28.
 */

public class CarQueryAdapter extends BaseRecycleViewAdapter<QueryBean.DataBean> {
    public CarQueryAdapter(Context context, List<QueryBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, QueryBean.DataBean bean) {
        final String carName = bean.getCarName();
        final String pics = bean.getPics();
        final double price = bean.getPrice();
        final String addr = bean.getAddr();
        final String orderCount = bean.getOrderCount();
        /*收藏暂无*/
        holder.setText(R.id.tv_car_name, carName);
        holder.setText(R.id.tv_car_location, addr);
        holder.setText(R.id.tv_car_price, "￥" + price + "/天");
        holder.setText(R.id.tv_car_detail, String.format("接单%s次 收藏0次", orderCount));
        final ImageView imageView = holder.getView(R.id.iv_car_pic);
        Picasso.with(mContext).load(pics)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    @Override
    protected int getItemView() {
        return R.layout.car_list_item;
    }

}
