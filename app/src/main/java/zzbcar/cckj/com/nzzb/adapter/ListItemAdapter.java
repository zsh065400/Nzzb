package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.QueryBean;

/**
 * Created by Admin on 2017/11/7.
 */

public class ListItemAdapter extends BaseRecycleViewAdapter<QueryBean.DataBean> {
    public ListItemAdapter(Context context, List<QueryBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(BaseRecycleViewAdapter<QueryBean.DataBean>.MyViewHolder holder, int position, QueryBean.DataBean dataBean) {
        holder.setText(R.id.tv_item_car, dataBean.getCarName());
        holder.setText(R.id.tv_item_price, String.format("%s/天", dataBean.getPrice()));

        ImageView iv_type = holder.getView(R.id.iv_car_type);
        iv_type.setBackgroundResource(dataBean.getUseType()==1?R.mipmap.car_type1:R.mipmap.car_type_2);
        ImageView iv = holder.getView(R.id.iv_item_car);
        Picasso.with(mContext)
                //load()下载图片
                .load(dataBean.getPics())
                //下载中显示的图片
                .placeholder(R.mipmap.ic_launcher)
                //下载失败显示的图片
                .error(R.mipmap.ic_launcher)
                .fit()
                //init()显示到指定控件
                .into(iv);
    }

    @Override
    protected int getItemView() {
        return R.layout.cardview;
    }
}
