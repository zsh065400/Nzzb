package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hegeyang on 2017/11/21.
 */

public class WeekPriceBean implements Serializable {

    /**
     * errno : 0
     * data : [{"date":"2017-12-13 00:00:00","occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"weekday":4},{"date":"2017-12-14 00:00:00","occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"weekday":5},{"date":"2017-12-15 00:00:00","occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"weekday":6},{"date":"2017-12-16 00:00:00","occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"weekday":7},{"date":"2017-12-17 00:00:00","occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"weekday":1},{"date":"2017-12-18 00:00:00","occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"weekday":2},{"date":"2017-12-19 00:00:00","occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"weekday":3},{"date":"2017-12-20 00:00:00","occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.00125,"weekday":4}]
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
         * date : 2017-12-13 00:00:00
         * occupyAllDay : 0
         * occupyTm1 :
         * occupyTm2 :
         * orderIn : 0
         * price : 0.01
         * weekday : 4
         */

        private String date;
        private int occupyAllDay;
        private String occupyTm1;
        private String occupyTm2;
        private int orderIn;
        private double price;
        private int weekday;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getOccupyAllDay() {
            return occupyAllDay;
        }

        public void setOccupyAllDay(int occupyAllDay) {
            this.occupyAllDay = occupyAllDay;
        }

        public String getOccupyTm1() {
            return occupyTm1;
        }

        public void setOccupyTm1(String occupyTm1) {
            this.occupyTm1 = occupyTm1;
        }

        public String getOccupyTm2() {
            return occupyTm2;
        }

        public void setOccupyTm2(String occupyTm2) {
            this.occupyTm2 = occupyTm2;
        }

        public int getOrderIn() {
            return orderIn;
        }

        public void setOrderIn(int orderIn) {
            this.orderIn = orderIn;
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
