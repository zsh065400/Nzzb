package zzbcar.cckj.com.nzzb.view.activity;

import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.main.MainViewPagerAdapter;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.fragment.FindCarFragment;
import zzbcar.cckj.com.nzzb.view.fragment.HomeFragment;
import zzbcar.cckj.com.nzzb.view.fragment.JourneyFragment;
import zzbcar.cckj.com.nzzb.view.fragment.MineFragment;
import zzbcar.cckj.com.nzzb.widget.NoScrollViewPager;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.vp_main)
    NoScrollViewPager vpMain;
    private static MainActivity mainActivity;
    private long backPressTime = 0;
    private static final int SECOND = 1000;
    private JourneyFragment mJourneyFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        initViewPagers();
    }

    @Override
    protected void initListeners() {
        /*切换页面*/
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpMain.setCurrentItem(0, false);
                        break;
                    case R.id.rb_find_car:
                        vpMain.setCurrentItem(1, false);
                        break;
                    case R.id.rb_journey:
                        vpMain.setCurrentItem(2, false);
                        break;
                    case R.id.rb_mine:

                        vpMain.setCurrentItem(3, false);
                        break;
                }
            }
        });
    }

    private void initViewPagers() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FindCarFragment());
        mJourneyFragment = new JourneyFragment();
        fragments.add(mJourneyFragment);
        fragments.add(new MineFragment());
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(adapter);
        /*禁止滑动*/
        vpMain.setScroll(false);
        /*增加缓存页面数量*/
        vpMain.setOffscreenPageLimit(fragments.size() - 1);

        /*默认选中第一个选项卡*/
        rgMain.check(R.id.rb_home);
    }


    @Override

    protected void initDatas() {
        /*初始化微信*/
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constant.WEIXIN_APP_ID, false);
        api.registerApp(Constant.WEIXIN_APP_ID);
        MyApplication.setWxApi(api);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }

    //切换导航栏
    public void setViewPager(int position) {
//        rgMain.check(R.id.rb_find_car);
        rgMain.check(rgMain.getChildAt(position).getId());
        if (position == 2 && mJourneyFragment != null) {
            mJourneyFragment.changeTab(2);
        }
    }


    public MainActivity() {
        mainActivity = this;
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SPUtils.saveString(this,"User","");
        mainActivity=null;
    }

    @Override
    public void onBackPressed() {
        final long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - backPressTime > 2 * SECOND) {
            backPressTime = uptimeMillis;
            Toast.makeText(this, R.string.press_again_to_leave, Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }

    }
}

