package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hegeyang on 2017/11/21.
 */

public class WeekPriceBean implements Serializable {

    /**
     * errno : 0
     * data : [{"date":"2017-11-21 00:00:00","price":0.01,"weekday":3},{"date":"2017-11-22 00:00:00","price":0.01,"weekday":4},{"date":"2017-11-23 00:00:00","price":0.01,"weekday":5},{"date":"2017-11-24 00:00:00","price":0.01,"weekday":6},{"date":"2017-11-25 00:00:00","price":0.01,"weekday":7},{"date":"2017-11-26 00:00:00","price":0.01,"weekday":1},{"date":"2017-11-27 00:00:00","price":0.01,"weekday":2},{"date":"2017-11-28 00:00:00","price":0.00125,"weekday":3}]
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
         * date : 2017-11-21 00:00:00
         * price : 0.01
         * weekday : 3
         */

        private String date;
        private double price;
        private int weekday;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getWeekday() {
            return weekday;
        }

        public void setWeekday(int weekday) {
            this.weekday = weekday;
        }
    }
}
