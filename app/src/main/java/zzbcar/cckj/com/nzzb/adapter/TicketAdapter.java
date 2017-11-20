package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.TicketBean;

/**
 * Created by hegeyang on 2017/11/20.
 */

public class TicketAdapter extends BaseRecycleViewAdapter<TicketBean.DataBean> {
    public TicketAdapter(Context context, List<TicketBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, TicketBean.DataBean dataBean) {
        holder.setText(R.id.tv_break_rule_address,dataBean.getCity()+dataBean.getAddress());
        holder.setText(R.id.tv_break_rule_time,dataBean.getViolationTime());
        holder.setText(R.id.tv_break_rule_money,dataBean.getFine()+"");
        holder.setText(R.id.tv_break_rule_content,"-"+dataBean.getPoint());
        holder.setText(R.id.tv_break_rule_track,dataBean.getReason());
    }

    @Override
    protected int getItemView() {

        return R.layout.item_break_rule;
    }
}
