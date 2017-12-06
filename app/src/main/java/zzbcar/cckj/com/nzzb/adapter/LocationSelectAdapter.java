package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.LocationSelectBean;

/**
 * Created by Admin on 2017/11/14.
 */

public class LocationSelectAdapter extends RecyclerView.Adapter<LocationSelectAdapter.MyViewHolder> {
    private List<LocationSelectBean.DataBean> data;
    private Context mContext;
    private View view;
    private OnRecycleItemListener listener;
    public LocationSelectAdapter(List<LocationSelectBean.DataBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }
    public void addRecycleItemListener(OnRecycleItemListener listener){
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.location_city_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
           myViewHolder.tv_location_city_item.setText(data.get(i).getName());
        myViewHolder.tv_location_city_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnRecycleItemClick(view,data.get(i));
            }
        });
    }



    @Override
    public int getItemCount() {

        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       private TextView tv_location_city_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_location_city_item = itemView.findViewById(R.id.tv_location_city_item);

        }
    }
    public interface OnRecycleItemListener <T>{
        void OnRecycleItemClick(View v,T o);
    }
}
