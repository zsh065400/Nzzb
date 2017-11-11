package zzbcar.cckj.com.nzzb.adapter.main;

import android.content.Context;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;

/**
 * 主页车型列表
 * <p>
 * Created by Scout
 * Created on 2017/11/11 14:59.
 */

public class CarTypeAdapter extends BaseRecycleViewAdapter {


    public CarTypeAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, Object o) {
        /*做更新操作*/
    }

    @Override
    protected int getItemView() {
        return R.layout.fresh_car_item;
    }

}
