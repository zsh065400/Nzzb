package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;

/**
 * Created by Scout
 * Created on 2017/11/13 16:00.
 */

public class OrderBean implements Serializable {

    private static final long serialVersionUID = -7793988363557979440L;

    /**
     * errno : 0
     * data : {"carId":2,"createTime":"2017-11-13 15:59:51","detail":"自尊宝自驾 奥迪R8 (2017-11-13 16:05:00~2017-11-13 16:10:00)自主取车","endTime":"2017-11-13 16:10:14","id":64,"leasePrice":0.01,"onlineAmount":0.01,"orderNo":"ZJ111130000020000110FTH","payStatus":0,"remark":"","returnAddrId":0,"returnAddress":"杭州市庆春路58号","returnHome":0,"returnMapLocation":"120.168069,30.278001","returnTime":null,"startTime":"2017-11-13 16:05:14","status":0,"subject":"自尊宝自驾 奥迪R8","takeAddrId":0,"takeAddress":"杭州市庆春路58号","takeHome":0,"takeMapLocation":"120.168069,30.278001","takeTime":null,"totalAmount":20000.01,"trafficDepositMoney":0,"type":1,"userId":11}
     * message : 请求成功
     */

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

    public static class DataBean implements Serializable{
        /**
         * carId : 2
         * createTime : 2017-11-13 15:59:51
         * detail : 自尊宝自驾 奥迪R8 (2017-11-13 16:05:00~2017-11-13 16:10:00)自主取车
         * endTime : 2017-11-13 16:10:14
         * id : 64
         * leasePrice : 0.01
         * onlineAmount : 0.01
         * orderNo : ZJ111130000020000110FTH
         * payStatus : 0
         * remark :
         * returnAddrId : 0
         * returnAddress : 杭州市庆春路58号
         * returnHome : 0
         * returnMapLocation : 120.168069,30.278001
         * returnTime : null
         * startTime : 2017-11-13 16:05:14
         * status : 0
         * subject : 自尊宝自驾 奥迪R8
         * takeAddrId : 0
         * takeAddress : 杭州市庆春路58号
         * takeHome : 0
         * takeMapLocation : 120.168069,30.278001
         * takeTime : null
         * totalAmount : 20000.01
         * trafficDepositMoney : 0
         * type : 1
         * userId : 11
         */

        private int carId;
        private String createTime;
        private String detail;
        private String endTime;
        private int id;
        private double leasePrice;
        private double onlineAmount;
        private String orderNo;
        private int payStatus;
        private String remark;
        private int returnAddrId;
        private String returnAddress;
        private int returnHome;
        private String returnMapLocation;
        private Object returnTime;
        private String startTime;
        private int status;
        private String subject;
        private int takeAddrId;
        private String takeAddress;
        private int takeHome;
        private String takeMapLocation;
        private Object takeTime;
        private double totalAmount;
        private double trafficDepositMoney;
        private int type;
        private int userId;

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getLeasePrice() {
            return leasePrice;
        }

        public void setLeasePrice(double leasePrice) {
            this.leasePrice = leasePrice;
        }

        public double getOnlineAmount() {
            return onlineAmount;
        }

        public void setOnlineAmount(double onlineAmount) {
            this.onlineAmount = onlineAmount;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getReturnAddrId() {
            return returnAddrId;
        }

        public void setReturnAddrId(int returnAddrId) {
            this.returnAddrId = returnAddrId;
        }

        public String getReturnAddress() {
            return returnAddress;
        }

        public void setReturnAddress(String returnAddress) {
            this.returnAddress = returnAddress;
        }

        public int getReturnHome() {
            return returnHome;
        }

        public void setReturnHome(int returnHome) {
            this.returnHome = returnHome;
        }

        public String getReturnMapLocation() {
            return returnMapLocation;
        }

        public void setReturnMapLocation(String returnMapLocation) {
            this.returnMapLocation = returnMapLocation;
        }

        public Object getReturnTime() {
            return returnTime;
        }

        public void setReturnTime(Object returnTime) {
            this.returnTime = returnTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public int getTakeAddrId() {
            return takeAddrId;
        }

        public void setTakeAddrId(int takeAddrId) {
            this.takeAddrId = takeAddrId;
        }

        public String getTakeAddress() {
            return takeAddress;
        }

        public void setTakeAddress(String takeAddress) {
            this.takeAddress = takeAddress;
        }

        public int getTakeHome() {
            return takeHome;
        }

        public void setTakeHome(int takeHome) {
            this.takeHome = takeHome;
        }

        public String getTakeMapLocation() {
            return takeMapLocation;
        }

        public void setTakeMapLocation(String takeMapLocation) {
            this.takeMapLocation = takeMapLocation;
        }

        public Object getTakeTime() {
            return takeTime;
        }

        public void setTakeTime(Object takeTime) {
            this.takeTime = takeTime;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public double getTrafficDepositMoney() {
            return trafficDepositMoney;
        }

        public void setTrafficDepositMoney(double trafficDepositMoney) {
            this.trafficDepositMoney = trafficDepositMoney;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
