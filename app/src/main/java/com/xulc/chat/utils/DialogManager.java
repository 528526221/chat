package com.xulc.chat.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xulc.chat.R;
import com.xulc.chat.app.Resolution;

/**
 * Created by Cody on 2016/12/30.
 */


public class DialogManager {
    ImageView mIcon;
    ImageView mVolume;
    TextView mLabel;
    private Dialog mDialog;
    private Context mContext;

    public DialogManager(Context context) {
        mContext = context;

    }

    public void showDialog() {
        mDialog = new Dialog(mContext, R.style.AudioDialogTheme);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_record, null);
        mIcon = (ImageView) mView.findViewById(R.id.iv_record_icon);
        mVolume = (ImageView) mView.findViewById(R.id.iv_record_volume);
        mLabel = (TextView) mView.findViewById(R.id.tv_record_dialog);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Resolution.dp2px(166), Resolution.dp2px(166));
        mDialog.setContentView(mView, params);
        mDialog.show();
    }


    public void recording() {
        if (mDialog != null && mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVolume.setVisibility(View.VISIBLE);
            mLabel.setVisibility(View.VISIBLE);

            mIcon.setImageResource(R.drawable.recorder);
            mVolume.setImageResource(R.drawable.v1);
            mLabel.setText(R.string.str_recorder_cancel);
            mLabel.setBackgroundColor(Color.TRANSPARENT);
        }

    }


    public void readyCancel() {
        if (mDialog != null && mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVolume.setVisibility(View.GONE);
            mLabel.setVisibility(View.VISIBLE);
            mIcon.setImageResource(R.drawable.cancel);
            mLabel.setText(R.string.str_recorder_cancel_confirm);
            mLabel.setBackgroundColor(Color.parseColor("#00BCD4"));
        }


    }


    public void durationTooShort() {
        if (mDialog != null && mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVolume.setVisibility(View.GONE);
            mLabel.setVisibility(View.VISIBLE);

            mIcon.setImageResource(R.drawable.voice_to_short);
            mLabel.setText(R.string.str_recorder_duration_too_short);
            mLabel.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    /**
     * 通过level去更新voice上的图片
     */

    public void updateVolume(int level) {
        if (mDialog != null && mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVolume.setVisibility(View.VISIBLE);
            mLabel.setVisibility(View.VISIBLE);
            switch (level){
                case 1:
                    mVolume.setImageResource(R.drawable.v1);
                    break;
                case 2:
                    mVolume.setImageResource(R.drawable.v2);
                    break;
                case 3:
                    mVolume.setImageResource(R.drawable.v3);
                    break;
                case 4:
                    mVolume.setImageResource(R.drawable.v4);
                    break;
                case 5:
                    mVolume.setImageResource(R.drawable.v5);
                    break;
                case 6:
                    mVolume.setImageResource(R.drawable.v6);
                    break;
                case 7:
                    mVolume.setImageResource(R.drawable.v7);
                    break;
            }
        }
    }


    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}

