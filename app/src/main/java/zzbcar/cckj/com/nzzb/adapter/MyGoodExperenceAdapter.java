package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;

/**
 * Created by Admin on 2017/11/9.
 */

public class MyGoodExperenceAdapter extends PagerAdapter {
    private Context mContext;
    private List<Integer> mDatas;

    private LayoutInflater inflater;
    private ImageView iv_good_experence_item;

    public MyGoodExperenceAdapter(Context context, List<Integer> list) {
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
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.good_experence_item, container, false);
        iv_good_experence_item = (ImageView) view.findViewById(R.id.iv_chaozhi_pic);
        iv_good_experence_item.setImageResource(mDatas.get(position));
        iv_good_experence_item.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
