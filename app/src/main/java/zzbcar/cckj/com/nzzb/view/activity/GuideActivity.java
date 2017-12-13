package zzbcar.cckj.com.nzzb.view.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.SPUtils;

public class GuideActivity extends BaseActivity {
    //向导图片
    private int[] imgs = new int[]{R.mipmap.icon_guide1, R.mipmap.icon_guide2, R.mipmap.icon_guide3};

    @BindView(R.id.vp_guide_items)
    ViewPager vpGuide;

    @BindView(R.id.btn_start)
    ImageButton btnStart;

    @BindView(R.id.indicator_guide)
    CircleIndicator indicatorGuide;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(MainActivity.class);
                finish();
            }
        });

        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == imgs.length - 2) {
                    btnStart.setAlpha(positionOffset);
                    btnStart.setClickable(false);
                } else if (position == imgs.length - 1) {
                    btnStart.setAlpha(1);
                    btnStart.setClickable(true);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initDatas() {
        SPUtils.saveBoolean(mContext, Constant.KEY_HAS_GUIDE,true);
        List<View> views = new ArrayList<>();
        for (int img : imgs) {
            final ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(img);
            views.add(imageView);
        }
        initGuideItems(views);
    }

    private void initGuideItems(List<View> views) {
        final GuidePagerAdapter adapter = new GuidePagerAdapter(views);
        vpGuide.setAdapter(adapter);
        indicatorGuide.setViewPager(vpGuide);
    }

    private class GuidePagerAdapter extends PagerAdapter {

        private List<View> views;

        public GuidePagerAdapter(List<View> views) {

            this.views = views;
        }

        @Override
        public int getCount() {

            return views != null ? views.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final View child = views.get(position);
            container.addView(child);
            return child;
        }
    }
}
