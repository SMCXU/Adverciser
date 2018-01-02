package com.ht.pushlib.xiaomi;

import android.content.Context;

import com.ht.baselib.utils.LogUtils;
import com.ht.baselib.utils.SharedPreferencesUtils;
import com.ht.pushlib.PushManager;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/27 15:59
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class XiaomiPushReceiver extends PushMessageReceiver{

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {

    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {

    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {

    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {

    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            List<String> arguments = message.getCommandArguments();
            String regId = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                SharedPreferencesUtils.put(PushManager.REGISTER_ID, regId);
            } else {
                LogUtils.error("MiPush register fail");
            }
        }
    }

}