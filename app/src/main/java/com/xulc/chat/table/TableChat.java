package com.xulc.chat.table;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 *聊天记录表
 * Created by xuliangchun on 2016/10/11.
 */
@Table(name = "_table_chat")
public class TableChat {
    @Column(name = "id",isId = true,autoGen = false)
    private long id;
    @Column(name = "toTel")
    private String toTel;//对方电话
    @Column(name = "toPartyId")
    private String toPartyId;//对方partyID
    @Column(name = "headImg")
    private String headImg;//头像
    @Column(name = "fromMe")
    private int fromMe;//消息来源是否我说的(0默认我说的)
    @Column(name = "sendSuccess")
    private int sendSuccess;//消息是否发送成功(0默认成功)
    @Column(name = "contentType")
    private int contentType;//消息类型
    @Column(name = "text")
    private String text;//文本内容
    @Column(name = "imgUrl")
    private String imgUrl;//图片网络地址
    @Column(name = "localImgUrl")
    private String localImgUrl;//图片本地路径
    @Column(name = "audioUrl")
    private String audioUrl;//音频网络地址
    @Column(name = "localAudioUrl")
    private String localAudioUrl;//音频本地路径
    @Column(name = "durationSeconds")
    private float durationSeconds;//音频时长
    @Column(name = "sending")
    private boolean sending;//正在发送
    @Column(name = "createTime")
    private long createTime;//时间


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

    public float getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(float durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getToTel() {
        return toTel;
    }

    public void setToTel(String toTel) {
        this.toTel = toTel;
    }

    public int getSendSuccess() {
        return sendSuccess;
    }

    public void setSendSuccess(int sendSuccess) {
        this.sendSuccess = sendSuccess;
    }

    public String getLocalImgUrl() {
        return localImgUrl;
    }

    public void setLocalImgUrl(String localImgUrl) {
        this.localImgUrl = localImgUrl;
    }

    public String getLocalAudioUrl() {
        return localAudioUrl;
    }

    public void setLocalAudioUrl(String localAudioUrl) {
        this.localAudioUrl = localAudioUrl;
    }

    public String getKindlySeconds(){
        int min = (int) (durationSeconds/60);
        int sec = (int) (durationSeconds%60);
        if (min>0){
            return String.format("%d'%d''",min,sec);
        }else {
            return String.format("%d''",sec);
        }
    }

    public boolean isSending() {
        return sending;
    }

    public void setSending(boolean sending) {
        this.sending = sending;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
