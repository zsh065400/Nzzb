package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.ll_master_xieyi)
    LinearLayout llMasterXieyi;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_about_us;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("关于至尊宝").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void initListeners() {
        llMasterXieyi.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_master_xieyi:
                Intent intent = new Intent(mContext, ProtocolActivity.class);
                intent.putExtra("title","用户协议");
                intent.putExtra("url","");
                startActivity(intent);
                break;
        }
    }
}
