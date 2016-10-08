package com.xulc.chat.View;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.xulc.chat.R;


/**
 * 自定义等待框
 * Created by acer on 2016/6/23.
 */
public class WaitDialog extends ProgressDialog {
    private AnimationDrawable mAnimation;
    private Context mContext;
    private ImageView mImageView;
    private String mLoadingTip;
    private int count = 0;
    private String oldLoadingTip;
    private int mResid;

    public WaitDialog(Context context, int id) {
        super(context, R.style.Theme_Light_NoTitle_NoShadow_Dialog);//去掉dialog的阴影效果
        this.mContext = context;
        this.mResid = id;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        mImageView.setBackgroundResource(mResid);
        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();

            }
        });

    }



    private void initView() {
        setContentView(R.layout.data_loading);
        mImageView = (ImageView) findViewById(R.id.loadingIv);
    }

	/*@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		mAnimation.start();
		super.onWindowFocusChanged(hasFocus);
	}*/
}
