package zzbcar.cckj.com.nzzb.bean;

import java.util.List;

/**
 * Created by Admin on 2017/11/21.
 */

public class MyCollectBean {

    /**
     * errno : 0
     * data : [{"addr":"杭州市庆春路58号","age":5,"brand":18,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Audi.png","brandName":"奥迪","carName":"奥迪R8","collectCount":1,"createTime":"2017-10-12 11:59:03","deposit":20000,"distance":0,"engineLiter":"V6","id":2,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.278001,"longitude":120.168069,"mileage":50000,"model":9,"modelName":"R8","name":"奥迪 S8 2016款 4.0 TFSI quattro","onShelfTime":"2017-11-10 14:41:55","orderCount":"10","owner":5,"ownerAvatar":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg","ownerName":"ZZB","pics":"http://living-2016.oss-cn-hangzhou.aliyuncs.com/7f40bce7e9992e64a3197d2082f4acc8.png","plateNo":"浙AK136A","price":0.01,"receivePercent":0.25,"remark":"测试车辆","seatNum":2,"series":13,"seriesName":"R8","status":1,"transmissionCase":0,"useCity":"重庆,杭州,北京","useType":1},{"addr":"杭州市庆春路58号","age":0,"brand":23,"brandLogo":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/brand/Lamborghini.png","brandName":"兰博基尼","carName":"兰博基尼Aventador","collectCount":1,"createTime":"2017-10-12 11:59:03","deposit":20000,"distance":0,"engineLiter":"V6","id":3,"imgs":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/111.jpg,http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/222.jpg","latitude":30.22935,"longitude":120.033163,"mileage":0,"model":17,"modelName":"LP-700-4","name":"至尊宝CAR2","onShelfTime":"2017-10-12 11:59:03","orderCount":"4","owner":10,"ownerAvatar":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg","ownerName":"Happy","pics":"http://living.cto1024.com/a604af1f51dcdc715edabae672a536e2.jpg","plateNo":"浙AK137A","price":0.01,"receivePercent":0,"remark":"测试车辆","seatNum":2,"series":22,"seriesName":"Aventador","status":2,"transmissionCase":2,"useCity":"重庆,杭州,北京","useType":1}]
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
         * name : 奥迪 S8 2016款 4.0 TFSI quattro
         * onShelfTime : 2017-11-10 14:41:55
         * orderCount : 10
         * owner : 5
         * ownerAvatar : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/headimg/1511185739418.jpg
         * ownerName : ZZB
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
         * useCity : 重庆,杭州,北京
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
