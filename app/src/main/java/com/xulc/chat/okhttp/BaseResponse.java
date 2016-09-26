package com.xulc.chat.okhttp;

/**
 * response的基类
 * Created by xuliangchun on 2016/9/21.
 */
public class BaseResponse {
    public int reqseq;
    public int statusCode;

    public int getReqseq() {
        return reqseq;
    }

    public void setReqseq(int reqseq) {
        this.reqseq = reqseq;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
