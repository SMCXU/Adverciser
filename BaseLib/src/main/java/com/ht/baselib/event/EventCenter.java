package com.ht.baselib.event;

/**
 * Created by huangtao on 16/6/24.
 */
public class EventCenter <T> {

    public T data;
    public int eventCode;

    public EventCenter(int eventCode) {
        this.eventCode = eventCode;
    }

    public EventCenter(int eventCode, T data) {
        this.data = data;
        this.eventCode = eventCode;
    }
}
