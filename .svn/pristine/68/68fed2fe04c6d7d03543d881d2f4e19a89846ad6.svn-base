package zzbcar.cckj.com.nzzb.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.AaAdapter;
import zzbcar.cckj.com.nzzb.bean.CarBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkManager;

public class TestActivity extends AppCompatActivity {
  private String json_path="http://192.168.1.108:8080/zzb/app/car/queryCarByCondition?brand=27&pageSize=10&pageNum=0&orderBy=1d2a&seatNum=2";
    private String login_path="http://192.168.1.108:8080/";
    private Button bt_prise;
   private OkManager manager;//工具类
    public final String TAG = OkManager.class.getSimpleName();//获取类名
    private Button bt_send;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        bt_prise = (Button) findViewById(R.id.bt_parse);
        bt_send = (Button) findViewById(R.id.bt_send);
        lv = (ListView) findViewById(R.id.lv);
        manager=OkManager.getInstance();
//获取字符串
        bt_prise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.asyncJsonByURL(Constant.CAR_BRAND_URL, new OkManager.Func1() {
                    @Override
                    public void onResponse(String result) {
                        Log.i(TAG,result);//获取json字符串
                        CarBean carBean = GsonUtil.parseJsonWithGson(result, CarBean.class);
                        List<CarBean.DataBean> data = carBean.getData();

                        lv.setAdapter(new AaAdapter((Context) data));


                    }
                });



            }
        });
        //表单提交
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("username","admin");
                map.put("password","12345");
                manager.sendComplexForm(login_path, map, new OkManager.Fun4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                         Log.i(TAG,jsonObject.toString());
                    }
                });

            }
        });
    }



//    class MyAdapter extends BaseAdapter{
//
//        private final List<CarBean.DataBean> list;
//
//        public MyAdapter(List<CarBean.DataBean> list){
//
//            this.list = list;
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            View view1 = View.inflate(TestActivity.this, R.layout.aa, null);
//            ImageView iv_car = (ImageView)view1.findViewById(R.id.iv_car);
//            TextView tv_car = (TextView)view1.findViewById(R.id.tv_car);
//
//            CarBean.DataBean dataBean = list.get(i);
//           iv_car.setImageDrawable(Drawable.createFromPath(dataBean.getLogo()));
//
//            tv_car.setText(dataBean.getName());
//
//            return view1;
//        }
//    }
}
