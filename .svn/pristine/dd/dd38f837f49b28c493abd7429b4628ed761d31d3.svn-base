package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/6.
 */

public class PayActivity extends BaseActivity {
    private int minute = 29;
    private int second = 59;
    private static final int SENDING = -1;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SENDING:
                    if(second>=10){
                        tv_time_remain.setText(minute+":"+second);
                    }else {
                        tv_time_remain.setText(minute+":0"+second);
                    }
            }
            super.handleMessage(msg);
        }
    };
    private TextView tv_time_remain;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;

    }

    @Override
    protected void initView() {
        tv_time_remain = (TextView) findViewById(R.id.tv_time_remain);
        initTime();


    }

    private void initTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;minute>=0;minute--){
                    for (;second>=-1;second--){
                        if(second==-1&&minute>=0){
                            handler.sendEmptyMessage(-1);
                            second=59;
                            minute--;
                        }
                        SystemClock.sleep(1000);
                        handler.sendEmptyMessage(-1);
                    }
                }
            }
        }){

        }.start();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
