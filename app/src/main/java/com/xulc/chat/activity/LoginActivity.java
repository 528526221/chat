package com.xulc.chat.activity;



import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.xulc.chat.R;
import com.xulc.chat.app.BaseActivity;
import com.xulc.chat.app.CWApplication;
import com.xulc.chat.bean.Login;
import com.xulc.chat.bean.User;
import com.xulc.chat.bean.UserLogin;
import com.xulc.chat.constans.ShareKey;
import com.xulc.chat.okhttp.HttpRequest;
import com.xulc.chat.okhttp.ResponseListener;
import com.xulc.chat.response.LoginResponse;
import com.xulc.chat.utils.MD5Util;
import com.xulc.chat.utils.PreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * A login screen that offers login via email/password.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements ResponseListener {
    @ViewInject(R.id.etName) EditText etName;
    @ViewInject(R.id.etPwd) EditText etPwd;
    @ViewInject(R.id.btnLogin) Button btnLogin;


    @Override
    public void underCreate() {
        setAppTitle("登录");
    }
    @Event(value = R.id.btnLogin)
    private void onClick(View view){
        if (TextUtils.isEmpty(etName.getText())){
            etName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText())){
            etPwd.requestFocus();
            return;
        }
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        PreferencesUtils.getInstance().putString(ShareKey.USER_NAME,name);
        PreferencesUtils.getInstance().putString(ShareKey.USER_PWD,pwd);
        showWaiting();
        HttpRequest.login(name, MD5Util.getMD5String(pwd),1,this,this);
    }

    @Override
    public void onSuccess(int code, String result) {
        LoginResponse response = JSON.parseObject(result,LoginResponse.class);
        User user = new User();
        Login login = response.getContent();
        user.setSessionID(login.getSessionID());
        UserLogin userLogin = login.getUserLogin();
        user.setAppType(userLogin.getAppType());
        user.setAppUserRole(userLogin.getAppUserRole());
        user.setFirstName(userLogin.getFirstName());
        user.setHeadPhotoUrl(userLogin.getHeadPhotoUrl());
        user.setPartyId(userLogin.getPartyId());
        user.setUserLoginId(userLogin.getUserLoginId());
        CWApplication.getInstance().setUser(user);
        cancelWaiting();
    }

    @Override
    public void onFailure(int code, String msg) {
        cancelWaiting();
    }
}

