package zzbcar.cckj.com.nzzb.adapter.search;

import android.content.Context;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.QueryBean;

/**
 * Created by Admin on 2017/11/7.
 */

public class QueryResultAdapter extends BaseRecycleViewAdapter<QueryBean.DataBean> {
    public QueryResultAdapter(Context context, List<QueryBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, QueryBean.DataBean dataBean) {
        holder.setText(R.id.tv_simple_1_line, dataBean.getCarName());
    }

    @Override
    protected int getItemView() {
        return R.layout.simple_1_line_text;
    }
}
