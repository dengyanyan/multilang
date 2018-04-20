package com.qwqer.ioc;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qwqer.lanioc.MultiLang;
import com.qwqer.lanioc.Lang;
import com.qwqer.lanioc.Language;
import com.qwqer.lanioc.app.LangApp;
import com.qwqer.lanioc.interf.LangCallback;
import com.qwqer.lanioc.interf.LangDelegate;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private Lang mLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLang = App.getApp().getLang();
        mLang.inject(this);

        mBtn = findViewById(R.id.btn);
        mBtn.setText(getResources().getString(R.string.test_lang));
    }

    @MultiLang(R.id.btn)
    public void multiLang(View view) {
        mLang.showDiaLog(new LangCallback() {
            @Override
            public void onFinish() {
                reStart();
            }
        });
    }

    public void reStart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
