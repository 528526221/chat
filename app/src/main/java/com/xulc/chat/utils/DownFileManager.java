package com.xulc.chat.utils;

import android.os.Environment;
import android.util.Log;

import com.xulc.chat.bean.EventMsg;
import com.xulc.chat.constans.EventCode;
import com.xulc.chat.table.TableChat;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.util.KeyValue;
import java.io.File;
import java.util.UUID;

import okhttp3.Call;

/**
 * Created by xuliangchun on 2017/1/3.
 */
public class DownFileManager {
    public static void downVoice(String url, final long id){
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory() + "/QINBAN/AUDIO", UUID.randomUUID().toString() + ".amr") {
            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                Log.e("xlc","下载录音文件出错");
            }

            @Override
            public void onResponse(File file, int i) {
                KeyValue keyValue = new KeyValue("localAudioUrl",file.getAbsolutePath());
                DbUtils.getInstance().updateById(TableChat.class,id,keyValue);

                /**
                 * 下载完成还要再刷新一次
                 */
                EventMsg msg = new EventMsg();
                msg.setMsgCode(EventCode.IM_CHAT);
                EventBus.getDefault().post(msg);
            }
        });
    }
}
