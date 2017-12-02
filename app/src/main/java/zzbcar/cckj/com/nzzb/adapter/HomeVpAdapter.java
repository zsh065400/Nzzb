package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.LunbobBean;
import zzbcar.cckj.com.nzzb.utils.GlideApp;

/**
 * Created by Admin on 2017/11/1.
 */

public class HomeVpAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<LunbobBean.DataBean> mLunBoBeanList;
    private int mEntitySize;

    public HomeVpAdapter(Context context, List<LunbobBean.DataBean> mLunBoBeanList) {
        this.context = context;
        this.mLunBoBeanList = mLunBoBeanList;
        mEntitySize=mLunBoBeanList.size();
        layoutInflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mEntitySize*10000 ;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       ImageView imagtView= (ImageView) layoutInflater.inflate(R.layout.vp_item, container,false);
      container.addView(imagtView);
        LunbobBean.DataBean dataBean = mLunBoBeanList.get(position % mEntitySize);
        GlideApp
                .with(container)
                .load(dataBean.getPicUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imagtView);
        return imagtView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }
}
