package com.yunqinghui.watersupport.di.module;

import android.content.Context;

import com.yunqinghui.watersupport.base.BaseAPP;
import com.yunqinghui.watersupport.model.http.api.HttpHelper;
import com.yunqinghui.watersupport.model.http.api.RetrofitHelper;
import com.yunqinghui.watersupport.utils.SPUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HJH
 * Data 2017/11/7.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(BaseAPP application) {
        this.context = application;
    }

    @Singleton
    @Provides
    public Context ProviderApplicationContext(){
        return context;
    }

    @Singleton
    @Provides
    public HttpHelper providerRetrofitHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }

    @Singleton
    @Provides
    public SPUtils providerSPUtil(){
        return SPUtils.getInstance("66sp");
    }
}
