package com.ht.netlib.utils;

import com.ht.baselib.application.HTApplication;
import com.ht.baselib.utils.LogUtils;

import java.util.Set;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class NetLogUtils extends LogUtils{
	private static final String TAG = "NetLib";

    public static void error(Response response) {
        if (!HTApplication.IS_DEBUG_MODE) return;
        Request request = response.request();
        String method = request.method();

        String requestMessage = "--> " + method + ' ' + request.url().toString() + ' ' + response.code();

        //header
        Headers headers = request.headers();
        if (headers != null && headers.names().size() > 0){
            requestMessage+="\nheaders:";
            Set<String> nameSet = headers.names();
            for (String headerName : nameSet) {
                requestMessage+="\n　　　"+headerName+"="+headers.get(headerName);
            }
        }



        //parameter
        RequestBody requestBody = request.body();
        if (requestBody != null){
            requestMessage+="\nparameters";
            if (requestBody instanceof  FormBody){
                FormBody body = (FormBody) requestBody;
                body.contentType();
                for (int i = 0; i < body.size(); i++) {
                    String paramName = body.name(i);
                    String paramValue = body.value(i);
                    requestMessage += "\n　　　"+paramName+"="+paramValue;
                }
            } else {
                requestMessage+="\n　　　"+requestBody.contentType().toString();
            }
        }

        error(TAG, requestMessage);
    }
}
