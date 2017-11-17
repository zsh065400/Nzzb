package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.REGutil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

/**
 * Created by Admin on 2017/11/7.
 */

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_leftIco)
    ImageView titleLeftIco;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_rightIco)
    ImageView titleRightIco;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.tv_add_address_conn_person)
    TextView tvAddAddressConnPerson;
    @BindView(R.id.et_add_address_comm_name)
    EditText etAddAddressCommName;
    @BindView(R.id.tv_add_address_conn_phone_number)
    TextView tvAddAddressConnPhoneNumber;
    @BindView(R.id.et_add_address_comm_number)
    EditText etAddAddressCommNumber;
    @BindView(R.id.tv_add_address_put_car)
    TextView tvAddAddressPutCar;
    @BindView(R.id.tv_add_address_comm_addr)
    TextView tvAddAddressCommAddr;
    @BindView(R.id.et_add_address_comm_addreDetail)
    EditText etAddAddressCommAddreDetail;
    @BindView(R.id.bt_add_address_submit)
    Button btAddAddressSubmit;
    private String latitude;
    private String longitude;

    @Override
    public int getLayoutId() {
        return R.layout.add_common_address;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    public void initDatas() {
        new TitleBuilder(this).setTitleText("添加地址").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String user = SPUtils.getString(mContext, "User", "");
        if (!TextUtils.isEmpty(user)) {
            SigninBean.DataBean.MemberBean member = GsonUtil.parseJsonWithGson(user, SigninBean.class).getData().getMember();
            String name = member.getName();
            String mobile = member.getMobile();
            if (!TextUtils.isEmpty(name))
                etAddAddressCommName.setText(name);
            if (!TextUtils.isEmpty(mobile))
                etAddAddressCommNumber.setText(mobile);
        }
    }

    @Override
    protected void initListeners() {
        tvAddAddressCommAddr.setOnClickListener(this);
        btAddAddressSubmit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_add_address_comm_addr:
                //跳转选择地址界面
                Intent intent = new Intent(mContext, SetAddressActivity.class);
                intent.putExtra("type",SetAddressActivity.CHOOSE_ADDR);
                startActivityForResult(intent,100);
                break;
            case R.id.bt_add_address_submit:
                //增加地址
                submitAddress();
                break;
        }
    }

    private void submitAddress() {
        String name = etAddAddressCommName.getText().toString().trim();
        String phone = etAddAddressCommNumber.getText().toString().trim();
        String addres1 = tvAddAddressCommAddr.getText().toString().trim();
        String addres2 = etAddAddressCommAddreDetail.getText().toString().trim();
        if(name.length()<1){
            Toast.makeText(mContext, "名字不能小于一个汉字", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!REGutil.checkCellphone(phone)){
            Toast.makeText(mContext, "手机号好像输错了，检查一下吧", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(addres1) || addres1.equals("请选择小区/写字楼/街道等")){
            Toast.makeText(mContext, "请选择小区/写字楼/街道等", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(addres2)){
            Toast.makeText(mContext, "请填写详细的地址", Toast.LENGTH_SHORT).show();
            return;
        }
        String user = SPUtils.getString(mContext, "User", "");
        //SigninBean.DataBean.MemberBean member = GsonUtil.parseJsonWithGson(user, SigninBean.class).getData().getMember();
        HashMap<String, String> params = new HashMap<>();
        //TODO 数据测试暂时ID写1
        params.put("userId","1");
        params.put("linkman",name);
        params.put("mobile",phone);
        params.put("addr",addres1);
        params.put("addrdetail",addres2);
        params.put("latitude",latitude);
        params.put("longitude",longitude);

        OkGo.<String>get(Constant.ADD_ADDR)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            if(jsonObject.getInt("errno")!=0){
                                Toast.makeText(mContext, "添加失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                return;
                            }else{
                                Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String address = data.getStringExtra("address");
            tvAddAddressCommAddr.setText(address);
            latitude = data.getStringExtra("latitude");
            longitude = data.getStringExtra("longitude");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
