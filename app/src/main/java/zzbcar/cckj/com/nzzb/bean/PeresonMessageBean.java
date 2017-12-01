package zzbcar.cckj.com.nzzb.bean;

/**
 * Created by Admin on 2017/11/22.
 */

public class PeresonMessageBean {

    /**
     * errno : 0
     * data : {"orderCount":2,"collectCount":1}
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

    public static class DataBean {
        /**
         * orderCount : 2
         * collectCount : 1
         */

        private int orderCount;
        private int collectCount;

        public int getOrderCount() {
            return orderCount;
        }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }
}
}
