package com.ht.baselib.event;

/**
 * Created by huangtao on 16/6/24.
 */
public class EventCode {

    //网络状态变化
    public static final int EVENT_CODE_NET_STATE_CHANGE = 1000;



    /**************************下载APK*******************************/
    //监听apk下载进度变化
    public static final int EVENT_CODE_UPDATE_APK_PROGRESS = 2000;
    //下载完成
    public static final int EVENT_CODE_UPDATE_APK_END= 2001;
    //下载apk文件失败
    public static final int EVENT_CODE_UPDATE_APK_ERROR= 2002;



    /**************************IM*******************************/
    //开始录音
    public static final int EVENT_CODE_START_RECORDER_VOICE= 9000;
    //选择表情
    public static final int EVENT_CODE_SELECT_EXPRESS= 9100;
    //删除表情
    public static final int EVENT_CODE_DELETE_EXPRESS= 9101;
    //来消息
    public static final int EVENT_CODE_MSG_ARRIVED= 9900;

    //刷新聊天列表
    public static final int EVENT_CODE_REFRESH_CHAT_LIST = 9800;


}