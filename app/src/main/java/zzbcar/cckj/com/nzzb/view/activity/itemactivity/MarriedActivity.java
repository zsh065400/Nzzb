package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/7.
 */

public class MarriedActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_connection_us;
    private ImageButton iv_married_back;
    private AlertDialog alertDialog;
    private Intent callIntent;
    private AlertDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_married_rent;
    }

    @Override
    protected void initViews() {
        bt_connection_us = (Button) findViewById(R.id.bt_connection_us);
        iv_married_back = (ImageButton) findViewById(R.id.iv_married_back);

    }

    @Override
    protected void initDatas() {
        bt_connection_us.setOnClickListener(this);
        iv_married_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_connection_us:

                builder = new AlertDialog.Builder(mContext);
                alertDialog = builder.setMessage("13295815771")
                        .setTitle("要拨打电话给客服么?")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                callService();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        }).create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);


                break;
            case R.id.iv_married_back:
                finish();
                break;
        }
    }

    private void callService() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13295815771"));
        startActivity(callIntent);
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null);
    }
}
