package com.xulc.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xulc.chat.R;

import com.xulc.chat.table.TableChat;
import com.xulc.chat.utils.BitMapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuliangchun on 2016/10/11.
 */
public class ChatAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<TableChat> chatList;
    private final int TEXT_LEFT=0;
    private final int TEXT_RIGHT=1;
    private final int IMG_LEFT=2;
    private final int IMG_RIGHT=3;

    public ChatAdapter(Context mContext) {
        this.mContext = mContext;
        this.chatList = new ArrayList<>();
        this.inflater = LayoutInflater.from(mContext);
    }

    public void refresh(List<TableChat> chatList){
        this.chatList = chatList;
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
                        break;
                    case 1:
                        //left
                        break;
                }
                break;
        }

        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextHolderLeft textHolderLeft;
        TextHolderRight textHolderRight;
        ImgHolderLeft imgHolderLeft;
        ImgHolderRight imgHolderRight;
        TableChat bean = getItem(position);
        int type = getItemViewType(position);
        if (convertView == null){
            switch (type){
                case TEXT_LEFT:
                    convertView = inflater.inflate(R.layout.item_text_left,parent,false);
                    textHolderLeft = new TextHolderLeft();
                    textHolderLeft.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    textHolderLeft.tvText = (TextView) convertView.findViewById(R.id.tvText);
                    convertView.setTag(textHolderLeft);
                    textHolderLeft.tvText.setText(bean.getText());
                    BitMapUtils.displayImg(textHolderLeft.headImg, bean.getHeadImg());
                    break;
                case TEXT_RIGHT:
                    convertView = inflater.inflate(R.layout.item_text_right,parent,false);
                    textHolderRight = new TextHolderRight();
                    textHolderRight.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    textHolderRight.tvText = (TextView) convertView.findViewById(R.id.tvText);
                    convertView.setTag(textHolderRight);
                    textHolderRight.tvText.setText(bean.getText());
                    BitMapUtils.displayImg(textHolderRight.headImg, bean.getHeadImg());
                    break;
                case IMG_LEFT:
                    convertView = inflater.inflate(R.layout.item_img_left,parent,false);
                    imgHolderLeft = new ImgHolderLeft();
                    imgHolderLeft.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    imgHolderLeft.chatImg = (ImageView) convertView.findViewById(R.id.chatImg);
                    convertView.setTag(imgHolderLeft);
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), imgHolderLeft
                            .headImg, BitMapUtils.headOptions);
                    ImageLoader.getInstance().displayImage(bean.getImgUrl(),
                            imgHolderLeft.chatImg, BitMapUtils.imageOptions);
                    break;
                case IMG_RIGHT:
                    convertView = inflater.inflate(R.layout.item_img_right,parent,false);
                    imgHolderRight = new ImgHolderRight();
                    imgHolderRight.headImg = (ImageView) convertView.findViewById(R.id.headImg);
                    imgHolderRight.chatImg = (ImageView) convertView.findViewById(R.id.chatImg);
                    convertView.setTag(imgHolderRight);
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), imgHolderRight
                            .headImg, BitMapUtils.headOptions);
                    ImageLoader.getInstance().displayImage(bean.getImgUrl(),
                            imgHolderRight.chatImg, BitMapUtils.imageOptions);
                    break;
            }
        }else {
            switch (type){
                case TEXT_LEFT:
                    textHolderLeft = (TextHolderLeft) convertView.getTag();
                    textHolderLeft.tvText.setText(bean.getText());
                    BitMapUtils.displayImg(textHolderLeft.headImg, bean.getHeadImg());
                    break;
                case TEXT_RIGHT:
                    textHolderRight = (TextHolderRight) convertView.getTag();
                    textHolderRight.tvText.setText(bean.getText());
                    BitMapUtils.displayImg(textHolderRight.headImg, bean.getHeadImg());
                    break;
                case IMG_LEFT:
                    imgHolderLeft = (ImgHolderLeft) convertView.getTag();
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), imgHolderLeft
                            .headImg, BitMapUtils.headOptions);
                    ImageLoader.getInstance().displayImage(bean.getImgUrl(),
                            imgHolderLeft.chatImg, BitMapUtils.imageOptions);
                    break;
                case IMG_RIGHT:
                    imgHolderRight = (ImgHolderRight) convertView.getTag();
                    ImageLoader.getInstance().displayImage(bean.getHeadImg(), imgHolderRight
                            .headImg, BitMapUtils.headOptions);
                    ImageLoader.getInstance().displayImage(bean.getImgUrl(),
                            imgHolderRight.chatImg, BitMapUtils.imageOptions);
                    break;
            }
        }
        return convertView;
    }

    private static class TextHolderLeft{
        TextView tvText;
        ImageView headImg;
    }
    private static class TextHolderRight{
        TextView tvText;
        ImageView headImg;
    }
    private static class ImgHolderLeft{
        ImageView chatImg;
        ImageView headImg;
    }

    private static class ImgHolderRight{
        ImageView chatImg;
        ImageView headImg;
    }

}
