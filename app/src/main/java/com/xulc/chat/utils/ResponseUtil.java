package com.xulc.chat.utils;



import com.xulc.chat.okhttp.BaseResponse;

import java.util.HashMap;

/**
 * Created by 徐椋春 on 2016/8/21.
 */
public class ResponseUtil {
    private static ResponseUtil util;
    private HashMap<Integer,String> errorMsg = new HashMap<>();

    /**
     * 构造对象时将错误列表初始化
     */
    public ResponseUtil() {
        errorMsg.put(100002,"用户名或密码错误");
    }

    public static ResponseUtil getInstance(){
        if (util==null){
            util = new ResponseUtil();
        }
        return util;
    }

    /**
     * 判断response是否请求数据成功
     * @param response
     * @return
     */
    public boolean isSuccess(BaseResponse response){
        if (response.getStatusCode()==0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * response请求失败时根据错误码返回错误描述
     * @param response
     * @return
     */
    public  String getErrorMsg(BaseResponse response){
        String msg = errorMsg.get(response.getStatusCode());
        if (msg==null){
            msg = String.valueOf(response.getStatusCode()).concat("错误表未定义");
        }
        return msg;
    }
}
