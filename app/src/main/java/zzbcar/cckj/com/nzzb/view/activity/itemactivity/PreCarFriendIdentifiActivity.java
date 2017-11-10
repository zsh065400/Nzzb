package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/6.
 */

public class PreCarFriendIdentifiActivity extends BaseActivity implements View.OnClickListener{

    private Button bt_mine_fragment_becomedriver;


    @Override
    protected int getLayoutId() {
        return R.layout.layout_become_carfrienf;
    }

    @Override
    protected void initViews() {
        bt_mine_fragment_becomedriver = (Button)findViewById(R.id.bt_mine_fragment_becomedriver);



    }

    @Override
    protected void initDatas() {
        bt_mine_fragment_becomedriver.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()){
            case R.id.bt_mine_fragment_becomedriver:
                intent=new Intent(this,CarFriendIdentifiActivity.class);
                startActivity(intent);
                break;

        }
    }
}
