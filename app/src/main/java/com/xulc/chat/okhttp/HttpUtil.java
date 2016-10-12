package com.xulc.chat.okhttp;


import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.xulc.chat.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

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
    public static void enqueue(String url,Map<String,String> map, final int code, final ResponseListener listener,Object tag) {

        OkHttpUtils.post().url(url).params(map).tag(tag).build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int i) {
                listener.onFailure(code, e.getMessage());
                ToastUtils.getInstance().showToast(e.getMessage());

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
                        ToastUtils.getInstance().showToast(ResponseUtil.getInstance().getErrorMsg(baseResponse));
                        listener.onFailure(code, s);
                    }
                }

            }

        });
    }

    /**
     * 带主机地址请求
     * @param host
     * @param url
     * @param map
     * @param code
     * @param listener
     * @param tag
     */
    public static void request(String host,String url,Map<String,String> map, int code, ResponseListener listener,Object tag) {
        enqueue(host + url, map, code, listener, tag);
    }


    /**
     * 不带主机地址请求则默认一个
     * @param url
     * @param map
     * @param code
     * @param listener
     * @param tag
     */
    public static void request2(String url,Map<String,String> map, int code, ResponseListener listener,Object tag) {

        enqueue(HttpRequest.AppDomainTest+url,map,code,listener,tag);
    }


    public static void downFile(ImageView imageView,String url){
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment
                .getExternalStorageDirectory().getAbsolutePath(), url) {
            @Override
            public void onError(Call call, Exception e, int i) {
                Log.e("xlc", "onError :" + e.getMessage());
            }

            @Override
            public void onResponse(File file, int i) {

            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
                Log.i("xlc", "下载中：" + progress);
            }
        });
    }

    public static void displayImg(final ImageView imageView,String url){
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int i) {

                    }

                });
    }

}
