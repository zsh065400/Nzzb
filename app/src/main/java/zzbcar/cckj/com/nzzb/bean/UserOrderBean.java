package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Scout
 * Created on 2017/11/18 16:01.
 */

public class UserOrderBean implements Serializable {

    private static final long serialVersionUID = -1370481167658443335L;


    /**
     * errno : 0
     * data : [{"abolishMoney":0,"abolishTime":null,"car":{"addr":"杭州市庆春路58号","age":5,"brand":18,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png","brandName":"奥迪","carName":"奥迪R8","collectCount":1,"createTime":"2017-10-12 11:59:03","deposit":20000,"distance":0,"engineLiter":"V6","id":2,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.278001,"longitude":120.168069,"mileage":50000,"model":9,"modelName":"R8","modelYear":2016,"name":"奥迪 S8 2016款 4.0 TFSI quattro","onShelfTime":"2017-11-10 14:41:55","orderCount":"61","owner":5,"ownerAvatar":"https://zzb-2017.oss-cn-hangzhou.aliyuncs.com/1511698798ios.jpg","ownerName":"21","pics":"http://living-2016.oss-cn-hangzhou.aliyuncs.com/7f40bce7e9992e64a3197d2082f4acc8.png","plateNo":"浙AK136A","price":0.01,"receivePercent":0.25,"remark":"测试车辆","seatNum":2,"series":13,"seriesName":"R8","status":1,"transmissionCase":0,"useCity":"重庆市,杭州市,北京市","useType":1},"carId":2,"city":3,"createTime":"2017-12-02 10:33:25","detail":"自尊宝自驾 奥迪R8 (2017-12-04 10:32:00~2017-12-09 10:32:00)自主取车","endTime":"2017-12-09 10:32:00","id":202,"leasePrice":0.06,"onlineAmount":0.06999999999999999,"orderNo":"ZJ112020000020000140BYJ","payStatus":1,"remark":"","returnAddrId":0,"returnAddress":"杭州市庆春路58号","returnHome":0,"returnMapLocation":"120.168069,30.278001","returnTime":null,"rtdepositTime":null,"startTime":"2017-12-04 10:32:00","status":0,"subject":"自尊宝自驾 奥迪R8","takeAddrId":0,"takeAddress":"杭州市庆春路58号","takeHome":0,"takeMapLocation":"120.168069,30.278001","takeTime":null,"totalAmount":20000.07,"trafficDepositMoney":0.01,"trafficPunlishMoney":0,"type":1,"userId":14,"userName":""}]
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

    public static class DataBean implements Serializable{
        /**
         * abolishMoney : 0
         * abolishTime : null
         * car : {"addr":"杭州市庆春路58号","age":5,"brand":18,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png","brandName":"奥迪","carName":"奥迪R8","collectCount":1,"createTime":"2017-10-12 11:59:03","deposit":20000,"distance":0,"engineLiter":"V6","id":2,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.278001,"longitude":120.168069,"mileage":50000,"model":9,"modelName":"R8","modelYear":2016,"name":"奥迪 S8 2016款 4.0 TFSI quattro","onShelfTime":"2017-11-10 14:41:55","orderCount":"61","owner":5,"ownerAvatar":"https://zzb-2017.oss-cn-hangzhou.aliyuncs.com/1511698798ios.jpg","ownerName":"21","pics":"http://living-2016.oss-cn-hangzhou.aliyuncs.com/7f40bce7e9992e64a3197d2082f4acc8.png","plateNo":"浙AK136A","price":0.01,"receivePercent":0.25,"remark":"测试车辆","seatNum":2,"series":13,"seriesName":"R8","status":1,"transmissionCase":0,"useCity":"重庆市,杭州市,北京市","useType":1}
         * carId : 2
         * city : 3
         * createTime : 2017-12-02 10:33:25
         * detail : 自尊宝自驾 奥迪R8 (2017-12-04 10:32:00~2017-12-09 10:32:00)自主取车
         * endTime : 2017-12-09 10:32:00
         * id : 202
         * leasePrice : 0.06
         * onlineAmount : 0.06999999999999999
         * orderNo : ZJ112020000020000140BYJ
         * payStatus : 1
         * remark :
         * returnAddrId : 0
         * returnAddress : 杭州市庆春路58号
         * returnHome : 0
         * returnMapLocation : 120.168069,30.278001
         * returnTime : null
         * rtdepositTime : null
         * startTime : 2017-12-04 10:32:00
         * status : 0
         * subject : 自尊宝自驾 奥迪R8
         * takeAddrId : 0
         * takeAddress : 杭州市庆春路58号
         * takeHome : 0
         * takeMapLocation : 120.168069,30.278001
         * takeTime : null
         * totalAmount : 20000.07
         * trafficDepositMoney : 0.01
         * trafficPunlishMoney : 0
         * type : 1
         * userId : 14
         * userName :
         */

