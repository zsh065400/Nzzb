package zzbcar.cckj.com.nzzb.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.view.activity.MainActivity;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = new MainActivity();

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
                Toast.makeText(mainActivity, "支付成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mainActivity, "支付失败, 请重试!", Toast.LENGTH_SHORT).show();
                // TODO: 2017/11/13 微信支付后有返回有白屏问题 
            }
        }
    }
}

