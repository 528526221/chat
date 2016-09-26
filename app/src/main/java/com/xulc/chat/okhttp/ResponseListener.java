package com.xulc.chat.okhttp;

/**
 * Created by xuliangchun on 2016/9/21.
 */
public interface ResponseListener {
    void onSuccess(int code, String result);
    void onFailure(int code, String msg);
}
