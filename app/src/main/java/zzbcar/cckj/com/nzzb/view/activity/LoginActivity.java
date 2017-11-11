package zzbcar.cckj.com.nzzb.view.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_code)
    EditText etCode;

    @BindView(R.id.tv_signin)
    TextView tvSignin;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    @BindView(R.id.iv_wx_signin)
    ImageView ivWxSignin;
    @BindView(R.id.iv_qq_signin)
    ImageView ivQQSignin;

    private Tencent mTencent;
    private IWXAPI mWxApi;
    private UMShareAPI mShareAPI = null;

    private String mPhone;
    private String mCode;
    /*登录类型，0手机、1QQ、2微信
    *
    * 根据登录类型,上传相应的参数
        0:电话号码+"-"+验证码
        1:微信openId
        2:qq号
        3.公众号
    * */
    private String mType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        // TODO: 2017/11/10 权限请求逻辑需要调整
        String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
        ActivityCompat.requestPermissions(this, mPermissionList, 123);
    }

    @Override
    protected void initListeners() {
        ivQQSignin.setOnClickListener(this);
        ivWxSignin.setOnClickListener(this);
        tvSignin.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mShareAPI = UMShareAPI.get(this);
        mWxApi = MyApplication.getWxApi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_qq_signin:
                loginQQ();
                break;
            case R.id.iv_wx_signin:
                loginWX();
                break;
            case R.id.tv_signin:
                doSignin();
                break;
            case R.id.tv_get_code:
                getVerifyCode();
                break;
        }
    }

    private void getVerifyCode() {
        /*同步更新界面显示，倒计时验证码*/
        mPhone = etPhoneNumber.getText().toString().trim();
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_GET_CODE, "phone", mPhone);
        OkGo.<String>get(url).tag(TAG).execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                /*默认验证码9527，此处不做其它操作*/
                //可以提示验证码已发送
            }

            @Override
            public void onError(Response<String> response) {

            }
        });
    }

    private void doSignin() {
        mPhone = etPhoneNumber.getText().toString().trim();
        mCode = etCode.getText().toString().trim();
        mType = "0";
        if (TextUtils.isEmpty(mCode)) {
            Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else {
            final String url = OkHttpUtil.obtainGetUrl(Constant.API_SIGN,
                    "type", mType,
                    "param", mPhone + "-" + 9527);
            OkGo.<String>get(url).tag(TAG).execute(new StringCallback() {

                @Override
                public void onSuccess(Response<String> response) {
                    final SigninBean bean = GsonUtil.parseJsonWithGson(response.body(), SigninBean.class);
                    /*缓存用户数据，可用于自动登录等*/
                    SPUtils.saveString(mContext, "User", response.body());
                    Log.d(TAG, "onSuccess: "+ response.body());
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(Response<String> response) {

                }
            });
        }
    }

    private void loginWX() {
        if (mWxApi != null && mWxApi.isWXAppInstalled()) {
//            SendAuth.Req req = new SendAuth.Req();

//            req.scope = "snsapi_userinfo";
            //自定义信息
//            req.state = "zzbcar";
            //向微信发送请求
//            mWxApi.sendReq(req);
            //授权登录
//            mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
            //获取用户信息
            mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
        } else {
            Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginQQ() {
        mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
//        mTencent = Tencent.createInstance("1106313801", MyApplication.getMyApplicaiton());
//        mTencent.login(LoginActivity.this, "all", new BaseUiListener());
    }

    /**
     * QQ登录回调
     */
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "登陆成功", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

    /**
     * 微信登录回调
     */
    UMAuthListener authListener = new MyAuthListener();

    private class MyAuthListener implements UMAuthListener {
        /**
         * @param platform 平台名称
         * @desc 授权开始的回调
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @param platform 平台名称
         * @param action   行为序号，开发者用不上
         * @param data     用户资料返回
         * @desc 授权成功的回调
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, final Map<String, String> data) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "登录成功，昵称：" + data.get("screen_name"), Toast.LENGTH_LONG).show();
                }
            });
        }

        /**
         * @param platform 平台名称
         * @param action   行为序号，开发者用不上
         * @param t        错误原因
         * @desc 授权失败的回调
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "登录错误", Toast.LENGTH_LONG).show();
                }
            });
            Log.d(TAG, "onError: " + t.getLocalizedMessage() + t.getMessage());
        }

        /**
         * @param platform 平台名称
         * @param action   行为序号，开发者用不上
         * @desc 授权取消的回调
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "用户取消", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
