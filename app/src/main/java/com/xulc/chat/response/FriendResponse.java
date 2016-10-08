package com.xulc.chat.response;

import com.xulc.chat.bean.Friend;
import com.xulc.chat.okhttp.BaseResponse;

import java.util.List;

/**
 * Created by xuliangchun on 2016/10/8.
 */
public class FriendResponse extends BaseResponse {
    private List<Friend> content;

    public List<Friend> getContent() {
        return content;
    }

    public void setContent(List<Friend> content) {
        this.content = content;
    }
}
