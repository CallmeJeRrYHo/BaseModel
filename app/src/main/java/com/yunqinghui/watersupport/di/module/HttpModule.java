package com.yunqinghui.watersupport.di.module;

import com.yunqinghui.watersupport.base.Url;
import com.yunqinghui.watersupport.model.http.api.Api;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HJH
 * Data 2017/11/7.
 */
@Module
public class HttpModule {
    @Singleton
    @Provides
    public OkHttpClient providerOkhttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (com.yunqinghui.watersupport.BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit providerRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public Api providerGankApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }
}
