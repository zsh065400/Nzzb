package zzbcar.cckj.com.nzzb.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.HomeVpAdapter;
import zzbcar.cckj.com.nzzb.bean.LunbobBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkManager;

;

/**
 * Created by Admin on 2017/11/1.
 */

public class LunboActivity extends BaseActivity {
    private OkManager manager = new OkManager();//工具类

    @BindView(R.id.vp_homefragment_lunbo)
    ViewPager vpHomefragmentLunbo;
    @BindView(R.id.rb_homefragment_self_rent)
    RadioButton rbHomefragmentSelfRent;
    @BindView(R.id.rb_homefragment_business_rent)
    RadioButton rbHomefragmentBusinessRent;
    @BindView(R.id.rb_homefragment_married_rent)
    RadioButton rbHomefragmentMarriedRent;


    private LunbobBean mlunbo;
    private String[] lunboUrls;

    private HomeVpAdapter mVpAdapter;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_lunbo;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        initViewPager();

    }

    private void initViewPager() {
        manager.asyncJsonByURL(Constant.HOME_LUNBO_URL, new OkManager.Func1() {
            @Override
            public void onResponse(String result) {
                parseLunBoData(result);
            }
        });


    }

    private void parseLunBoData(String result) {


        mlunbo = GsonUtil.parseJsonWithGson(result, LunbobBean.class);
        List<LunbobBean.DataBean> data = mlunbo.getData();
        if (mVpAdapter == null) {
            mVpAdapter = new HomeVpAdapter(this, data);

        }
        vpHomefragmentLunbo.setAdapter(mVpAdapter);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
