package com.ht.uilib.bean;


public class OperatorBean {
    public int iconRes;
    public boolean isCenter;
    public String title;
    public String subtitle;
    public String detail;


    public int rightResId;
    public boolean isHideRightRes;

    public boolean isStart;

    public boolean isCheckbox;
    public boolean checkBoxDefaultValue;

    public OnItemClickListener onItemClickListener;

    /*           图片   文字    >                    */
    public OperatorBean(int iconRes, String title, OnItemClickListener onItemClickListener) {
        this(false, iconRes, title, onItemClickListener);
    }

    public OperatorBean(boolean isStart, int iconRes, String title, OnItemClickListener onItemClickListener) {
        this.isStart = isStart;
        this.iconRes = iconRes;
        this.title = title;
        this.onItemClickListener = onItemClickListener;
    }

    /*          文字  >                 */
    public OperatorBean(String title, OnItemClickListener onItemClickListener) {
        this(false, title, onItemClickListener);
    }

    public OperatorBean(boolean isStart, String title, OnItemClickListener onItemClickListener) {
        this.isStart = isStart;
        this.title = title;
        this.onItemClickListener = onItemClickListener;
    }

    /*          文字  checkbox                */
    public OperatorBean(String title, boolean isCheckbox, boolean defaultValue, OnItemClickListener onItemClickListener) {
        this(false, title, isCheckbox, defaultValue, onItemClickListener);
    }

    public OperatorBean(boolean isStart, String title, boolean isCheckbox, boolean defaultValue, OnItemClickListener onItemClickListener) {
        this.isStart = isStart;
        this.title = title;
        this.isCheckbox = isCheckbox;
        this.checkBoxDefaultValue = defaultValue;
        this.onItemClickListener = onItemClickListener;
        this.isHideRightRes = true;
    }

    /*          文字  文字                */
    public OperatorBean(String title, String detail, OnItemClickListener onItemClickListener) {
        this.title = title;
        this.detail = detail;
        this.onItemClickListener = onItemClickListener;
    }

    public OperatorBean(boolean isStart, String title, String detail, OnItemClickListener onItemClickListener) {
        this.isStart = isStart;
        this.title = title;
        this.detail = detail;
        this.onItemClickListener = onItemClickListener;
        this.isHideRightRes = true;
    }

    /*          文字  文字                */
    public OperatorBean(String title, String detail, boolean isHideRightRes, OnItemClickListener onItemClickListener) {
        this(false, title, detail, isHideRightRes, onItemClickListener);
    }

    public OperatorBean(boolean isStart, String title, String detail, boolean isHideRightRes, OnItemClickListener onItemClickListener) {
        this.isStart = isStart;
        this.title = title;
        this.detail = detail;
        this.onItemClickListener = onItemClickListener;
        this.isHideRightRes = isHideRightRes;
    }

    public OperatorBean(String title, boolean isCenter, OnItemClickListener onItemClickListener) {
        this(false, title, isCenter, onItemClickListener);
    }

    public OperatorBean(boolean isStart, String title, boolean isCenter, OnItemClickListener onItemClickListener) {
        this.isStart = isStart;
        this.isCenter = isCenter;
        this.title = title;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(boolean isChecked);
    }

}