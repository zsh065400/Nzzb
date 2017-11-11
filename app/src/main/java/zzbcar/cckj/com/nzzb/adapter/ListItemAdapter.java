package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.CarDefaultBean;

/**
 * Created by Admin on 2017/11/7.
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.MyViewHolder>{
    private List<CarDefaultBean.DataBean> list;
    private Context mContext;
    private View view;

    /*应根据不同的数据进行动态适配*/

    public ListItemAdapter(List<CarDefaultBean.DataBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

         holder.tv_item_car.setText(list.get(position).getCarName());
        Picasso.with(mContext)
                //load()下载图片
                .load(list.get(position).getPics())

                //下载中显示的图片
                .placeholder(R.mipmap.ic_launcher)

                //下载失败显示的图片
                .error(R.mipmap.ic_launcher)

                //init()显示到指定控件
                .into(holder.iv_item_car);

    }



    @Override
    public int getItemCount() {

        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

          private ImageView iv_item_car;
          private TextView tv_item_car;

        public MyViewHolder(View itemView) {

            super(itemView);
            iv_item_car = itemView.findViewById(R.id.iv_item_car);
            tv_item_car = itemView.findViewById(R.id.tv_item_car);
        }
    }
}
