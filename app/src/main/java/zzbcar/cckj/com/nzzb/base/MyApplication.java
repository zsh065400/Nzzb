package zzbcar.cckj.com.nzzb.base;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Admin on 2017/11/5.
 */

public class MyApplication extends Application {

    private static Context context;
    public static Context getContext()
    {

        return context;
    }


    private static MyApplication instance;

    public static MyApplication getInstance() {

        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        UMShareAPI.get(this);
//        api.registerApp(Constant.WEIXIN_APP_ID);
//        PayReq req = new PayReq();
////....拼接req参数
//        api.sendReq(req);// 调用支付
    }

    {

        PlatformConfig.setWeixin("wxad1065edbfa4ed3a", "70943f855a89703a47c1a35c9ee07b05");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }
}
