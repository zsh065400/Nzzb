package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.AddressAdapter;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.AddressBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.AddAddressFragment;

public class CommonAddressActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.title_leftIco)
    ImageView titleLeftIco;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_rightIco)
    ImageView titleRightIco;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.iv_add_Address_un)
    ImageView ivAddAddressUn;
    @BindView(R.id.tv_add_newAddress_describe)
    TextView tvAddNewAddressDescribe;
    @BindView(R.id.tv_commom_address_add)
    TextView tvCommomAddressAdd;
    @BindView(R.id.fl_common_address_main)
    FrameLayout flCommonAddressMain;
    @BindView(R.id.rv_comm_addr)
    RecyclerView rvCommAddr;
    @BindView(R.id.ll_no_address)
    LinearLayout llNoAddress;
    private AddressAdapter addressAdapter;
    private List<AddressBean.DataBean> addrList;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_common_address;
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("常用地址").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getAddressData();

    }

    private void getAddressData() {
        String user = SPUtils.getString(mContext, "User", "");
        //SigninBean.DataBean.MemberBean member = GsonUtil.parseJsonWithGson(user, SigninBean.class).getData().getMember();
        OkGo.<String>get(Constant.GET_ADDR)
                .params("userId","1")//TODO 数据测试暂时ID写1
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });
    }

    private void parseData(String data) {
        addrList = GsonUtil.getGson().fromJson(data, AddressBean.class).getData();
        if (addrList.size()==0){
            llNoAddress.setVisibility(View.VISIBLE);
            rvCommAddr.setVisibility(View.GONE);
            return;
        }else{
            llNoAddress.setVisibility(View.GONE);
            rvCommAddr.setVisibility(View.VISIBLE);
        }
        rvCommAddr.setLayoutManager(new LinearLayoutManager(mContext));
        if(addressAdapter==null){
            addressAdapter = new AddressAdapter(mContext, addrList);
            addressAdapter.setOnItemDeleteClickListener(new AddressAdapter.OnItemDeleEditClickListener() {
                @Override
                public void onClick(View v, int position) {
                    switch (v.getId()){
                        case R.id.ll_addr_item_delete:
                            deleteItem(position);
                            break;
                    }
                }
            });
            rvCommAddr.setAdapter(addressAdapter);
        }else{
            addressAdapter.refresh(addrList);
            addressAdapter.notifyDataSetChanged();
        }
    }

    private void deleteItem(final int position) {
        String user = SPUtils.getString(mContext, "User", "");
        //SigninBean.DataBean.MemberBean member = GsonUtil.parseJsonWithGson(user, SigninBean.class).getData().getMember();
        //TODO 数据测试暂时ID写1
        new AlertDialog.Builder(mContext)
                .setTitle("提示")
                .setMessage("确定删除地址？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        OkGo.<String>get(Constant.DEL_ADDR)
                                .params("userId","1")
                                .params("addrId",addrList.get(position).getId()+"")
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.body());
                                            if(jsonObject.getInt("errno")!=0){
                                                Toast.makeText(mContext, "删除失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                                return;
                                            }else{
                                                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                                                addrList.remove(position);
                                                addressAdapter.refresh(addrList);
                                                addressAdapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消",null)
                .show();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commom_address_add:
                Intent intent = new Intent(mContext, AddAddressFragment.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void initListeners() {
        tvCommomAddressAdd.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddressData();
    }
}
