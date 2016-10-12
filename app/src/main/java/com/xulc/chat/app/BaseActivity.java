package com.xulc.chat.app;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.xulc.chat.R;
import com.xulc.chat.View.WaitDialog;
import com.xulc.chat.utils.MyUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.xutils.x;

/**
 * Created by acer on 2016/6/23.
 */
public abstract class BaseActivity extends FragmentActivity {
    public WaitDialog proDialog;
    public boolean openEventBus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        x.view().inject(this);
        CWApplication.getInstance().pushActivity(this);
        underCreate();
    }


    /**
     * 设置是否打开eventbus
     * @param isOpen true打开
     */
    protected void setOpenEventBus(boolean isOpen){
        openEventBus = isOpen;
        if (openEventBus){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
        CWApplication.getInstance().popActivity(this);

        if (proDialog != null) {
            proDialog.dismiss();
        }
        if (openEventBus){
            EventBus.getDefault().unregister(this);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyUtils.hintKbTwo(this);
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 关闭当前activity
     * @param view 视图
     */
    public void onBack(View view) {
        MyUtils.hintKbTwo(this);
        finish();
    }

    /**
     * 设置标题
     * @param title 文字
     */
    public void setAppTitle(CharSequence title) {
        ((TextView)findViewById(R.id.tv_title)).setText(title);

    }


    /**
     * onCreate()事件处理
     */
    public abstract void underCreate();
    /**
     * 初始化等待框
     */
    private void initProDialog() {
        proDialog =new WaitDialog(this, R.anim.frame);
        proDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 显示等待动画
     */
    public void showWaiting(){
        if (proDialog==null){
            initProDialog();
        }
        proDialog.show();
    }

    /**
     * 关闭等待动画
     */
    public void cancelWaiting() {
        if (proDialog!=null&&proDialog.isShowing()){
            proDialog.dismiss();
        }
    }

}

