package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.UserOrderBean;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by liling on 2017/11/24.
 */

public class CarStatusActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_details)
    ImageView ivDetails;
    @BindView(R.id.top_bar)
    RelativeLayout topBar;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_time_Surplus)
    TextView tvTimeSurplus;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_member)
    TextView tvMember;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    @BindView(R.id.tv_car_name)
    TextView tvCarName;
    @BindView(R.id.tv_car_model_name)
    TextView tvCarModelName;
    @BindView(R.id.tv_car_address)
    TextView tvCarAddress;
    @BindView(R.id.tv_car_detail)
    TextView tvCarDetail;
    @BindView(R.id.tv_take_address)
    TextView tvTakeAddress;
    @BindView(R.id.tv_return_address)
    TextView tvReturnAddress;
    @BindView(R.id.tv_zunt_time)
    TextView tvZuntTime;
    @BindView(R.id.tv_zunt_formula)
    TextView tvZuntFormula;
    @BindView(R.id.car_zujin)
    TextView carZujin;
    @BindView(R.id.tv_illegal_memory)
    TextView tvIllegalMemory;
    @BindView(R.id.tv_baozhengjin)
    TextView tvBaozhengjin;
    @BindView(R.id.tv_offline_memory)
    TextView tvOfflineMemory;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_car_status)
    TextView tvCarStatus;
    @BindView(R.id.ll_sure_pay)
    RelativeLayout llSurePay;
    @BindView(R.id.tv_car_zujinname)
    TextView tvCarZujinname;

    private UserOrderBean databean;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_details;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        //接受详情页面数据
        databean = (UserOrderBean) getIntent().getExtras().getSerializable("data");
        int position = getIntent().getExtras().getInt("position", 0);
        //获取订单对象
        List<UserOrderBean.DataBean> data = databean.getData();
        UserOrderBean.DataBean.CarBean car = data.get(position).getCar();
        //显示数据
        Picasso.with(mContext).load(car.getImgs()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).fit().into(ivCar);//车图片
        tvCarName.setText(car.getBrandName());//车名
        tvCarAddress.setText(car.getPlateNo());//车牌
//        tvCarDetail.setText(car.getModelYear()+car.getSeatNum());//2017款/4座/自动
        tvCarModelName.setText(car.getModel()+"");
        tvTakeAddress.setText(data.get(position).getTakeAddress());//取车地址
        tvReturnAddress.setText(data.get(position).getReturnAddress());//还车地址
        tvZuntTime.setText("");//租赁时长
        carZujin.setText((int) data.get(position).getTotalAmount()+"");//租金
        tvZuntFormula.setText("");//租金计算公式
        tvIllegalMemory.setText("");//违章押金
        tvOfflineMemory.setText("");//线下保证金
        tvSum.setText("");//合计钱数
        tvCarStatus.setText("");//车的类型 例如我要还车

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_car_status)
    public void onViewClicked() {
        // 车的类型  例如我要还车  跳转相应的界面


    }
}
