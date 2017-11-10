package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class HelpCenterActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {

        return R.layout.activity_help_center;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new TitleBuilder(this).setTitleText("帮助中心").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

    }


}
