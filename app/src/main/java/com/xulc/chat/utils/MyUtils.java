package com.xulc.chat.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

/**
 * Created by acer on 2016/6/11.
 */
public class MyUtils {
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity activity,float bgAlpha)
    {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
    /**
     *  此方法只是关闭软键盘
     */

    public static void hintKbTwo(Activity activity) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&activity.getCurrentFocus()!=null){
            if (activity.getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    /**
     * 密码正则
     * 6-12位
     * 英文和数字组合
     * 不得全为数字或字母
     * @param pwd
     * @return
     */
    public static boolean testPwdRegex(String pwd){
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";
        if (pwd.matches(regex)){
            return true;
        }
        return false;
    }

    /**
     * 用户名正则
     * 第一位必须字母
     * 必须要2到12位
     * 字母或者字母和数字的组合
     * @param account
     * @return
     */
    public static boolean testAccountRegex(String account)
    {
        String regex = "^[a-z,A-Z][A-Za-z0-9-]{1,11}$";
        if (account.matches(regex)){
            return true;
        }
        return false;
    }

    /**
     * 判断文件是否存在
     * @param strFile
     * @return
     */
    public static boolean fileIsExists(String strFile)
    {
        File f=new File(strFile);
        if(f.exists())
        {
            return true;
        }else {
            return false;
        }
    }

}
