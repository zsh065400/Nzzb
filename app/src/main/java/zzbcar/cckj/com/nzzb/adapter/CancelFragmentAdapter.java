package zzbcar.cckj.com.nzzb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import zzbcar.cckj.com.nzzb.R;

/**
 * Created by Admin on 2017/11/6.
 */

public class CancelFragmentAdapter extends RecyclerView.Adapter {
    private View view;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(parent.getContext(), R.layout.fragment_cancel_item, null);
        return new NoDoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class NoDoneViewHolder extends RecyclerView.ViewHolder {
        NoDoneViewHolder(View view) {
            super(view);
        }
    }

}
