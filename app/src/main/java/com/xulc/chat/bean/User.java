package com.xulc.chat.bean;

/**
 * Created by xuliangchun on 2016/10/8.
 */
public class User {
    private String sessionID;
    private String appType;
    private String firstName;
    private String headPhotoUrl;
    private String appUserRole;
    private String partyId;
    private String userLoginId;


    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHeadPhotoUrl() {
        return headPhotoUrl;
    }

    public void setHeadPhotoUrl(String headPhotoUrl) {
        this.headPhotoUrl = headPhotoUrl;
    }

    public String getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(String appUserRole) {
        this.appUserRole = appUserRole;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }
}
