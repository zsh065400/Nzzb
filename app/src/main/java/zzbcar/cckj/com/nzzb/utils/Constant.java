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

//    public static final String QQZONE_APP_ID = "100424468";
//    public static final String QQZONE_APP_KEY = "c7394704798a158208a74ab60104f0ba";
public static final String QQZONE_APP_ID = "1106313801";
    public static final String QQZONE_APP_KEY = "bz7PCjrvT9gCxCve";

    public static final String SINA_APP_KEY = "3921700954";
    public static final String SINA_APP_SERCET = "04b48b094faeb16683c32669824ebdad";
    public static final String SINA_APP_URL = "http://sns.whalecloud.com";

    /****************接口首页*********************/
    /****************Test*********************/
    public static final String SERVE_URL = "http://www.zzbcar.com/";
//    public static final String SERVE_URL = "http://192.168.1.253:8080/";

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
    /*查询车辆价格表*/
    public static final String API_CAR_WEEK_PRICE = SERVE_URL+"zzb/app/bus/week_price";
    /*获取支付*/
    public static final String API_ORDER_PAY = SERVE_URL + "zzb/app/pay/request";

    /*查询租金*/
    public static final String API_QUERY_PRICE = SERVE_URL + "zzb/app/bus/calcPrice";

    /*车辆筛选*/
    public static final String API_CAR_QUERY = SERVE_URL + "zzb/app/car/queryCarByCondition";

    /*车辆品牌*/
    public static final String API_CAR_BRAND = SERVE_URL + "zzb/app/car/brand";

    /*车系*/
    public static final String API_CAR_SERIES = SERVE_URL + "zzb/app/car/series";

    /*车型*/
    public static final String API_CAR_MODEL = SERVE_URL + "zzb/app/car/model";

    /*车友认证*/
    public static final String USER_AUTH = SERVE_URL + "zzb/app/member/userAuth";

    /*获取服务器时间*/
    public static final String API_SERVER_TIME = SERVE_URL + "zzb/app/common/svrtm";

    /*获取热搜内容*/
    public static final String API_FIRE_SEARCH = SERVE_URL + "zzb/app/car/searchfreq";

    /*绑定三方登录账号*/
    public static final String API_BIND_QQWX = SERVE_URL + "zzb/app/member/bind";

    /*查询用户订单*/
    public static final String API_GET_USER_ORDER = SERVE_URL + "zzb/app/order/query";

    public static final String HOME_LUNBO_URL = SERVE_URL + "zzb/app/common/marquee";
    //品牌
    public static final String CAR_BRAND_URL = SERVE_URL + "zzb/app/car/brand";






    public static final String CAR_DEFAULT_URL = SERVE_URL + "zzb/app/car/queryCarByCondition?pageNum=0&pageSize=10";
    public static final String LOCATION_SELECT_URL = SERVE_URL + "zzb/app/common/citys";
    //查询订单
    public static final String SELECT_ORDER_URL=SERVE_URL+"zzb/app/order/query";
    //常用地址
    public static final String GET_ADDR = SERVE_URL + "zzb/app/member/getAddr";
    public static final String ADD_ADDR = SERVE_URL + "zzb/app/member/addAddr";
    public static final String DEL_ADDR = SERVE_URL + "zzb/app/member/delAddr";
    public static final String SP_LOGIN = "login";
    //用户
    public static final String CHANGE_INFO = SERVE_URL +"zzb/app/member/changeInfo";
    //查询违章
    public static final String QUERRY_TICKET =SERVE_URL+ "zzb/app/car/ticket";
    //我的收藏查询
    public static final String MY_COLLECT_URL =SERVE_URL+"zzb/app/common/loadCollectCar";
    //收藏车辆
    public static final String COLLECT_CAR_URL=SERVE_URL+"zzb/app/common/collectcar";
    //收藏车辆id
    public static final String COLLECT_CAR_ID_URL=SERVE_URL+"zzb/app/common/loadCollectList";
    //个人用户信息的统计
    public static final String PERSON_MESSAGE =SERVE_URL+"zzb/app/member/userStatisInfo";
    //价格日历
    public static final String API_PRICE_MONTH = SERVE_URL+"zzb/app/bus/monthprice";
    //提交车主
    public static final String API_ADD_OWNER = SERVE_URL+"zzb/app/bus/addOwnerContact";

    public static final String SP_LAST_LOCATION = "last_location";
    //纬度
    public static final String SP_LATITUDE = "latitude";
    //经度
    public static final String SP_LONGITUDE = "longitude";
    //aliyun oss
    //TOdo 这里持久化AcciessId 和 Psd 会出现安全性问题。建议以后修改。
    public static final String ACCESS_ID = "LTAIwAHa3lPn0JXN";
    public static final String ACCESS_PAS = "eN01vFRyf3uBe6KxM1R2Abdzy0QcPw";
    public static final String END_POINT = "http://oss-cn-hangzhou.aliyuncs.com";
    public static final String BUCKET = "zzb-2017";
    public static final String IDCARD_KEYPATH = "zzbcar/idcard/";
    public static final String CARLICENCE_KEYPATH = "zzbcar/car/";
    public static final String SERVER_PHOTO_HEAD = "http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/";
    public static final String HEAD_KEYPATH = "headimg/";

    //加载的网页链接
    //用户协议
    public static final String MASTER_PROTOCOL = "http://app.zzbcar.com/zzb/helpcenter/protocol.html";
    //平台规则
    public static final String PTGZ = "http://app.zzbcar.com/zzb/helpcenter/protocol.html";
    //用车流程
    public static final String USE_PROCEDURE = "http://app.zzbcar.com/zzb/helpcenter/user1.html";
    //用户押金
    public static final String USER_DEPOSIT = "http://app.zzbcar.com/zzb/helpcenter/user2.html";
    //注册审核
    public static final String SIGN_PROTOCOL = "http://app.zzbcar.com/zzb/helpcenter/user3.html";
    //平台保障
    public static final String USER_4 = "http://app.zzbcar.com/zzb/helpcenter/user4.html";
    //违章处理
    public static final String USER_5 = "http://app.zzbcar.com/zzb/helpcenter/user5.html";
    //账户信息
    public static final String USER_6 = "http://app.zzbcar.com/zzb/helpcenter/user6.html";
    //APP操作引导
    public static final String USER_7 = "http://app.zzbcar.com/zzb/helpcenter/user7.html";
    //纠纷处理
    public static final String USER_8 = "http://app.zzbcar.com/zzb/helpcenter/user8.html";
}

