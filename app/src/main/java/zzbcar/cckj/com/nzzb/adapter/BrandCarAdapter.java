package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.QueryBean;
import zzbcar.cckj.com.nzzb.utils.GlideApp;

/**
 * Created by Admin on 2017/11/7.
 */

public class BrandCarAdapter extends BaseRecycleViewAdapter<QueryBean.DataBean> {
    public BrandCarAdapter(Context context, List<QueryBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, QueryBean.DataBean dataBean) {
        holder.setText(R.id.tv_item_car, dataBean.getCarName());
        holder.setText(R.id.tv_item_price, String.format("%s/天", dataBean.getPrice()));
        ImageView iv = holder.getView(R.id.iv_item_car);
        GlideApp
                .with(mContext)
                .load(dataBean.getPics())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv);
        ImageView iv_type = holder.getView(R.id.iv_car_type);
        iv_type.setBackgroundResource(dataBean.getUseType()==1?R.mipmap.car_type1:R.mipmap.car_type_2);
    }

    @Override
    protected int getItemView() {
        return R.layout.cardview;
    }
}
