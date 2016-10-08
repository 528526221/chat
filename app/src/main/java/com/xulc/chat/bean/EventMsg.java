package com.xulc.chat.bean;

/**
 * Created by lenovo on 2016/9/5.
 */

import android.os.Bundle;

/**
 * Created by xlc on 2016/4/1.
 * msgCode 消息码 在EbConstant中可查
 * Bundle 消息内容
 */
public class EventMsg {
    private int msgCode;
    private Bundle bundle;

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }


    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public int getMsgCode() {
        return msgCode;
    }

}
