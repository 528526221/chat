package com.xulc.chat.response;


import com.xulc.chat.okhttp.BaseResponse;
import com.xulc.chat.bean.Login;

/**
 * Created by xuliangchun on 2016/9/21.
 */
public class LoginResponse extends BaseResponse {
    private Login content;

    public Login getContent() {
        return content;
    }

    public void setContent(Login content) {
        this.content = content;
    }
}
