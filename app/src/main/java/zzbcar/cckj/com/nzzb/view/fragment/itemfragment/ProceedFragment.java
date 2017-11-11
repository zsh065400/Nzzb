package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.ProceedFragmentAdapter;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/4.
 */

public class ProceedFragment extends BaseFragment {


    private RecyclerView recyclerView;
    private ProceedFragmentAdapter proceedFragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_proceed;
    }

    @Override
    public void initDatas() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        proceedFragmentAdapter = new ProceedFragmentAdapter();
        recyclerView.setAdapter(proceedFragmentAdapter);
    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_process_fragment_doing);

    }
}

