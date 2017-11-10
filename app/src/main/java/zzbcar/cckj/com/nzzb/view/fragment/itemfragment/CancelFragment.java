package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.CancelFragmentAdapter;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/4.
 */

public class CancelFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private View view;
    private CancelFragmentAdapter cancelFragmentAdapter;

    @Override
    public View initView(LayoutInflater inflater) {
        view = View.inflate(mActivity, R.layout.fragment_cancel, null);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_cancel_fragment_cancel);
    }

    @Override
    public void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false));
        cancelFragmentAdapter = new CancelFragmentAdapter();
        recyclerView.setAdapter(cancelFragmentAdapter);
    }
}
