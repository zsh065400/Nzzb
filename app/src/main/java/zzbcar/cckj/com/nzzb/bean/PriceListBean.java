package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Scout
 * Created on 2017/11/13 17:37.
 */

public class PriceListBean implements Serializable {

    private static final long serialVersionUID = 4376949588545050115L;
    /**
     * errno : 0
     * data : {"amount":0.00875,"list":[{"amount":0.00875,"date":"2017-11-13 17:14:29","forHour":1,"price":0.00125,"type":1,"weekday":0}]}
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
         * amount : 0.00875
         * list : [{"amount":0.00875,"date":"2017-11-13 17:14:29","forHour":1,"price":0.00125,"type":1,"weekday":0}]
         */

        private double amount;
        private List<ListBean> list;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * amount : 0.00875
             * date : 2017-11-13 17:14:29
             * forHour : 1
             * price : 0.00125
             * type : 1
             * weekday : 0
             */

            private double amount;
            private String date;
            private int forHour;
            private double price;
            private int type;
            private int weekday;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getForHour() {
                return forHour;
            }

            public void setForHour(int forHour) {
                this.forHour = forHour;
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
}
