package com.xulc.chat.okhttp;


/**
 * Created by acer on 2016/4/16.
 */
public class LoginContent {
    private UserLogin userLogin;
    private String sessionID;
    private int ttlSeconds;
    private String appDownloadUrl="";
    private String guidePageUrl;

    public String getGuidePageUrl() {
        return guidePageUrl;
    }

    public void setGuidePageUrl(String guidePageUrl) {
        this.guidePageUrl = guidePageUrl;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public int getTtlSeconds() {
        return ttlSeconds;
    }

    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }
}
