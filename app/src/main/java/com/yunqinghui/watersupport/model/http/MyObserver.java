package com.yunqinghui.watersupport.model.http;

import com.yunqinghui.watersupport.base.BasePresenter;
import com.yunqinghui.watersupport.base.BaseView;
import com.yunqinghui.watersupport.utils.LogUtils;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by HJH
 * Data 2017/11/15.
 */

public abstract class MyObserver<T> implements Observer<T> {
    protected final BaseView mBaseView;
    protected final BasePresenter mBasePresenter;
    protected boolean isShowLoading=true;
    public MyObserver( BasePresenter basePresenter) {
        this(basePresenter,true);
    }

    public MyObserver( BasePresenter basePresenter,boolean isShowLoading) {
        mBasePresenter = basePresenter;
        mBaseView = basePresenter.getView();
        this.isShowLoading=isShowLoading;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        LogUtils.d(Thread.currentThread().toString());
        mBasePresenter.addSubscribe(d);
        if (mBaseView!=null&&isShowLoading)
            mBaseView.showLoading();
    }


    @Override
    public void onError(@NonNull Throwable e) {
        if (mBaseView==null)
            return;
        if (mBaseView!=null)
            mBaseView.hideLoading();
        mBaseView.getListFail();
        LogUtils.e(e.getMessage()+" "+e.getClass().getName());
        if (e instanceof MyHttpException)
            mBaseView.showMessage(((MyHttpException) e).getMsg());
        else if (e instanceof ConnectException)
            mBaseView.showMessage("网络开小差，请稍后重试！");
        else if (e instanceof HttpException)
            mBaseView.showMessage("服务器异常:"+e.getMessage());
        else if (e instanceof ConnectException)
            mBaseView.showMessage("远程服务器异常！");
        else
            mBaseView.showMessage("网络开小差，请稍后重试!!!");
    }

    @Override
    public void onComplete() {
        LogUtils.d(Thread.currentThread().toString());
        if (mBaseView!=null)
            mBaseView.hideLoading();
    }
}
