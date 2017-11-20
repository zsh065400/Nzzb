package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.MyOrderBean;

/**
 * Created by Admin on 2017/11/20.
 */

public class MyOrderAdapter extends BaseRecycleViewAdapter<MyOrderBean.DataBean> {
    public MyOrderAdapter(Context context, List <MyOrderBean.DataBean> datas){
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MyOrderBean.DataBean dataBean) {

    }

    @Override
    protected int getItemView() {
        return R.layout.item_my_order;
    }


}
