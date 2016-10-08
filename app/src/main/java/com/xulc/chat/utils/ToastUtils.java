package com.xulc.chat.utils;

import android.widget.Toast;

import com.xulc.chat.app.CWApplication;


/**
 * Created by xuliangchun on 2016/6/15.
 */
public class ToastUtils {
    private static ToastUtils toastUtils;
    private Toast toast;

    public static ToastUtils getInstance(){
        if (toastUtils==null){
            toastUtils = new ToastUtils();
        }
        return toastUtils;
    }

    public void showToast(CharSequence msg){
        if (toast==null){
            toast = Toast.makeText(CWApplication.getInstance().getApplicationContext(),msg,Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
        toast.show();
    }

    public void showToast(CharSequence msg,int duration){
        if (toast==null){
            toast = Toast.makeText(CWApplication.getInstance().getApplicationContext(),msg,Toast.LENGTH_SHORT);
        }else {
            toast.setDuration(duration);
            toast.setText(msg);
        }
        toast.show();
    }
}
