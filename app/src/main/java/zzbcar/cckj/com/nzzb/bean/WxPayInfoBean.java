package zzbcar.cckj.com.nzzb.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Scout
 * Created on 2017/11/13 16:14.
 */

public class WxPayInfoBean implements Serializable {

    private static final long serialVersionUID = 4425492343680864028L;

    private int errno;
    private DataBean data;
    private String message;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * appid : wxad1065edbfa4ed3a
         * noncestr : aa39f762fe88411398c921fd6e4eae64
         * package : Sign=WXPay
         * partnerid : 1483423462
         * prepayid : wx20171113161400fb2cd5e3750178443815
         * sign : F91D04C78C73BC80C196B534B26CE4DE
         * timestamp : 1510560840
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String sign;
        private String timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
