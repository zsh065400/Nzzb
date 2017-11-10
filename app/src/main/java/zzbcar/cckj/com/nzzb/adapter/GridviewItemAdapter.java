package zzbcar.cckj.com.nzzb.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.BrandCarBean;

public class GridviewItemAdapter extends BaseAdapter {


    private LayoutInflater layoutInflater;

    public GridviewItemAdapter(Context context, List<BrandCarBean.DataBean> objects) {
        this.context = context;
        this.objects = objects;
        layoutInflater=LayoutInflater.from(context);
    }

    private Context context;
    private List<BrandCarBean.DataBean> objects ;


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public BrandCarBean.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.gridview_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((BrandCarBean.DataBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(BrandCarBean.DataBean object, ViewHolder holder) {
        //TODO implement
        Picasso.with(context).load(object.getLogo()).into(holder.ivGridBrandItem);
    }

    protected class ViewHolder {
        private ImageView ivGridBrandItem;

        public ViewHolder(View view) {
            ivGridBrandItem = (ImageView) view.findViewById(R.id.iv_grid_brand_item);
        }
    }
}
