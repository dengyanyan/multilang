package com.qwqer.ioc;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.qwqer.lanioc.Lang;
import com.qwqer.lanioc.app.LangApp;
import com.qwqer.lanioc.interf.LangDelegate;

/**
 * <pre>
 *      author  : David Daniel
 *      time    : 2018/04/19
 *      desc    : 代理模式注入application
 *      version : 1.0.0
 * </pre>
 */

public class App extends Application {

    private LangApp mDelegate;
    private static App sApp;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sApp = this;
        mDelegate = new LangApp();
        mDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDelegate.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDelegate.onConfigurationChanged(newConfig);
    }

    public static App getApp() {
        return sApp;
    }

    public Lang getLang() {
        return mDelegate.mLang;
    }
}
