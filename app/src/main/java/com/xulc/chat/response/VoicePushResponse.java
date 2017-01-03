package com.xulc.chat.response;

import com.alibaba.fastjson.JSON;
import com.xulc.chat.bean.VoicePush;

/**
 * Created by xuliangchun on 2016/10/10.
 */
public class VoicePushResponse extends BasePushResponse{
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private VoicePush voicePush;

    public VoicePush getVoicePush() {
        if (voicePush==null){
            voicePush = JSON.parseObject(content, VoicePush.class);
        }
        return voicePush;
    }

    public void setVoicePush(VoicePush voicePush) {
        this.voicePush = voicePush;
    }
}
