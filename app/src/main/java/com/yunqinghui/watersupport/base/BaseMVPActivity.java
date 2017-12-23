package com.yunqinghui.watersupport.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yunqinghui.watersupport.R;
import com.yunqinghui.watersupport.utils.EmptyUtils;
import com.yunqinghui.watersupport.utils.ToastUtils;

import javax.inject.Inject;

/**
 * base
 * Created by HJH on 2017/6/28.
 */

public abstract class BaseMVPActivity<P extends BasePresenter,V extends BaseView> extends BaseActivity implements BaseView{
    @Inject
    protected P mPresenter;
    private AlertDialog mProgressDialog;
    protected boolean cancelable=false;
    protected BaseQuickAdapter mBaseQuickAdapter;
    @Override
    protected void attachView() {
        if (mPresenter!=null)
            mPresenter.attachView((V)this);
    }

    /**
     * 是否绑定eventbus 默认否
     * @return
     */
    protected boolean isBindEventBus() {
        return hasBind;
    }


    @Override
    protected void onDestroy() {
        if (mPresenter!=null) {
            mPresenter.detachView();
        }
        super.onDestroy();

    }

    @Override
    public void getListFail() {
        if (EmptyUtils.isEmpty(mBaseQuickAdapter))
            return;
        mBaseQuickAdapter.loadMoreFail();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showLong(msg);
    }

    @Override
    public void showLoading() {
        if (EmptyUtils.isEmpty(mProgressDialog)) {
            mProgressDialog = new AlertDialog.Builder(this)
                    .setView(R.layout.dialog_loading)
                    .create();
            Window window = mProgressDialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mProgressDialog.setCancelable(cancelable);
        }
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }


    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

    }


}
