package com.yunqinghui.watersupport.model.bean;

/**
 * Created by HJH
 * Data 2017/11/15.
 */

public class Result<T> {
    private String code;
    private String message;
    private T result;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return "0".equals(code);
    }
}
