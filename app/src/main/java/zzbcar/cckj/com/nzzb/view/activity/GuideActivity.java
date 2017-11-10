package zzbcar.cckj.com.nzzb.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    //向导图片
    private int[] imgs = new int[]{R.mipmap.icon_guide1, R.mipmap.icon_guide2, R.mipmap.icon_guide3,R.mipmap.icon_guide4};
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.container_gray_point)
    LinearLayout containerGrayPoint;
    @BindView(R.id.red_point)
    View redPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
