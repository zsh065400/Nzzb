package zzbcar.cckj.com.nzzb.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.view.fragment.FindCarFragment;
import zzbcar.cckj.com.nzzb.view.fragment.HomeFragment;
import zzbcar.cckj.com.nzzb.view.fragment.JourneyFragment;
import zzbcar.cckj.com.nzzb.view.fragment.MineFragment;

public class MainActivity extends BaseActivity {
    private FragmentManager fm;
    private RadioGroup rgMain;
    private FrameLayout fl_main;
    private RadioButton rb;
    private RadioButton rb_home;
    private RadioButton rb_find_car;
    private RadioButton rb_journey;
    private RadioButton rb_mine;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_find_car = (RadioButton) findViewById(R.id.rb_find_car);
        rb_journey = (RadioButton) findViewById(R.id.rb_journey);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        rgMain.check(R.id.rb_home);
    }

    @Override
    protected void initDatas() {
        /*初始化微信*/
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constant.WEIXIN_APP_ID, false);
        api.registerApp(Constant.WEIXIN_APP_ID);
        MyApplication.setWxApi(api);
        fm = getSupportFragmentManager();
        FragmentTransaction begin = fm.beginTransaction();
        begin.replace(R.id.fl_main, new HomeFragment());
        begin.commit();

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                FragmentTransaction ft = fm.beginTransaction();
                switch (i){
                    case R.id.rb_home:
                        ft.replace(R.id.fl_main,new HomeFragment());
                        break;
                    case R.id.rb_find_car:
                        ft.replace(R.id.fl_main,new FindCarFragment());
                        break;
                    case R.id.rb_journey:
                        ft.replace(R.id.fl_main,new JourneyFragment());
                        break;
                    case R.id.rb_mine:
                        ft.replace(R.id.fl_main,new MineFragment());
                        break;
                }
                ft.commit();

            }
        });
    }



}
