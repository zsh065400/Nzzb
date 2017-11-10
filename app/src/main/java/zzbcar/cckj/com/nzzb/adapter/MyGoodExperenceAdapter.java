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
    private Context context;
    private List<Integer> list;

    private LayoutInflater inflater;
    private ImageView iv_good_experence_item;

    public MyGoodExperenceAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.good_experence_item, container, false);
        iv_good_experence_item = (ImageView) view.findViewById(R.id.iv_good_experence_item);
        iv_good_experence_item.setImageResource(list.get(position));
        iv_good_experence_item.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
