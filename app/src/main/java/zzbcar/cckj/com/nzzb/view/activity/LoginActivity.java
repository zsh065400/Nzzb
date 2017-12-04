package zzbcar.cckj.com.nzzb.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.bean.BaseBean;
import zzbcar.cckj.com.nzzb.bean.GeneralResponseBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.NoLineClickSpan;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_code)
    EditText etCode;

    @BindView(R.id.tv_signin)
    TextView tvSignin;
    @BindView(R.id.tv_call)
    TextView tvProtocol;
    @BindView(R.id.tv_protocol)
    TextView tvCall;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    @BindView(R.id.iv_wx_signin)
    ImageView ivWxSignin;
    @BindView(R.id.iv_qq_signin)
    ImageView ivQQSignin;

    //    private Tencent mTencent;
    private IWXAPI mWxApi;
    private UMShareAPI mShareAPI = null;

    private String mPhone;
    private String mCode;

    //    private String qqOpenId;
//    private String wxOpenId;
    /*1为QQ,2为微信*/
    private String thridType;
    private String openId;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // TODO: 2017/11/10 权限请求逻辑需要调整
        String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
        ActivityCompat.requestPermissions(this, mPermissionList, 123);
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);

        initProtocol();

        initCall();
    }

    /**
     * 拨打热线电话
     */
    private void initCall() {
        tvCall.setText("如需帮助可拨打至尊宝豪车共享服务热线");
        SpannableString spCall = new SpannableString("0571-86815027");
        String s2 = "0571-86815027";
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        NoLineClickSpan clickSpan1 = new NoLineClickSpan("#ff4049") {
            @Override
            public void onClick(View widget) {
                Toast.makeText(LoginActivity.this, "电话热线", Toast.LENGTH_SHORT).show();
                builder.setTitle("拨打给客服？");
                builder.setMessage("0571-86815027");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13295815771"));
                        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        };
        spCall.setSpan(clickSpan1, spCall.length() - s2.length(), spCall.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvCall.append(spCall);
        tvCall.setMovementMethod(LinkMovementMethod.getInstance());
        //设置文本不高亮，如果需要点击后高亮文本，删掉这句即可
        tvCall.setHighlightColor(Color.parseColor("#ff4049"));

    }

    /**
     * 协议
     */
    private void initProtocol() {
        tvProtocol.setText("未注册至尊宝豪车共享的手机号，点击确认时自动注册，且代表您已同意");
        SpannableString spStr = new SpannableString("《至尊宝豪车共享服务协议》");
        String s2 = "《至尊宝豪车共享服务协议》";
        NoLineClickSpan clickSpan2 = new NoLineClickSpan("#ff4049") {
            @Override
            public void onClick(View widget) {
                Toast.makeText(LoginActivity.this, "至尊宝豪车共享服务协议", Toast.LENGTH_SHORT).show();
            }
        };

        spStr.setSpan(clickSpan2, spStr.length() - s2.length(), spStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvProtocol.append(spStr);
        tvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
        //设置文本不高亮，如果需要点击后高亮文本，删掉这句即可
        tvProtocol.setHighlightColor(Color.parseColor("#ff4049"));

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
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.next = null;
        MyApplication.nextBundle = null;
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
                /*正常登录状态*/
                mPhone = etPhoneNumber.getText().toString().trim();
                mCode = etCode.getText().toString().trim();
                if (TextUtils.isEmpty(mPhone) || TextUtils.isEmpty(mCode)) {
                    Toast.makeText(mContext, "手机号或验证码为空", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (((TextView) view).getText().toString().equals("确认")) {
                doSignin("0", String.format("%s-%s", mPhone, mCode));
//                } else {
////                    bindPhone();
//                    bindThird("1", openId);
//                }
                break;
            case R.id.tv_get_code:
                getVerifyCode();
                break;
        }
    }

    private void getVerifyCode() {
        /*同步更新界面显示，倒计时验证码*/
        mPhone = etPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            Toast.makeText(mContext, "手机号为空", Toast.LENGTH_SHORT).show();
            return;
        }
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_GET_CODE, "mobile", mPhone);
        OkGo.<String>get(url).tag(TAG).execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                final GeneralResponseBean responseBean = GsonUtil.parseJsonWithGson(response.body(), GeneralResponseBean.class);
                if (responseBean.getErrno() == 0) {
                    handler.sendEmptyMessage(MSG_SEND_CODE_SUCCESS);
                } else {
                    handler.sendEmptyMessage(MSG_SEND_CODE_ERROR);
                }
            }

            @Override
            public void onError(Response<String> response) {
                handler.sendEmptyMessage(MSG_SEND_CODE_ERROR);
            }
        });
    }

    private void doSignin(final String type, String param) {
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_SIGN,
                "type", type,
                "param", param);
        if (TextUtils.isEmpty(param)) {
            Toast.makeText(LoginActivity.this, "用户信息不全，请重试", Toast.LENGTH_SHORT).show();
        } else {
            OkGo.<String>get(url).tag(TAG).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    final String body = response.body();
                    final SigninBean bean = GsonUtil.parseJsonWithGson(body, SigninBean.class);
                    /*缓存用户数据，可用于自动登录等*/
                    final int errno = bean.getErrno();
                    if (errno == 0) {
                        SPUtils.saveString(mContext, "User", body);
                        /*如果之前使用第三方登录，则自动绑定*/
                        final SigninBean.DataBean.MemberBean member = bean.getData().getMember();
                        if (TextUtils.isEmpty(member.getQqOpenId()) && thridType.equals("1"))
                            bindThird(thridType, openId, bean);
                        else if (TextUtils.isEmpty(member.getWxOpenId()) && thridType.equals("2"))
                            bindThird(thridType, openId, bean);
                        asyncShowToast("登陆成功");
                        /*登陆成功后跳转*/
                        toNextActivity();
                        finish();
                        /*此处判断可改换成更加稳妥的*/
                    } else if ((type.equals("1") || type.equals("2")) && errno == 3) {
                        asyncShowToast("请在当前页面使用手机登陆即可自动绑定");
                        changeSignStatus(0);
                    } else {
                        asyncShowToast(bean.getMessage());
                    }
                }

                @Override
                public void onError(Response<String> response) {
                    asyncShowToast(response.getException().getLocalizedMessage());
                }
            });
        }
    }


    /**
     * 因接口问题，当三方登录未与手机号绑定时，需要改换登录策略
     * 由原先的登录接口，改换成绑定接口
     * 绑定完成后，再次通过对应的三方登录即可完成登录功能
     */
    private void changeSignStatus(int status) {
        if (status == 0)
            tvSignin.setText("绑定手机号");
        else if (status == 1) {
            tvSignin.setText("使用该手机号或该第三方登录");
            tvSignin.setEnabled(false);
        } else if (status == 2)
            tvSignin.setEnabled(true);
    }

    private void bindThird(final String type, String param, final SigninBean signinBean) {
        if (openId == null || openId.equals("")) {
            return;
        }
        final int id = signinBean.getData().getMember().getId();
        final String token = signinBean.getData().getToken();
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_BIND_THIRD,
                "userId", id + "",
                "token", token,
                "type", type,
                "param", param);
        OkGo.<String>get(url).tag(TAG).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final BaseBean bean = GsonUtil.parseJsonWithGson(response.body(), BaseBean.class);
                if (bean.getErrno() == 0) {
                    final SigninBean.DataBean.MemberBean member = signinBean.getData().getMember();
                    if (type.equals("1")) {
                        member.setQqOpenId(openId);
                    } else {
                        member.setWxOpenId(openId);
                    }
                    SPUtils.putSignInfo(LoginActivity.this, member);
                    asyncShowToast("用户信息已关联，下次可直接登录");
                } else asyncShowToast(bean.getMessage());
                openId = null;
            }

            @Override
            public void onError(Response<String> response) {
                asyncShowToast(response.getException().getLocalizedMessage());
                openId = null;
            }
        });
    }

    /**
     * 第三方授权后通过该方法绑定手机号
     */
    private void bindPhone() {
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_BIND_QQWX,
                "mobile", mPhone,
                "authCode", mCode);
