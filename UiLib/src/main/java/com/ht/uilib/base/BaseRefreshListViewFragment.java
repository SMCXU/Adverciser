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

/**
 * Created by huangtao on 16/2/24.
 */
public abstract class BaseRefreshListViewFragment<T> extends BaseFragment implements AdapterView.OnItemClickListener{
    private ListView mListView;
    protected final List<T> mDataList = new ArrayList<>();
    private BaseAdapter mAdapter;
    private PullToRefreshListView mPullToRefreshListView;

    @Override
    protected View initContentView() {
        mPullToRefreshListView = new PullToRefreshListView(mContext);
        mPullToRefreshListView.setPullLoadEnabled(false);

        if (!isStaticPage()){
            mPullToRefreshListView.setScrollLoadEnabled(hasMoreData());
        } else {
            mPullToRefreshListView.setPullRefreshEnabled(false);
            mPullToRefreshListView.setScrollLoadEnabled(false);
        }

        return mPullToRefreshListView;
    }

    @Override
    protected void initChildView() {
        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setSelector(R.drawable.bg_list_item_select_transparent);
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
    protected void setChildViewListener() {
        mListView.setOnItemClickListener(this);

        if (!isStaticPage()){
            mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    if (!NetUtils.isNetWorkConnection()) {
                        if (mDataList.size() == 0) {
                            UIUtils.showToast("请检查您的网络设置");
                            onRefreshCompleteError();
                        } else {
                            onRefreshCompleteSuccess();
                        }
                        return;
                    }
                    mPullToRefreshListView.resetHasMore();
                    BaseRefreshListViewFragment.this.loadData();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    onLoadMore();
                }
            });
        }

    }

    @Override
    protected boolean isStaticPage() {
        return false;
    }
    protected abstract View getHeaderView();
    protected abstract void initChildDataFromCache();
    protected abstract BaseAdapter getAdapter();
    protected abstract void loadData();
    protected boolean hasMoreData(){
        return true;
    }
    protected abstract void onLoadMore();


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position = position - mListView.getHeaderViewsCount();// 此时的position是加上了header的
        onItemClickInner(position);
    }

    public void onItemClickInner(int position) {

    }

    protected void onRefreshCompleteError(){
        mPullToRefreshListView.onPullDownRefreshComplete();
        onLoadFinish(LoadingView.LoadResult.ERROR);
    }

    protected void onRefreshCompleteEmpty(){
        mPullToRefreshListView.onPullDownRefreshComplete();
        onLoadFinish(LoadingView.LoadResult.EMPTY);
    }

    protected void onRefreshCompleteSuccess() {
        mPullToRefreshListView.onPullDownRefreshComplete();
        onLoadFinish(LoadingView.LoadResult.SUCCEED);
        mAdapter.notifyDataSetChanged();
    }

    protected void onLoadMoreComplete(){
        mPullToRefreshListView.onPullUpRefreshComplete();
        mAdapter.notifyDataSetChanged();
        mPullToRefreshListView.setHasMoreData(hasMoreData());
    }

    @Override
    protected void load() {
//        mPullToRefreshListView.doPullRefreshing(true, 200);
        if (!isStaticPage()){
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!NetUtils.isNetWorkConnection()) {
                        UIUtils.showToast("请检查您的网络设置");
                        onRefreshCompleteError();
                        return;
                    }
                    mPullToRefreshListView.resetHasMore();
                    BaseRefreshListViewFragment.this.loadData();
                }
            }, 200);
        } else {
            BaseRefreshListViewFragment.this.loadData();
        }
    }

    @Override
    protected void reloadData() {
        mPullToRefreshListView.doPullRefreshing(true, 200);
    }
}