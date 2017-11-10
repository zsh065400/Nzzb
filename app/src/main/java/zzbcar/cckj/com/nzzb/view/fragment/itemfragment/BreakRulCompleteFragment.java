package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.view.LayoutInflater;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/8.
 */

public class BreakRulCompleteFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater) {
        View view = View.inflate(MyApplication.getContext(), R.layout.break_rule_complete, null);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {

    }
}
