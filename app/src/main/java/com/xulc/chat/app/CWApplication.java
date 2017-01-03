package com.xulc.chat.app;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;


import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.bugly.crashreport.CrashReport;
import com.xulc.chat.bean.User;
import com.xulc.chat.constans.ShareKey;
import com.xulc.chat.utils.PreferencesUtils;

import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xuliangchun on 2016/6/24.
 */
public class CWApplication extends Application {
    /** Activity管理 */
    private static List<Activity> mActivityList = new LinkedList<Activity>();
    private static CWApplication  instance;
    public User user;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setResolution();//首次启动应用初始化分辨率
        x.Ext.init(this);
        x.Ext.setDebug(true);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        CrashReport.initCrashReport(getApplicationContext(), "0456eafee6", false);
    }



    public static CWApplication getInstance() {
        return instance;
    }

    private void setResolution(){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Resolution.init(metrics.widthPixels,metrics.heightPixels,metrics.density,metrics.scaledDensity);
    }

    /**
     * 添加一个 activity到数组中
     */
    public void pushActivity(Activity activity) {
        mActivityList.add(activity);
    }

    /**
     *去除当前数组的中的一个activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            mActivityList.remove(activity);
        }
    }

    /**
     * 关闭数组中名字是itName以外的activity
     * @param itName
     */
    public void closeOtherActivities(String itName) {
        for (Activity iter : mActivityList) {
            if (null != iter) {
                String activityName = iter.getClass().getSimpleName();

                if (!activityName.equalsIgnoreCase(itName))
                    iter.finish();
            }
        }
    }


    /**
     *  关闭所有的 activity
     */
    public void killAllActivity() {
        for (Activity iter : mActivityList) {
            if (null != iter)
                iter.finish();
        }
    }

    /**
     * 保存用户信息到share
     * @param user
     */
    public void setUser(User user){
        this.user = user;
        String str = JSON.toJSONString(user);
        PreferencesUtils.getInstance().putString(ShareKey.USER,str);
    }

    /**
     * 从share中读取用户信息
     * @return
     */
    public User getUser() {
        if (user==null){
            String str = PreferencesUtils.getInstance().getString(ShareKey.USER);
            user = JSON.parseObject(str,User.class);
        }
        return user;
    }
}
