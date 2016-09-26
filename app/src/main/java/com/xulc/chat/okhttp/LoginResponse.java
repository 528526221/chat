package com.xulc.chat.okhttp;


/**
 * Created by xuliangchun on 2016/9/21.
 */
public class LoginResponse extends BaseResponse {
    private LoginContent content;

    public LoginContent getContent() {
        return content;
    }

    public void setContent(LoginContent content) {
        this.content = content;
    }
}
