package com.xulc.chat.okhttp;

import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.xulc.chat.response.PostFileResponse;
import com.xulc.chat.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

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


    /**
     * 上传文件
     * @param path
     * @param code
     * @param listener
     */
    public static void postFile(String path,final int code, final ResponseListener listener){
        String uploadToken = "";
        String uploadUrl = "";
        OkHttpUtils.post().addFile("file","",new File(path)).url(uploadUrl).addParams("token",
                uploadToken).build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int i) {
                listener.onFailure(code, e.getMessage());
                ToastUtils.getInstance().showToast(e.getMessage());
            }

            @Override
            public void onResponse(String s, int i) {
                PostFileResponse response = JSON.parseObject(s, PostFileResponse.class);
                if (response == null) {
                    listener.onFailure(code, s);
                } else {
                    if (response.getKey() != null) {
                        listener.onSuccess(code, s);
                    } else {
                        listener.onFailure(code, s);
                    }
                }


            }

        });
    }


        /**
         * 上传图片
         * @param request
         */
    public static void postImage(Request request, final int code, final ResponseListener listener , final int reqseq) {

        OkHttpUtils.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailure(code, e.getMessage());
                ToastUtils.getInstance().showToast(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                PostFileResponse postFileResponse = JSON.parseObject(s, PostFileResponse.class);
                postFileResponse.setReqseq(reqseq);
                s = JSON.toJSONString(postFileResponse);
                if (response == null) {
                    listener.onFailure(code, s);
                } else {
                    if (postFileResponse.getKey() != null) {
                        listener.onSuccess(code, s);
                    } else {
                        listener.onFailure(code, s);
                    }
                }
            }
        });
    }


}
