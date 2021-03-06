package com.xulc.chat.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xulc.chat.R;

import com.xulc.chat.table.TableChat;
import com.xulc.chat.utils.BitMapUtils;
import com.xulc.chat.utils.DateUtils;
import com.xulc.chat.utils.MediaManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuliangchun on 2016/10/11.
 */
public class ChatAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<TableChat> chatList;
    private PlayAudioListener playAudioListener;
    private final int TEXT_LEFT=0;
    private final int TEXT_RIGHT=1;
    private final int IMG_LEFT=2;
    private final int IMG_RIGHT=3;
    private final int AUDIO_LEFT=4;
    private final int AUDIO_RIGHT=5;

    public ChatAdapter(Context mContext,PlayAudioListener playAudioListener) {
        this.mContext = mContext;
        this.chatList = new ArrayList<>();
        this.inflater = LayoutInflater.from(mContext);
        this.playAudioListener = playAudioListener;
    }

    public interface PlayAudioListener{
        void onPlay(View view,int position,boolean left);
    }

    public void refresh(List<TableChat> chatList){
        this.chatList = chatList;
        notifyDataSetChanged();
    }

    public void addMsg(TableChat chat){
        this.chatList.add(chat);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return chatList.size();
    }

    @Override
    public TableChat getItem(int position) {
        return chatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3*2;
    }

    @Override
    public int getItemViewType(int position) {
        int contentType = getItem(position).getContentType();
        int formMe = getItem(position).getFromMe();
        switch (contentType){
            case 1:
                switch (formMe){
                    case 0:
                        //right
                        return TEXT_RIGHT;
                    case 1:
                        //left
                        return TEXT_LEFT;
                }
                break;
            case 2:
                switch (formMe){
                    case 0:
                        //right
                        return IMG_RIGHT;
                    case 1:
                        //left
                        return IMG_LEFT;
                }
                break;
            case 4:
                switch (formMe){
                    case 0:
                        //right
                        return AUDIO_RIGHT;
                    case 1:
                        //left
                        return AUDIO_LEFT;
                }
                break;
            default:
                switch (formMe){
                    case 0:
                        //right
                        return TEXT_RIGHT;
                    case 1:
                        //left
                        return TEXT_LEFT;
                }
                break;
        }

        return super.getItemViewType(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextHolderLeft textHolderLeft;
        TextHolderRight textHolderRight;
        ImgHolderLeft imgHolderLeft;
        ImgHolderRight imgHolderRight;
        final AudioHolderLeft audioHolderLeft;
        final AudioHolderRight audioHolderRight;
        final TableChat bean = getItem(position);
        int type = getItemViewType(position);
        if (convertView == null){
            switch (type){
                case TEXT_LEFT:
                    convertView = inflater.inflate(R.layout.item_text_left,parent,false);
                    textHolderLeft = new TextHolderLeft();
                    textHolderLeft.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    textHolderLeft.tvText = (TextView) convertView.findViewById(R.id.tvText);
                    textHolderLeft.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                    convertView.setTag(textHolderLeft);
                    textHolderLeft.tvText.setText(bean.getText());
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), textHolderLeft
                            .headImg, BitMapUtils.headOptions);
                    textHolderLeft.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case TEXT_RIGHT:
                    convertView = inflater.inflate(R.layout.item_text_right,parent,false);
                    textHolderRight = new TextHolderRight();
                    textHolderRight.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    textHolderRight.tvText = (TextView) convertView.findViewById(R.id.tvText);
                    textHolderRight.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                    convertView.setTag(textHolderRight);
                    textHolderRight.tvText.setText(bean.getText());
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), textHolderRight
                            .headImg, BitMapUtils.headOptions);
                    textHolderRight.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case IMG_LEFT:
                    convertView = inflater.inflate(R.layout.item_img_left,parent,false);
                    imgHolderLeft = new ImgHolderLeft();
                    imgHolderLeft.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    imgHolderLeft.chatImg = (ImageView) convertView.findViewById(R.id.chatImg);
                    imgHolderLeft.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                    convertView.setTag(imgHolderLeft);
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), imgHolderLeft
                            .headImg, BitMapUtils.headOptions);
                    ImageLoader.getInstance().displayImage(bean.getImgUrl(),
                            imgHolderLeft.chatImg, BitMapUtils.imageOptions);
                    imgHolderLeft.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case IMG_RIGHT:
                    convertView = inflater.inflate(R.layout.item_img_right,parent,false);
                    imgHolderRight = new ImgHolderRight();
                    imgHolderRight.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    imgHolderRight.chatImg = (ImageView) convertView.findViewById(R.id.chatImg);
                    imgHolderRight.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                    convertView.setTag(imgHolderRight);
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), imgHolderRight
                            .headImg, BitMapUtils.headOptions);
                    ImageLoader.getInstance().displayImage(bean.getImgUrl(),
                            imgHolderRight.chatImg, BitMapUtils.imageOptions);
                    imgHolderRight.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case AUDIO_LEFT:
                    convertView = inflater.inflate(R.layout.item_audio_left,parent,false);
                    audioHolderLeft = new AudioHolderLeft();
                    audioHolderLeft.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                    audioHolderLeft.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    audioHolderLeft.ivRecord = convertView.findViewById(R.id.ivRecord);
                    audioHolderLeft.lyAudio = (LinearLayout) convertView.findViewById(R.id.lyAudio);
                    audioHolderLeft.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    audioHolderLeft.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
                    audioHolderLeft.sendProgressbar = (ProgressBar) convertView.findViewById(R.id.sendProgressbar);
                    convertView.setTag(audioHolderLeft);
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), audioHolderLeft
                            .headImg, BitMapUtils.headOptions);
                    audioHolderLeft.tvDuration.setText(bean.getKindlySeconds());
                    audioHolderLeft.lyAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            playAudioListener.onPlay(audioHolderLeft.ivRecord,position,true);
                        }
                    });
                    if (bean.isSending()){
                        audioHolderLeft.sendProgressbar.setVisibility(View.VISIBLE);
                    }else {
                        audioHolderLeft.sendProgressbar.setVisibility(View.INVISIBLE);
                    }
                    audioHolderLeft.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case AUDIO_RIGHT:
                    convertView = inflater.inflate(R.layout.item_audio_right,parent,false);
                    audioHolderRight = new AudioHolderRight();
                    audioHolderRight.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                    audioHolderRight.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    audioHolderRight.ivRecord = convertView.findViewById(R.id.ivRecord);
                    audioHolderRight.lyAudio = (LinearLayout) convertView.findViewById(R.id.lyAudio);
                    audioHolderRight.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    audioHolderRight.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
                    audioHolderRight.sendProgressbar = (ProgressBar) convertView.findViewById(R.id.sendProgressbar);

                    convertView.setTag(audioHolderRight);
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), audioHolderRight
                            .headImg, BitMapUtils.headOptions);
                    audioHolderRight.tvDuration.setText(bean.getKindlySeconds());

                    audioHolderRight.lyAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            playAudioListener.onPlay(audioHolderRight.ivRecord,position,false);
                        }
                    });
                    if (bean.isSending()){
                        audioHolderRight.sendProgressbar.setVisibility(View.VISIBLE);
                    }else {
                        audioHolderRight.sendProgressbar.setVisibility(View.INVISIBLE);
                    }
                    audioHolderRight.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
            }
        }else {
            switch (type){
                case TEXT_LEFT:
                    textHolderLeft = (TextHolderLeft) convertView.getTag();
                    textHolderLeft.tvText.setText(bean.getText());
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), textHolderLeft
                            .headImg, BitMapUtils.headOptions);
                    textHolderLeft.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case TEXT_RIGHT:
                    textHolderRight = (TextHolderRight) convertView.getTag();
                    textHolderRight.tvText.setText(bean.getText());
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), textHolderRight
                            .headImg, BitMapUtils.headOptions);
                    textHolderRight.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case IMG_LEFT:
                    imgHolderLeft = (ImgHolderLeft) convertView.getTag();
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), imgHolderLeft
                            .headImg, BitMapUtils.headOptions);
                    ImageLoader.getInstance().displayImage(bean.getImgUrl(),
                            imgHolderLeft.chatImg, BitMapUtils.imageOptions);
                    imgHolderLeft.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case IMG_RIGHT:
                    imgHolderRight = (ImgHolderRight) convertView.getTag();
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), imgHolderRight
                            .headImg, BitMapUtils.headOptions);
                    ImageLoader.getInstance().displayImage(bean.getImgUrl(),
                            imgHolderRight.chatImg, BitMapUtils.imageOptions);
                    imgHolderRight.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case AUDIO_LEFT:
                    audioHolderLeft = (AudioHolderLeft) convertView.getTag();
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), audioHolderLeft
                            .headImg, BitMapUtils.headOptions);
                    audioHolderLeft.tvDuration.setText(bean.getKindlySeconds());
                    if (bean.isSending()){
                        audioHolderLeft.sendProgressbar.setVisibility(View.VISIBLE);
                    }else {
                        audioHolderLeft.sendProgressbar.setVisibility(View.INVISIBLE);
                    }
                    audioHolderLeft.lyAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            playAudioListener.onPlay(audioHolderLeft.ivRecord,position,true);
                        }
                    });
                    audioHolderLeft.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
                case AUDIO_RIGHT:
                    audioHolderRight = (AudioHolderRight) convertView.getTag();
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), audioHolderRight
                            .headImg, BitMapUtils.headOptions);
                    audioHolderRight.tvDuration.setText(bean.getKindlySeconds());
                    if (bean.isSending()){
                        audioHolderRight.sendProgressbar.setVisibility(View.VISIBLE);
                    }else {
                        audioHolderRight.sendProgressbar.setVisibility(View.INVISIBLE);
                    }
                    audioHolderRight.lyAudio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            playAudioListener.onPlay(audioHolderRight.ivRecord,position,true);
                        }
                    });
                    audioHolderRight.tvDate.setText(DateUtils.ToMDHM(bean.getCreateTime()));
                    break;
            }
        }
        return convertView;
    }

    private static class TextHolderLeft{
        TextView tvText;
        ImageView headImg;
        TextView tvDate;
    }
    private static class TextHolderRight{
        TextView tvText;
        ImageView headImg;
        TextView tvDate;
    }
    private static class ImgHolderLeft{
        ImageView chatImg;
        ImageView headImg;
        TextView tvDate;
    }

    private static class ImgHolderRight{
        ImageView chatImg;
        ImageView headImg;
        TextView tvDate;
    }

    private static class AudioHolderLeft{
        TextView tvDuration;
        View ivRecord;
        LinearLayout lyAudio;
        ImageView headImg;
        ProgressBar sendProgressbar;
        TextView tvDate;
    }
    private static class AudioHolderRight{
        TextView tvDuration;
        View ivRecord;
        LinearLayout lyAudio;
        ImageView headImg;
        ProgressBar sendProgressbar;
        TextView tvDate;
    }

}
