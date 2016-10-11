package com.xulc.chat.table;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 *聊天记录表
 * Created by xuliangchun on 2016/10/11.
 */
@Table(name = "table_chat")
public class TableChat {
    @Column(name = "id",isId = true)
    private long id;
    @Column(name = "toPartyId")
    private String toPartyId;//和谁聊
    @Column(name = "headImg")
    private String headImg;//头像
    @Column(name = "fromMe")
    private int fromMe;//消息来源是否我说的(0默认我说的)
    @Column(name = "contentType")
    private int contentType;//消息类型
    @Column(name = "text")
    private String text;//文本内容
    @Column(name = "imgUrl")
    private String imgUrl;//图片网络地址
    @Column(name = "audioUrl")
    private String audioUrl;//音频网络地址
    @Column(name = "durationSeconds")
    private int durationSeconds;//音频时长

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToPartyId() {
        return toPartyId;
    }

    public void setToPartyId(String toPartyId) {
        this.toPartyId = toPartyId;
    }

    public int getFromMe() {
        return fromMe;
    }

    public void setFromMe(int fromMe) {
        this.fromMe = fromMe;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
