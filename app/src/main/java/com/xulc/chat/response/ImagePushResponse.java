package com.xulc.chat.response;


import com.alibaba.fastjson.JSON;
import com.xulc.chat.bean.ImagePush;

/**
 * Created by xuliangchun on 2016/10/10.
 */
public class ImagePushResponse extends BasePushResponse {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private ImagePush imagePush;

    public ImagePush getImagePush() {
        if (imagePush==null){
            imagePush = JSON.parseObject(content,ImagePush.class);
        }
        return imagePush;
    }

    public void setImagePush(ImagePush imagePush) {
        this.imagePush = imagePush;
    }
}
