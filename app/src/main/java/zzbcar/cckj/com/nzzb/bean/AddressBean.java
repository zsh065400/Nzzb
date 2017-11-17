package zzbcar.cckj.com.nzzb.bean;

import java.util.List;

/**
 * Created by hegeyang on 2017/11/17.
 */

public class AddressBean {

    /**
     * errno : 0
     * data : [{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":2,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":3,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":4,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":5,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":6,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":7,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":8,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":9,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":10,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":11,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":12,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"},{"addr":"杭州市拱墅区华源创意工场","addrDetail":"48幢1楼","firstChoice":0,"id":13,"latitude":30,"linkman":"小超","longitude":120.32323,"mobile":"13712345678"}]
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
         * addr : 杭州市拱墅区华源创意工场
         * addrDetail : 48幢1楼
         * firstChoice : 0
         * id : 2
         * latitude : 30
         * linkman : 小超
         * longitude : 120.32323
         * mobile : 13712345678
         */

        private String addr;
        private String addrDetail;
        private int firstChoice;
        private int id;
        private double latitude;
        private String linkman;
        private double longitude;
        private String mobile;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAddrDetail() {
            return addrDetail;
        }

        public void setAddrDetail(String addrDetail) {
            this.addrDetail = addrDetail;
        }

        public int getFirstChoice() {
            return firstChoice;
        }

        public void setFirstChoice(int firstChoice) {
            this.firstChoice = firstChoice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
