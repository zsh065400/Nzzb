package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class HelpCenterActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_helper_user1)
    TextView tvHelperUser1;
    @BindView(R.id.tv_helper_user2)
    TextView tvHelperUser2;
    @BindView(R.id.tv_helper_user3)
    TextView tvHelperUser3;
    @BindView(R.id.tv_helper_user4)
    TextView tvHelperUser4;
    @BindView(R.id.tv_helper_user5)
    TextView tvHelperUser5;
    @BindView(R.id.tv_helper_user6)
    TextView tvHelperUser6;
    @BindView(R.id.tv_helper_user7)
    TextView tvHelperUser7;
    @BindView(R.id.tv_helper_user8)
    TextView tvHelperUser8;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help_center;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("帮助中心").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void initListeners() {
        tvHelperUser1.setOnClickListener(this);
        tvHelperUser2.setOnClickListener(this);
        tvHelperUser3.setOnClickListener(this);
        tvHelperUser4.setOnClickListener(this);
        tvHelperUser5.setOnClickListener(this);
        tvHelperUser6.setOnClickListener(this);
        tvHelperUser7.setOnClickListener(this);
        tvHelperUser8.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        String title="";
        String url = "";
        switch (view.getId()){
            case R.id.tv_helper_user1:
                title = tvHelperUser1.getText().toString();
                url = Constant.USE_PROCEDURE;
                break;
            case R.id.tv_helper_user2:
                title = tvHelperUser2.getText().toString();
                url = Constant.USER_DEPOSIT;
                break;
            case R.id.tv_helper_user3:
                title = tvHelperUser3.getText().toString();
                url = Constant.SIGN_PROTOCOL;
                break;
            case R.id.tv_helper_user4:
                title = tvHelperUser4.getText().toString();
                url = Constant.USER_4;
                break;
            case R.id.tv_helper_user5:
                title = tvHelperUser5.getText().toString();
                url = Constant.USER_5;
                break;
            case R.id.tv_helper_user6:
                title = tvHelperUser6.getText().toString();
                url = Constant.USER_6;
                break;
            case R.id.tv_helper_user7:
                title = tvHelperUser7.getText().toString();
                url = Constant.USER_7;
                break;
            case R.id.tv_helper_user8:
                title = tvHelperUser8.getText().toString();
                url = Constant.USER_8;
                break;
        }
        Intent intent = new Intent(mContext, ProtocolActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
