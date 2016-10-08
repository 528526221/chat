package com.xulc.chat.okhttp;

/**
 * Created by zqzhang on 16/8/16.
 * tools相关的请求
 */
public class HttpInterfaceRequest {

    public static final String AuthDomainCeremonial = "https://auth.tyhealth.net";
    public static final String AuthDomainTest = "https://auth-test.tyhealth.net";

    public static final String AppDomainCeremonial = "https://appapi.tyhealth.net";
    public static final String AppDomainTest = "https://appapi-test.tyhealth.net";

    public static final String AppImpushCeremonial = "wss://appimpush.tyhealth.net";
    public static final String AppImpushTest = "wss://appimpush-test.tyhealth.net";

    public static final String AppWebCeremonial = "https://g.tyhealth.net";
    public static final String AppWebTest = "https://g-test.tyhealth.net";


    public synchronized static String getAuthDomain()
    {
        return AuthDomainTest;
    }
    public synchronized static String getApiDomain()
    {
        return AppDomainTest;
    }
    public synchronized static String getImpushDomain()
    {
        return AppImpushTest;

    }

    public synchronized static String getWebDomain()
    {

        return AppWebTest;
    }

    public static String applogin = "/app-login";
    public static String get_app_list = "/apptv/familytree/member/list";
    public static String getFriendList = "/im-busi/app/friend/list";

    public static String imChatP2pText = "/im-busi/app/im/send/p2p/text";
    public static String imChatFgText = "/im-busi/app/im/send/fg/text";

    public static String imChatP2pImage = "/im-busi/app/im/send/p2p/image";
    public static String imChatFgImage = "/im-busi/app/im/send/fg/image";

    public static String imChatP2pVoice = "/im-busi/app/im/send/p2p/voice";
    public static String imChatFgVoice = "/im-busi/app/im/send/fg/voice";

    public static String imChatP2pVideo = "/im-busi/app/im/send/p2p/video";
    public static String imChatFgVideo = "/im-busi/app/im/send/fg/video";

    public static String fileUpload = "/file-cloud/app/upload/token/apply";

    public static String LogoutUrl = "/app-logout";//退出登录



    public static String GetUserDetailUrl = "/app/person/detail";//获取用户详细资料
    public static String SendSms = "/app/user/account/sms/send";//发送验证码
    public static String RedistAccount = "/app/user/account/regist";//注册用户
    public static String FindPwd = "/app/user/account/pwd/find";//找回密码


}
