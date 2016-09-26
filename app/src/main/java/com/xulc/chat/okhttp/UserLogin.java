package com.xulc.chat.okhttp;

/**
 * Created by acer on 2016/4/26.
 */
public class UserLogin {
    private String appType;
    private String firstName;
    private String headPhotoUrl;
    private String appUserRole;
    private int errorCode;
    private String partyId;
    private boolean requireChangePwd;
    private boolean success;
    private String userLoginId;
    private boolean tvUser =false;
    public String getHeadPhotoUrl() {
        return headPhotoUrl;
    }

    public void setHeadPhotoUrl(String headPhotoUrl) {
        this.headPhotoUrl = headPhotoUrl;
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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public boolean isRequireChangePwd() {
        return requireChangePwd;
    }

    public void setRequireChangePwd(boolean requireChangePwd) {
        this.requireChangePwd = requireChangePwd;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public boolean isTvUser() {
        return tvUser;
    }

    public void setTvUser(boolean tvUser) {
        this.tvUser = tvUser;
    }

    public String getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(String appUserRole) {
        this.appUserRole = appUserRole;
    }
}
