package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/8.
 */

public class BreakRulDoingFragment extends BaseFragment {

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
}
