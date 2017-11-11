package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.CompleteFragmentAdapter;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/4.
 */

public class CompleteFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private CompleteFragmentAdapter completeFragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_complete;
    }

    @Override
    public void initDatas() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        completeFragmentAdapter = new CompleteFragmentAdapter();
        recyclerView.setAdapter(completeFragmentAdapter);


    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_complete_fragment_done);
    }
}
