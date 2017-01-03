package com.xulc.chat.utils;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Cody on 2016/12/30.
 */

public class AudioManager {
    private MediaRecorder mRecorder;
    private String mDir;
    private String mCurrentFilePath;
    private boolean isPrepared;

    private static AudioManager mInstance;
    private static final String TAG = "AudioManager";


    private AudioManager(String dir) {
        mDir = dir;
    }

    public static AudioManager getInstance(String dir) {
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(dir);
                }
            }
        }
        return mInstance;
    }

    public String getCurrentFilePath() {
        return mCurrentFilePath;
    }

    /**
     * 回调准备完毕
     */

    public interface AudioStateListener {
        void DonePrepared();
    }

    public AudioStateListener mListener;


    public void setOnAudioStateListener(AudioStateListener listener) {
        mListener = listener;
    }


    public void prepareAudio() {
        try {
            isPrepared = false;

            //不存在目录则创建
            File dir = new File(mDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = generateFileName();
            File file = new File(mDir, fileName);
            mCurrentFilePath = file.getAbsolutePath();

            mRecorder = new MediaRecorder();
            //设置音频输出文件
            mRecorder.setOutputFile(mCurrentFilePath);
            //设置音频源为麦克风
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置音频格式
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            //设置音频编码
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //准备好了
            mRecorder.prepare();
            isPrepared = true;
            mRecorder.start();


            if (mListener != null) {
                mListener.DonePrepared();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成音频名称
     */

    private String generateFileName() {
        return UUID.randomUUID().toString() + ".amr";
    }

    public int getVolumeLevel(int maxLevel) {
        if (isPrepared && mRecorder != null) {  //很关键！mRecorder!=null
            // mRecorder.getMaxAmplitude()=32767
            //7*(0-1)   mRecorder.getMaxAmplitude()/32768得到0-1之间的值
            int result = (maxLevel * mRecorder.getMaxAmplitude() / 32768 + 1);
            return result;
        }

        return 1;
    }

    public void release() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public void cancel() {
        if (mCurrentFilePath != null) {
            File file = new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath = null;
        }
        release();
    }



}



