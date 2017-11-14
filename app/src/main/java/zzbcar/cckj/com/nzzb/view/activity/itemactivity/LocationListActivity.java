package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.LocationSelectAdapter;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.LocationSelectBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkManager;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;



/**
 * Created by Admin on 2017/11/14.
 */

public class LocationListActivity extends BaseActivity {
    private RecyclerView rv_location_list;
    private OkManager manager;
    private List<LocationSelectBean.DataBean> data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location_list;
    }

    @Override
    protected void initViews() {
        rv_location_list = (RecyclerView) findViewById(R.id.rv_location_list);

        rv_location_list.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));

        manager = new OkManager();
        manager.asyncJsonByURL(Constant.LOCATION_SELECT_URL, new OkManager.Func1() {
            @Override
            public void onResponse(String result) {
                parseLocationData(result);
            }
        });

    }

    private void parseLocationData(String result) {
        LocationSelectBean selectBean = GsonUtil.parseJsonWithGson(result, LocationSelectBean.class);
        data = selectBean.getData();
        LocationSelectAdapter locationSelectAdapter = new LocationSelectAdapter(data, mContext);
        rv_location_list.setAdapter(locationSelectAdapter);
        locationSelectAdapter.addRecycleItemListener(new LocationSelectAdapter.OnRecycleItemListener<LocationSelectBean.DataBean>() {
            @Override
            public void OnRecycleItemClick(View v, LocationSelectBean.DataBean o) {
                SPUtils.saveString(LocationListActivity.this,Constant.SP_LAST_LOCATION,o.getName());
                finish();
            }
        });
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("选择城市").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
