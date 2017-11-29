package zzbcar.cckj.com.nzzb;

import android.content.Context;

import java.util.List;

import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;

/**
 * Created by Admin on 2017/11/29.
 */

public class MarqueenMessageAdapter extends BaseRecycleViewAdapter<MainPageBean.DataBean.MarqueeBean> {

    public MarqueenMessageAdapter(Context context, List datas) {
        super(context, datas);
    }



    @Override
    protected void convert(MyViewHolder holder, int position, MainPageBean.DataBean.MarqueeBean marqueeBean) {
   holder.setText(R.id.tv_marquee_message,marqueeBean.getTitle());
    }

    @Override
    protected int getItemView() {
        return R.layout.activity_marquee_list;
    }


}
