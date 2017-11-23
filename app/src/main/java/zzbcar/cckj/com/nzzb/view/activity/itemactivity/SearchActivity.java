package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.base.BaseRecycleViewAdapter;
import zzbcar.cckj.com.nzzb.adapter.search.FireSearchAdapter;
import zzbcar.cckj.com.nzzb.adapter.search.QueryResultAdapter;
import zzbcar.cckj.com.nzzb.adapter.search.SimpleTextAdapter;
import zzbcar.cckj.com.nzzb.base.SpaceSize;
import zzbcar.cckj.com.nzzb.base.SpacesItemDecoration;
import zzbcar.cckj.com.nzzb.bean.FireSearchBean;
import zzbcar.cckj.com.nzzb.bean.QueryBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.OkHttpUtil;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_search_fire)
    RecyclerView rvSearchFire;
    @BindView(R.id.rv_search_history)
    RecyclerView rvSearchHistory;
    @BindView(R.id.tv_clear_history)
    TextView tvClearHistory;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rv_search_result)
    RecyclerView rvResult;

    private List<QueryBean.DataBean> queryResult;
    private QueryResultAdapter resultAdapter;

    @BindView(R.id.ll_result)
    LinearLayout llResult;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;

    private String searchText = "";
    private List<String> historyDatas = new LinkedList<>();
    private SimpleTextAdapter historyAdapter;
    private Gson gson = new Gson();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        /*解决嵌套滑动问题*/
        rvSearchFire.setNestedScrollingEnabled(false);
        rvSearchHistory.setNestedScrollingEnabled(false);

        rvSearchFire.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rvSearchFire.addItemDecoration(new SpacesItemDecoration(new SpaceSize(20, 10, 20, 10)));

        rvSearchHistory.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvSearchHistory.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initListeners() {
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvCancel.getText().equals("取消")) finish();
                else doSearch();
            }
        });

        /*监听搜索*/
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    tvCancel.setText("搜索");
                    doQueryResult(s.toString());
                    llResult.setVisibility(View.VISIBLE);
                    llSearch.setVisibility(View.INVISIBLE);
                } else {
                    tvCancel.setText("取消");
                    llResult.setVisibility(View.INVISIBLE);
                    llSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*软键盘回车事件*/
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    doSearch();
                }
                return false;
            }
        });

        /*清除历史记录*/
        tvClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.saveString(mContext, "histories", "");
                v.setVisibility(View.GONE);
                updateHistories(null, true);
            }
        });
        //setBackButon(R.id.iv_back);
    }

    /**
     * 请求搜索
     */
    private void doQueryResult(String text) {
        final String url = OkHttpUtil.obtainGetUrl(Constant.API_CAR_QUERY, "text", text);
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final QueryBean queryBean = GsonUtil.parseJsonWithGson(response.body(), QueryBean.class);
                if (queryBean.getErrno() == 0) {
                    queryResult = queryBean.getData();
                    if(queryResult.size()!=0){
                        showResultView();
                    }else{
                        Toast.makeText(mContext, "没有找到您想要的车辆", Toast.LENGTH_SHORT).show();
                    }

                } else {

                }
            }
        });
    }

    /*显示结果对话框*/
    private void showResultView() {
        if (resultAdapter != null) {
            resultAdapter.refresh(queryResult);
            resultAdapter.notifyDataSetChanged();
            return;
        }
        handleListView();
    }

    /*搜索结果列表*/
    private void handleListView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvResult.setLayoutManager(manager);
        resultAdapter = new QueryResultAdapter(mContext, queryResult);
        resultAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                final QueryBean.DataBean dataBean = queryResult.get(position);
                /*跳转详情页面*/
                final Intent intent = new Intent(mContext, CarDetailActivity.class);
                intent.putExtra("carid", dataBean.getId());
                startActivity(intent);
            }
        });
        rvResult.setAdapter(resultAdapter);
    }

    @Override
    protected void initDatas() {
        OkGo.<String>get(Constant.API_FIRE_SEARCH).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final FireSearchBean searchBean = GsonUtil.parseJsonWithGson(response.body(), FireSearchBean.class);
                if (searchBean.getErrno() == 0) {
                    final List<FireSearchBean.DataBean> data = searchBean.getData();
                    initFireRecycle(data);
                }
            }
        });
        initHistories();
    }

    /*加载历史记录*/
    private void initHistories() {
        final String histories = SPUtils.getString(mContext, "histories", "");
        LinkedList<String> datas =
                (LinkedList<String>) GsonUtil.
                        parseJsonToList(
                                histories,
                                new TypeToken<LinkedList<String>>() {
                                }.getType());
        if (datas != null) historyDatas = datas;
        historyDatas.add("凯迪拉克");
        historyDatas.add("12312312");
        if (historyDatas != null && historyDatas.size() != 0) {
            historyAdapter = new SimpleTextAdapter(mContext, historyDatas);
            historyAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    searchText = historyDatas.get(position);
                    refreshSearchText();
                }
            });
            rvSearchHistory.setAdapter(historyAdapter);
            tvClearHistory.setVisibility(View.VISIBLE);
        }
    }

    private void initFireRecycle(final List<FireSearchBean.DataBean> searchBean) {
        final FireSearchAdapter adapter = new FireSearchAdapter(mContext, searchBean);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                searchText = searchBean.get(position).getName();
                refreshSearchText();
            }
        });
        rvSearchFire.setAdapter(adapter);
    }

    private void refreshSearchText() {
        etSearch.setText(searchText);
        /*移动到末尾*/
        etSearch.setSelection(etSearch.getText().length());
        doSearch();
    }

    /*搜索操作*/
    private void doSearch() {
        searchText = etSearch.getText().toString().trim();
        if (!TextUtils.isEmpty(searchText)) {
            updateHistories(searchText, false);
        }
    }

    /*刷新历史记录*/
    private void updateHistories(String item, boolean clear) {
        if (clear) {
            historyDatas.clear();
        } else {
            if (!TextUtils.isEmpty(item)) {
                ((LinkedList<String>) historyDatas).addFirst(item);
                final String value = gson.toJson(historyDatas);
                Log.i(TAG, "updateHistories: " + value);
                SPUtils.saveString(mContext, "histories", value);
            }
        }
        historyAdapter.notifyDataSetChanged();
    }
}