        private int abolishMoney;
        private Object abolishTime;
        private CarBean car;
        private int carId;
        private int city;
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
        private Object rtdepositTime;
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
        private int trafficPunlishMoney;
        private int type;
        private int userId;
        private String userName;

        public int getTimeoutMoney() {
            return timeoutMoney;
        }

        public void setTimeoutMoney(int timeoutMoney) {
            this.timeoutMoney = timeoutMoney;
        }

        public int getExceedMoney() {
            return exceedMoney;
        }

        public void setExceedMoney(int exceedMoney) {
            this.exceedMoney = exceedMoney;
        }

        private int timeoutMoney;
        private int exceedMoney;

        public int getLastReturnMoney() {
            return lastReturnMoney;
        }

        public void setLastReturnMoney(int lastReturnMoney) {
            this.lastReturnMoney = lastReturnMoney;
        }

        private int lastReturnMoney;


        public int getAbolishMoney() {
            return abolishMoney;
        }

        public void setAbolishMoney(int abolishMoney) {
            this.abolishMoney = abolishMoney;
        }

        public Object getAbolishTime() {
            return abolishTime;
        }

        public void setAbolishTime(Object abolishTime) {
            this.abolishTime = abolishTime;
        }

        public CarBean getCar() {
            return car;
        }

