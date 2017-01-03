package com.xulc.chat.okhttp;


import com.xulc.chat.app.CWApplication;

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
    public static final String FriendLsitUrl = "/im-busi/app/friend/list";
    public static final String LogoutUrl = "/app-logout";
    public static final String SendTextUrl = "/im-busi/app/im/send/p2p/text";
    public static final String SendGroupTextUrl = "/im-busi/app/im/send/fg/text";

    public static final String SendImgUrl = "/im-busi/app/im/send/p2p/image";
    public static final String SendGroupImgUrl = "/im-busi/app/im/send/fg/image";

    public static final String SendVoiceUrl = "/im-busi/app/im/send/p2p/voice";
    public static final String SendGroupVoiceUrl = "/im-busi/app/im/send/fg/voice";

    public static final String SendVideoUrl = "/im-busi/app/im/send/p2p/video";
    public static final String SendGroupVideoUrl = "/im-busi/app/im/send/fg/video";

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
        HttpUtil.request(AuthDomainTest,LoginUrl,map,code,listener,tag);
    }

    /**
     * 获取好友列表
     * @param code
     * @param listener,tag
     */
    public static void getFriendList(int code,ResponseListener listener,Object tag){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWApplication.getInstance().getUser().getSessionID());
        map.put("reqseq","1");
        HttpUtil.request2(FriendLsitUrl, map, code, listener, tag);
    }

    /**
     * 退出
     * @param code
     * @param listener
     * @param tag
     */
    public static void Logout(int code,ResponseListener listener,Object tag){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWApplication.getInstance().getUser().getSessionID());
        map.put("reqseq","1");
        HttpUtil.request(AuthDomainTest, LogoutUrl, map, code, listener, tag);
    }

    /**
     * 单聊发送文本
     * @param receiverId
     * @param content
     * @param receiverUserType
     * @param code
     * @param listener
     * @param reqseq
     * @param tag
     */
    public static void sendText(String receiverId,String content,String receiverUserType,int code,ResponseListener listener,long reqseq,Object tag){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWApplication.getInstance().getUser().getSessionID());
        map.put("reqseq",String.valueOf(reqseq));
        map.put("receiverId",receiverId);
        map.put("content",content);
        map.put("receiverUserType",receiverUserType);
        HttpUtil.request2(SendTextUrl, map, code, listener, tag);
    }

    /**
     * 单聊发送语音
     * @param receiverId
     * @param content 序列化后的string
     * @param code
     * @param listener
     * @param reqseq
     * @param tag
     */
    public static void sendVoice(String receiverId,String content,int code,ResponseListener listener,long reqseq,Object tag){
        Map<String,String> map = new HashMap<>();
        map.put("sessionID", CWApplication.getInstance().getUser().getSessionID());
        map.put("reqseq",String.valueOf(reqseq));
        map.put("receiverId",receiverId);
        map.put("content",content);
        HttpUtil.request2(SendTextUrl, map, code, listener, tag);
    }

}
