package zzbcar.cckj.com.nzzb.utils;

/**
 * Created by Admin on 2017/10/31.
 */

public class Constant {
    public static final long TIME_OUT = 3000;//网络请求超时时间
    public static final String KEY_HAS_GUIDE = "key_has_guide";
    /****************社会化组件：登录、分享****************/
    public static final String WEIXIN_APP_ID = "wxad1065edbfa4ed3a";
    public static final String WEIXIN_APP_SECRET = "70943f855a89703a47c1a35c9ee07b05";

    public static final String QQZONE_APP_ID = "100424468";
    public static final String QQZONE_APP_KEY = "c7394704798a158208a74ab60104f0ba";

    public static final String SINA_APP_KEY = "3921700954";
    public static final String SINA_APP_SERCET = "04b48b094faeb16683c32669824ebdad";
    public static final String SINA_APP_URL = "http://sns.whalecloud.com";

    /****************接口首页*********************/
    /****************Test*********************/
    public static final String SERVE_URL = "http://www.zzbcar.com/";

    /*登录*/
    public static final String API_SIGN = SERVE_URL + "zzb/app/member/login";

    /*获取短信验证码*/
    public static final String API_GET_CODE = SERVE_URL + "zzb/app/common/smscode";

    /*下单*/
    public static final String API_ADD_ORDER = SERVE_URL + "zzb/app/order/doOrder";

    /*主页*/
    public static final String API_MAIN_PAGE = SERVE_URL + "zzb/app/compound/mainpage";

    /*查询车辆信息*/
    public static final String API_CAR_DETAIL = SERVE_URL + "zzb/app/car/queryCar";

    /*获取支付*/
    public static final String API_ORDER_PAY = SERVE_URL + "zzb/app/pay/request";

    /*查询租金*/
    public static final String API_QUERY_PRICE = SERVE_URL + "zzb/app/bus/calcPrice";

    public static final String HOME_LUNBO_URL = SERVE_URL + "zzb/app/common/marquee";
    //品牌
    public static final String CAR_BRAND_URL = SERVE_URL + "zzb/app/car/brand";
    //  车系
    public static final String CAR_SERIES_URL = SERVE_URL + "zzb/app/car/series";

    public static final String CAR_DEFAULT_URL = SERVE_URL + "zzb/app/car/queryCarByCondition?pageNum=0&pageSize=10";
    public static final String LOCATION_SELECT_URL=SERVE_URL+"zzb/app/common/citys";

    public static final String SP_LOGIN = "login";
    public static final String SP_LAST_LOCATION="last_location";

}

