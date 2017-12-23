package com.yunqinghui.watersupport.di.component;


import android.content.Context;

import com.yunqinghui.watersupport.di.module.AppModule;
import com.yunqinghui.watersupport.di.module.HttpModule;
import com.yunqinghui.watersupport.model.http.api.RetrofitHelper;
import com.yunqinghui.watersupport.model.http.api.SPHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HJH
 * Data 2017/11/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    RetrofitHelper retrofitHelper();  //提供http的帮助类
    SPHelper spHelper();
    Context context();
}
