package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hegeyang on 2017/11/20.
 */

public class TicketBean implements Serializable {

    /**
     * errno : 0
     * data : [{"address":"[西湖区]之江路_之江路珊瑚沙水库建功立业碑西","canSelect":0,"carId":2,"city":"杭州市","code":"59-707205","fine":0,"id":51,"markFee":0,"paymentStatus":1,"point":0,"processStatus":1,"province":"浙江省","reason":"驾驶中型以上载客载货汽车、危险物品运输车辆以外的机动车超过规定时速10%以下的","serviceFee":0,"violationCity":"杭州市","violationNum":"6050","violationTime":"2017-09-25 10:27:17"},{"address":"[西湖区]学院路_学院路文三路口","canSelect":0,"carId":2,"city":"杭州市","code":"59-707206","fine":100,"id":52,"markFee":0,"paymentStatus":1,"point":3,"processStatus":1,"province":"浙江省","reason":"机动车违反禁令标志指示的","serviceFee":0,"violationCity":"杭州市","violationNum":"1344","violationTime":"2017-09-01 16:52:56"},{"address":"S26诸永高速诸暨方向200KM+50M","canSelect":0,"carId":2,"city":"温州市","code":"59-707207","fine":200,"id":53,"markFee":0,"paymentStatus":1,"point":3,"processStatus":1,"province":"浙江省","reason":"驾驶中型以上载客载货汽车、危险物品运输车辆以外的其他机动车行驶超过规定时速10%未达20%的","serviceFee":0,"violationCity":"温州市","violationNum":"13522","violationTime":"2016-11-06 19:52:00"},{"address":"[杭州]学源街_学源街幸福南路叉口","canSelect":0,"carId":2,"city":"杭州市","code":"59-756927","fine":100,"id":54,"markFee":0,"paymentStatus":1,"point":3,"processStatus":1,"province":"浙江省","reason":"机动车违反禁止标线指示的","serviceFee":0,"violationCity":"杭州市","violationNum":"1345","violationTime":"2017-10-23 14:41:25"}]
     * message : 请求成功
     */

    private int errno;
    private String message;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : [西湖区]之江路_之江路珊瑚沙水库建功立业碑西
         * canSelect : 0
         * carId : 2
         * city : 杭州市
         * code : 59-707205
         * fine : 0
         * id : 51
         * markFee : 0
         * paymentStatus : 1
         * point : 0
         * processStatus : 1
         * province : 浙江省
         * reason : 驾驶中型以上载客载货汽车、危险物品运输车辆以外的机动车超过规定时速10%以下的
         * serviceFee : 0
         * violationCity : 杭州市
         * violationNum : 6050
         * violationTime : 2017-09-25 10:27:17
         */

        private String address;
        private int canSelect;
        private int carId;
        private String city;
        private String code;
        private int fine;
        private int id;
        private int markFee;
        private int paymentStatus;
        private int point;
        private int processStatus;
        private String province;
        private String reason;
        private int serviceFee;
        private String violationCity;
        private String violationNum;
        private String violationTime;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCanSelect() {
            return canSelect;
        }

        public void setCanSelect(int canSelect) {
            this.canSelect = canSelect;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getFine() {
            return fine;
        }

        public void setFine(int fine) {
            this.fine = fine;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMarkFee() {
            return markFee;
        }

        public void setMarkFee(int markFee) {
            this.markFee = markFee;
        }

        public int getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(int paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getProcessStatus() {
            return processStatus;
        }

        public void setProcessStatus(int processStatus) {
            this.processStatus = processStatus;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getServiceFee() {
            return serviceFee;
        }

        public void setServiceFee(int serviceFee) {
            this.serviceFee = serviceFee;
        }

        public String getViolationCity() {
            return violationCity;
        }

        public void setViolationCity(String violationCity) {
            this.violationCity = violationCity;
        }

        public String getViolationNum() {
            return violationNum;
        }

        public void setViolationNum(String violationNum) {
            this.violationNum = violationNum;
        }

        public String getViolationTime() {
            return violationTime;
        }

        public void setViolationTime(String violationTime) {
            this.violationTime = violationTime;
        }
    }
}
