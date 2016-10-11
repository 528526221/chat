package com.xulc.chat.bean;

import android.text.TextUtils;

/**
 * Created by xuliangchun on 2016/10/8.
 */
public class Friend {
    private String callName;
    private String deviceId;
    private String firstName;
    private String geneValue;
    private String intimacy;
    private String partyId;
    private String profileImgUrl;
    private String remarkName;
    private String tvUser;
    private String userLoginId;
    private String familyTreeMemberId;
    private String activedOnFamilyTree;

    public String getCallName() {
        if (TextUtils.isEmpty(callName)){
            callName = getFirstName();
        }
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFirstName() {
        if (TextUtils.isEmpty(firstName)){
            firstName = getUserLoginId();
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGeneValue() {
        return geneValue;
    }

    public void setGeneValue(String geneValue) {
        this.geneValue = geneValue;
    }

    public String getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(String intimacy) {
        this.intimacy = intimacy;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getTvUser() {
        return tvUser;
    }

    public void setTvUser(String tvUser) {
        this.tvUser = tvUser;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getFamilyTreeMemberId() {
        return familyTreeMemberId;
    }

    public void setFamilyTreeMemberId(String familyTreeMemberId) {
        this.familyTreeMemberId = familyTreeMemberId;
    }

    public String getActivedOnFamilyTree() {
        return activedOnFamilyTree;
    }

    public void setActivedOnFamilyTree(String activedOnFamilyTree) {
        this.activedOnFamilyTree = activedOnFamilyTree;
    }
}