        public void setCar(CarBean car) {
            this.car = car;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
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

        public Object getRtdepositTime() {
            return rtdepositTime;
        }

        public void setRtdepositTime(Object rtdepositTime) {
            this.rtdepositTime = rtdepositTime;
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

        public int getTrafficPunlishMoney() {
            return trafficPunlishMoney;
        }

        public void setTrafficPunlishMoney(int trafficPunlishMoney) {
            this.trafficPunlishMoney = trafficPunlishMoney;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public static class CarBean implements Serializable{
            /**
             * addr : 杭州市庆春路58号
             * age : 5
             * brand : 18
             * brandLogo : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png
             * brandName : 奥迪
             * carName : 奥迪R8
             * collectCount : 1
             * createTime : 2017-10-12 11:59:03
             * deposit : 20000
             * distance : 0
             * engineLiter : V6
             * id : 2
             * imgs : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg
             * latitude : 30.278001
             * longitude : 120.168069
             * mileage : 50000
             * model : 9
             * modelName : R8
             * modelYear : 2016
             * name : 奥迪 S8 2016款 4.0 TFSI quattro
             * onShelfTime : 2017-11-10 14:41:55
             * orderCount : 61
             * owner : 5
             * ownerAvatar : https://zzb-2017.oss-cn-hangzhou.aliyuncs.com/1511698798ios.jpg
             * ownerName : 21
             * pics : http://living-2016.oss-cn-hangzhou.aliyuncs.com/7f40bce7e9992e64a3197d2082f4acc8.png
             * plateNo : 浙AK136A
             * price : 0.01
             * receivePercent : 0.25
             * remark : 测试车辆
             * seatNum : 2
             * series : 13
             * seriesName : R8
             * status : 1
             * transmissionCase : 0
             * useCity : 重庆市,杭州市,北京市
             * useType : 1
             */

            private String addr;
            private int age;
            private int brand;
            private String brandLogo;
            private String brandName;
            private String carName;
            private int collectCount;
            private String createTime;
            private int deposit;
            private int distance;
            private String engineLiter;
            private int id;
            private String imgs;
            private double latitude;
            private double longitude;
            private int mileage;
            private int model;
            private String modelName;
            private int modelYear;
            private String name;
            private String onShelfTime;
            private String orderCount;
            private int owner;
            private String ownerAvatar;
            private String ownerName;
            private String pics;
            private String plateNo;
            private double price;
            private double receivePercent;
            private String remark;
            private int seatNum;
            private int series;
            private String seriesName;
            private int status;
            private int transmissionCase;
            private String useCity;
            private int useType;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getBrand() {
                return brand;
            }

            public void setBrand(int brand) {
                this.brand = brand;
            }

            public String getBrandLogo() {
                return brandLogo;
            }

            public void setBrandLogo(String brandLogo) {
                this.brandLogo = brandLogo;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getCarName() {
                return carName;
            }

            public void setCarName(String carName) {
                this.carName = carName;
            }

            public int getCollectCount() {
                return collectCount;
            }

            public void setCollectCount(int collectCount) {
                this.collectCount = collectCount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getDeposit() {
                return deposit;
            }

            public void setDeposit(int deposit) {
                this.deposit = deposit;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getEngineLiter() {
                return engineLiter;
            }

            public void setEngineLiter(String engineLiter) {
                this.engineLiter = engineLiter;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public int getMileage() {
                return mileage;
            }

            public void setMileage(int mileage) {
                this.mileage = mileage;
            }

            public int getModel() {
                return model;
            }

            public void setModel(int model) {
                this.model = model;
            }

            public String getModelName() {
                return modelName;
            }

            public void setModelName(String modelName) {
                this.modelName = modelName;
            }

            public int getModelYear() {
                return modelYear;
            }

            public void setModelYear(int modelYear) {
                this.modelYear = modelYear;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOnShelfTime() {
                return onShelfTime;
            }

            public void setOnShelfTime(String onShelfTime) {
                this.onShelfTime = onShelfTime;
            }

            public String getOrderCount() {
                return orderCount;
            }

            public void setOrderCount(String orderCount) {
                this.orderCount = orderCount;
            }

            public int getOwner() {
                return owner;
            }

            public void setOwner(int owner) {
                this.owner = owner;
            }

            public String getOwnerAvatar() {
                return ownerAvatar;
            }

            public void setOwnerAvatar(String ownerAvatar) {
                this.ownerAvatar = ownerAvatar;
            }

            public String getOwnerName() {
                return ownerName;
            }

            public void setOwnerName(String ownerName) {
                this.ownerName = ownerName;
            }

            public String getPics() {
                return pics;
            }

            public void setPics(String pics) {
                this.pics = pics;
            }

            public String getPlateNo() {
                return plateNo;
            }

            public void setPlateNo(String plateNo) {
                this.plateNo = plateNo;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getReceivePercent() {
                return receivePercent;
            }

            public void setReceivePercent(double receivePercent) {
                this.receivePercent = receivePercent;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getSeatNum() {
                return seatNum;
            }

            public void setSeatNum(int seatNum) {
                this.seatNum = seatNum;
            }

            public int getSeries() {
                return series;
            }

            public void setSeries(int series) {
                this.series = series;
            }

            public String getSeriesName() {
                return seriesName;
            }

            public void setSeriesName(String seriesName) {
                this.seriesName = seriesName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTransmissionCase() {
                return transmissionCase;
            }

            public void setTransmissionCase(int transmissionCase) {
                this.transmissionCase = transmissionCase;
            }

            public String getUseCity() {
                return useCity;
            }

            public void setUseCity(String useCity) {
                this.useCity = useCity;
            }

            public int getUseType() {
                return useType;
            }

            public void setUseType(int useType) {
                this.useType = useType;
            }
        }
    }
}
