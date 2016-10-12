package com.xulc.chat.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.xulc.chat.R;
import com.xulc.chat.adapter.ChatAdapter;
import com.xulc.chat.app.BaseActivity;
import com.xulc.chat.bean.EventMsg;
import com.xulc.chat.constans.EventCode;
import com.xulc.chat.table.TableChat;
import com.xulc.chat.utils.DbUtils;

import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by xuliangchun on 2016/10/11.
 */
@ContentView(R.layout.activity_chat)
public class ChatActivity extends BaseActivity{
    @ViewInject(R.id.lvChat) ListView lvChat;
    @ViewInject(R.id.etText) EditText etText;
    @ViewInject(R.id.btnSend) Button btnSend;
    private ChatAdapter adapter;
    private List<TableChat> chatList;
    private String partyId;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            chatList = DbUtils.getInstance().findByWhere(TableChat.class,"toPartyId","=",partyId);
            adapter.refresh(chatList);
            lvChat.setSelection(adapter.getCount()-1);
        }
    };

    @Override
    public void underCreate() {
        setOpenEventBus(true);
        Intent intent = getIntent();
        partyId = intent.getStringExtra("partyId");
        String name = intent.getStringExtra("name");
        setAppTitle(String.format("和%s聊天",name));
        adapter = new ChatAdapter(this);
        lvChat.setAdapter(adapter);
        handler.sendEmptyMessage(0);
    }
    @Subscribe
    public void onEventMainThread(EventMsg msg){
        switch (msg.getMsgCode()){
            case EventCode.IM_CHAT:
                handler.sendEmptyMessage(0);
                break;
        }
    }
}
