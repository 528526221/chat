package com.xulc.chat.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.xutils.x;

/**
 * Created by acer on 2016/6/23.
 */
public abstract class BaseFragment extends Fragment {
    public View rootView;
    public boolean openEventBus;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            setContentView(inflater, container);
            x.view().inject(this, rootView);
            underCreate();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    /**
     * 设置上下文布局
     */
    protected abstract void setContentView(LayoutInflater inflater, ViewGroup container);
    /**
     * onCreate()事件处理
     */
    public abstract void underCreate();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (openEventBus){
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 子类需在oncreate中执行此方法
     * 设置是否打开eventbus
     * @param isOpen true打开
     */
    protected void setOpenEventBus(boolean isOpen){
        openEventBus = isOpen;
        if (openEventBus){
            EventBus.getDefault().register(this);
        }
    }
}
