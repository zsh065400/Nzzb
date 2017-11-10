package zzbcar.cckj.com.nzzb.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import zzbcar.cckj.com.nzzb.R;

import static zzbcar.cckj.com.nzzb.view.activity.MainActivity.api;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private ImageView iv_wx;
    private ImageView iv_qq;
    private Tencent mTencent;
    private MainActivity mainActivity;
    private UMShareAPI mShareAPI = null;
    private SHARE_MEDIA platform = null;
    private EditText etPhoneNumber;
    private EditText et_code;
    private TextView tvSure;
    private TextView tvProtocol;
    private TextView tv_call;
    private TextView getCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;

    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
            iv_wx = (ImageView) findViewById(R.id.iv_wx);
            iv_qq = (ImageView) findViewById(R.id.iv_qq);
            etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
            et_code = (EditText) findViewById(R.id.et_code);
            tvSure = (TextView) findViewById(R.id.tv_login_activity_sure);
            tvSure = (TextView) findViewById(R.id.tv_protocol);
            tv_call = (TextView) findViewById(R.id.tv_call);
            getCode = (TextView) findViewById(R.id.get_code);
            tvProtocol = (TextView) findViewById(R.id.tv_protocol);

            initEtPhoneNumber();
        }
    }

    private void initEtPhoneNumber() {

    }


    @Override
    protected void initData() {
        iv_qq.setOnClickListener(this);
        iv_wx.setOnClickListener(this);
        etPhoneNumber.setOnClickListener(this);
        et_code.setOnClickListener(this);
        tvSure.setOnClickListener(this);
        tvProtocol.setOnClickListener(this);
        tv_call.setOnClickListener(this);
        getCode.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_qq:
                loginQQ();
                break;
            case R.id.iv_wx:
                loginWX();
                break;
        }

    }

    private void loginWX() {

        if (api != null && api.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            //授权读取用户信息



            req.scope = "snsapi_userinfo";
            //自定义信息
            req.state = "wechat_sdk_demo_test";
            //向微信发送请求
            api.sendReq(req);
            mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
            mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
        } else {
            Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show();
        }


    }


    private void loginQQ() {
        mTencent = Tencent.createInstance("1106313801", getApplicationContext());
        mTencent.login(LoginActivity.this, "all", new BaseUiListener());

    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            Toast.makeText(mainActivity, "登陆成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(mainActivity, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(mainActivity, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mainActivity, "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
