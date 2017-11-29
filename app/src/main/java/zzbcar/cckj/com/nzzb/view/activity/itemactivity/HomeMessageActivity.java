package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.MarqueenMessageAdapter;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.MainPageBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
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
    @BindView(R.id.rv_marquee_Message)
    RecyclerView rvMarqueeMessage;

    private List<MainPageBean.DataBean.MarqueeBean> marqueeBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_homemessage;
    }

    @Override
    protected void initViews() {
        rvMarqueeMessage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("消息").setLeftIco(R.mipmap.row_back).setLeftIcoListening( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
          }
        });

        OkGo.<String>get(Constant.API_MAIN_PAGE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }

                    private void parseData(String body) {
                        final MainPageBean.DataBean dataList = GsonUtil.parseJsonWithGson(body, MainPageBean.class).getData();
                        final List<MainPageBean.DataBean.MarqueeBean> marqueeMessage = dataList.getMarquee();
                        MarqueenMessageAdapter marqueenMessageAdapter = new MarqueenMessageAdapter(mContext, marqueeMessage);
                        marqueenMessageAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                //跳转消息详情页面
                             final Intent intent=new Intent(mContext,ProtocolActivity.class);
                              intent.putExtra("title",marqueeMessage.get(position).getTitle());
                              intent.putExtra("url",marqueeMessage.get(position).getContent());
                                startActivity(intent);

                            }
                        });
                        rvMarqueeMessage.setAdapter(marqueenMessageAdapter);

                    }
                });



//        String content = marqueeBean.getContent();
//        WebSettings settings = wvHomeMessage.getSettings();
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        settings.setDomStorageEnabled(true);
//        settings.setSupportZoom(false);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setDisplayZoomControls(false);
//        settings.setDefaultTextEncodingName("utf-8");
//        settings.setAppCacheEnabled(true);
//        wvHomeMessage.loadUrl(content);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
