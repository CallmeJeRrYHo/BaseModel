package com.yunqinghui.watersupport.activity.presenter;

import com.yunqinghui.watersupport.activity.contract.MainContract;
import com.yunqinghui.watersupport.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by HJH
 * Data 2017/12/23.
 */

public class MainPresenter extends BasePresenter<MainContract.MainView> implements MainContract.Presenter {
    @Inject
    public MainPresenter() {
    }
}
