package com.ht.uilib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

import com.ht.baselib.utils.DeviceUtils;
import com.ht.uilib.R;


public class TipsDialog extends Dialog implements OnClickListener {

	private TextView mOkButton;
	private TextView mCancelButton;

	private OnDialogTipsClickListener mListener;
    private String name;
    private Uri uri;
    private String title;
    private String content;
    private TextView mTitleView;
    private TextView mContentView;
    private String leftBtnText;
    private String rightBtnText;

    public TipsDialog(Context context) {
		super(context, R.style.albumDialog);
	}

    public static TipsDialog newInstance(Context context) {
        TipsDialog tipsDialog = new TipsDialog(context);
        return tipsDialog;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_ios_tip);
		WindowManager.LayoutParams lp = this.getWindow().getAttributes();
		lp.alpha = 1.0f;
		this.getWindow().setWindowAnimations(R.style.Dialog_Fade);
		this.getWindow().setAttributes(lp);
		this.getWindow().setLayout(DeviceUtils.getScreenWidth(), DeviceUtils.getScreenHeight());


        mTitleView = (TextView) findViewById(R.id.tv_dialog_tip_title);
        mContentView = (TextView) findViewById(R.id.tv_dialog_tip_content);

        if (!TextUtils.isEmpty(title)){
            mTitleView.setText(title);
        }

        if (!TextUtils.isEmpty(content)){
            mContentView.setText(content);
        }

        mOkButton = (TextView) findViewById(R.id.tv_dialog_tip_sure);
		mCancelButton = (TextView) findViewById(R.id.tv_dialog_tip_cancle);

        if (!TextUtils.isEmpty(leftBtnText)){
            mOkButton.setText(leftBtnText);
        }

        if (!TextUtils.isEmpty(rightBtnText)){
            mCancelButton.setText(rightBtnText);
        }

		mCancelButton = (TextView) findViewById(R.id.tv_dialog_tip_cancle);
		//mBtnLineView = findViewById(R.id.v_btn_line);

		mOkButton.setOnClickListener(this);
		mCancelButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
        if (v.getId() == R.id.tv_dialog_tip_sure){
            dismiss();
            if (mListener != null){
                mListener.doEnter();
            }
        } else if (v.getId() == R.id.tv_dialog_tip_cancle){
            dismiss();
            if (mListener != null){
                mListener.doCancel();
            }
        }
	}

    public TipsDialog setName(String name){
        this.name = name;
        return this;
    }

    public TipsDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public TipsDialog setContent(String content){
        this.content = content;
        return this;
    }

    public TipsDialog setLeftBtnText(String leftBtnText) {
        this.leftBtnText = leftBtnText;
        return this;
    }

    public TipsDialog setRightBtnText(String rightBtnText) {
        this.rightBtnText = rightBtnText;
        return this;
    }

    public TipsDialog setOnDialogTipsClickListener(OnDialogTipsClickListener listener) {
		mListener = listener;
        return this;
	}

	public interface OnDialogTipsClickListener {
		void doEnter();

		void doCancel();
	}

}
