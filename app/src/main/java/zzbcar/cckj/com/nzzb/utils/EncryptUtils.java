package zzbcar.cckj.com.nzzb.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     desc  : 加密解密相关的工具类
 * </pre>
 */
public final class EncryptUtils {

    /**
     * 获得MD5加密字符串
     *
     * @param source 源字节数组
     * @return 加密后的字符串
     */
    public static String getMD5(String str){
        String result = "";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            md5.update((str).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte b[] = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        result = buf.toString();
        return result;
    }
}
