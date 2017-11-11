package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/7.
 */

public class AddAddressFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.add_common_address;
    }

    @Override
    public void initDatas() {
        new TitleBuilder(mActivity).setTitleText("添加地址").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.finish();
            }
        });

    }

    @Override
    public void initViews(View view) {

    }
}
