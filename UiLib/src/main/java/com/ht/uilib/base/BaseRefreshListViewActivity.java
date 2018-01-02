package com.ht.uilib.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ht.baselib.utils.NetUtils;
import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;
import com.ht.uilib.widget.LoadingView;
import com.ht.uilib.widget.pullrefresh.PullToRefreshBase;
import com.ht.uilib.widget.pullrefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseRefreshListViewActivity<T> extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    protected final List<T> mDataList = new ArrayList<>();
    private BaseAdapter mAdapter;
    protected PullToRefreshListView mPullToRefreshListView;

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_base_refresh_listview;
    }

    @Override
    protected void initChildView() {
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.plv_activity_base_refresh_listview);
        mPullToRefreshListView.setPullLoadEnabled(false);
        mPullToRefreshListView.setScrollLoadEnabled(hasMoreData());

        mListView = mPullToRefreshListView.getRefreshableView();
        mAdapter = getAdapter();
        View headerView = getHeaderView();
        if (headerView != null){
            mListView.addHeaderView(headerView);
        }
        initChildDataFromCache();

        if (mDataList.size() > 0){
            onLoadFinish(LoadingView.LoadResult.SUCCEED);
        }

        if (mAdapter != null){
            mListView.setAdapter(mAdapter);
        }else{
            throw new RuntimeException("请设置数据适配器");
        }
    }

    @Override
    protected void initChildData() {

    }

    @Override
    protected void setChildViewListener() {
        mListView.setOnItemClickListener(this);

        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (!NetUtils.isNetWorkConnection()) {
                    UIUtils.showToast("请检查您的网络设置");
                    onRefreshCompleteError();
                    return;
                }
                mPullToRefreshListView.resetHasMore();
                BaseRefreshListViewActivity.this.load();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                onLoadMore(mDataList);
            }
        });
        mPullToRefreshListView.doPullRefreshing(true, 800);
    }

    protected abstract View getHeaderView();
    protected abstract void initChildDataFromCache();
    protected abstract BaseAdapter getAdapter();
    protected abstract void load();
    protected boolean hasMoreData(){
        return true;
    }

    protected abstract void onLoadMore(List<T> dataList);

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position = position - mListView.getHeaderViewsCount();// 此时的position是加上了header的
        onItemClickInner(mDataList, position);
    }

    public void onItemClickInner(List<T> dataList, int position) {

    }


    protected void onRefreshCompleteError(){
        if (mDataList.size() > 0){
            onLoadFinish(LoadingView.LoadResult.SUCCEED);
        } else {
            onLoadFinish(LoadingView.LoadResult.ERROR);
        }

        mPullToRefreshListView.onPullDownRefreshComplete();
    }

    protected void onRefreshCompleteEmpty(){
        onLoadFinish(LoadingView.LoadResult.EMPTY);
        mPullToRefreshListView.onPullDownRefreshComplete();
    }

    protected void onRefreshCompleteSuccess() {
        onLoadFinish(LoadingView.LoadResult.SUCCEED);
        mPullToRefreshListView.onPullDownRefreshComplete();
        mAdapter.notifyDataSetChanged();
    }

    protected void onLoadMoreComplete(){
        mPullToRefreshListView.onPullUpRefreshComplete();
        mAdapter.notifyDataSetChanged();
        mPullToRefreshListView.setHasMoreData(hasMoreData());
    }

    @Override
    protected void reloadData() {
        mPullToRefreshListView.doPullRefreshing(true, 0);
    }
}