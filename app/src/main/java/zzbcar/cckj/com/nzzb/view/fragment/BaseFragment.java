package zzbcar.cckj.com.nzzb.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Admin on 2017/11/3.
 */

public abstract class BaseFragment extends Fragment {
    public FragmentActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (FragmentActivity) context;
    }

    /*
         * 返回一个需要展示的View
         */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mActivity = getActivity();
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /*界面初始化后即处理数据*/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        initDatas();
        initListeners();
    }

    protected void toActivity(Class<?> target) {
        toActivity(target, null);
    }

    protected void toActivity(Class<?> target, Bundle bundle) {
        final Intent intent = new Intent(mActivity, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void toActivityWithResult(Class<?> target, int requestCode) {
        toActivityWithResult(target, null, requestCode);
    }

    protected void toActivityWithResult(Class<?> target, Bundle bundle, int requestCode) {
        final Intent intent = new Intent(mActivity, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /*
     * 当Activity初始化之后可以在这里进行一些数据的初始化操作
     */
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        initDatas();
//        initListeners();
//    }

    /**
     * 子类实现此抽象方法返回View进行展示
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
//    protected abstract void initFindViewById(View view);

    /**
     * 子类在此方法中实现数据的初始化
     */
    public abstract void initDatas();

    /**
     * 初始化控件
     */
    public abstract void initViews(View view);

    /**
     * 子类可以复写此方法初始化事件
     */
    protected void initListeners() {

    }
}
