package zzbcar.cckj.com.nzzb.adapter.main;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;

/**
 * 主页车型列表
 * <p>
 * Created by Scout
 * Created on 2017/11/11 14:59.
 */

public class NewCarAdapter extends BaseRecycleViewAdapter<MainPageBean.DataBean.NewCarListBean> {


    public NewCarAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MainPageBean.DataBean.NewCarListBean newCarListBean) {
        holder.setText(R.id.iv_fresh_name, newCarListBean.getCarName());
        holder.setText(R.id.iv_fresh_price, "￥" + newCarListBean.getPrice());
        ImageView pic = holder.getView(R.id.iv_fresh_pic);
        Picasso.with(mContext).load(newCarListBean.getPics())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fit()
                .into(pic);
    }

    @Override
    protected int getItemView() {
        return R.layout.fresh_car_item;
    }

}
