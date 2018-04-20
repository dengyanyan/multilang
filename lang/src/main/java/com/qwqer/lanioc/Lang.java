package com.qwqer.lanioc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qwqer.lanioc.interf.LangCallback;
import com.qwqer.lanioc.util.LocalManageUtil;
import com.qwqer.lanioc.util.SPUtil;

import java.lang.reflect.Method;

/**
 * <pre>
 *      author  : David Daniel
 *      time    : 2018/04/19
 *      desc    :
 *      version : 1.0.0
 * </pre>
 */

public class Lang {

    private static final String TAG = "Lang";

    private static Lang instance = null;
    private final Context mContext;
    private Activity mActivity;
    private TextView mTvSave;
    private RadioGroup mLang_radio;
    private AlertDialog mAlertDialog;

    private Lang(Context context) {
        mContext = context;
    }

    public static Lang getInstance(Context context) {
        if (instance == null) {
            synchronized (Lang.class) {
                if (instance == null) {
                    instance = new Lang(context);
                }
            }
        }
        return instance;
    }


    public void inject(Activity activity) {
        mActivity = activity;
        injectEvent(mActivity, mActivity);
    }


    public void injectEvent(Activity activity, Object obj) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            MultiLang checkLan = method.getAnnotation(MultiLang.class);
            if (checkLan != null) {
                int value = checkLan.value();//控件id
                View viewById = mActivity.findViewById(value);
                if (viewById != null) {
                    viewById.setOnClickListener(new MultiLangClickListener(method, mActivity));
                }
            }
        }
    }

    public void showDiaLog(final LangCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_multi_lang, null);

        mLang_radio = view.findViewById(R.id.language_radio);
        mTvSave = view.findViewById(R.id.tv_save_lang);
        RadioButton rbtnChinese = view.findViewById(R.id.chinese);
        RadioButton rbtnEnglish = view.findViewById(R.id.english);
        RadioButton rbtnRussian = view.findViewById(R.id.russian);

        int language = SPUtil.getInstance(mContext).getSelectLanguage();
        if (language == Language.CHINESE) {
            rbtnChinese.setChecked(true);
        } else if (language == Language.ENGLISH) {
            rbtnEnglish.setChecked(true);
        } else {
            rbtnRussian.setChecked(true);
        }
        builder.setView(view);
        mAlertDialog = builder.create();
        mAlertDialog.show();

        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioButtonId = mLang_radio.getCheckedRadioButtonId();
                int lang = getLangId(checkedRadioButtonId);
                LocalManageUtil.saveSelectLanguage(mActivity, lang);
                mAlertDialog.dismiss();
                callback.onFinish();
            }
        });

    }

    private int getLangId(int checkedRadioButtonId) {
        if (checkedRadioButtonId == R.id.chinese) {
            return Language.CHINESE;
        } else if (checkedRadioButtonId == R.id.english) {
            return Language.ENGLISH;
        } else if (checkedRadioButtonId == R.id.russian) {
            return Language.RUSSIAN;
        }
        return Language.CHINESE;
    }

    private class MultiLangClickListener implements View.OnClickListener {

        private Method mMethod;
        private Object mObj;

        public MultiLangClickListener(Method method, Object obj) {
            mMethod = method;
            mObj = obj;
        }

        @Override
        public void onClick(View v) {
            try {
                mMethod.setAccessible(true);
                mMethod.invoke(mObj, v);
            } catch (Exception e) {
                try {
                    mMethod.invoke(mObj, new Object[]{});
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

}
