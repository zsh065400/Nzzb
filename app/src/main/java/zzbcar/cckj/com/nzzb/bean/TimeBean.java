package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;

/**
 * Created by Scout
 * Created on 2017/11/16 16:18.
 */

public class TimeBean implements Serializable {

    private static final long serialVersionUID = -6913768305788270195L;
    /**
     * errno : 0
     * data : 1510818645
     * message : 请求成功
     */

    private int errno;
    private int data;
    private String message;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
