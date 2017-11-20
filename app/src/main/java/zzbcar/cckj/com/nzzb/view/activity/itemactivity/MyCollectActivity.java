package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/20.
 */

public class MyCollectActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {

        return R.layout.activity_mycollect;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("我的收藏").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
