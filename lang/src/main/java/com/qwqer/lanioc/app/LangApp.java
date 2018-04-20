package com.qwqer.lanioc.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.qwqer.lanioc.Lang;
import com.qwqer.lanioc.interf.LangDelegate;
import com.qwqer.lanioc.util.LocalManageUtil;

/**
 * <pre>
 *      author  : David Daniel
 *      time    : 2018/04/19
 *      desc    :
 *      version : 1.0.0
 * </pre>
 */

public class LangApp implements LangDelegate {

    private Context mContext;
    public Lang mLang;

    @Override
    public void attachBaseContext(Context base) {
        mContext = base;
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        mLang = Lang.getInstance(base);
    }

    @Override
    public void onCreate() {
        LocalManageUtil.setApplicationLanguage(mContext);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(mContext);
    }
}
