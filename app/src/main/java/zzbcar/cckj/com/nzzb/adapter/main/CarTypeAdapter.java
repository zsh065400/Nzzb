package zzbcar.cckj.com.nzzb.adapter.main;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.utils.GlideApp;

/**
 * 主页车型列表
 * <p>
 * Created by Scout
 * Created on 2017/11/11 14:59.
 */

public class CarTypeAdapter extends BaseRecycleViewAdapter<MainPageBean.DataBean.CarListBean> {


    public CarTypeAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MainPageBean.DataBean.CarListBean newCarListBean) {
        holder.setText(R.id.iv_fresh_name, newCarListBean.getCarName());
        holder.setText(R.id.iv_fresh_price, "￥" + newCarListBean.getPrice());
        ImageView pic = holder.getView(R.id.iv_fresh_pic);
        GlideApp
                .with(mContext)
                .load(newCarListBean.getPics())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(pic);
        ImageView iv_type = holder.getView(R.id.iv_car_type);
        iv_type.setBackgroundResource(newCarListBean.getUseType()==1?R.mipmap.car_type1:R.mipmap.car_type_2);
    }

    @Override
    protected int getItemView() {
        return R.layout.fresh_car_item;
    }

}
