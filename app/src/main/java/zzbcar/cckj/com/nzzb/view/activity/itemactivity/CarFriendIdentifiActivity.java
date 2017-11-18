package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.view.View;
import android.widget.Button;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/4.
 */

public class CarFriendIdentifiActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_carfriend_identifi_commit;

    @Override
    protected int getLayoutId() {
        return R.layout.car_friend_identifi;
    }

    @Override
    protected void initViews() {
        bt_carfriend_identifi_commit = (Button) findViewById(R.id.bt_carfriend_identifi_commit);
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        bt_carfriend_identifi_commit.setOnClickListener(this);
        new TitleBuilder(this).setTitleText("填写信息").setRightIco(R.mipmap.call_service).setRightIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }).setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_carfriend_identifi_commit:
                break;
        }
    }
}
