package com.xulc.chat.response;

import com.xulc.chat.bean.ReceiverId;
import com.xulc.chat.bean.SenderId;

/**
 * Created by xuliangchun on 2016/10/10.
 */
public class BasePushResponse {
    private int contentType;
    private long createTime;
    private String id;
    private int priority;
    private boolean putOfflineMsg;
    private int qos;
    private ReceiverId receiverId;
    private SenderId senderId;
    private int sendType;
    private int ttl;
    private boolean uiShow;

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isPutOfflineMsg() {
        return putOfflineMsg;
    }

    public void setPutOfflineMsg(boolean putOfflineMsg) {
        this.putOfflineMsg = putOfflineMsg;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public ReceiverId getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(ReceiverId receiverId) {
        this.receiverId = receiverId;
    }

    public SenderId getSenderId() {
        return senderId;
    }

    public void setSenderId(SenderId senderId) {
        this.senderId = senderId;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public boolean isUiShow() {
        return uiShow;
    }

    public void setUiShow(boolean uiShow) {
        this.uiShow = uiShow;
    }
}
