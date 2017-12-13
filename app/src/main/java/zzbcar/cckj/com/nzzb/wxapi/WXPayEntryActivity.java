package zzbcar.cckj.com.nzzb.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.PayActivity;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_blank;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = MyApplication.getWxApi();
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {//判断是否是微信支付状态码
            if (resp.errCode == 0) {
                //支付成功
                asyncShowToast("支付成功");
            } else if (resp.errCode == -1) {
                //支付失败
                asyncShowToast("支付失败, 请重试!");
            } else if (resp.errCode == -2) {
                //取消支付
                asyncShowToast("用户取消");
            }
        }
        finish();
        /*结束activity*/
        MyApplication.appManager.finishActivity(PayActivity.class);
//        MyApplication.appManager.finishActivity(CarDetailActivity.class);
    }
}

