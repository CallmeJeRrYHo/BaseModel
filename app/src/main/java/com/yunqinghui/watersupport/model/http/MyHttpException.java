package com.yunqinghui.watersupport.model.http;

/**
 * Created by HJH
 * Data 2017/11/16.
 */

public class MyHttpException extends Exception {
    private String msg;

    public MyHttpException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
