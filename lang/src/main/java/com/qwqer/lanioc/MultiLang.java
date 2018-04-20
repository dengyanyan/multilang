package com.qwqer.lanioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *      author  : David Daniel
 *      time    : 2018/04/19
 *      desc    : 点击后弹出多语言选择，并切换语言,内置了一个dialog弹出框
 *      version : 1.0.0
 * </pre>
 * <p>
 * {@linkplain @MultiLang(R.id.btn)}
 * <br>public void multiLang(View view) {
 * <br>mLang.showDiaLog(new LangCallback() {
 * <br>{@linkplain @Override}
 * <br> public void onFinish() {
 * <br>reStart();
 * <br>}
 * <br>});
 * <br>}
 * <br>
 * <br>public void reStart() {
 * <br> Intent intent = new Intent(this, MainActivity.class);
 * <br>startActivity(intent);
 * <br>}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiLang {
    int value();
}
