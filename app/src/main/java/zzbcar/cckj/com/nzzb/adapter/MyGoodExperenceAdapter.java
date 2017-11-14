package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;

/**
 * Created by Admin on 2017/11/9.
 */

public class MyGoodExperenceAdapter extends PagerAdapter {
    private Context mContext;
    private List<MainPageBean.DataBean.ActivityBean> mDatas;

    private LayoutInflater inflater;

    public MyGoodExperenceAdapter(Context context, List<MainPageBean.DataBean.ActivityBean> list) {
        this.mContext = context;
        this.mDatas = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final View view = inflater.inflate(R.layout.good_experence_item, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) itemClickListener.onItemClick(view, position);
            }
        });
        final MainPageBean.DataBean.ActivityBean bean = mDatas.get(position);

        ImageView pic = (ImageView) view.findViewById(R.id.iv_chaozhi_pic);
        TextView name = (TextView) view.findViewById(R.id.tv_chaozhi_name);
        TextView price = (TextView) view.findViewById(R.id.tv_chaozhi_price);
        Picasso.with(mContext).load(bean.getPicUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fit()
                .into(pic);
        name.setText(bean.getCarName());
        price.setText(bean.getPrice() + "");
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
