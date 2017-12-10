package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.activity.MainActivity;

/**
 * Created by Admin on 2017/11/4.
 */

public class IdentiCompleteActivity extends BaseActivity implements View.OnClickListener{
    private Button bt_identifi_complete_rent;
    private FrameLayout fl_complate_success_maim;
    private List<MainPageBean.DataBean.CarListBean> carDatas;
    private Fragment mContentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_identicar_complete;
    }

    @Override
    protected void initViews() {
        bt_identifi_complete_rent = (Button) findViewById(R.id.bt_identifi_complete_rent);
        fl_complate_success_maim = (FrameLayout) findViewById(R.id.fl_complate_success_maim);
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        bt_identifi_complete_rent.setOnClickListener(this);
        new TitleBuilder(this).setTitleText("认证完成").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bt_identifi_complete_rent:

                MainActivity mActivity = MainActivity.getMainActivity();
                mActivity.setViewPager(1);

                break;
        }
        finish();
    }
}
