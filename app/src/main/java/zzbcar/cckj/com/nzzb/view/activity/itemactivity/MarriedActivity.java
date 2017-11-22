package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/7.
 */

public class MarriedActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_connection_us;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_married_rent;
    }

    @Override
    protected void initViews() {
        bt_connection_us = (Button) findViewById(R.id.bt_connection_us);

    }

    @Override
    protected void initDatas() {
        bt_connection_us.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_connection_us:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13295815771"));
                if (ActivityCompat.checkSelfPermission(MarriedActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);


                break;
        }
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null);
    }
}
