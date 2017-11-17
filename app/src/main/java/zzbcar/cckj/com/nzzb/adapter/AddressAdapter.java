package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.AddressBean;

/**
 * Created by hegeyang on 2017/11/17.
 */

public class AddressAdapter extends BaseRecycleViewAdapter<AddressBean.DataBean> {

    private OnItemDeleEditClickListener clickListener;

    public AddressAdapter(Context context, List<AddressBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, AddressBean.DataBean dataBean) {
        String linkman = dataBean.getLinkman();
        String addr = dataBean.getAddr() + dataBean.getAddrDetail();
        String mobile = dataBean.getMobile();
        holder.setText(R.id.tv_addr_item_name,linkman);
        holder.setText(R.id.tv_addr_item_address,addr);
        holder.setText(R.id.tv_addr_item_number,mobile);
        if(clickListener!=null){
            holder.getView(R.id.ll_addr_item_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(view,position);
                }
            });
            holder.getView(R.id.ll_addr_item_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(view,position);
                }
            });
        }

    }

    @Override
    protected int getItemView() {
        return R.layout.item_comm_address;
    }
    public interface OnItemDeleEditClickListener {
        void onClick(View v, int position);
    }
    public void setOnItemDeleteClickListener(OnItemDeleEditClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
