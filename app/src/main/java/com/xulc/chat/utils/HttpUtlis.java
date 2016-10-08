package com.xulc.chat.utils;


import com.xulc.chat.okhttp.ResponseListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 接口请求工具类
 * Created by 徐椋春 on 2016/8/21.
 */
public class HttpUtlis {

    private static HttpUtlis util;
    public static HttpUtlis getInstance(){
        if (util==null){
            util = new HttpUtlis();
        }
        return util;
    }

    public void HttpPost(RequestParams params, final int code, final ResponseListener listener){
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                listener.onSuccess(code,result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtils.getInstance().showToast(ex.getMessage());
                listener.onFailure(code,ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
