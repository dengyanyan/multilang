package com.qwqer.lanioc.interf;

import android.content.Context;
import android.content.res.Configuration;

/**
 * <pre>
 *      author  : David Daniel
 *      time    : 2018/04/19
 *      desc    :  需要在application类中监听配置改变，避免代码侵入，代理模式
 *      version : 1.0.0
 * </pre>
 */

public interface LangDelegate {
    void attachBaseContext(Context base);

    void onCreate();

    void onConfigurationChanged(Configuration newConfig);
}
