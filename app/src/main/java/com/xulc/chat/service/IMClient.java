package com.xulc.chat.service;

import android.util.Log;

import com.xulc.chat.app.CWApplication;
import com.xulc.chat.bean.EventMsg;
import com.xulc.chat.constans.EventCode;
import com.xulc.chat.okhttp.HttpRequest;

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
	private static IMClient imClient;

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
