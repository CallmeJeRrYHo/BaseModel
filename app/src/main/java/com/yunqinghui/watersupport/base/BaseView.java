package com.yunqinghui.watersupport.base;

/**
 * Created by HJH on 2017/7/8.
 */

public interface BaseView {
    void showMessage(String msg);
    void showLoading();
    void hideLoading();
    void showRefresh();
    void hideRefresh();

    /**
     * 获取列表数据失败！adapter调用loadmoreFail
     */
    void getListFail();
}
