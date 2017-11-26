package zzbcar.cckj.com.nzzb.bean;

import java.util.List;

/**
 * Created by Admin on 2017/11/24.
 */

public class UserCollectBean {

    /**
     * errno : 0
     * data : [19,4]
     * message : 请求成功
     */

    private int errno;
    private String message;
    private List<Integer> data;

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

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
