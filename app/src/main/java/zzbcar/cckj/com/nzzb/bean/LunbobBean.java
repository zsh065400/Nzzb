package zzbcar.cckj.com.nzzb.bean;

import java.util.List;

/**
 * Created by Admin on 2017/11/6.
 */

public class LunbobBean {

    /**
     * errno : 0
     * data : [{"content":"192.168.1.253:8080/marquee/00000a-20171103102015.htm","createTime":"2017-10-30 12:04:29","id":10,"picUrl":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/20171103102515_656.jpg","sort":0,"title":"测试修改","updateTime":"2017-11-03 10:20:15"},{"content":"192.168.1.253:8080/marquee/000008-20171103102030.htm","createTime":"2017-10-25 09:58:04","id":8,"picUrl":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/555.jpg","sort":0,"title":"123","updateTime":"2017-11-03 10:20:30"},{"content":"192.168.1.253:8080/marquee/000006-20171102154808.htm","createTime":"2017-10-24 16:06:14","id":6,"picUrl":"http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/333.jpg","sort":0,"title":"1234567","updateTime":"2017-11-02 15:48:08"}]
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
         * content : 192.168.1.253:8080/marquee/00000a-20171103102015.htm
         * createTime : 2017-10-30 12:04:29
         * id : 10
         * picUrl : http://zzb-2017.oss-cn-hangzhou.aliyuncs.com/zzbcar/20171103102515_656.jpg
         * sort : 0
         * title : 测试修改
         * updateTime : 2017-11-03 10:20:15
         */

        private String content;
        private String createTime;
        private int id;
        private String picUrl;
        private int sort;
        private String title;
        private String updateTime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
