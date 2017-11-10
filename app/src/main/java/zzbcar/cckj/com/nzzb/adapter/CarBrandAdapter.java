package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.CarBean;

public class CarBrandAdapter extends BaseAdapter {

    private List<CarBean.DataBean> data ;

    private Context context;
    private LayoutInflater layoutInflater;

    public CarBrandAdapter(List<CarBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CarBean.DataBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.aa, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((CarBean.DataBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(CarBean.DataBean data, ViewHolder holder) {
        //TODO implement
        holder.tvCar.setText(data.getName());
        Picasso.with(context).load(data.getLogo()).into(holder.ivCar);
    }

    protected class ViewHolder {
        private ImageView ivCar;
    private TextView tvCar;

        public ViewHolder(View view) {
            ivCar = (ImageView) view.findViewById(R.id.iv_car);
            tvCar = (TextView) view.findViewById(R.id.tv_car);
        }
    }
}
