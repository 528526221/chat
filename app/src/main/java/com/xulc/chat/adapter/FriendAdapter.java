package com.xulc.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xulc.chat.R;
import com.xulc.chat.bean.Friend;
import com.xulc.chat.utils.BitMapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuliangchun on 2016/10/8.
 */
public class FriendAdapter extends BaseAdapter {
    private Context mContext;
    private List<Friend> friends;

    public FriendAdapter(Context mContext) {
        this.mContext = mContext;
        this.friends = new ArrayList<>();
    }

    public void refresh(List<Friend> friends){
        this.friends = friends;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Friend getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_friend,parent,false);
            holder = new Holder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        Friend friend = getItem(position);
        holder.text.setText(friend.getUserLoginId());
        BitMapUtils.displayImg(holder.image,friend.getProfileImgUrl());
        return convertView;
    }

    private static class Holder{
        ImageView image;
        TextView text;
    }
}
