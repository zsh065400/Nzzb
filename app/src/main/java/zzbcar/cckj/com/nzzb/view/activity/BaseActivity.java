package zzbcar.cckj.com.nzzb.view.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by Admin on 2017/10/31.
 */

public abstract class BaseActivity extends FragmentActivity {
    protected String TAG = this.getClass().getSimpleName();

    protected Context mContext;

    protected Resources mResources;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mContext = this;
        mResources = getResources();

        ButterKnife.bind(this);
        initViews();
        initListeners();
        initDatas();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = null;
        mResources = null;
    }

    /**
     * 初始化监听器
     */
    protected void initListeners() {
    }

    /**
     * 获取布局id
     **/
    protected abstract int getLayoutId();

    /**
     * 初始化view
     **/
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();


}
