package com.xulc.chat.response;


import com.xulc.chat.bean.UploadFileToken;

/**
 * Created by xuliangchun on 2016/9/21.
 */
public class UploadTokenResponse {
    private UploadFileToken content;

    public UploadFileToken getContent() {
        return content;
    }

    public void setContent(UploadFileToken content) {
        this.content = content;
    }
}
