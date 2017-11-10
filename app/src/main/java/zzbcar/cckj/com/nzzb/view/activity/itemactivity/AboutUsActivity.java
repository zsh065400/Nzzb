package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class AboutUsActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {

        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new TitleBuilder(this).setTitleText("关于至尊宝").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}
