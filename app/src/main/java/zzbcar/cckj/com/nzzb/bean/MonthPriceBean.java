package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hegeyang on 2017/11/22.
 */

public class MonthPriceBean implements Serializable {

    /**
     * errno : 0
     * data : [{"date":"2017-11-01 00:00:00","day":1,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":4},{"date":"2017-11-02 00:00:00","day":2,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":5},{"date":"2017-11-03 00:00:00","day":3,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":6},{"date":"2017-11-04 00:00:00","day":4,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":7},{"date":"2017-11-05 00:00:00","day":5,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":1},{"date":"2017-11-06 00:00:00","day":6,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":2},{"date":"2017-11-07 00:00:00","day":7,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":3},{"date":"2017-11-08 00:00:00","day":8,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":4},{"date":"2017-11-09 00:00:00","day":9,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":5},{"date":"2017-11-10 00:00:00","day":10,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":2,"weekday":6},{"date":"2017-11-11 00:00:00","day":11,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":7},{"date":"2017-11-12 00:00:00","day":12,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":1},{"date":"2017-11-13 00:00:00","day":13,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":2},{"date":"2017-11-14 00:00:00","day":14,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":3},{"date":"2017-11-15 00:00:00","day":15,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":4},{"date":"2017-11-16 00:00:00","day":16,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":5},{"date":"2017-11-17 00:00:00","day":17,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":6},{"date":"2017-11-18 00:00:00","day":18,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":7},{"date":"2017-11-19 00:00:00","day":19,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":1},{"date":"2017-11-20 00:00:00","day":20,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":2},{"date":"2017-11-21 00:00:00","day":21,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":3},{"date":"2017-11-22 00:00:00","day":22,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":4},{"date":"2017-11-23 00:00:00","day":23,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":5},{"date":"2017-11-24 00:00:00","day":24,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":6},{"date":"2017-11-25 00:00:00","day":25,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":7},{"date":"2017-11-26 00:00:00","day":26,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":1},{"date":"2017-11-27 00:00:00","day":27,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":2},{"date":"2017-11-28 00:00:00","day":28,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":3},{"date":"2017-11-29 00:00:00","day":29,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":4},{"date":"2017-11-30 23:59:59","day":30,"month":11,"occupyAllDay":0,"occupyTm1":"","occupyTm2":"","orderIn":0,"price":0.01,"type":1,"weekday":5}]
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
         * date : 2017-11-01 00:00:00
         * day : 1
         * month : 11
         * occupyAllDay : 0
         * occupyTm1 :
         * occupyTm2 :
         * orderIn : 0
         * price : 0.01
         * type : 2
         * weekday : 4
         */

        private String date;
        private int day;
        private int month;
        private int occupyAllDay;
        private String occupyTm1;
        private String occupyTm2;
        private int orderIn;
        private double price;
        private int type;
        private int weekday;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getWeekday() {
            return weekday;
        }

        public void setWeekday(int weekday) {
            this.weekday = weekday;
        }
    }
}
