package zzbcar.cckj.com.nzzb.adapter.search;

import android.content.Context;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;

/**
 * Created by Scout
 * Created on 2017/11/16 19:04.
 */

public class SimpleTextAdapter extends BaseRecycleViewAdapter<String> {

    public SimpleTextAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, String s) {
        holder.setText(R.id.tv_simple_1_line, s);
    }

    @Override
    protected int getItemView() {
        return R.layout.simple_1_line_text;
    }
}
