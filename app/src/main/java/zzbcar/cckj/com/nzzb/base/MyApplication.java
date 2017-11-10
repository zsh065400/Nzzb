package zzbcar.cckj.com.nzzb.base;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import zzbcar.cckj.com.nzzb.utils.Constant;

/**
 * Created by Admin on 2017/11/5.
 */

public class MyApplication extends Application {

    private static IWXAPI SWXAPI;
    private static MyApplication SINSTANCE;

    public static MyApplication getMyApplicaiton() {
        return SINSTANCE;
    }

    public static IWXAPI getWxApi() {
        return SWXAPI;
    }

    public static void setWxApi(IWXAPI api) {
        SWXAPI = api;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SINSTANCE = this;
        UMShareAPI.get(this);
        Config.DEBUG = true;
        initOkGo();
//        api.registerApp(Constant.WEIXIN_APP_ID);
//        PayReq req = new PayReq();
////....拼接req参数
//        api.sendReq(req);// 调用支付
    }

    private void initOkGo() {
        OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        OkGo.getInstance().setOkHttpClient(builder.build());
    }

    {
        PlatformConfig.setWeixin(Constant.WEIXIN_APP_ID, Constant.WEIXIN_APP_SECRET);
        PlatformConfig.setQQZone(Constant.QQZONE_APP_ID, Constant.QQZONE_APP_KEY);
        PlatformConfig.setSinaWeibo(Constant.SINA_APP_KEY,
                Constant.SINA_APP_SERCET, Constant.SINA_APP_URL);
    }
}
