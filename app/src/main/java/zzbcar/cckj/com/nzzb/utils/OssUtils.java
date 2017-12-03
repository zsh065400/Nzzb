package zzbcar.cckj.com.nzzb.utils;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.io.File;
import java.util.HashMap;

/**
 * Created by hegeyang on 2017/11/15.
 */

public class OssUtils {

    private static OSSClient oss;
    private static PutObjectRequest put;

    public static OSSClient initOss(Context context){
       if(oss==null){
           // ACCESS_ID,ACCESS_KEY是在阿里云申请的
           OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(Constant.ACCESS_ID, Constant.ACCESS_PAS);
           ClientConfiguration conf = new ClientConfiguration();
           conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
           conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
           conf.setMaxConcurrentRequest(8); // 最大并发请求数，默认5个
           conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

           // oss为全局变量，OSS_ENDPOINT是一个OSS区域地址
           oss = new OSSClient(context, Constant.END_POINT, credentialProvider, conf);
       }
        return oss;
    }
    //上传图片
    public static PutObjectRequest putImage(File file,String ossPath){
            ObjectMetadata objectMeta = new ObjectMetadata();
            //objectMeta.setContentType("image/jpeg");
            LogUtil.e("============"+file.getPath()+"========="+file.getName());
            put = new PutObjectRequest(Constant.BUCKET, ossPath+file.getName(),file.getPath());
            put.setMetadata(objectMeta);
            put.setCallbackParam(new HashMap<String, String>() {
                {
                    //put("callbackUrl", "110.75.82.106/callback");
                    put("callbackHost", "oss-cn-hangzhou.aliyuncs.com");
                    put("callbackBodyType", "application/json");
                    put("callbackBody", "{\"mimeType\":${mimeType},\"size\":${size}}");
                }
            });


        return put;
    }
}
