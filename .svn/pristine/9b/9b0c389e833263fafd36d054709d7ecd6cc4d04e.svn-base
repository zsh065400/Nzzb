package zzbcar.cckj.com.nzzb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;

/**
 * gridview适配器
 *
 * Created by Scout
 * Created on 2017/11/3 13:39.
 */

public class GridItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mViews;

    /*自定义GridAdapter*/

    public GridItemAdapter(Context context, List<String> views) {
        mContext = context;
        mViews = views;
    }

    @Override
    public int getCount() {
        /*此处返回数量，应根据传入的数组进行动态指定*/
//        return mViews.size();
        return 8;
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
        /*在此处改变ImageView上显示的内容，注意复用，此处没写，仅供Demo参考*/
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, parent, false);
        }
        return convertView;
    }
}
