package com.ht.uilib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ht.baselib.utils.UIUtils;
import com.ht.uilib.R;


public class LoadingDialog extends Dialog {
	private TextView mMsgText;

	public LoadingDialog(Context context) {
		super(context, R.style.LoadingDialog);
	}

    public static LoadingDialog newInstance(Context context, boolean cancelable) {
        LoadingDialog dialog = new LoadingDialog(context);
        dialog.setCancelable(cancelable);
        return dialog;
    }

    public static LoadingDialog newInstance(Context context, CharSequence message, boolean cancelable) {
        LoadingDialog dialog = new LoadingDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(cancelable);
        return dialog;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        View contentView = UIUtils.inflate(R.layout.dialog_loading);
		setContentView(contentView);
        mMsgText = (TextView) contentView.findViewById(R.id.tv_dialog_msg);
	}

    public LoadingDialog setMessage(CharSequence message) {
        mMsgText.setText(message);
        return this;
    }

}
