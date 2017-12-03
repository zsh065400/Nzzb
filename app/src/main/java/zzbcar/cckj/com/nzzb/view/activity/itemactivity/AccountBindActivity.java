package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.BaseBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class AccountBindActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_qq_bind)
    ImageView ivQQBind;

    @BindView(R.id.iv_weixin_bind)
    ImageView ivWxBind;

    @BindView(R.id.tv_qq_info)
    TextView tvQQInfo;

    @BindView(R.id.tv_wx_info)
    TextView tvWxInfo;

    private boolean isQQBind = false;
    private boolean isWxBind = false;
    private SigninBean.DataBean.MemberBean signInfo;
    private String token;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_bind;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("账号绑定").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signInfo = SPUtils.getSignInfo(this);
        token = SPUtils.getToken(this);
        changeStatus(signInfo);
        mShareAPI = UMShareAPI.get(this);
        mWxApi = MyApplication.getWxApi();
    }

    private void changeStatus(SigninBean.DataBean.MemberBean signInfo) {
        final String qq = signInfo.getQq();
        final String qqOpenId = signInfo.getQqOpenId();
        final String wxOpenId = signInfo.getWxOpenId();
        final String wxPubOpenId = signInfo.getWxPubOpenId();
        if (!TextUtils.isEmpty(qq) || !TextUtils.isEmpty(qqOpenId)) {
            isQQBind = true;
            changeStatus("1", isQQBind);
        }
        if (!TextUtils.isEmpty(wxOpenId) || !TextUtils.isEmpty(wxPubOpenId)) {
            isWxBind = true;
            changeStatus("2", isWxBind);
        }
    }

    @Override
    protected void initListeners() {
        tvQQInfo.setOnClickListener(this);
        tvWxInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_qq_info:
                if (isQQBind) {
                    unBindThird("1");
                } else {
                    loginQQ();
                }
                break;

            case R.id.tv_wx_info:
                if (isWxBind) {
                    unBindThird("2");
                } else
                    loginWX();
                break;
        }
    }

    private IWXAPI mWxApi;
    private UMShareAPI mShareAPI = null;

    private void loginWX() {
        if (mWxApi != null && mWxApi.isWXAppInstalled()) {
            mShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
        } else {
            Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginQQ() {
        mShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
    }

    private void bindThird(final String type, String param) {
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_BIND_THIRD,
                "userId", String.valueOf(signInfo.getId()),
                "token", token,
                "type", type,
                "param", param);
        OkGo.<String>get(url).tag(TAG).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final BaseBean bean = GsonUtil.parseJsonWithGson(response.body(), BaseBean.class);
                if (bean.getErrno() == 0) {
                    asyncShowToast("绑定成功，下次可直接登录");
                    if (type.equals("1")) {
                        isQQBind = true;
                        changeStatus(type, isQQBind);
                    } else {
                        isWxBind = true;
                        changeStatus(type, isWxBind);
                    }
                } else asyncShowToast(bean.getMessage());
            }

            @Override
            public void onError(Response<String> response) {
                asyncShowToast(response.getException().getLocalizedMessage());
            }
        });
    }

    private void unBindThird(final String type) {
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_UNBIND_THIRD,
                "userId", String.valueOf(signInfo.getId()),
                "token", token,
                "type", type);
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final String body = response.body();
                final BaseBean baseBean = GsonUtil.parseJsonWithGson(body, BaseBean.class);
                if (baseBean.getErrno() == 0) {
                    asyncShowToast("解绑成功，将不能使用该方式登录");
                    if (type.equals("1")) {
                        isQQBind = false;
                        changeStatus(type, isQQBind);
                    } else {
                        isWxBind = false;
                        changeStatus(type, isWxBind);
                    }
                }
            }
        });
    }

    /**
     * 改变显示状态
     */
    private void changeStatus(String type, boolean isBind) {
        switch (type) {
            case "1"://QQ
                if (isBind) {
                    ivQQBind.setImageResource(R.mipmap.qq_light);
                    tvQQInfo.setText("QQ已绑定");
                } else {
                    ivQQBind.setImageResource(R.mipmap.qq_low);
                    tvQQInfo.setText("QQ未绑定");
                }
                break;

            case "2"://微信
                if (isBind) {
                    ivWxBind.setImageResource(R.mipmap.wx_light);
                    tvWxInfo.setText("微信已绑定");
                } else {
                    ivWxBind.setImageResource(R.mipmap.wx_low);
                    tvWxInfo.setText("微信未绑定");
                }
                break;
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
            for (Map.Entry<String, String> entry : data.entrySet()) {
                Log.d(TAG, "onComplete: " + entry.getKey() + ":" + entry.getValue());
            }
            String openId = data.get("openid");
            switch (platform) {
                case QQ:
                    bindThird("1", openId);
                    break;
                case WEIXIN:
                    bindThird("2", openId);
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
