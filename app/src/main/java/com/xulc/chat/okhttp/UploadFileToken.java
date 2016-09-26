package com.xulc.chat.okhttp;

/**
 * Created by zqzhang on 16/4/28.
 */
public class UploadFileToken {
    private String downloadUrlPrefix;
    private String uploadToken;
    private String uploadUrl;

    public String getDownloadUrlPrefix() {
        return downloadUrlPrefix;
    }

    public void setDownloadUrlPrefix(String downloadUrlPrefix) {
        this.downloadUrlPrefix = downloadUrlPrefix;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
}
