package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;

/**
 * Created by Scout
 * Created on 2017/11/18 15:40.
 */

public class AliPayResultBean implements Serializable {

    private static final long serialVersionUID = -8045966414545859373L;

    /**
     * memo : xxxxx
     * result : {                     "alipay_trade_app_pay_response":{                         "code":"10000",                         "msg":"Success",                         "app_id":"2014072300007148",                         "out_trade_no":"081622560194853",                         "trade_no":"2016081621001004400236957647",                         "total_amount":"0.01",                         "seller_id":"2088702849871851",                         "charset":"utf-8",                         "timestamp":"2016-10-11 17:43:36"                     },                     "sign":"NGfStJf3i3ooWBuCDIQSumOpaGBcQz+aoAqyGh3W6EqA/gmyPYwLJ2REFijY9XPTApI9YglZyMw+ZMhd3kb0mh4RAXMrb6mekX4Zu8Nf6geOwIa9kLOnw0IMCjxi4abDIfXhxrXyj********",                     "sign_type":"RSA2"                 }
     * resultStatus : 9000
     */

    private String memo;
    private String result;
    private String resultStatus;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }
}
