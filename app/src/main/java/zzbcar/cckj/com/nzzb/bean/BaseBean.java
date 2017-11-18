package zzbcar.cckj.com.nzzb.bean;

import java.io.Serializable;

/**
 * Created by Scout
 * Created on 2017/11/18 12:33.
 */

public class BaseBean implements Serializable {

    private static final long serialVersionUID = -967372124004657425L;


    /**
     * errno : 0
     * data : 1
     * message : 请求成功
     */

    private int errno;
    private String data;
    private String message;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
