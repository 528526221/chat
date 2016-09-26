package com.xulc.chat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xuliangchun on 2016/9/26.
 */
public abstract class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        underCreate();
    }

    protected abstract int setLayout();

    protected abstract void underCreate();

    /**
     * 关闭当前activity
     * @param view 视图
     */
    public void onBack(View view) {
        finish();
    }

    /**
     * 设置标题
     * @param title 文字
     */
    public void setAppTitle(CharSequence title) {
        ((TextView)findViewById(R.id.tv_title)).setText(title);

    }
}
