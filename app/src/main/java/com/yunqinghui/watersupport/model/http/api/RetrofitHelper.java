package com.yunqinghui.watersupport.model.http.api;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by HJH
 * Data 2017/11/7.
 */

public class RetrofitHelper implements HttpHelper {
    private Api mApi;
    private Retrofit mRetrofit;
    @Inject
    public RetrofitHelper(Api api, Retrofit retrofit) {
        mApi = api;
        mRetrofit=retrofit;
    }
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public Api getApi() {
        return mApi;
    }
}
