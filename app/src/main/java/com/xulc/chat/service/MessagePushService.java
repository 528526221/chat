package com.xulc.chat.service;

/**
 * Created by xuliangchun on 2016/10/8.
 */
public class MessagePushService extends Service {

    private static final String TAG = MessagePushService.class.getSimpleName();

    private Socket mSocket;

    private boolean isConnected;

    /**
     * 初始化Socket,Https的连接方式
     */
    private void initSocketHttps() {
        SSLContext sc = null;
        TrustManager[] trustCerts = new TrustManager[] { new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted( X509Certificate[] chain, String authType )
                    throws CertificateException {
            }

            @Override
            public void checkClientTrusted( X509Certificate[] chain, String authType )
                    throws CertificateException {

            }
        } };
        try {
            sc = SSLContext.getInstance( "TLS" );
            sc.init( null, trustCerts, null );
            IO.Options opts = new IO.Options();
            opts.sslContext = sc;
            opts.hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify( String s, SSLSession sslSession ) {
                    return true;
                }
            };
            mSocket = IO.socket( "https://192.168.205.125:10443", opts );
        } catch ( NoSuchAlgorithmException e ) {
            e.printStackTrace();
        } catch ( KeyManagementException e ) {
            e.printStackTrace();
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化Socket,Http的连接方式
     */
    private void initSocketHttp() {
        try {
            mSocket = IO.socket( "http://192.168.205.125:10443" ); // 初始化Socket
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        }
    }

    private void connectSocket() {
        try {
            mSocket.connect();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put( "userName", "小王" ); // 这里一般是设置登录名
            mSocket.emit( "loginName", jsonObject ); // 发送登录人
        } catch ( JSONException e ) {
            e.printStackTrace();
        }

        mSocket.on( Socket.EVENT_CONNECT, onConnect );// 连接成功
        mSocket.on( Socket.EVENT_DISCONNECT, onDisconnect );// 断开连接
        mSocket.on( Socket.EVENT_CONNECT_ERROR, onConnectError );// 连接异常
        mSocket.on( Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeoutError );// 连接超时
        mSocket.on( "newMessage", onConnectMsg );// 监听消息事件回调
    }

    private void disConnectSocket() {
        mSocket.disconnect();

        mSocket.off( Socket.EVENT_CONNECT, onConnect );// 连接成功
        mSocket.off( Socket.EVENT_DISCONNECT, onDisconnect );// 断开连接
        mSocket.off( Socket.EVENT_CONNECT_ERROR, onConnectError );// 连接异常
        mSocket.off( Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeoutError );// 连接超时
        mSocket.off( "newMessage", onConnectMsg );// 监听消息事件回调
    }

    private Emitter.Listener onConnectMsg = new Emitter.Listener() {
        @Override
        public void call( final Object... args ) {
            // 在这里处理你的消息
            Log.e( TAG, "服务器返回来的消息 : " + args[0] );
        }
    };

    /**
     * 实现消息回调接口
     */
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call( final Object... args ) {
            Log.e( TAG, "连接成功 " + args[0] );
            if (!isConnected) { // 如果已经断开，重新发送
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put( "userName", "小王" ); // 这里一般是设置登录名
                    mSocket.emit( "loginName", jsonObject ); // 发送登录人
                } catch ( JSONException e ) {
                    e.printStackTrace();
                }
                isConnected = true;
            }
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call( Object... args ) {
            Log.e( TAG, "断开连接 " + args[0] );
            isConnected = false;
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call( final Object... args ) {
            Log.e( TAG, "连接 失败" + args[0] );
        }
    };

    private Emitter.Listener onConnectTimeoutError = new Emitter.Listener() {
        @Override
        public void call( final Object... args ) {
            Log.e( TAG, "连接 超时" + args[0] );

        }
    };

    @Nullable
    @Override
    public IBinder onBind( Intent intent ) {
        return null;
    }
}
