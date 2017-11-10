package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/4.
 */

public class UnProceedFragment extends BaseFragment {
    private RecyclerView recyclerView;
    @Override
    public View initView(LayoutInflater inflater) {
        View view = View.inflate(mActivity, R.layout.fragment_unproceed, null);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_unproceed_fragment_nodone);

    }

    @Override
    public void initData() {

    }
}
