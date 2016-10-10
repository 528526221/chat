package com.xulc.chat.response;

import com.xulc.chat.bean.VoicePush;

/**
 * Created by xuliangchun on 2016/10/10.
 */
public class VoicePushResponse extends BasePushResponse{
    private VoicePush content;

    public VoicePush getContent() {
        return content;
    }

    public void setContent(VoicePush content) {
        this.content = content;
    }
}
