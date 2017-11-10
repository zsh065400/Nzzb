package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.CompleteFragmentAdapter;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/4.
 */

public class CompleteFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private CompleteFragmentAdapter completeFragmentAdapter;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = View.inflate(mActivity, R.layout.fragment_complete, null);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_complete_fragment_done);

    }

    @Override
    public void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false));
        completeFragmentAdapter = new CompleteFragmentAdapter();
        recyclerView.setAdapter(completeFragmentAdapter);


    }
}
