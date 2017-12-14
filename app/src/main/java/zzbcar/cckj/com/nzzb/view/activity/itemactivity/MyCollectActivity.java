package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.MyCollectAdapter;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.MyCollectBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.activity.LoginActivity;

/**
 * Created by Admin on 2017/11/20.
 */

public class MyCollectActivity extends BaseActivity {
    private RecyclerView rv_car_mycollect;
    private List<MyCollectBean.DataBean> dataList;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_mycollect;
    }

    @Override
    protected void initViews() {
        rv_car_mycollect = (RecyclerView) findViewById(R.id.rv_car_mycollect);
        rv_car_mycollect.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        final SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(this);
        OkGo.<String>get(Constant.MY_COLLECT_URL)
                .params("userId",signInfo.getId())
//                .params("userId",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(signInfo!=null){
                            parseData(response.body());
                        }


                    }
                });

        new TitleBuilder(this).setTitleText("我的收藏").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void parseData(String body) {
//        dataList = GsonUtil.parseJsonWithGson(body, MyCollectBean.class).getData();
        MyCollectBean myCollectBean = GsonUtil.parseJsonWithGson(body, MyCollectBean.class);
        int errno = myCollectBean.getErrno();
        if (errno==0){
            dataList = myCollectBean.getData();
            if (dataList.size()==0){
                Toast.makeText(mContext, "还没有收藏可查询哦!", Toast.LENGTH_SHORT).show();
                return;
            }
            MyCollectAdapter myCollectAdapter = new MyCollectAdapter(mContext, dataList);
            myCollectAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {


                    final Intent intent = new Intent(mContext, CarDetailActivity.class);
                    intent.putExtra("carid", dataList.get(position).getId());
                    startActivity(intent);
                }
            });
            rv_car_mycollect.setAdapter(myCollectAdapter);
        }
        else if (errno==101){
            Toast.makeText(mContext, "会话超时,请重新登陆", Toast.LENGTH_SHORT).show();
            toActivity(LoginActivity.class);
            return;
        }


    }
}
