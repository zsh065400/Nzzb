package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/8.
 */

public class BreakRulDoingFragment extends BaseFragment {

    @BindView(R.id.rv_break_doing)
    RecyclerView rvBreakDoing;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.break_rule_doing;
    }

    @Override
    public void initDatas() {
        //获取网络数据
        getData();
    }

    private void getData() {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
