package zzbcar.cckj.com.nzzb.view.fragment.itemfragment;

import android.view.LayoutInflater;
import android.view.View;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.view.fragment.BaseFragment;

/**
 * Created by Admin on 2017/11/7.
 */

public class AddAddressFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater) {
        View view = View.inflate(getContext(), R.layout.add_common_address, null);

        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {
        new TitleBuilder(mActivity).setTitleText("添加地址").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.finish();
            }
        });

    }
}
