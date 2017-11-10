package zzbcar.cckj.com.nzzb.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Admin on 2017/11/1.
 */

public class OkManager {
    private OkHttpClient client;
    public volatile static OkManager manager;
    public final String TAG = OkManager.class.getSimpleName();//获取类名
    private Handler handler;

    //提交Json数据
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    //提交字符串
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");


    public OkManager() {
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());

    }

    //使用单例模式 获取对象
    public static OkManager getInstance() {
        OkManager instance = null;

        if (manager == null) {
            synchronized (OkManager.class) {
                if (instance == null) {
                    instance = new OkManager();
                    manager = instance;


                }
            }
        }
        return instance;
    }

    /**
     * 同步请求 开发不常用  会阻塞UI线程
     *
     * @param url
     * @return
     */

    public String syncGetByURL(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;

        try {
            response = client.newCall(request).execute();    //同步请求数据
            if (response.isSuccessful()) {
                return response.body().string();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步请求指定的url 返回的结果是json字符串
     *
     * @param url
     * @param callBack
     */

    public void asyncJsonByURL(String url, final Func1 callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJSonStringMethod(response.body().string(), callBack);
                }
            }
        });
    }

    /**
     * 异步请求返回的是Json对象
     *
     * @param url
     * @param callBack
     */
    public void asyncJsonObjectByURL(String url, final Fun4 callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonObjectMethod(response.body().string(), callBack);
                }
            }
        });

    }

    /**
     * 请求返回的是byte字节数组
     *
     * @param url
     * @param callBack
     */
    public void asyncGetByteURL(String url, final Func2 callBack) {
        final Request request = new Request.Builder().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessByteMethod(response.body().bytes(), callBack);
                }
            }
        });
    }

    /**
     * 异步请求返回的是ImageView类型 bitmap类型
     * @param url
     * @param callBack
     */
    public void asyncDownLoadImageByURL(String url, final Func3 callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    byte[] data = response.body().bytes();
                    Bitmap bitmap = new CropSquareTrans().transform(BitmapFactory.decodeByteArray(data, 0, data.length));
                     callBack.onResponse(bitmap);


                }
            }
        });
    }

    /**
     * 模拟表单提交
     * @param url
     * @param params
     * @param callBack
     */
      public void sendComplexForm(String url, Map<String, String> params,final Fun4 callBack){
          FormBody.Builder form_builder = new FormBody.Builder(); //表单对象 包含以input开始的对象，以html表单为主
          if(params!=null&&!params.isEmpty()){
              for(Map.Entry<String,String> entry:params.entrySet()){
                  form_builder.add(entry.getKey(),entry.getValue());
              }

          }
          RequestBody request_body=form_builder.build();
          Request request = new Request.Builder().url(url).post(request_body).build(); //采用post方式提交
          client.newCall(request).enqueue(new Callback() {
              @Override
              public void onFailure(Call call, IOException e) {
                  e.printStackTrace();
              }

              @Override
              public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&& response.isSuccessful()){
                    onSuccessJsonObjectMethod(response.body().string(),callBack);
                }
              }
          });

      }

    /**
     * 向服务器提交String 请求
     * @param url
     * @param content
     * @param callBack
     */
      public void sendStringByPostMethod(String url,String content,final  Fun4 callBack){
          Request request = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_MARKDOWN, content)).build();
          client.newCall(request).enqueue(new Callback() {
              @Override
              public void onFailure(Call call, IOException e) {
                  e.printStackTrace();
              }

              @Override
              public void onResponse(Call call, Response response) throws IOException {
           if(response!=null&&response.isSuccessful()){
               onSuccessJsonObjectMethod(response.body().string(),callBack);
           }

              }
          });

      }

    //请求返回的结果是Json 字符串
    private void onSuccessJSonStringMethod(final String jsonValue, final Func1 callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onResponse(jsonValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    //请求返回的是bate数组
    private void onSuccessByteMethod(final byte[] data, final Func2 callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onResponse(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    //返回相应的结果是Json对象
    private void onSuccessJsonObjectMethod(final String jsonValue, final Fun4 callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onResponse(new JSONObject(jsonValue));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public interface Func1 {
        void onResponse(String result);
    }

    public interface Func2 {
        void onResponse(byte[] result);
    }

    public interface Func3 {
        void onResponse(Bitmap bitmap);

    }

    public interface Fun4 {
        void onResponse(JSONObject jsonObject);
    }
}
