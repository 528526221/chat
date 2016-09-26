package com.xulc.chat;


import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity{
    @ViewInject(R.id.etName) EditText etName;
    @ViewInject(R.id.etPwd) EditText etPwd;
    @ViewInject(R.id.btnLogin) Button btnLogin;
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void underCreate() {
        setAppTitle("登录");
    }

    @Event(value = R.id.btnLogin)
    private void onClick(View v){
        if (TextUtils.isEmpty(etName.getText())){
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText())){
            return;
        }


    }

}

