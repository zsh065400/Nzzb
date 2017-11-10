package zzbcar.cckj.com.nzzb.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Admin on 2017/10/31.
 */

public abstract class BaseActivity extends FragmentActivity  {
    private int layoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        regComButton();

    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private void regComButton() {

    }


    /**
     * 获取布局id
     **/
    protected abstract int getLayoutId();

    /**
     * 初始化view
     **/
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();





}
