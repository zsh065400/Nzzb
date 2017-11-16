package zzbcar.cckj.com.nzzb.adapter.search;

import android.content.Context;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.FireSearchBean;

/**
 * Created by Scout
 * Created on 2017/11/16 18:25.
 */

public class FireSearchAdapter extends BaseRecycleViewAdapter<FireSearchBean.DataBean> {

    public FireSearchAdapter(Context context, List<FireSearchBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, FireSearchBean.DataBean fireSearchBean) {
        holder.setText(R.id.tv_fire_search_item, fireSearchBean.getName());
    }

    @Override
    protected int getItemView() {
        return R.layout.fire_search_item;
    }
}
