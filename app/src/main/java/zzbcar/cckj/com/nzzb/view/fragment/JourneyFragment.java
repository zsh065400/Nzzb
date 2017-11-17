package zzbcar.cckj.com.nzzb.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.MyViewPagerAdapter;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.CancelFragment;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.CompleteFragment;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.ProceedFragment;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.UnProceedFragment;

/**
 * Created by Admin on 2017/10/31.
 */

public class JourneyFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private TabLayout journet_tab_layou;
    private ViewPager journey_view_pager;
    //TabLayout标签
    private String[] titles = new String[]{"未进行", "进行中", "已完成", "已取消"};
    private List<Fragment> fragments = new ArrayList<>();
    private MyViewPagerAdapter viewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_journey;
    }

    @Override
    public void initDatas() {
        //设置TabLayout点击事件
        journet_tab_layou.setOnTabSelectedListener(this);
        fragments.add(new UnProceedFragment());
        fragments.add(new ProceedFragment());
        fragments.add(new CompleteFragment());
        fragments.add(new CancelFragment());
        viewPagerAdapter = new MyViewPagerAdapter(mActivity.getSupportFragmentManager(), titles, fragments);
        journey_view_pager.setAdapter(viewPagerAdapter);
        journet_tab_layou.setupWithViewPager(journey_view_pager);

    }

    @Override
    public void initViews(View view) {
        journet_tab_layou = (TabLayout) view.findViewById(R.id.journet_tab_layou);
        journey_view_pager = (ViewPager) view.findViewById(R.id.journey_view_pager);
        //设置TabLayout标签的显示方式
        journet_tab_layou.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab : titles) {
            journet_tab_layou.addTab(journet_tab_layou.newTab().setText(tab));
        }

        StatusBarUtil.setViewTopPadding(mActivity, view, R.id.top_bar);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        journey_view_pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
