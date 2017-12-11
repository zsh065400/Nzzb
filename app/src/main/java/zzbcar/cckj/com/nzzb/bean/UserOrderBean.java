package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Scout
 * Created on 2017/11/18 16:01.
 */

public class UserOrderBean implements Serializable {

    private static final long serialVersionUID = -1370481167658443335L;

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

    public static class DataBean implements Serializable {
        private int abolishMoney;
        private Object abolishTime;
        private CarBean car;
        private int carId;
        private int city;
        private String createTime;
        private String detail;
        private String endTime;
        private int exceedMoney;
        private int id;
        private int lastReturnMoney;
        private double leasePrice;
        private double onlineAmount;
        private String orderNo;
        private int payStatus;
        private String remark;
        private int returnAddrId;
        private String returnAddress;
        private int returnHome;
        private String returnMapLocation;
        private String returnTime;
        private Object rtdepositTime;
        private String startTime;
        private int status;
        private String subject;
        private int takeAddrId;
        private String takeAddress;
        private int takeHome;
        private String takeMapLocation;
        private String takeTime;
        private int timeoutMoney;
        private double totalAmount;
        private double trafficDepositMoney;
        private int trafficPunlishMoney;
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

        public int getExceedMoney() {
            return exceedMoney;
        }

        public void setExceedMoney(int exceedMoney) {
            this.exceedMoney = exceedMoney;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLastReturnMoney() {
            return lastReturnMoney;
        }

        public void setLastReturnMoney(int lastReturnMoney) {
            this.lastReturnMoney = lastReturnMoney;
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

        public String getReturnTime() {
            return returnTime;
        }

        public void setReturnTime(String returnTime) {
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

        public String getTakeTime() {
            return takeTime;
        }

        public void setTakeTime(String takeTime) {
            this.takeTime = takeTime;
        }

        public int getTimeoutMoney() {
            return timeoutMoney;
        }

        public void setTimeoutMoney(int timeoutMoney) {
            this.timeoutMoney = timeoutMoney;
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
             * addr : 杭州市五常街道
             * age : 6
             * applyCity : ,1,2,3,10,
             * brand : 21
             * brandLogo : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/NISSAN.png
             * brandName : 日产
             * carName : 日产GTR
             * collectCount : 1
             * createTime : 2017-10-12 11:59:03
             * deposit : 20000
             * distance : 0
             * engineLiter : V6
             * id : 1
             * imgs : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg
             * latitude : 30.278001
             * longitude : 120.168069
             * mileage : 30001
             * model : 11
             * modelName : GTR
             * modelYear : 2016
             * name : 测试车辆
             * onShelfTime : 2017-11-10 14:45:08
             * orderCount : 57
             * owner : 4
             * ownerAvatar :
             * ownerName :
             * pics : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzb/car/car_car/20120809135707632.jpg
             * plateNo : 沪C1CU76
             * price : 0.01
             * receivePercent : 0.36
             * remark : 测试车辆
             * seatNum : 2
             * series : 16
             * seriesName : GTR
             * status : 1
             * transmissionCase : 0
             * useCity : 重庆市,杭州市,北京市,温州市
             * useType : 1
             */

            private String addr;
            private int age;
            private String applyCity;
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

            public String getApplyCity() {
                return applyCity;
            }

            public void setApplyCity(String applyCity) {
                this.applyCity = applyCity;
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
