package com.ht.netlib.progress;

import android.os.SystemClock;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * **************************************************************
 * [好刷刷]
 * **************************************************************
 * Authors: 黄涛 on 17/3/6 16:14
 * QQ：876046815
 * Email:876046815@qq.com
 */
public class ProgressResponseBody extends ResponseBody {

    private final ResponseBody responseBody;
    private final OnProgressListener mOnProgressListener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody,
                                OnProgressListener onProgressListener) {
        this.responseBody = responseBody;
        this.mOnProgressListener = onProgressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (null != mOnProgressListener) {
                    SystemClock.sleep(80);
                    mOnProgressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                }
                return bytesRead;
            }
        };
    }

    public interface OnProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }
}
