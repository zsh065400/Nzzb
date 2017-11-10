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
import zzbcar.cckj.com.nzzb.bean.SearchCarDefaultBean;

public class FindcarDefaultItemAdapter extends BaseAdapter {

    private List<SearchCarDefaultBean.DataBean> objects ;

    private Context context;
    private LayoutInflater layoutInflater;

    public FindcarDefaultItemAdapter(List<SearchCarDefaultBean.DataBean> objects, Context context) {
        this.objects = objects;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public SearchCarDefaultBean.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.findcar_default_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((SearchCarDefaultBean.DataBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(SearchCarDefaultBean.DataBean object, ViewHolder holder) {

        //TODO implement
                  holder.tvFindcarDefaultDetail.setText(object.getName());
            holder.tvFindcarDefaultMoney.setText(object.getPics());
            Picasso.with(context).load(object.getPics()).into(holder.ivFindcarDefault);
        }

        protected class ViewHolder {
            private ImageView ivFindcarDefault;
            private TextView tvFindcarDefaultDetail;
            private TextView tvFindcarDefaultMoney;

            public ViewHolder(View view) {
                ivFindcarDefault = (ImageView) view.findViewById(R.id.iv_findcar_default);
                tvFindcarDefaultDetail = (TextView) view.findViewById(R.id.tv_findcar_default_detail);
                tvFindcarDefaultMoney = (TextView) view.findViewById(R.id.tv_findcar_default_money);

        }
    }
}
