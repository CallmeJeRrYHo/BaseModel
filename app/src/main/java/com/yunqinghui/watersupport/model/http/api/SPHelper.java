package com.yunqinghui.watersupport.model.http.api;
import com.yunqinghui.watersupport.utils.SPUtils;

import javax.inject.Inject;

/**
 * Created by HJH
 * Data 2017/11/16.
 */

public class SPHelper {
    private static final String KEY_TOKEN = "TOKEN";
    private final SPUtils mSpUtils;
    private static final String KEY_USER = "USER";
    private static final String KEY_LAT = "LAT";
    private static final String KEY_LON = "LON";
    private static final String KEY_CITY = "CITY";
    private static final String KEY_USER_ID = "USER_ID";
    private static final String KEY_ACCOUNT = "ACCOUNT";
    private static final String KEY_PWD = "PWD";
    @Inject
    public SPHelper(SPUtils spUtils) {
        mSpUtils = spUtils;
    }

}
