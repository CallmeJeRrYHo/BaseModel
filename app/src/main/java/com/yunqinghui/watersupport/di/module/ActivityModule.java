package com.yunqinghui.watersupport.di.module;

import android.app.Activity;

import com.yunqinghui.watersupport.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HJH
 * Data 2017/11/7.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity providerActivity(){
        return mActivity;
    }

}
