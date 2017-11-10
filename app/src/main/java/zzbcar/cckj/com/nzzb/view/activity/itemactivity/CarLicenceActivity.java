package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.IdentiCompleteActivity;

public class CarLicenceActivity extends BaseActivity implements View.OnClickListener {


    private Button bt_licence_car_nest;


    @Override
    protected int getLayoutId() {

        return R.layout.activity_car_identifi2;
    }

    @Override
    protected void initViews() {
        bt_licence_car_nest = (Button) findViewById(R.id.bt_licence_car_nest);


    }

    @Override
    protected void initDatas() {
        bt_licence_car_nest.setOnClickListener(this);
        new TitleBuilder(this).setTitleText("车友认证").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_licence_car_nest:
                Intent intent=new Intent(this,IdentiCompleteActivity.class);
                startActivity(intent);
                break;
        }
    }
}
