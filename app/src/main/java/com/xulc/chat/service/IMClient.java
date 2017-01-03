package com.xulc.chat.service;

import android.app.DownloadManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.xulc.chat.app.CWApplication;
import com.xulc.chat.bean.EventMsg;
import com.xulc.chat.constans.EventCode;
import com.xulc.chat.okhttp.HttpRequest;
import com.xulc.chat.response.BasePushResponse;
import com.xulc.chat.response.ImagePushResponse;
import com.xulc.chat.response.TextPushResponse;
import com.xulc.chat.response.VoicePushResponse;
import com.xulc.chat.table.TableChat;
import com.xulc.chat.utils.DbUtils;
import com.xulc.chat.utils.DownFileManager;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;


import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class IMClient extends WebSocketClient {
	/** 用来接收心跳，并判断心跳隔了多久才回复的。 */
	private final BlockingQueue<String> hbQueue;
	public static IMClient imClient;

	public static IMClient getInstance(){
		if (imClient==null){
			URI uri = null;
			try {
				uri = new URI(HttpRequest.AppImpushTest+"/ws?sessionID="+ CWApplication.getInstance().getUser().getSessionID());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			imClient = new IMClient(uri);
		}
		return imClient;
	}


	public IMClient( URI serverURI ) {
		super(serverURI);
		this.hbQueue = new LinkedBlockingQueue<String>(1);
	}

	@Override
	public void onOpen( ServerHandshake handshakedata ) {
		Log.i("xlc", "onOpen");
		EventMsg msg = new EventMsg();
		msg.setMsgCode(EventCode.SOCKET_CONNECT);
		EventBus.getDefault().post(msg);
	}

	@Override
	public void onMessage( String message ) {
		if (message.startsWith("hb~")) {
			Log.i("xlc", "心跳回复消息:" + message);
			// 收到心跳回复了，放在队列里，然后心跳发送线程来从队列取。
			this.hbQueue.offer(message);
			return;
		}
		Log.i("xlc", "received:" + message);
		BasePushResponse basePushResponse = JSON.parseObject(message,BasePushResponse.class);
		if (basePushResponse==null)
			return;
		switch (basePushResponse.getContentType()){
			case 1:
				//文本消息
				TableChat chat = new TableChat();
				TextPushResponse response = JSON.parseObject(message,TextPushResponse.class);
				long maxId = DbUtils.getInstance().getCurMaxId() +1;
				DbUtils.getInstance().setCurMaxId(maxId);
				chat.setId(maxId);
				chat.setContentType(1);
				chat.setFromMe(1);
				chat.setToPartyId(response.getSenderId().getPartyId());
				chat.setToTel(response.getSenderId().getUserId());
				chat.setText(response.getContent());
				chat.setHeadImg(response.getSenderId().getImgUrl());
				DbUtils.getInstance().save(chat);
				break;
			case 2:
				//图片消息
				TableChat chat1 = new TableChat();
				ImagePushResponse response1 = JSON.parseObject(message,ImagePushResponse.class);
				long maxId1 = DbUtils.getInstance().getCurMaxId() +1;
				DbUtils.getInstance().setCurMaxId(maxId1);
				chat1.setId(maxId1);
				chat1.setContentType(2);
				chat1.setFromMe(1);
				chat1.setToPartyId(response1.getSenderId().getPartyId());
				chat1.setToTel(response1.getSenderId().getUserId());
				chat1.setImgUrl(response1.getImagePush().getFileUrl());
				chat1.setHeadImg(response1.getSenderId().getImgUrl());
				DbUtils.getInstance().save(chat1);
				break;
			case 4:
				//语音消息
				TableChat chat2 = new TableChat();
				VoicePushResponse response2 = JSON.parseObject(message,VoicePushResponse.class);
				long maxId2 = DbUtils.getInstance().getCurMaxId() +1;
				DbUtils.getInstance().setCurMaxId(maxId2);
				chat2.setId(maxId2);
				chat2.setContentType(4);
				chat2.setFromMe(1);
				chat2.setToPartyId(response2.getSenderId().getPartyId());
				chat2.setToTel(response2.getSenderId().getUserId());
				chat2.setAudioUrl(response2.getVoicePush().getFileUrl());
				chat2.setDurationSeconds(response2.getVoicePush().getDurationSeconds());
				chat2.setHeadImg(response2.getSenderId().getImgUrl());
				DbUtils.getInstance().save(chat2);
				DownFileManager.downVoice(chat2.getAudioUrl(),maxId2);
				break;
		}

		EventMsg msg = new EventMsg();
		msg.setMsgCode(EventCode.IM_CHAT);
		EventBus.getDefault().post(msg);
	}



	@Override
	public void onClose( int code, String reason, boolean remote ) {
		Log.i("xlc", "onClose:" + reason);
	}

	@Override
	public void onError( Exception ex ) {
		Log.i("xlc", "onError:"+ex.getMessage());

	}

	public BlockingQueue<String> getHbQueue() {
		return hbQueue;
	}

}
