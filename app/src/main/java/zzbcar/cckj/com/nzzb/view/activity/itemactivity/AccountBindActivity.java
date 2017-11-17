package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class AccountBindActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {

        return R.layout.activity_account_bind;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {

    }


}
