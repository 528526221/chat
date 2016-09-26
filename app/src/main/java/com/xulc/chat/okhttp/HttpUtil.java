package com.xulc.chat.okhttp;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * okhttp 请求工具类
 * Created by xuliangchun on 2016/9/21.
 */
public class HttpUtil {


    /**
     * 开启异步线程访问网络
     * @param map
     * @param code
     * @param listener
     */
    public static void enqueue(String url,Map<String,String> map, final int code, final ResponseListener listener) {

        OkHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int i) {
                listener.onFailure(code, e.getMessage());
                if (e.getMessage().contains("request failed , reponse's code is")) {

                } else {

                }

            }

            @Override
            public void onResponse(String s, int i) {
                BaseResponse baseResponse = JSON.parseObject(s, BaseResponse.class);
                if (baseResponse == null) {
                    listener.onFailure(code, s);
                } else {
                    if (ResponseUtil.getInstance().isSuccess(baseResponse)) {
                        listener.onSuccess(code, s);
                    } else {


                    }
                }

            }

        });
    }




}