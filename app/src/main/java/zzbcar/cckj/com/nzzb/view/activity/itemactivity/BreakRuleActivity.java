package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.BreakRuleVpAdapter;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.BreakRulCompleteFragment;
import zzbcar.cckj.com.nzzb.view.fragment.itemfragment.BreakRulDoingFragment;

public class BreakRuleActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{


    private TabLayout tablayout_break_rule;
    private ViewPager break_rule_view_pager;
    //TabLayout标签
    private String[] titles=new String[]{"正在进行","已处理"};
    private List<Fragment> fragments=new ArrayList<>();
    private BreakRuleVpAdapter break_rule_vpadapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_break_rule;
    }

    @Override
    protected void initViews() {
        tablayout_break_rule = (TabLayout) findViewById(R.id.tablayout_break_rule);
        break_rule_view_pager = (ViewPager) findViewById(R.id.break_rule_view_pager);
        //设置TabLayout标签的显示方式
        tablayout_break_rule.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab : titles) {
            tablayout_break_rule.addTab(tablayout_break_rule.newTab().setText(tab));

        }
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("违章专区").setLeftIco(R.drawable.ic_keyboard_arrow_left_black_36dp).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//设置TabLayout点击事件
        tablayout_break_rule.setOnTabSelectedListener(this);
        fragments.add(new BreakRulDoingFragment());
        fragments.add(new BreakRulCompleteFragment());
        break_rule_vpadapter = new BreakRuleVpAdapter(getSupportFragmentManager(), titles, fragments);
        break_rule_view_pager.setAdapter(break_rule_vpadapter);
        tablayout_break_rule.setupWithViewPager(break_rule_view_pager);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        break_rule_view_pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
