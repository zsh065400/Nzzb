package zzbcar.cckj.com.nzzb.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 盛放gridview的viewpager适配器
 *
 * Created by Scout
 * Created on 2017/11/3 13:55.
 */

public class GridPagerAdapter extends PagerAdapter {

    private List<View> mViews;

    public GridPagerAdapter(List<View> views) {

        mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View view = mViews.get(position);
        container.addView(view);
        return view;
    }
}
