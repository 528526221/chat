package com.xulc.chat.View;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.xulc.chat.R;
import com.xulc.chat.app.Resolution;
import com.xulc.chat.utils.AudioManager;
import com.xulc.chat.utils.DialogManager;

/**
 * Created by Cody on 2016/12/30.
 */

public class AudioRecordButton extends Button implements AudioManager.AudioStateListener {
    private static final String TAG = "AudioRecordButton";
    private static final int DISTANCE_Y_CANCEL = Resolution.dp2px(30);
    private static final int STATE_NORMAL = 1;
    private static final int STATE_RECORDING = 2;
    private static final int STATE_CANCLE = 3;
    private static final float MIN_DURATION = 0.8f;
    private int mCurState = STATE_NORMAL;
    private static final int MSG_AUDIO_PREPARED = 101;
    private static final int MSG_VOLUME_CHANGED = 102;
    private static final int MSG_DIALOG_DISMISSED = 103;
    private static final int MAX_VOLUME_LEVEL = 7;
    private float mDuration;   //录音时长
    private boolean mReady = false;  //是否触发长按

    private boolean isRecording = false;
    private DialogManager mDialogManager;
    private AudioManager mAudioManager;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_AUDIO_PREPARED:
                    //准备好录音了
                    mDialogManager.showDialog();
                    isRecording = true;
                    //开分线程获取音量大小
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (isRecording) {
                                SystemClock.sleep(100);//每0.1秒获取音量大小
                                mDuration += 0.1f;  //记录录音时长
                                mHandler.sendEmptyMessage(MSG_VOLUME_CHANGED);
                            }
                        }
                    }).start();
                    break;

                case MSG_VOLUME_CHANGED:
                    mDialogManager.updateVolume(mAudioManager.getVolumeLevel(MAX_VOLUME_LEVEL));
                    break;

                case MSG_DIALOG_DISMISSED:
                    mDialogManager.dismissDialog();
                    break;
            }
        }
    };


    public AudioRecordButton(Context context) {
        this(context, null);
    }

    public AudioRecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        String dir = Environment.getExternalStorageDirectory() + "/cody_audios";
        mAudioManager = AudioManager.getInstance(dir);
        mAudioManager.setOnAudioStateListener(this);

        mDialogManager = new DialogManager(getContext());
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按准备录音
                mReady = true;
                mAudioManager.prepareAudio();
                return true;
            }
        });
    }

    /**
     * 录音完成后的回调
     */

    private DoneRecordListener mListener;

    public interface DoneRecordListener {
        void onFinish(float seconds, String filePath);
    }


    public void setDoneRecordListener(DoneRecordListener listener) {
        mListener = listener;
    }


    /**
     * 准备好的回调
     */
    @Override
    public void DonePrepared() {
        mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                changeState(STATE_RECORDING);
                break;

            case MotionEvent.ACTION_MOVE: //录音过程中，根据x,y的坐标判断是否要取消录音
                if (isRecording) {
                    if (readyToCancel(y)) {
                        changeState(STATE_CANCLE);
                    } else {
                        changeState(STATE_RECORDING);
                    }

                }
                break;

            case MotionEvent.ACTION_UP:  //三种情况 触发长按 不触发长按 触发长按时长太短
                if (!mReady) { //没准备好
                    reset();
                    Log.d(TAG, "state1");
                    return super.onTouchEvent(event);
                }

                //recorder,prepare()没完成 up了
                if (!isRecording || mDuration < MIN_DURATION) {
                    mAudioManager.cancel();
                    mDialogManager.durationTooShort();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DISMISSED, 1200); //1.2秒后关闭对话框
                    Log.d(TAG, "state2");
                } else if (mCurState == STATE_RECORDING) {
                    // 正常结束
                    //release
                    //callback to activity

                    if (mListener != null) {  //调用接口方法 回调时间和文件路径
                        mListener.onFinish(mDuration,mAudioManager.getCurrentFilePath());
                    }

                    mAudioManager.release();
                    mDialogManager.dismissDialog();
                    Log.d(TAG, "state3");
                } else if (mCurState == STATE_CANCLE) {
                    //cancel
                    mDialogManager.dismissDialog();
                    mAudioManager.cancel();
                }
                //重置
                reset();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 恢复状态及标志位
     */
    private void reset() {
        mDuration = 0;
        mReady = false;
        isRecording = false;
        changeState(STATE_NORMAL);
    }


    private boolean readyToCancel(int y) {
        if (y < 0 - DISTANCE_Y_CANCEL) {   //上移取消发送
            return true;
        }
        return false;
    }

    private void changeState(int state) {
        if (mCurState != state) {
            mCurState = state;
            switch (state) {
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.bt_bg_normal);
                    setText(R.string.str_recorder_normal);
                    break;
                case STATE_RECORDING:
                    setBackgroundResource(R.drawable.bt_bg_recording);
                    setText(R.string.str_recorder_recording);
                    if (isRecording) {
                        mDialogManager.recording();
                    }
                    break;
                case STATE_CANCLE:
                    setBackgroundResource(R.drawable.bt_bg_recording);
                    setText(R.string.str_recorder_recording);
                    mDialogManager.readyCancel();
                    break;
            }
        }
    }

}
