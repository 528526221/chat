package com.xulc.chat.service;

import android.app.Service;
import android.content.Intent;


import android.os.IBinder;
import android.support.annotation.Nullable;

import android.util.Log;

import com.xulc.chat.bean.EventMsg;
import com.xulc.chat.constans.EventCode;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

/**
 * Created by xuliangchun on 2016/10/10.
 */
public class IMConnectService extends Service{

    IMClient ws;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Subscribe
    public void onEventMainThread(EventMsg msg){
        switch (msg.getMsgCode()){
            case EventCode.SOCKET_CONNECT:

                // 发送心跳。
                // 发送任意内容，服务端收到后，会在内容后面加上“-ok”，再返回。
                // 比如发的“hb~”，那么服务端会返回一个“hb~-ok”
                new Thread(new Runnable() {
                    public void run() {
                        for (;;) {
                            if (ws==null||ws.isClosed()||ws.isClosing()){
                                break;
                            }
                            ws.send("hb~");
                            Log.i("xlc", "发送心跳消息:" + "hb~");
                            // 发完后，从回复队列里取，如果5秒取不到，或者回复内容不对，就认为连接不可用了，断开。
                            String reply = null;
                            try {
                                reply = ws.getHbQueue().poll(5, TimeUnit.SECONDS);
                                if (reply == null || !reply.equals("hb~-ok")) {
                                    ws.close();
                                    return;
                                }
                                // 不用太频繁，30秒发一次心跳。
                                Thread.sleep(30 * 1000);
                            } catch (InterruptedException e) {
                                return;
                            }

                        }
                    }
                }, "HearbeatSendThread").start();
                break;
        }
    }





    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        Log.i("xlc","启动服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IMClient.imClient = null;
        ws = IMClient.getInstance();
        SSLContext sslCtx = null;
        try {
            sslCtx = SSLContextBuilder.create(true, BestlinkerCertificate.CERT);
            ws.setSocket(sslCtx.getSocketFactory().createSocket());
            ws.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 开始连接后3秒检查状态
         */
       new Thread(new Runnable() {
           @Override
           public void run() {
               for (;;){
                   try {
                       Thread.sleep(3*1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   if (ws.isOpen()){
                       break;
                   }  else {
                      ws.connect();
                   }
               }

           }
       }).start();



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ws.close();
        Log.i("xlc", "停止服务");
    }
}
