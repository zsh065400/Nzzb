package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.CancelFragmentAdapter;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/4.
 */

public class CancelFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private View view;
    private CancelFragmentAdapter cancelFragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cancel;
    }

    @Override
    public void initDatas() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        cancelFragmentAdapter = new CancelFragmentAdapter();
        recyclerView.setAdapter(cancelFragmentAdapter);
    }

    @Override
    public void initViews(View view) {

    }
}
