package com.xulc.chat.response;

/**
 * Created by xuliangchun on 2016/9/21.
 */
public class PostFileResponse {
    private String etag;
    private String ext;
    private String fname;
    private int fsize;
    private String imageColorModel;
    private String imageFormat;
    private int imageHeight;
    private String imageOrientation;
    private int imageWidth;
    private String key;
    private String mimeType;
    private int reqseq;

    public int getReqseq() {
        return reqseq;
    }

    public void setReqseq(int reqseq) {
        this.reqseq = reqseq;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getFsize() {
        return fsize;
    }

    public void setFsize(int fsize) {
        this.fsize = fsize;
    }

    public String getImageColorModel() {
        return imageColorModel;
    }

    public void setImageColorModel(String imageColorModel) {
        this.imageColorModel = imageColorModel;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getImageOrientation() {
        return imageOrientation;
    }

    public void setImageOrientation(String imageOrientation) {
        this.imageOrientation = imageOrientation;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
