package zzbcar.cckj.com.nzzb.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.utils.GlideApp;

/**
 * gridview适配器
 * <p>
 * Created by Scout
 * Created on 2017/11/3 13:39.
 */

public class GridItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<MainPageBean.DataBean.BrandBean> mViews;

    /*自定义GridAdapter*/

    public GridItemAdapter(Context mContext, List<MainPageBean.DataBean.BrandBean> mViews) {
        this.mContext = mContext;
        this.mViews = mViews;
    }

    @Override
    public int getCount() {
        /*此处返回数量，应根据传入的数组进行动态指定*/
        return mViews == null ? 0 : mViews.size();

    }

    @Override
    public Object getItem(int position) {

        return mViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder mv;
        if (convertView == null) {
            mv = new MyViewHolder();
            convertView =
                    LayoutInflater.from(mContext)
                            .inflate(R.layout.gridview_item, parent, false);
            mv.iv_grid_brand_item = convertView.findViewById(R.id.iv_grid_brand_item);
            mv.tv_home_brand_name = convertView.findViewById(R.id.tv_home_brand_name);
            convertView.setTag(mv);
        } else {
            mv = (MyViewHolder) convertView.getTag();
        }

        GlideApp
                .with(mContext)
                .load(mViews.get(position).getLogo())
//                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mv.iv_grid_brand_item);

        mv.tv_home_brand_name.setText(mViews.get(position).getName());
        return convertView;

    }

    static class MyViewHolder {
        ImageView iv_grid_brand_item;
        TextView tv_home_brand_name;
    }

}
