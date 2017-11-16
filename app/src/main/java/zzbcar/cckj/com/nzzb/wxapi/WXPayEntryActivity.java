package zzbcar.cckj.com.nzzb.wxapi;

import android.content.Intent;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MyApplication.getWxApi().handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {//判断是否是微信支付状态码
            if (0 != resp.errCode) {//用户没有取消，执行微信支付结果确认逻辑，根据实际需求请求服务器进行支付结果确认
                asyncShowToast("支付成功");
            } else {
                asyncShowToast("支付失败, 请重试!");
            }
            finish();
        }
    }
}

