package com.yunqinghui.watersupport.di.component;

import android.app.Activity;

import com.yunqinghui.watersupport.activity.MainActivity;
import com.yunqinghui.watersupport.di.module.ActivityModule;
import com.yunqinghui.watersupport.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by HJH
 * Data 2017/11/7.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivty();
    void inject(MainActivity mainActivity);
}

