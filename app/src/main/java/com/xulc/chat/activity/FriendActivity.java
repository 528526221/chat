package com.xulc.chat.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xulc.chat.R;
import com.xulc.chat.adapter.FriendAdapter;
import com.xulc.chat.app.BaseActivity;

import com.xulc.chat.okhttp.HttpRequest;
import com.xulc.chat.okhttp.ResponseListener;
import com.xulc.chat.response.FriendResponse;
import com.xulc.chat.service.IMConnectService;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


/**
 * Created by xuliangchun on 2016/10/8.
 */
@ContentView(R.layout.activity_friend)
public class FriendActivity extends BaseActivity implements ResponseListener {
    @ViewInject(R.id.lvFriend) ListView lvFriend;
    @ViewInject(R.id.tvLogOut) TextView tvLogOut;
    FriendAdapter adapter;
    @Override
    public void underCreate() {
        setAppTitle("好友列表");
        adapter = new FriendAdapter(this);
        lvFriend.setAdapter(adapter);
        HttpRequest.getFriendList(1, this, this);
        startService(new Intent(this, IMConnectService.class));
    }

    @Event(value = R.id.tvLogOut)
    private void onClick(View view){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("确认")
                .setMessage("确定吗？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        stopService(new Intent(FriendActivity.this, IMConnectService.class));
                        HttpRequest.Logout(2, FriendActivity.this, FriendActivity.this);
                        startActivity(new Intent(FriendActivity.this, LoginActivity.class));
                        FriendActivity.this.finish();
                    }
                })
                .setNegativeButton("否", null)
                .show();
    }

    @Event(value = R.id.lvFriend,type = AdapterView.OnItemClickListener.class)
    private void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Intent intent = new Intent(this,ChatActivity.class);
        intent.putExtra("partyId",adapter.getItem(position).getPartyId());
        intent.putExtra("name",adapter.getItem(position).getCallName());
        startActivity(intent);
    }

    @Override
    public void onSuccess(int code, String result) {
        switch (code){
            case 1:
                FriendResponse response = JSON.parseObject(result,FriendResponse.class);
                adapter.refresh(response.getContent());
                break;
        }

    }

    @Override
    public void onFailure(int code, String msg) {

    }
}
