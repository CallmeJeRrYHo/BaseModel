package com.yunqinghui.watersupport.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import com.yunqinghui.watersupport.di.component.AppComponent;
import com.yunqinghui.watersupport.di.component.DaggerAppComponent;
import com.yunqinghui.watersupport.di.module.AppModule;
import com.yunqinghui.watersupport.di.module.HttpModule;
import com.yunqinghui.watersupport.utils.Utils;

import java.util.Iterator;
import java.util.List;


/**
 * Created by HJH on 2017/6/28.
 */

public class BaseAPP extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupComponent();
        Utils.init(this);
    }




    private void setupComponent() {
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }

    //获取AppComponent 以便于SubComponent继承
    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            this.setupComponent();
        }
        return mAppComponent;
    }


    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}
