package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.REGutil;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/4.
 */

public class CarFriendIdentifiActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_carfriend_identifi_commit;
    private EditText et_carfriend_identifi_name;
    private EditText et_carfriend_identifi_phone;


    @Override
    protected int getLayoutId() {
        return R.layout.car_friend_identifi;
    }

    @Override
    protected void initViews() {
        bt_carfriend_identifi_commit = (Button) findViewById(R.id.bt_carfriend_identifi_commit);
        et_carfriend_identifi_name = (EditText) findViewById(R.id.et_carfriend_identifi_name);
        et_carfriend_identifi_phone = (EditText) findViewById(R.id.et_carfriend_identifi_phone);

        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        bt_carfriend_identifi_commit.setOnClickListener(this);


        new TitleBuilder(this).setTitleText("填写信息").setRightIco(R.mipmap.call_service).setRightIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CarFriendIdentifiActivity.this);
                AlertDialog alertDialog = builder.setMessage("13295815771")
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
            }
        }).setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void callService() {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13295815771"));
        if (ActivityCompat.checkSelfPermission(CarFriendIdentifiActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_carfriend_identifi_commit:
                String name = et_carfriend_identifi_name.getText().toString().trim();
                String phone = et_carfriend_identifi_phone.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)){
                    Toast.makeText(mContext, "姓名和手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!REGutil.checkCellphone(phone)){
                    Toast.makeText(mContext, "手机格式错误了，请检查重试", Toast.LENGTH_SHORT).show();
                    return;
                }
               commitInfo(name,phone);

                break;
        }
    }

    private void commitInfo(String name, String phone) {
        final  String url= OkHttpUtil.obtainGetUrl(Constant.API_ADD_OWNER,
                "name",name,
                "mobile",phone);
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Toast.makeText(mContext, "提交成功", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(mContext, "提交失败，请再次提交", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
