package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.AddAddressFragment;

public class CommonAddressActivity extends BaseActivity implements View.OnClickListener{


    private TextView tv_commom_address_add;

    @Override
    protected int getLayoutId() {
        
        return R.layout.activity_common_address;
    }

    @Override
    protected void initViews() {

        tv_commom_address_add = (TextView) findViewById(R.id.tv_commom_address_add);

    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("常用地址").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_commom_address_add.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_commom_address_add:
                tv_commom_address_add.setVisibility(View.INVISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_common_address_main,new AddAddressFragment());
                transaction.commit();


                break;
        }

    }
}