//                "qq", qqOpenId,
//                "wxOpenId", wxOpenId);
        OkGo.<String>get(url).tag(TAG).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final BaseBean bean = GsonUtil.parseJsonWithGson(response.body(), BaseBean.class);
                if (bean.getErrno() == 0) {
                    asyncShowToast("绑定成功,使用该手机号或该第三方即可登录");
                    changeSignStatus(1);
                } else asyncShowToast(bean.getMessage());
            }

            @Override
            public void onError(Response<String> response) {
                asyncShowToast(response.getException().getLocalizedMessage());
            }
        });
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
     * 跳转
     */
    protected void toNextActivity() {
        if (MyApplication.next != null) {
            toActivity(MyApplication.next, MyApplication.nextBundle);
            MyApplication.next = null;
            MyApplication.nextBundle = null;
        }
    }

    private TimerHandler handler = new TimerHandler();

    private static final int MSG_SEND_CODE_SUCCESS = 0;
    private static final int MSG_SEND_CODE_ERROR = 1;
    private static final int MSG_TIMER = 2;

    private int time = 60;

    private class TimerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            /*验证码倒计时*/
            switch (msg.what) {
                case MSG_SEND_CODE_SUCCESS:
                    Toast.makeText(mContext, "验证码发送成功", Toast.LENGTH_SHORT).show();
                    handler.sendEmptyMessage(MSG_TIMER);
                    break;

                case MSG_SEND_CODE_ERROR:
                    Toast.makeText(mContext, "验证码发送失败，请重试", Toast.LENGTH_SHORT).show();
                    break;

                case MSG_TIMER:
                    startTimer();
                    break;
            }
        }
    }

    /**
     * 设置是否可发送短信
     */
    private void chageSendStatus(boolean canSend) {
        if (canSend) {
            tvGetCode.setEnabled(true);
            tvGetCode.setClickable(true);
        } else {
            tvGetCode.setEnabled(false);
            tvGetCode.setClickable(false);
        }
    }

    private void startTimer() {
        if (time > 0) {
            tvGetCode.setText(String.format("倒计时%s秒", time));
            time -= 1;
            chageSendStatus(false);
            handler.sendEmptyMessageDelayed(MSG_TIMER, 1000);
        } else {
            tvGetCode.setText("获取验证码");
            time = 60;
            chageSendStatus(true);
        }
    }

    /**
     * QQ登录回调
     */
//    private class BaseUiListener implements IUiListener {
//        @Override
//        public void onComplete(Object o) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(mContext, "登陆成功", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        @Override
//        public void onError(UiError uiError) {
//
//        }
//
//        @Override
//        public void onCancel() {
//
//        }
//    }

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
            for (Map.Entry<String, String> entry : data.entrySet()) {
                Log.d(TAG, "onComplete: " + entry.getKey() + ":" + entry.getValue());
            }
//            final String openid = data.get("openid");
            openId = data.get("openid");
            switch (platform) {
                case QQ:
//                    qqOpenId = openid;
                    doSignin("2", openId);
                    thridType = "1";
                    break;
                case WEIXIN:
//                    wxOpenId = openid;
                    doSignin("1", openId);
                    thridType = "2";
                    break;
            }
            asyncShowToast("登录成功，昵称：" + data.get("screen_name"));
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
