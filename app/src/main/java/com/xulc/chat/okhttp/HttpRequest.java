package com.xulc.chat.okhttp;


import java.util.HashMap;

import java.util.Map;

/**
 * app中所有的接口请求
 * Created by xuliangchun on 2016/9/21.
 */
public class HttpRequest {

    public static final String AuthDomainCeremonial = "https://auth.tyhealth.net";
    public static final String AuthDomainTest = "https://auth-test.tyhealth.net";

    public static final String AppDomainCeremonial = "https://appapi.tyhealth.net";
    public static final String AppDomainTest = "https://appapi-test.tyhealth.net";

    public static final String AppImpushCeremonial = "wss://appimpush.tyhealth.net";
    public static final String AppImpushTest = "wss://appimpush-test.tyhealth.net";

    public static final String AppWebCeremonial = "https://g.tyhealth.net";
    public static final String AppWebTest = "https://g-test.tyhealth.net";

    public static final String LoginUrl = "/app-login";
    /**
     * 登录
     * @param username
     * @param password
     * @param code
     * @param listener
     */
    public static void login(String username,String password,int code,ResponseListener listener,Object tag){
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("ttlseconds","604800");
        map.put("apptype","1");
        map.put("reqseq","1");
        HttpUtil.enqueue(AuthDomainTest+LoginUrl,map,code,listener,tag);
    }







}
