package zzbcar.cckj.com.nzzb.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.SPUtils;

public class SplshActivity extends BaseActivity implements Animation.AnimationListener {

    @BindView(R.id.rl_splash)
    RelativeLayout mRl_Splash;

    private Handler mHandler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splsh;
    }

    @Override
    protected void initViews() {
        Animation animation = createAnimation();
        mRl_Splash.startAnimation(animation);
        //监听动画
        animation.setAnimationListener(this);
    }

    @Override
    protected void initDatas() {

    }

    //创建动画
    private Animation createAnimation() {
        AnimationSet set = new AnimationSet(false);
        //缩放
        ScaleAnimation scale = new ScaleAnimation(0.9f, 1, 0.9f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(2000);
        //透明度
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        return set;

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //延时2s进入向导界面GuideActivity  该方法在主线程   发送一个延时的消息
        mHandler.postDelayed(new MyTask(), 1500);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private class MyTask implements Runnable {

        @Override
        public void run() {
            boolean has_gudi = SPUtils.getBoolean(mContext, Constant.KEY_HAS_GUIDE, false);
            if (has_gudi) {
                //进入主界面
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            } else {
                //进入向导界面
                Intent intent = new Intent(mContext, GuideActivity.class);
                startActivity(intent);
            }
            finish();
        }
    }
}
