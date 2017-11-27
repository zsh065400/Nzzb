package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/27.
 */

public class HomeMessageActivity extends BaseActivity {
    @BindView(R.id.title_leftIco)
    ImageView titleLeftIco;

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_rightIco)
    ImageView titleRightIco;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.top_bar)
    LinearLayout topBar;
    @BindView(R.id.wv_homeMessage)
    WebView wvHomeMessage;
    private List<MainPageBean.DataBean.MarqueeBean> marqueeDatas;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_homemessage;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("消息").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        MainPageBean.DataBean.MarqueeBean marqueeBean = (MainPageBean.DataBean.MarqueeBean) getIntent().getSerializableExtra("marquee");
//        marqueeBean.getId();
        WebSettings settings = wvHomeMessage.getSettings();
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDisplayZoomControls(false);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        wvHomeMessage.loadUrl(marqueeBean.getContent());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
