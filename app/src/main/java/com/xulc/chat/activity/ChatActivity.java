package com.xulc.chat.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.tencent.bugly.crashreport.CrashReport;
import com.xulc.chat.R;
import com.xulc.chat.View.AudioRecordButton;
import com.xulc.chat.adapter.ChatAdapter;
import com.xulc.chat.app.BaseActivity;
import com.xulc.chat.app.CWApplication;
import com.xulc.chat.bean.EventMsg;
import com.xulc.chat.constans.EventCode;
import com.xulc.chat.okhttp.BaseResponse;
import com.xulc.chat.okhttp.HttpRequest;
import com.xulc.chat.okhttp.ResponseListener;
import com.xulc.chat.table.TableChat;
import com.xulc.chat.utils.DbUtils;

import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.util.KeyValue;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by xuliangchun on 2016/10/11.
 */
@ContentView(R.layout.activity_chat)
public class ChatActivity extends BaseActivity implements ResponseListener {
    @ViewInject(R.id.lvChat) ListView lvChat;
    @ViewInject(R.id.etText) EditText etText;
    @ViewInject(R.id.btnSend) Button btnSend;
    @ViewInject(R.id.btnTextRecord)Button btnTextRecord;
    @ViewInject(R.id.audioRecordBtn)AudioRecordButton audioRecordBtn;
    private ChatAdapter adapter;
    private List<TableChat> chatList;
    private String partyId;
    private String tel;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            chatList = DbUtils.getInstance().findByWhere(TableChat.class,"toPartyId","=",partyId);
            adapter.refresh(chatList);
            lvChat.setSelection(ListView.FOCUS_DOWN);
        }
    };

    @Override
    public void underCreate() {
        setOpenEventBus(true);
        Intent intent = getIntent();
        partyId = intent.getStringExtra("partyId");
        tel = intent.getStringExtra("tel");
        String name = intent.getStringExtra("name");
        setAppTitle(String.format("和%s聊天",name));
        adapter = new ChatAdapter(this);
        lvChat.setAdapter(adapter);
        handler.sendEmptyMessage(0);
        audioRecordBtn.setDoneRecordListener(new AudioRecordButton.DoneRecordListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                long maxId = DbUtils.getInstance().getCurMaxId() +1;
                DbUtils.getInstance().setCurMaxId(maxId);
                TableChat bean = new TableChat();
                bean.setContentType(4);
                bean.setId(maxId);
                bean.setToPartyId(partyId);
                bean.setToTel(tel);
                bean.setHeadImg(CWApplication.getInstance().getUser().getHeadPhotoUrl());
                bean.setLocalAudioUrl(filePath);
                bean.setDurationSeconds(seconds);
                bean.setFromMe(0);
                DbUtils.getInstance().save(bean);

                adapter.addMsg(bean);
                lvChat.setSelection(adapter.getCount() - 1);
            }
        });
    }
    @Subscribe
    public void onEventMainThread(EventMsg msg){
        switch (msg.getMsgCode()){
            case EventCode.IM_CHAT:
                handler.sendEmptyMessage(0);
                break;
        }
    }

    @Event(value = {R.id.btnSend,R.id.btnTextRecord})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.btnSend:
                if (TextUtils.isEmpty(etText.getText()))
                    return;
                String text = etText.getText().toString();
                etText.setText("");
                long maxId = DbUtils.getInstance().getCurMaxId() +1;
                DbUtils.getInstance().setCurMaxId(maxId);
                TableChat bean = new TableChat();
                bean.setContentType(1);
                bean.setId(maxId);
                bean.setToPartyId(partyId);
                bean.setToTel(tel);
                bean.setHeadImg(CWApplication.getInstance().getUser().getHeadPhotoUrl());
                bean.setText(text);
                bean.setFromMe(0);
                DbUtils.getInstance().save(bean);

                HttpRequest.sendText(tel, text, "APP", R.id.code_send_text, this, maxId, this);

                adapter.addMsg(bean);
                lvChat.setSelection(adapter.getCount()-1);
                break;
            case R.id.btnTextRecord:
                if (btnTextRecord.getText().toString().equals("语音")){
                    etText.setVisibility(View.INVISIBLE);
                    audioRecordBtn.setVisibility(View.VISIBLE);
                    btnTextRecord.setText("文本");
                }else {
                    etText.setVisibility(View.VISIBLE);
                    audioRecordBtn.setVisibility(View.INVISIBLE);
                    btnTextRecord.setText("语音");
                }
                break;
        }

    }

    @Override
    public void onSuccess(int code, String result) {

    }

    @Override
    public void onFailure(int code, String msg) {
        switch (code){
            case R.id.code_send_text:

                break;
        }
    }
}
