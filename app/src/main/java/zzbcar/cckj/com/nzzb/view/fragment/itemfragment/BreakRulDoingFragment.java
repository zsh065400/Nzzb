package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.TicketAdapter;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.bean.TicketBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/8.
 */

public class BreakRulDoingFragment extends BaseFragment {

    @BindView(R.id.rv_break_doing)
    RecyclerView rvBreakDoing;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.break_rule_doing;
    }

    @Override
    public void initDatas() {
        //获取网络数据
        getData();
    }

    private void getData() {
        SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(mActivity);
        OkGo.<String>get(Constant.QUERRY_TICKET)
//                .params("userId",signInfo.getId())
                .params("userId",1)
                .params("status",0)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });
    }

    private void parseData(String body) {
        List<TicketBean.DataBean> dataList = GsonUtil.parseJsonWithGson(body, TicketBean.class).getData();
        if (dataList.size()==0){
            Toast.makeText(mActivity, "暂无违章数据", Toast.LENGTH_SHORT).show();
            return;
        }
        rvBreakDoing.setLayoutManager(new LinearLayoutManager(mActivity));
        TicketAdapter ticketAdapter = new TicketAdapter(mActivity, dataList);
        rvBreakDoing.setAdapter(ticketAdapter);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
