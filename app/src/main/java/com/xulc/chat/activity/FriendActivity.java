package com.xulc.chat.activity;

import android.content.Intent;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.xulc.chat.R;
import com.xulc.chat.adapter.FriendAdapter;
import com.xulc.chat.app.BaseActivity;

import com.xulc.chat.okhttp.HttpRequest;
import com.xulc.chat.okhttp.ResponseListener;
import com.xulc.chat.response.FriendResponse;
import com.xulc.chat.service.IMConnectService;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * Created by xuliangchun on 2016/10/8.
 */
@ContentView(R.layout.activity_friend)
public class FriendActivity extends BaseActivity implements ResponseListener {
    @ViewInject(R.id.lvFriend) ListView lvFriend;
    FriendAdapter adapter;
    @Override
    public void underCreate() {
        setAppTitle("好友列表");
        adapter = new FriendAdapter(this);
        lvFriend.setAdapter(adapter);
        HttpRequest.getFriendList(1, this, this);
        startService(new Intent(this, IMConnectService.class));
    }

    @Override
    public void onSuccess(int code, String result) {
        FriendResponse response = JSON.parseObject(result,FriendResponse.class);
        adapter.refresh(response.getContent());
    }

    @Override
    public void onFailure(int code, String msg) {

    }
}
