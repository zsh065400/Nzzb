package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.CarBean;
import zzbcar.cckj.com.nzzb.utils.GlideApp;

public class AaAdapter extends BaseAdapter {

    private List<CarBean.DataBean> objects = new ArrayList<CarBean.DataBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public AaAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public CarBean.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.car_brand, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((CarBean.DataBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(CarBean.DataBean object, ViewHolder holder, int position) {
        //TODO implement
        holder.tvCar.setText(object.getName());
        GlideApp
                .with(context)
                .load(object.getLogo())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivCar);
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
