package com.xulc.chat.bean;

/**
 * Created by xuliangchun on 2016/10/10.
 */
public class VoicePush {
    private int durationSeconds;
    private int fileSizeBytes;
    private String fileUrl;
    private String localPath;
    private boolean openFile;

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public int getFileSizeBytes() {
        return fileSizeBytes;
    }

    public void setFileSizeBytes(int fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public boolean isOpenFile() {
        return openFile;
    }

    public void setOpenFile(boolean openFile) {
        this.openFile = openFile;
    }
}
