package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.OrderStatusAdapter;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.bean.UserOrderBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.view.activity.MainActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.OrderStatusActivity;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/4.
 */

public class OrderStatusFragment extends BaseFragment {

    @BindView(R.id.rv_order_list)
    RecyclerView recyclerView;

    @BindView(R.id.order_empty)
    View empty;
    @BindView(R.id.bt_journey_grag_toselect_car)
    Button btJourneyGragToselectCar;

    private OrderStatusAdapter orderStatusAdapter;

    private List<UserOrderBean.DataBean> orderBean;

    /*
    * 四种状态 对应order状态
    * 0 未进行  0 1 2
    * 1 进行中  3 5 6
    * 2 已完成  10
    * 3 已取消  7 8 9
    * */
    private int[] status = {0, 1, 2, 3};

    /*
    * 订单状态：
    * 0 下单未付款
    * //1 付款
    * 2 接单未取车
    * 3 取车
    * 5 用户还车
    * 6 车主收车
    * 7 用户取消
    * 8 后台取消
    * 9 关闭
    * 10 交易成功
    * */
    private String[] orderStatus = {"下单", "已付款", "未取车", "", "已取车", "已还车", "已收车", "已取消", "已取消", "已关闭", "交易成功"};

    private SigninBean.DataBean.MemberBean signInfo;

    private String url = Constant.API_GET_USER_ORDER + "?status=";

    /*
    * 实现逻辑
    * 1 请求每种不同的状态，显示到指定页面（已完成）
    * 2 此Fragment为复用，会多次创建实例，故保存了每个实例显示的订单类型（参见上边注释），数据和对应类型已经具备
    *   此步骤为根据不同的订单类型处理相应的单击事件
    * 3 此处单击事件大致分为几种状态：去支付，取车，还车，取消，收车等，根据不同的状态跳转到不同的页面进行相应操作
    *   此处大致有几个页面需要跳转：支付页面，订单详情页面，以及部分dialog，跳转之后进行操作即可，返回后会自动刷新该页面
    * 4 还有需要修改的页面，即订单详情页面，大致需要显示的状态包括：未支付、未取车、已收车、已取消、已完成等，此处仅仅是显示的内容不同，其它部分会包括剩余时间、超时、超里程、退押金等，根据UI图进行相应修改，根据不同类型的页面进行相应的显示和隐藏即可
    * 5 订单时间表有待开发，根据不同的状态显示不同的路径即可
    * 6 去选车按钮可跳转到筛选车辆，找车等页面
    * 7 相关重要文件 该页面所需的adapter，内部还有部分数据需要进行提取和显示
    *
    * 确认订单信息详细详细
    * 筛车页面时间和位置筛选
    * 车辆详情（选择租用时间、信息设置）
    * */

    @Override
    public void onResume() {
        super.onResume();
        signInfo = SPUtils.getSignInfo(mActivity);
        queryUserOrder();
    }

    public OrderStatusFragment() {
    }

    private String queryStatus = "";

    @SuppressLint("ValidFragment")
    public OrderStatusFragment(String status) {
        /*组成当前界面需要查询的链接*/
        this.queryStatus = status;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_status;
    }

    @Override
    public void initDatas() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    /*查询订单*/
    private void queryUserOrder() {
        if (signInfo != null) {
            final String url = OkHttpUtil.obtainGetUrl(Constant.API_GET_USER_ORDER,
                    "status", queryStatus,
                    "userId", String.valueOf(signInfo.getId()),
                    "token", SPUtils.getToken(mActivity));
            Log.d(TAG, "queryUserOrder: " + url);
            OkGo.<String>get(url)
//                    .params("userId", signInfo.getId())
//                    .params("status", queryStatus)
//                    .params("token", SPUtils.getToken(mActivity))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            UserOrderBean orderBean = GsonUtil.parseJsonWithGson(response.body(), UserOrderBean.class);
                            final int errno = orderBean.getErrno();
                            Log.d(TAG, "onSuccess: " + response.body());
                            if (errno == 0) {
                                final List<UserOrderBean.DataBean> data = orderBean.getData();
                                initOrderList(data);
                            }
                        }
                    });
        } else {
            empty.setVisibility(View.VISIBLE);
        }
    }

    /*显示订单列表*/
    private void initOrderList(final List<UserOrderBean.DataBean> data) {
        if (data == null || data.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            return;
        }
        empty.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        this.orderBean = data;
        Log.e(TAG, "initOrderList: data" + data);
        orderStatusAdapter = new OrderStatusAdapter(mActivity, orderBean);
        orderStatusAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                /*根据不同的订单类型进行不同的操作*/
                UserOrderBean.DataBean dataBean = orderBean.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", dataBean);
                toActivity(OrderStatusActivity.class, bundle);
            }
        });
        recyclerView.setAdapter(orderStatusAdapter);
    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_order_list);
    }


    @OnClick(R.id.bt_journey_grag_toselect_car)
    public void onViewClicked() {
       MainActivity mActivity = (MainActivity) this.mActivity;
        mActivity.setViewPager(1);
    }
}

