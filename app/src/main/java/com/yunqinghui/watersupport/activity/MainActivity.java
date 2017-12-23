package com.yunqinghui.watersupport.activity;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.yunqinghui.watersupport.R;
import com.yunqinghui.watersupport.activity.contract.MainContract;
import com.yunqinghui.watersupport.activity.presenter.MainPresenter;
import com.yunqinghui.watersupport.base.BaseMVPActivity;

import butterknife.BindView;

public class MainActivity extends BaseMVPActivity<MainPresenter, MainContract.MainView> {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTv.setText("test");
        mTvTitle.setText("test");
    }



}
