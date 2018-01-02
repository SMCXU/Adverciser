package com.ht.uilib.holder;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ht.baselib.application.HTApplication;
import com.ht.uilib.R;
import com.ht.uilib.base.BaseViewHolder;
import com.ht.uilib.bean.OperatorBean;

import java.util.List;

/**
 * Created by huangtao on 16/9/18.
 */
public class OperatorViewHolder extends BaseViewHolder<OperatorBean> {
    private List<OperatorBean> mDataList;
    private int position;

    private View view_list_operater_start;
    private ImageView iv_list_operater_icon;
    private TextView tv_list_operater_title;
    private TextView tv_list_operater_desc;
    private ImageView iv_list_operater_arrow;
    private CheckBox cb_list_operater_switch;
    private View ll_list_operator_item_split_line;
    private View view_list_operater_end;

    public OperatorViewHolder(int position, List<OperatorBean> dataList) {
        super(R.layout.list_operator_item);
        this.position = position;
        this.mDataList = dataList;
    }

    @Override
    protected void initView() {
        view_list_operater_start = findViewById(R.id.view_list_operater_start);
        iv_list_operater_icon = findViewById(R.id.iv_list_operater_icon);
        tv_list_operater_title = findViewById(R.id.tv_list_operater_title);
        tv_list_operater_desc = findViewById(R.id.tv_list_operater_desc);
        cb_list_operater_switch = findViewById(R.id.cb_list_operater_switch);
        iv_list_operater_arrow = findViewById(R.id.iv_list_operater_arrow);
        ll_list_operator_item_split_line = findViewById(R.id.ll_list_operator_item_split_line);
        view_list_operater_end = findViewById(R.id.view_list_operater_end);
    }

    @Override
    protected void refreshView(final OperatorBean operatorBean) {
        if (operatorBean.isStart){
            view_list_operater_start.setVisibility(View.VISIBLE);
        } else {
            view_list_operater_start.setVisibility(View.GONE);
        }

        if (operatorBean.iconRes != 0){
            iv_list_operater_icon.setVisibility(View.VISIBLE);
            iv_list_operater_icon.setImageResource(operatorBean.iconRes);
        } else {
            iv_list_operater_icon.setVisibility(View.GONE);
        }

        if (operatorBean.isCenter){
            tv_list_operater_title.setTextColor(HTApplication.getContext().getResources().getColor(R.color.black78));
            tv_list_operater_title.setGravity(Gravity.CENTER);
            operatorBean.isHideRightRes = true;
        }else{
            tv_list_operater_title.setTextColor(HTApplication.getContext().getResources().getColor(R.color.black));
            tv_list_operater_title.setGravity(Gravity.LEFT);
        }

        if (!TextUtils.isEmpty(operatorBean.title)){
            tv_list_operater_title.setVisibility(View.VISIBLE);
            tv_list_operater_title.setText(operatorBean.title);
        }else {
            tv_list_operater_title.setVisibility(View.GONE);
        }

        int nextPosition = position+1;
        if (nextPosition < mDataList.size()){
            if (mDataList.get(nextPosition).isStart){
                ll_list_operator_item_split_line.setVisibility(View.GONE);
            } else {
                ll_list_operator_item_split_line.setVisibility(View.VISIBLE);
            }
        }else {
            ll_list_operator_item_split_line.setVisibility(View.GONE);
        }

        if (operatorBean.rightResId != 0){
            iv_list_operater_arrow.setVisibility(View.VISIBLE);
            iv_list_operater_arrow.setImageResource(operatorBean.rightResId);
        } else {
            if (operatorBean.isHideRightRes){
                iv_list_operater_arrow.setVisibility(View.GONE);
            } else {
                iv_list_operater_arrow.setVisibility(View.VISIBLE);
            }
        }

        if (operatorBean.detail != null){
            tv_list_operater_desc.setVisibility(View.VISIBLE);
            tv_list_operater_desc.setText(operatorBean.detail);
        } else {
            tv_list_operater_desc.setVisibility(View.GONE);
        }

        if (operatorBean.isCheckbox){
            cb_list_operater_switch.setChecked(operatorBean.checkBoxDefaultValue);
            cb_list_operater_switch.setVisibility(View.VISIBLE);
            cb_list_operater_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (operatorBean.onItemClickListener != null) {
                        operatorBean.onItemClickListener.onClick(isChecked);
                    }
                }
            });
        } else {
            cb_list_operater_switch.setVisibility(View.GONE);
        }

        if (position == mDataList.size() - 1){
            view_list_operater_end.setVisibility(View.VISIBLE);
        } else {
            view_list_operater_end.setVisibility(View.GONE);
        }
    }
}
