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
     * data : [{"abolishMoney":0,"abolishTime":null,"car":{"addr":"杭州市庆春路58号","age":18,"brand":27,"brandName":"阿斯顿马丁","carName":"阿斯顿马丁V8","collectCount":0,"createTime":"2017-10-24 15:31:33","deposit":22,"distance":0,"engineLiter":"V6","id":18,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":43,"modelName":"V8软顶敞篷","name":"V8软顶敞篷","onShelfTime":"2017-10-24 15:31:33","orderCount":"4","owner":0,"ownerName":"","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/20120809135707632.jpg","plateNo":"19","price":0.01,"receivePercent":1,"seatNum":2,"series":36,"seriesName":"V8","status":1,"transmissionCase":1,"useCity":"重庆,杭州,北京","useType":1},"carId":18,"createTime":"2017-11-18 15:01:37","detail":"自尊宝自驾 阿斯顿马丁V8 (2017-11-18 15:06:00~2017-11-19 15:01:00)自主取车","endTime":"2017-11-19 15:01:28","id":66,"leasePrice":0.02,"onlineAmount":0.02,"orderNo":"ZJ1111800001800001304SZ","payStatus":0,"returnAddrId":0,"returnAddress":"杭州市庆春路58号","returnHome":0,"returnMapLocation":"120.444372,30.154682","returnTime":null,"startTime":"2017-11-18 15:06:28","status":0,"subject":"自尊宝自驾 阿斯顿马丁V8","takeAddrId":0,"takeAddress":"杭州市庆春路58号","takeHome":0,"takeMapLocation":"120.444372,30.154682","takeTime":null,"totalAmount":22.02,"trafficDepositMoney":0,"type":1,"userId":13,"userName":""},{"abolishMoney":0,"abolishTime":null,"car":{"addr":"杭州市庆春路58号","age":0,"brand":26,"brandName":"保时捷","carName":"保时捷911","collectCount":0,"createTime":"2017-10-24 15:28:03","deposit":22,"distance":0,"engineLiter":"V6","id":17,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":39,"modelName":"911软顶敞篷","name":"911软顶敞篷","onShelfTime":"2017-10-24 15:28:03","orderCount":"1","owner":0,"ownerName":"","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/3264x2448-161710_10191392970630147_55808efe09bfafe98f2676361dc820a6.jpg","plateNo":"19","price":0.01,"receivePercent":1,"seatNum":2,"series":33,"seriesName":"911","status":1,"transmissionCase":0,"useCity":"重庆,杭州,北京","useType":1},"carId":17,"createTime":"2017-11-18 15:29:50","detail":"自尊宝自驾 保时捷911 (2017-11-18 15:34:00~2017-11-19 15:29:00)自主取车","endTime":"2017-11-19 15:29:41","id":67,"leasePrice":0.02,"onlineAmount":0.02,"orderNo":"ZJ111180000170000130WFS","payStatus":1,"returnAddrId":0,"returnAddress":"杭州市庆春路58号","returnHome":0,"returnMapLocation":"120.444372,30.154682","returnTime":null,"startTime":"2017-11-18 15:34:41","status":0,"subject":"自尊宝自驾 保时捷911","takeAddrId":0,"takeAddress":"杭州市庆春路58号","takeHome":0,"takeMapLocation":"120.444372,30.154682","takeTime":null,"totalAmount":22.02,"trafficDepositMoney":0,"type":1,"userId":13,"userName":""}]
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
         * abolishMoney : 0
         * abolishTime : null
         * car : {"addr":"杭州市庆春路58号","age":18,"brand":27,"brandName":"阿斯顿马丁","carName":"阿斯顿马丁V8","collectCount":0,"createTime":"2017-10-24 15:31:33","deposit":22,"distance":0,"engineLiter":"V6","id":18,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.154682,"longitude":120.444372,"mileage":0,"model":43,"modelName":"V8软顶敞篷","name":"V8软顶敞篷","onShelfTime":"2017-10-24 15:31:33","orderCount":"4","owner":0,"ownerName":"","pics":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/20120809135707632.jpg","plateNo":"19","price":0.01,"receivePercent":1,"seatNum":2,"series":36,"seriesName":"V8","status":1,"transmissionCase":1,"useCity":"重庆,杭州,北京","useType":1}
         * carId : 18
         * createTime : 2017-11-18 15:01:37
         * detail : 自尊宝自驾 阿斯顿马丁V8 (2017-11-18 15:06:00~2017-11-19 15:01:00)自主取车
         * endTime : 2017-11-19 15:01:28
         * id : 66
         * leasePrice : 0.02
         * onlineAmount : 0.02
         * orderNo : ZJ1111800001800001304SZ
         * payStatus : 0
         * returnAddrId : 0
         * returnAddress : 杭州市庆春路58号
         * returnHome : 0
         * returnMapLocation : 120.444372,30.154682
         * returnTime : null
         * startTime : 2017-11-18 15:06:28
         * status : 0
         * subject : 自尊宝自驾 阿斯顿马丁V8
         * takeAddrId : 0
         * takeAddress : 杭州市庆春路58号
         * takeHome : 0
         * takeMapLocation : 120.444372,30.154682
         * takeTime : null
         * totalAmount : 22.02
         * trafficDepositMoney : 0
         * type : 1
         * userId : 13
         * userName :
         */

        private int abolishMoney;
        private Object abolishTime;
        private CarBean car;
        private int carId;
        private String createTime;
        private String detail;
        private String endTime;
        private int id;
        private double leasePrice;
        private double onlineAmount;
        private String orderNo;
        private int payStatus;
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
        private int trafficDepositMoney;
        private int type;
        private int userId;
        private String userName;

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

        public int getTrafficDepositMoney() {
            return trafficDepositMoney;
        }

        public void setTrafficDepositMoney(int trafficDepositMoney) {
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public static class CarBean {
            /**
             * addr : 杭州市庆春路58号
             * age : 18
             * brand : 27
             * brandName : 阿斯顿马丁
             * carName : 阿斯顿马丁V8
             * collectCount : 0
             * createTime : 2017-10-24 15:31:33
             * deposit : 22
             * distance : 0
             * engineLiter : V6
             * id : 18
             * imgs : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg
             * latitude : 30.154682
             * longitude : 120.444372
             * mileage : 0
             * model : 43
             * modelName : V8软顶敞篷
             * name : V8软顶敞篷
             * onShelfTime : 2017-10-24 15:31:33
             * orderCount : 4
             * owner : 0
             * ownerName :
             * pics : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/20120809135707632.jpg
             * plateNo : 19
             * price : 0.01
             * receivePercent : 1
             * seatNum : 2
             * series : 36
             * seriesName : V8
             * status : 1
             * transmissionCase : 1
             * useCity : 重庆,杭州,北京
             * useType : 1
             */

            private String addr;
            private int age;
            private int brand;
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
            private String name;
            private String onShelfTime;
            private String orderCount;
            private int owner;
            private String ownerName;
            private String pics;
            private String plateNo;
            private double price;
            private double receivePercent;
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
