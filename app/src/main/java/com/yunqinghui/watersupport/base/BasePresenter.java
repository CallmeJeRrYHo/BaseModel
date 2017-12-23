package com.yunqinghui.watersupport.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by HJH on 2017/7/8.
 */

public class BasePresenter<V extends BaseView> implements Presenter<V> {
    protected V mView;
    protected CompositeDisposable mCompositeDisposable;
    @Override
    public void attachView(V view){
        mView=view;
    }
    @Override
    public void detachView(){
        mView=null;
        //有用rx的话
        unSubscribe();
    }
    @Override
    public void addSubscribe(Disposable disposable){
        if (mCompositeDisposable==null)
            mCompositeDisposable=new CompositeDisposable();
        mCompositeDisposable.add(disposable);
    }
    @Override
    public void unSubscribe(){
        if (mCompositeDisposable!=null)
            mCompositeDisposable.clear();
    }

    /**
     * 是否与v层连接
     * @return
     */
    public boolean isAttach(){
        return mView!=null;
    }

    /**
     * 获取v层，进行ui的设置
     * @return
     */
    public V getView(){
        if (mView!=null)
            return mView;
        else
            throw new UnsupportedOperationException("mView为空！！");
    }
}
