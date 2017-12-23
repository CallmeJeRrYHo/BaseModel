package com.yunqinghui.watersupport.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.yunqinghui.watersupport.R;
import com.yunqinghui.watersupport.di.component.ActivityComponent;
import com.yunqinghui.watersupport.di.component.DaggerActivityComponent;
import com.yunqinghui.watersupport.di.module.ActivityModule;
import com.yunqinghui.watersupport.utils.ActivityTool;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by HJH
 * Data 2017/11/23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected int mScreenHeight;
    protected int mScreenWidth;
    private Toolbar mToolbar;
    protected boolean hasBind=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        if (isBindEventBus())
            EventBus.getDefault().register(this);
        mScreenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        mScreenWidth = this.getResources().getDisplayMetrics().widthPixels; //获取屏幕高度
        initComponent();
        attachView();
        setToolBar();
        initView();
        ActivityTool.addActivity(this);
    }

    /**
     * mvp present进行attachview
     */
    protected void attachView() {

    }

    /**
     * 是否绑定eventbus 默认否
     * @return
     */
    protected boolean isBindEventBus() {
        return hasBind;
    }

    /**
     * 设置自定义toolbar
     */
    private void setToolBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (hasToolbar()) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            //mToolbar.setNavigationIcon();
            if (mToolbar == null) {
                throw new IllegalStateException("子类需要有toolbar");
            }
            mToolbar.setNavigationIcon(getNavigationIcon());
            setSupportActionBar(mToolbar);

            if (canBack()) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null)
                    actionBar.setDisplayHomeAsUpEnabled(true);
            } else {

                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null)
                    actionBar.setDisplayHomeAsUpEnabled(false);
            }
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean canBack() {
        return true;
    }

    protected int getNavigationIcon() {
        return R.drawable.icon_return;
    }

    protected boolean hasToolbar(){
        return true;
    }

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(((BaseAPP) getApplication()).getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 开始注入component
     */
    protected abstract void initComponent();


    /**
     * 返回view id
     * @return
     */
    protected abstract int getContentViewId();


    protected abstract void initView();

    /**
     * 跳转到指定act
     * @param cls
     * @param finish
     */
    public void gotoActivity(Class cls, boolean finish){
        Intent i=new Intent(this,cls);
        this.startActivity(i);
        if (finish)
            finish();
    }


    public void gotoActivity(Class cls){
        gotoActivity(cls,false);
    }


    @Override
    protected void onDestroy() {
        if (isBindEventBus())
            EventBus.getDefault().unregister(this);
        super.onDestroy();

    }
}
