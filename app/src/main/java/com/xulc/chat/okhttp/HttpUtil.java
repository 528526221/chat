package com.xulc.chat.okhttp;


import com.alibaba.fastjson.JSON;
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
    public static void requestEnqueue(String url,Map<String,String> map, final int code, final ResponseListener listener) {

        OkHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int i) {
                listener.onFailure(code, e.getMessage());
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
                        listener.onFailure(code, s);
                    }
                }

            }

        });
    }

    /**
     * 不带主机地址则默认请求api主机
     * @param path
     * @param map
     * @param code
     * @param listener
     */
    public static void enqueue(String path,Map<String,String> map, int code, ResponseListener listener) {
        requestEnqueue(HttpInterfaceRequest.getApiDomain()+path,map,code,listener);
    }

    /**
     * 带主机地址访问
     * @param domin
     * @param path
     * @param map
     * @param code
     * @param listener
     */
    public static void enqueue(String domin,String path,Map<String,String> map, int code, ResponseListener listener) {
        requestEnqueue(domin+path,map,code,listener);
    }










}
