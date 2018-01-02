package com.ht.uilib.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;
import com.ht.uilib.adapter.OperatorAdapter;
import com.ht.uilib.bean.OperatorBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by huangtao on 16/2/16.
 */
public abstract class BaseOperatorFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    protected ListView mListView;
    protected List<OperatorBean> mDataList;
    protected OperatorAdapter mOperatorAdapter;

    @Override
    protected boolean isStaticPage() {
        return true;
    }

    @Override
    protected View initContentView() {
        return UIUtils.inflate(R.layout.view_list_view);
    }

    @Override
    protected void initChildView() {
        mListView = findViewById(R.id.lv_view_list);

        View headerView = getHeaderView();
        if (headerView != null){
            mListView.addHeaderView(headerView);
        }

    }

    protected abstract View getHeaderView();

    @Override
    protected void setChildViewListener() {
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void load() {
        mDataList = new ArrayList<>();
        loadData();
        mOperatorAdapter = new OperatorAdapter(mContext, mDataList);
        mListView.setAdapter(mOperatorAdapter);
    }

    protected abstract void loadData();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position = position - mListView.getHeaderViewsCount();// 此时的position是加上了header的
        OperatorBean operator = mDataList.get(position);
        if (operator.onItemClickListener != null) {
            operator.onItemClickListener.onClick(false);
        }
    }

    protected void add(int iconRes, String title, OperatorBean.OnItemClickListener onItemClickListener) {
        add(false, iconRes, title, onItemClickListener);
    }

    protected void add(boolean isStart, int iconRes, String title, OperatorBean.OnItemClickListener onItemClickListener) {
        mDataList.add(new OperatorBean(isStart, iconRes, title, onItemClickListener));
    }

    protected void add(String title, OperatorBean.OnItemClickListener onItemClickListener) {
        add(false, title, onItemClickListener);
    }

    protected void add(boolean isStart, String title, OperatorBean.OnItemClickListener onItemClickListener) {
        mDataList.add(new OperatorBean(isStart, title, onItemClickListener));
    }

    protected void add(String title, boolean isCheckbox, boolean defaultValue, OperatorBean.OnItemClickListener onItemClickListener) {
        add(false, title, isCheckbox, defaultValue, onItemClickListener);
    }

    protected void add(boolean isStart, String title, boolean isCheckbox, boolean defaultValue, OperatorBean.OnItemClickListener onItemClickListener) {
        mDataList.add(new OperatorBean(isStart, title, isCheckbox, defaultValue, onItemClickListener));
    }

    protected void add(String title, String detail, OperatorBean.OnItemClickListener onItemClickListener) {
        add(false, title, detail, onItemClickListener);
    }

    protected void add(boolean isStart, String title, String detail, OperatorBean.OnItemClickListener onItemClickListener) {
        mDataList.add(new OperatorBean(isStart, title, detail, onItemClickListener));
    }

    protected void add(String title, boolean isCenter, OperatorBean.OnItemClickListener onItemClickListener) {
        add(false, title, isCenter, onItemClickListener);
    }

    protected void add(boolean isStart, String title, boolean isCenter, OperatorBean.OnItemClickListener onItemClickListener) {
        mDataList.add(new OperatorBean(isStart, title, isCenter, onItemClickListener));
    }
}