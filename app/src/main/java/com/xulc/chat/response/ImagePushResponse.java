package com.xulc.chat.response;

import com.xulc.chat.bean.ImagePush;

/**
 * Created by xuliangchun on 2016/10/10.
 */
public class ImagePushResponse extends BasePushResponse {
    private ImagePush content;

    public ImagePush getContent() {
        return content;
    }

    public void setContent(ImagePush content) {
        this.content = content;
    }
}
