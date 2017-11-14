package zzbcar.cckj.com.nzzb.bean;

import java.util.List;

/**
 * Created by Admin on 2017/11/14.
 */

public class LocationSelectBean {

    /**
     * errno : 0
     * data : [{"id":1,"name":"重庆"},{"id":2,"name":"杭州"},{"id":3,"name":"北京"},{"id":4,"name":"上海"},{"id":5,"name":"武汉"},{"id":6,"name":"宁波"},{"id":7,"name":"成都"},{"id":8,"name":"三亚"},{"id":9,"name":"苏州"},{"id":10,"name":"温州"}]
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
         * id : 1
         * name : 重庆
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
